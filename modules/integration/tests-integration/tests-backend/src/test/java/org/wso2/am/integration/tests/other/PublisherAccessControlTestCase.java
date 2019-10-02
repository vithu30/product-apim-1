/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.am.integration.tests.other;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.am.integration.clients.publisher.api.v1.dto.APIDTO;
import org.wso2.am.integration.test.impl.RestAPIPublisherImpl;
import org.wso2.am.integration.test.utils.APIManagerIntegrationTestException;
import org.wso2.am.integration.test.utils.base.APIMIntegrationBaseTest;
import org.wso2.am.integration.test.utils.bean.APIRequest;
import org.wso2.am.integration.test.utils.clients.APIPublisherRestClient;
import org.wso2.am.integration.test.utils.clients.APIStoreRestClient;
import org.wso2.am.integration.tests.api.lifecycle.APIVisibilityByPublicTestCase;
import org.wso2.carbon.apimgt.api.model.APIIdentifier;
import org.wso2.carbon.automation.engine.context.TestUserMode;
import org.wso2.carbon.automation.test.utils.http.client.HttpResponse;
import org.wso2.carbon.integration.common.admin.client.UserManagementClient;
import org.wso2.carbon.user.mgt.stub.UserAdminUserAdminException;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.xpath.XPathExpressionException;

/**
 * This test case verifies the functionality of publisher access control restriction.
 */
public class PublisherAccessControlTestCase extends APIMIntegrationBaseTest {

    private static final String VERSION = "1.0.0";
    private static final String RESTRICTED_ACCESS_CONTROL = "restricted";
    private static final String PUBLIC_VISIBILITY = "public";
    private static final String NO_ACCESS_CONTROL = "all";
    private APIPublisherRestClient apiPublisher;
    private APIStoreRestClient apiStoreRestClient;
    private String contextUsername = "admin";
    private String contextUserPassword = "admin";
    private final String FIRST_USER = "publisher_user";
    private final String SECOND_USER = "publisher_user2";
    private final String PUB_SUB_USER = "pub_sub_user1";
    private final String FIRST_ROLE = "publisher_role1";
    private final String SECOND_ROLE = "publisher_role2";
    private final String SUBSCRIBER_ROLE = "subscriber_role1";
    private final String PUB_SUB_ROLE = "pub_sub_role1";
    private final String USER_PASSWORD = "123123";
    private final String SUBSCRIBER_USER = "subscriber_user1";
    private final String EP_URL = "http://gdata.youtube.com/feeds/api/standardfeeds";
    private String publisherAccessControlAPI = "PublisherAccessControl";
    private String publisherAccessControlAPI2 = "PublisherAccessControl2";
    private String publicAccessRestrictedVisibilityAPI = "PublicAccessRestrictedVisibility";
    private String accessControlledPublicVisibilityAPI = "AccessControlledPublicVisibility";
    private final String restrictedAccessRestrictedVisibilityAPI = "RestrictedAccessRestrictedVisibility";
    private RestAPIPublisherImpl restAPIPublisher;
    private RestAPIPublisherImpl restAPIPublisherFirstUser;
    private String apiID;
    UserManagementClient userManagementClient1;

    @BeforeClass
    public void initTestCase() throws APIManagerIntegrationTestException, XPathExpressionException, RemoteException,
            UserAdminUserAdminException {
        super.init();

        apiStoreRestClient = new APIStoreRestClient(storeURLHttp);
        contextUsername = keyManagerContext.getContextTenant().getContextUser().getUserName();
        contextUserPassword = keyManagerContext.getContextTenant().getContextUser().getPassword();
        userManagementClient1 = new UserManagementClient(
                keyManagerContext.getContextUrls().getBackEndUrl(), contextUsername, contextUserPassword);
        String apiCreatePermission = "/permission/admin/manage/api/create";
        String loginPermission = "/permission/admin/login";
        userManagementClient1
                .addRole(FIRST_ROLE, new String[] {}, new String[] { loginPermission, apiCreatePermission });
        String apiPublishPermission = "/permission/admin/manage/api/publish";
        String apiSubscribePermission = "/permission/admin/manage/api/subscribe";

        userManagementClient1
                .addRole(SECOND_ROLE, new String[] {}, new String[] { loginPermission, apiPublishPermission });
        userManagementClient1
                .addRole(SUBSCRIBER_ROLE, new String[] {}, new String[] { loginPermission, apiSubscribePermission });
        userManagementClient1.addRole(PUB_SUB_ROLE, new String[] {},
                new String[] { loginPermission, apiPublishPermission, apiSubscribePermission });
        userManagementClient1.addUser(FIRST_USER, USER_PASSWORD, new String[] { FIRST_ROLE }, FIRST_USER);
        userManagementClient1.addUser(SECOND_USER, USER_PASSWORD, new String[] { SECOND_ROLE }, SECOND_USER);
        userManagementClient1
                .addUser(SUBSCRIBER_USER, USER_PASSWORD, new String[] { SUBSCRIBER_ROLE }, SUBSCRIBER_USER);
        userManagementClient1
                .addUser(PUB_SUB_USER, USER_PASSWORD, new String[] { PUB_SUB_ROLE }, PUB_SUB_USER);

        restAPIPublisher = new RestAPIPublisherImpl(contextUsername, contextUserPassword,
                MultitenantConstants.SUPER_TENANT_DOMAIN_NAME, keyManagerHTTPSURL, gatewayHTTPSURL, publisherURLHttp);

    }

