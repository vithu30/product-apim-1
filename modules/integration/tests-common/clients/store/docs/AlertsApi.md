# AlertsApi

All URIs are relative to *https://apis.wso2.com/api/am/store/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getStoreAlertTypes**](AlertsApi.md#getStoreAlertTypes) | **GET** /alert-types | Get the list of API Store alert types. 


<a name="getStoreAlertTypes"></a>
# **getStoreAlertTypes**
> AlertTypesListDTO getStoreAlertTypes()

Get the list of API Store alert types. 

This operation is used to get the list of supportd alert types for the &#39;subscriber&#39; agent. 

### Example
```java
// Import classes:
import org.wso2.am.integration.clients.store.api.ApiClient;
import org.wso2.am.integration.clients.store.api.ApiException;
import org.wso2.am.integration.clients.store.api.Configuration;
import org.wso2.am.integration.clients.store.api.auth.*;
import org.wso2.am.integration.clients.store.api.models.*;
import org.wso2.am.integration.clients.store.api.v1.AlertsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://apis.wso2.com/api/am/store/v1");
    
    // Configure OAuth2 access token for authorization: OAuth2Security
    OAuth OAuth2Security = (OAuth) defaultClient.getAuthentication("OAuth2Security");
    OAuth2Security.setAccessToken("YOUR ACCESS TOKEN");

    AlertsApi apiInstance = new AlertsApi(defaultClient);
    try {
      AlertTypesListDTO result = apiInstance.getStoreAlertTypes();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AlertsApi#getStoreAlertTypes");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**AlertTypesListDTO**](AlertTypesListDTO.md)

### Authorization

[OAuth2Security](../README.md#OAuth2Security)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK. The list of subscriber alert types are returned.  |  * Content-Type - The content type of the body.  <br>  |
**500** | Internal Server Error. An internal server error occurred while retrieving the alert types.  |  -  |

