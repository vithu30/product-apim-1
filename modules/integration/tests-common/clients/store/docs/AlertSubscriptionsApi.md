# AlertSubscriptionsApi

All URIs are relative to *https://apis.wso2.com/api/am/store/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getSubscribedAlertTypes**](AlertSubscriptionsApi.md#getSubscribedAlertTypes) | **GET** /alert-subscriptions | Get the list of API Store alert types subscribed by the user. 
[**subscribeToAlerts**](AlertSubscriptionsApi.md#subscribeToAlerts) | **PUT** /alert-subscriptions | Subscribe to the selected alert types by the user. 
[**unsubscribeAllAlerts**](AlertSubscriptionsApi.md#unsubscribeAllAlerts) | **DELETE** /alert-subscriptions | Unsubscribe user from all the alert types. 


<a name="getSubscribedAlertTypes"></a>
# **getSubscribedAlertTypes**
> AlertsInfoDTO getSubscribedAlertTypes()

Get the list of API Store alert types subscribed by the user. 

This operation is used to get the list of subscribed alert types by the user. 

### Example
```java
// Import classes:
import org.wso2.am.integration.clients.store.api.ApiClient;
import org.wso2.am.integration.clients.store.api.ApiException;
import org.wso2.am.integration.clients.store.api.Configuration;
import org.wso2.am.integration.clients.store.api.auth.*;
import org.wso2.am.integration.clients.store.api.models.*;
import org.wso2.am.integration.clients.store.api.v1.AlertSubscriptionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://apis.wso2.com/api/am/store/v1");
    
    // Configure OAuth2 access token for authorization: OAuth2Security
    OAuth OAuth2Security = (OAuth) defaultClient.getAuthentication("OAuth2Security");
    OAuth2Security.setAccessToken("YOUR ACCESS TOKEN");

    AlertSubscriptionsApi apiInstance = new AlertSubscriptionsApi(defaultClient);
    try {
      AlertsInfoDTO result = apiInstance.getSubscribedAlertTypes();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AlertSubscriptionsApi#getSubscribedAlertTypes");
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

[**AlertsInfoDTO**](AlertsInfoDTO.md)

### Authorization

[OAuth2Security](../README.md#OAuth2Security)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK. The list of subscribed alert types are returned.  |  * Content-Type - The content type of the body.  <br>  |
**500** | Internal Server Error An error occurred while retrieving subscribed alert types by user.  |  -  |

<a name="subscribeToAlerts"></a>
# **subscribeToAlerts**
> AlertsInfoResponseDTO subscribeToAlerts(body)

Subscribe to the selected alert types by the user. 

This operation is used to get the list of subscribed alert types by the user. 

### Example
```java
// Import classes:
import org.wso2.am.integration.clients.store.api.ApiClient;
import org.wso2.am.integration.clients.store.api.ApiException;
import org.wso2.am.integration.clients.store.api.Configuration;
import org.wso2.am.integration.clients.store.api.auth.*;
import org.wso2.am.integration.clients.store.api.models.*;
import org.wso2.am.integration.clients.store.api.v1.AlertSubscriptionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://apis.wso2.com/api/am/store/v1");
    
    // Configure OAuth2 access token for authorization: OAuth2Security
    OAuth OAuth2Security = (OAuth) defaultClient.getAuthentication("OAuth2Security");
    OAuth2Security.setAccessToken("YOUR ACCESS TOKEN");

    AlertSubscriptionsApi apiInstance = new AlertSubscriptionsApi(defaultClient);
    AlertsInfoDTO body = new AlertsInfoDTO(); // AlertsInfoDTO | The alerts list and the email list to subscribe.
    try {
      AlertsInfoResponseDTO result = apiInstance.subscribeToAlerts(body);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AlertSubscriptionsApi#subscribeToAlerts");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**AlertsInfoDTO**](AlertsInfoDTO.md)| The alerts list and the email list to subscribe. |

### Return type

[**AlertsInfoResponseDTO**](AlertsInfoResponseDTO.md)

### Authorization

[OAuth2Security](../README.md#OAuth2Security)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | OK. Successful response with the newly subscribed alerts.  |  * Content-Type - The content type of the body.  <br>  |
**400** | Bad Request. Invalid Request or request validation failure.  |  -  |
**500** | Internal Server Error An internal server error occurred while subscribing to alerts.  |  -  |

<a name="unsubscribeAllAlerts"></a>
# **unsubscribeAllAlerts**
> unsubscribeAllAlerts()

Unsubscribe user from all the alert types. 

This operation is used to unsubscribe the respective user from all the alert types. 

### Example
```java
// Import classes:
import org.wso2.am.integration.clients.store.api.ApiClient;
import org.wso2.am.integration.clients.store.api.ApiException;
import org.wso2.am.integration.clients.store.api.Configuration;
import org.wso2.am.integration.clients.store.api.auth.*;
import org.wso2.am.integration.clients.store.api.models.*;
import org.wso2.am.integration.clients.store.api.v1.AlertSubscriptionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://apis.wso2.com/api/am/store/v1");
    
    // Configure OAuth2 access token for authorization: OAuth2Security
    OAuth OAuth2Security = (OAuth) defaultClient.getAuthentication("OAuth2Security");
    OAuth2Security.setAccessToken("YOUR ACCESS TOKEN");

    AlertSubscriptionsApi apiInstance = new AlertSubscriptionsApi(defaultClient);
    try {
      apiInstance.unsubscribeAllAlerts();
    } catch (ApiException e) {
      System.err.println("Exception when calling AlertSubscriptionsApi#unsubscribeAllAlerts");
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

null (empty response body)

### Authorization

[OAuth2Security](../README.md#OAuth2Security)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK. The user is unsubscribed from the alerts successfully.  |  * Content-Type - The content type of the body.  <br>  |
**500** | Internal Server Error  |  -  |