    @Test(groups = "wso2.am", description = "This test case tests the retrieval of API which was added with a access "
            + "control restriction.")
    public void testAPIAdditionWithAccessControlRestriction() throws Exception {
        APIRequest brokenApiRequest = new APIRequest(publisherAccessControlAPI, publisherAccessControlAPI,
                new URL(EP_URL));
        brokenApiRequest.setVersion(VERSION);
        brokenApiRequest.setProvider(contextUsername);
        brokenApiRequest.setAccessControl("RESTRICTED");
        brokenApiRequest.setAccessControlRoles(FIRST_ROLE);
        APIDTO apidto = restAPIPublisher.addAPI(brokenApiRequest, "v3");
        apiID = apidto.getId();
        System.out.println("################3 " + apiID);

        APIDTO response = restAPIPublisher.getAPIByID(apiID, MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
        Assert.assertTrue(response.getAccessControlRoles().contains(FIRST_ROLE),
                "API is not visible to the APIM admin user");

        restAPIPublisherFirstUser = new RestAPIPublisherImpl(FIRST_USER, USER_PASSWORD,
                MultitenantConstants.SUPER_TENANT_DOMAIN_NAME, keyManagerHTTPSURL, gatewayHTTPSURL, publisherURLHttp);
        response = restAPIPublisherFirstUser.getAPIByID(apiID, MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
        Assert.assertTrue(response.getAccessControlRoles().contains(FIRST_ROLE),
                "API is not visible to the creators who have the relevant access control roles of the API");

//        apiPublisher.login(SECOND_USER, USER_PASSWORD);
//        httpResponse = apiPublisher.getAPI(publisherAccessControlAPI, contextUsername);
//        Assert.assertFalse(response.getData().contains(FIRST_ROLE),
//                "API was visible to the creators who do not have the relevant access control roles of the API");

    }

//    @Test(groups = "wso2.am", description = "This test case tests the retrieval of API which was added without "
//            + "access control restriction.")
//    public void testAPIAdditionWithoutAccessControlRestriction()
//            throws APIManagerIntegrationTestException, MalformedURLException {
//        apiPublisher.login(contextUsername, contextUserPassword);
//        APIRequest brokenApiRequest = new APIRequest(publisherAccessControlAPI2, publisherAccessControlAPI2,
//                new URL(EP_URL));
//        brokenApiRequest.setVersion(VERSION);
//        brokenApiRequest.setProvider(contextUsername);
//        apiPublisher.addAPI(brokenApiRequest);
//        HttpResponse response = apiPublisher.getAPI(publisherAccessControlAPI2, contextUsername);
//        Assert.assertTrue(response.getData().contains("\"provider\" : \"admin\""),
//                "API is not visible to APIM admin" + " without access control restriction");
//        apiPublisher.logout();
//
//        apiPublisher.login(FIRST_USER, USER_PASSWORD);
//        response = apiPublisher.getAPI(publisherAccessControlAPI2, contextUsername);
//        Assert.assertTrue(response.getData().contains("\"provider\" : \"admin\""),
//                "API is not visible to creator" + " without access control restriction");
//        apiPublisher.logout();
//
//        apiPublisher.login(SECOND_USER, USER_PASSWORD);
//        response = apiPublisher.getAPI(publisherAccessControlAPI2, contextUsername);
//        Assert.assertTrue(response.getData().contains("\"provider\" : \"admin\""),
//                "API is not visible to creator" + " without access control restriction");
//    }

//    @Test (groups = "wso2.am", description = "This test case tests the retrieval of API which from store which was "
//            + "added without access control restriction and public visibility.")
//    public void testGetPublicAPIFromStoreWithRestrictedPublisherAccess()
//            throws APIManagerIntegrationTestException, MalformedURLException, XPathExpressionException {
//        apiPublisher.login(contextUsername, contextUserPassword);
//        APIRequest createAPIRequest = new APIRequest(accessControlledPublicVisibilityAPI,
//                accessControlledPublicVisibilityAPI, new URL(EP_URL));
//        createAPIRequest.setVersion(VERSION);
//        createAPIRequest.setProvider(contextUsername);
//        createAPIRequest.setAccessControl(RESTRICTED_ACCESS_CONTROL);
//        createAPIRequest.setAccessControlRoles(FIRST_ROLE);
//        createAPIRequest.setVisibility(PUBLIC_VISIBILITY);
//        apiPublisher.addAPI(createAPIRequest);
//
//        APIIdentifier apiIdentifier = new APIIdentifier(contextUsername, accessControlledPublicVisibilityAPI, VERSION);
//        apiPublisher.changeAPILifeCycleStatusToPublish(apiIdentifier, false);
//
//        apiStoreRestClient.login(SUBSCRIBER_USER, USER_PASSWORD);
//        HttpResponse httpResponse = apiStoreRestClient.getAllPublishedAPIs(storeContext.getContextTenant().getDomain());
//        Assert.assertEquals(httpResponse.getResponseCode(), 200, "Response code does not match");
//        Assert.assertTrue(httpResponse.getData().contains(accessControlledPublicVisibilityAPI),
//                "Public API with name " + accessControlledPublicVisibilityAPI + "is not " + "returned");
//    }

//    @Test (groups = "wso2.am", description = "This test case add restricted visibility on store for role1, but user "
//            + "who can log into publisher should be able to view the api even though he does not have the role role1")
//    public void testCheckPublisherRoleCanViewRestrictedVisibilityAPIs()
//            throws APIManagerIntegrationTestException, MalformedURLException, XPathExpressionException {
//        apiPublisher.login(contextUsername, contextUserPassword);
//        APIRequest createAPIRequest = new APIRequest(publicAccessRestrictedVisibilityAPI,
//                publicAccessRestrictedVisibilityAPI, new URL(EP_URL));
//        createAPIRequest.setVersion(VERSION);
//        createAPIRequest.setProvider(contextUsername);
//        createAPIRequest.setAccessControl(NO_ACCESS_CONTROL);
//        createAPIRequest.setVisibility(RESTRICTED_ACCESS_CONTROL);
//        createAPIRequest.setRoles(FIRST_ROLE);
//        apiPublisher.addAPI(createAPIRequest);
//
//        APIIdentifier apiIdentifier = new APIIdentifier(contextUsername, publicAccessRestrictedVisibilityAPI, VERSION);
//        apiPublisher.changeAPILifeCycleStatusToPublish(apiIdentifier, false);
//
//        apiStoreRestClient.login(PUB_SUB_USER, USER_PASSWORD);
//        HttpResponse httpResponse = apiStoreRestClient.getAllPublishedAPIs(storeContext.getContextTenant().getDomain());
//        Assert.assertEquals(httpResponse.getResponseCode(), 200, "Response code does not match");
//        Assert.assertTrue(httpResponse.getData().contains(publicAccessRestrictedVisibilityAPI),
//                "Restricted visible api " + publicAccessRestrictedVisibilityAPI + "is not" + " visible to user  "
//                        + PUB_SUB_USER + ", who can view it in publisher");
//    }

//    @Test (groups = "wso2.am", description = "This test case add restricted access in publisher(role1) and restricted "
//            + "visibility in store(subscriber_role1). So check correct behaviour in publisher and store ")
//    public void testPublisherAndStoreRestricted() throws Exception {
//        apiPublisher.login(contextUsername, contextUserPassword);
//
//        APIRequest createAPIRequest = new APIRequest(restrictedAccessRestrictedVisibilityAPI,
//                restrictedAccessRestrictedVisibilityAPI, new URL(EP_URL));
//        createAPIRequest.setVersion(VERSION);
//        createAPIRequest.setProvider(contextUsername);
//        createAPIRequest.setAccessControl(RESTRICTED_ACCESS_CONTROL);
//        createAPIRequest.setAccessControlRoles(FIRST_ROLE);
//        createAPIRequest.setVisibility(PUBLIC_VISIBILITY);
//        apiPublisher.addAPI(createAPIRequest);
//
//        APIIdentifier apiIdentifier = new APIIdentifier(contextUsername, restrictedAccessRestrictedVisibilityAPI,
//                VERSION);
//        apiPublisher.changeAPILifeCycleStatusToPublish(apiIdentifier, false);
//        // Waiting to index
//        Thread.sleep(10000);
//
//        apiPublisher.login(FIRST_USER, USER_PASSWORD);
//        HttpResponse publisherAllAPIS = apiPublisher.getAllAPIs();
//        Assert.assertEquals(publisherAllAPIS.getResponseCode(), 200, "Response code does not match");
//        Assert.assertTrue(publisherAllAPIS.getData().contains(restrictedAccessRestrictedVisibilityAPI),
//                "Restricted visible api " + restrictedAccessRestrictedVisibilityAPI + "is not" + " visible to user  "
//                        + FIRST_USER + ", who should be able to view it");
//
//        apiPublisher.login(PUB_SUB_USER, USER_PASSWORD);
//        publisherAllAPIS = apiPublisher.getAllAPIs();
//        Assert.assertEquals(publisherAllAPIS.getResponseCode(), 200, "Response code does not match");
//        Assert.assertFalse(publisherAllAPIS.getData().contains(restrictedAccessRestrictedVisibilityAPI),
//                "Restricted access api " + restrictedAccessRestrictedVisibilityAPI + "is visible to user  " + FIRST_USER
//                        + ", who should not be able to view it");
//
//        apiPublisher.login(contextUsername, contextUserPassword);
//        createAPIRequest.setVisibility(RESTRICTED_ACCESS_CONTROL);
//        createAPIRequest.setRoles(SUBSCRIBER_ROLE);
//        apiPublisher.updateAPI(createAPIRequest);
//        // Waiting to index after api update operation
//        Thread.sleep(10000);
//
//        apiStoreRestClient.login(SUBSCRIBER_USER, USER_PASSWORD);
//        HttpResponse storeAllAPIs = apiStoreRestClient
//                .getAllPaginatedPublishedAPIs(storeContext.getContextTenant().getDomain(), 0, 10);
//        Assert.assertEquals(storeAllAPIs.getResponseCode(), 200, "Response code does not match");
//        Assert.assertTrue(storeAllAPIs.getData().contains(restrictedAccessRestrictedVisibilityAPI),
//                "Restricted visible api " + restrictedAccessRestrictedVisibilityAPI + " is" + " not visible to user  "
//                        + SUBSCRIBER_USER + ", who can  view it in store");
//    }

    @AfterClass (alwaysRun = true)
    public void destroy() throws Exception {
//        apiPublisher.deleteAPI(publisherAccessControlAPI, VERSION, contextUsername);
//        apiPublisher.deleteAPI(publicAccessRestrictedVisibilityAPI, VERSION, contextUsername);
//        apiPublisher.deleteAPI(publisherAccessControlAPI2, VERSION, contextUsername);
//        apiPublisher.deleteAPI(restrictedAccessRestrictedVisibilityAPI, VERSION, contextUsername);
//        apiPublisher.deleteAPI(accessControlledPublicVisibilityAPI, VERSION, contextUsername);
        restAPIPublisher.deleteAPIByID(apiID);

//        userManagementClient1.deleteUser(FIRST_USER);
//        userManagementClient1.deleteUser(SECOND_USER);
//        userManagementClient1.deleteUser(SUBSCRIBER_USER);
//        userManagementClient1.deleteUser(PUB_SUB_USER);
//
//        userManagementClient1.deleteRole(FIRST_ROLE);
//        userManagementClient1.deleteRole(SECOND_ROLE);
//        userManagementClient1.deleteRole(SUBSCRIBER_ROLE);
//        userManagementClient1.deleteRole(PUB_SUB_ROLE);

    }

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.keyStore",
                "/home/vithursa/Documents/APIManagementProject/new-product-apim/product-apim/modules/distribution/product/target/wso2am-3.0.0-SNAPSHOT/repository/resources/security/wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStore",
                "/home/vithursa/Documents/APIManagementProject/new-product-apim/product-apim/modules/distribution/product/target/wso2am-3.0.0-SNAPSHOT/repository/resources/security/client-truststore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "wso2carbon");

        System.setProperty("framework.resource.location", "/home/vithursa/Documents/APIManagementProject/new-product-apim/product-apim/modules/integration/tests-integration/tests-backend/src/test/resources/");
        System.setProperty("user.dir",
                "/home/vithursa/Documents/APIManagementProject/new-product-apim/product-apim/modules/integration/tests-integration/tests-backend/src");
        PublisherAccessControlTestCase aCase = new PublisherAccessControlTestCase();
        aCase.initTestCase();

        try {
            aCase.testAPIAdditionWithAccessControlRestriction();
        } finally {
            System.out.println("ended");
            aCase.destroy();
        }
    }
}
