package org.wso2.am.integration.test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class ClientAuthenticator {

    public static final double JAVA_VERSION;
    private static TrustManager trustAll;
    private static String consumerKey = null;
    private static String consumerSecret = null;
    private static final String TLS_PROTOCOL = "TLS";

    static {
        JAVA_VERSION = Double.parseDouble(System.getProperty("java.specification.version"));

        trustAll = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
    }

    public static String getAccessToken(String scopeList, String appName, String callBackURL, String tokenScope, String appOwner,
                                        String grantType, String dcrEndpoint, String username, String password, String tenantDomain, String tokenEndpoint) {
       // if (consumerKey == null) {
            makeDCRRequest(appName,  callBackURL,  tokenScope,  appOwner, grantType,  dcrEndpoint,  username,  password,  tenantDomain);
       // }
        URL url;
        HttpsURLConnection urlConn = null;
        //calling token endpoint
        try {
            url = new URL(tokenEndpoint);
            urlConn = (HttpsURLConnection) url.openConnection();
            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String clientEncoded = DatatypeConverter.printBase64Binary(
                    (consumerKey + ':' + consumerSecret).getBytes(StandardCharsets.UTF_8));
            urlConn.setRequestProperty("Authorization", "Basic " + clientEncoded);
//            if (!"carbon.super".equalsIgnoreCase(tenantDomain)) {
//                username = username + "@" + tenantDomain;
//            }

            String postParams = "grant_type=password&username="+ username + "&password=" + password;

            if (!scopeList.isEmpty()) {
                System.out.println("scopeeee list " + scopeList);
                postParams += "&scope=" + scopeList;
            }
            urlConn.setHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            SSLContext sslContext = SSLContext.getInstance(TLS_PROTOCOL);
            sslContext.init(null, new TrustManager[]{trustAll}, new SecureRandom());
            urlConn.setSSLSocketFactory(sslContext.getSocketFactory());
            urlConn.getOutputStream().write((postParams).getBytes("UTF-8"));
            int responseCode = urlConn.getResponseCode();
            if (responseCode == 200) {
                String responseStr = getResponseString(urlConn.getInputStream());
                JsonParser parser = new JsonParser();
                JsonObject obj = parser.parse(responseStr).getAsJsonObject();
                return obj.get("access_token").getAsString();
            } else {
                throw new RuntimeException("Error occurred while getting token. Status code: " + responseCode);
            }
        } catch (Exception e) {
            String msg = "Error while creating the new token for token regeneration.";
            throw new RuntimeException(msg, e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
    }



    private static void makeDCRRequest(String appName, String callBackURL, String tokenScope, String appOwner,
                                       String grantType, String dcrEndpoint, String username, String password, String tenantDomain) {
        String applicationName = appName;
        URL url;
        HttpURLConnection urlConn = null;
        try {
            //Create json payload for DCR endpoint
            JsonObject json = new JsonObject();
            json.addProperty("callbackUrl", callBackURL);
            json.addProperty("clientName", applicationName);
            json.addProperty("tokenScope", tokenScope);
            json.addProperty("grantType", grantType);
            json.addProperty("saasApp", true);

            String clientEncoded;

            if (StringUtils.isEmpty(tenantDomain)) {
                json.addProperty("owner", appOwner);
                clientEncoded = DatatypeConverter.printBase64Binary((System.getProperty("systemUsername",
                        username) + ':' + System.getProperty("systemUserPwd", password))
                        .getBytes(StandardCharsets.UTF_8));
            } else {
                json.addProperty("owner", username + '@' + tenantDomain);
                clientEncoded = DatatypeConverter.printBase64Binary((username + '@' + tenantDomain + ':' + password)
                        .getBytes(StandardCharsets.UTF_8));
            }

            // Calling DCR endpoint
            url = new URL(dcrEndpoint);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestProperty("Authorization", "Basic " + clientEncoded); //temp fix
            urlConn.getOutputStream().write((json.toString()).getBytes("UTF-8"));

            int responseCode = urlConn.getResponseCode();
            if (responseCode == 200) {  //If the DCR call is success
                String responseStr = getResponseString(urlConn.getInputStream());
                JsonParser parser = new JsonParser();
                JsonObject jObj = parser.parse(responseStr).getAsJsonObject();
                consumerKey = jObj.getAsJsonPrimitive("clientId").getAsString();
                consumerSecret = jObj.getAsJsonPrimitive("clientSecret").getAsString();
            } else { //If DCR call fails
                throw new RuntimeException("DCR call failed. Status code: " + responseCode);
            }
        } catch (IOException e) {
            String errorMsg = "Can not create OAuth application  : " + applicationName;
            throw new RuntimeException(errorMsg, e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
    }


    private static String getResponseString(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String file = "";
            String str;
            while ((str = buffer.readLine()) != null) {
                file += str;
            }
            return file;
        }
    }
}
