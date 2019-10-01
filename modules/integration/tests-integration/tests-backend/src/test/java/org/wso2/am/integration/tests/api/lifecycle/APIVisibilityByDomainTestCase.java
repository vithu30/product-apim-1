/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.am.integration.tests.api.lifecycle;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.am.integration.clients.publisher.api.v1.dto.APIDTO;
import org.wso2.am.integration.clients.publisher.api.v1.dto.APIListDTO;
import org.wso2.am.integration.clients.store.api.ApiException;
import org.wso2.am.integration.test.impl.RestAPIStoreImpl;
import org.wso2.am.integration.test.utils.APIManagerIntegrationTestException;
import org.wso2.am.integration.test.utils.bean.APICreationRequestBean;
import org.wso2.am.integration.test.utils.bean.APIRequest;
import org.wso2.am.integration.test.utils.clients.APIPublisherRestClient;
import org.wso2.am.integration.test.utils.clients.APIStoreRestClient;
import org.wso2.am.integration.test.utils.generic.APIMTestCaseUtils;
import org.wso2.carbon.apimgt.api.model.APIIdentifier;
import org.wso2.carbon.automation.engine.context.TestUserMode;
import org.wso2.carbon.automation.test.utils.http.client.HttpRequestUtil;
import org.wso2.carbon.automation.test.utils.http.client.HttpResponse;
import org.wso2.am.integration.test.impl.RestAPIPublisherImpl;
import org.wso2.am.integration.test.utils.bean.APILifeCycleAction;

import java.net.URL;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Create a API with domain visibility and check the visibility in Publisher Store.
 */
public class APIVisibilityByDomainTestCase extends APIManagerLifecycleBaseTest {

    private final String API_NAME = "APIVisibilityByDomainTest";
    private final String API_CONTEXT = "APIVisibilityByDomain";
    private final String API_TAGS = "testTag1, testTag2, testTag3";
    private final String API_DESCRIPTION = "This is test API create by API manager integration test";
    private final String API_VERSION_1_0_0 = "1.0.0";
    private final String CARBON_SUPER_TENANT2_KEY = "userKey2";
    private final String TENANT_DOMAIN_KEY = "wso2.com";
    private final String TENANT_DOMAIN_ADMIN_KEY = "admin";
    private final String USER_KEY_USER2 = "userKey1";
    private final String OTHER_DOMAIN_TENANT_USER_KEY = "user1";
    private final String API_END_POINT_POSTFIX_URL = "jaxrs_basic/services/customers/customerservice/";
    private String apiEndPointUrl;
    private APIIdentifier apiIdentifier;
    private APIStoreRestClient apiStoreClientCarbonSuperUser2;
    private APIPublisherRestClient apiPublisherClientCarbonSuperUser2;
    private APIStoreRestClient apiStoreClientAnotherUserOtherDomain;
    private APIPublisherRestClient apiPublisherClientAnotherUserOtherDomain;
    private APIStoreRestClient apiStoreClientAdminOtherDomain;
    private APIPublisherRestClient apiPublisherClientAdminOtherDomain;
    private String providerName;
    private RestAPIPublisherImpl restAPIPublisherCarbonSuperUser1;
    private RestAPIStoreImpl restAPIStoreCarbonSuperUser1;
    private RestAPIPublisherImpl restAPIPublisherCarbonSuperAdmin;
    private RestAPIStoreImpl restAPIStoreCarbonSuperAdmin;
    private RestAPIPublisherImpl restAPIPublisherCarbonSuperUser2;
    private RestAPIStoreImpl restAPIStoreCarbonSuperUser2;
    private RestAPIPublisherImpl restAPIPublisherOtherDomainUser1;
    private RestAPIStoreImpl restAPIStoreOtherDomainUser1;
    private RestAPIPublisherImpl restAPIPublisherOtherDomainAdmin;
    private RestAPIStoreImpl restAPIStoreOtherDomainAdmin;

    private String storeURLHttp;
    private String otherDomain;
    private String apiCreatorStoreDomain;
    private String apiID;

    @BeforeClass(alwaysRun = true)
    public void initialize() throws Exception {
        //Creating CarbonSuper context
        super.init(TestUserMode.SUPER_TENANT_ADMIN);
        apiEndPointUrl = backEndServerUrl.getWebAppURLHttp() + API_END_POINT_POSTFIX_URL;

        // ----------------------------------------------
        storeURLHttp = getStoreURLHttp();
        // ----------------------------------------------

        providerName =
                publisherContext.getContextTenant().getTenantUser(USER_KEY_USER2).getUserName();

        //Login to API Publisher and Store with CarbonSuper normal user1
        restAPIPublisherCarbonSuperUser1 = new RestAPIPublisherImpl(
                publisherContext.getContextTenant().getTenantUser(USER_KEY_USER2).getUserName(),
                publisherContext.getContextTenant().getTenantUser(USER_KEY_USER2).getPassword(),
                "carbon.super",
                "https://localhost:9943/",
                "https://localhost:8743/",
                "https://localhost:9943"
        );
        restAPIStoreCarbonSuperUser1 = new RestAPIStoreImpl(
                publisherContext.getContextTenant().getTenantUser(USER_KEY_USER2).getUserName(),
                publisherContext.getContextTenant().getTenantUser(USER_KEY_USER2).getPassword(),
                "carbon.super",
                "https://localhost:9943",
                "https://localhost:8743",
                "https://localhost:9943"
        );

        restAPIPublisherCarbonSuperAdmin = new RestAPIPublisherImpl(
                "admin", "admin", "carbon.super", "https://localhost:9943/", "https://localhost:8743/",
                "https://localhost:9943");
        restAPIStoreCarbonSuperAdmin = new RestAPIStoreImpl(
                "admin", "admin", "carbon.super", "https://localhost:9943","https://localhost:8743/"
                ,"https://localhost:9943");

        apiCreatorStoreDomain = storeContext.getContextTenant().getDomain();

        //Login to API Publisher adn Store with CarbonSuper normal user2
        restAPIPublisherCarbonSuperUser2 = new RestAPIPublisherImpl(
                storeContext.getContextTenant().getTenantUser(CARBON_SUPER_TENANT2_KEY).getUserName(),
                storeContext.getContextTenant().getTenantUser(CARBON_SUPER_TENANT2_KEY).getPassword(),
                "carbon.super",
                "https://localhost:9943/",
                "https://localhost:8743/",
                "https://localhost:9943");
        restAPIStoreCarbonSuperUser2 = new RestAPIStoreImpl(
                publisherContext.getContextTenant().getTenantUser(CARBON_SUPER_TENANT2_KEY).getUserName(),
                publisherContext.getContextTenant().getTenantUser(CARBON_SUPER_TENANT2_KEY).getPassword(),
                "carbon.super",
                "https://localhost:9943",
                "https://localhost:8743/",
                "https://localhost:9943");

        //Creating Tenant contexts
        init(TENANT_DOMAIN_KEY, TENANT_DOMAIN_ADMIN_KEY);
        otherDomain = storeContext.getContextTenant().getDomain();

        //Login to the API Publisher and Store as Tenant user
        restAPIPublisherOtherDomainUser1 = new RestAPIPublisherImpl(
                storeContext.getContextTenant().getTenantUser(OTHER_DOMAIN_TENANT_USER_KEY).getUserName(),
                storeContext.getContextTenant().getTenantUser(OTHER_DOMAIN_TENANT_USER_KEY).getPassword(),
                otherDomain,
                "https://localhost:9943/",
                "https://localhost:8743/",
                "https://localhost:9943/");
        restAPIStoreOtherDomainUser1 = new RestAPIStoreImpl(
                publisherContext.getContextTenant().getTenantUser(OTHER_DOMAIN_TENANT_USER_KEY).getUserName(),
                publisherContext.getContextTenant().getTenantUser(OTHER_DOMAIN_TENANT_USER_KEY).getPassword(),
                otherDomain, "https://localhost:9943/", "https://localhost:8743/",
                "https://localhost:9943/");

        //Login to the API Publisher adn Store as Tenant admin
        restAPIPublisherOtherDomainAdmin = new RestAPIPublisherImpl(
                storeContext.getContextTenant().getContextUser().getUserName(),
                storeContext.getContextTenant().getContextUser().getPassword(),
                otherDomain,
                "https://localhost:9943/",
                "https://localhost:8743/",
                "https://localhost:9943/");
        restAPIStoreOtherDomainAdmin = new RestAPIStoreImpl(
                publisherContext.getContextTenant().getContextUser().getUserName(),
                publisherContext.getContextTenant().getContextUser().getPassword(),
                otherDomain, "https://localhost:9943/", "https://localhost:8743/"
                ,"https://localhost:9943/");
    }


    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Publisher for API creator ")
    public void testVisibilityForCreatorInPublisher() throws Exception {

        APIDTO apidto = new APIDTO();
        apidto.setName(API_NAME);
        apidto.setVersion(API_VERSION_1_0_0);
        apidto.setProvider(providerName);
        apiIdentifier = new APIIdentifier(providerName, API_NAME, API_VERSION_1_0_0);
        //Create API  with private visibility and publish.

        APIRequest apiCreationRequest = new APIRequest(API_NAME, API_CONTEXT, new URL(apiEndPointUrl));
        apiCreationRequest.setProvider(providerName);
        apiCreationRequest.setVersion(API_VERSION_1_0_0);
        apiCreationRequest.setTags(API_TAGS);
        apiCreationRequest.setDescription(API_DESCRIPTION);
        apiCreationRequest.setVisibility("PRIVATE");

        // Create API
        APIDTO apiAddResponse = restAPIPublisherCarbonSuperUser1.addAPI(apiCreationRequest, "v3");
        apiID = apiAddResponse.getId();

        // Publish API
        HttpResponse apiPublishResponse = restAPIPublisherCarbonSuperUser1.changeAPILifeCycleStatus(apiID,
                APILifeCycleAction.PUBLISH.getAction(), null);

        waitForAPIDeployment();

        APIListDTO getAllApisResponse = restAPIPublisherCarbonSuperUser1.getAllAPIs("carbon.super");

        assertTrue(APIMTestCaseUtils.isAPIAvailable(apiIdentifier, getAllApisResponse),
                "API is not visible to creator in APi Publisher. When Visibility is private. ");
    }


//    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Store for API creator",
//            dependsOnMethods = "testVisibilityForCreatorInPublisher")
//    public void testVisibilityForCreatorInStore() throws ApiException {
//        org.wso2.am.integration.clients.store.api.v1.dto.APIListDTO apiListDTO =
//                restAPIStoreCarbonSuperUser1.getAllPublishedAPIs();
//        assertTrue(APIMTestCaseUtils.isAPIAvailableInStore(apiIdentifier, apiListDTO),
//                "API is not visible to creator in API Store. When Visibility is private. " +
//                        getAPIIdentifierString(apiIdentifier));
//    }

    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Publisher for admin in same domain "
            )
    public void testVisibilityForAdminInSameDomainInPublisher() throws APIManagerIntegrationTestException,
            org.wso2.am.integration.clients.publisher.api.ApiException {
        APIListDTO apiListDTO = restAPIPublisherCarbonSuperAdmin.getAllAPIs("carbon.super");
        assertTrue(APIMTestCaseUtils.isAPIAvailable(apiIdentifier, apiListDTO),
                "API is not visible to admin in same domain in API Publisher. When Visibility is private. " +
                        getAPIIdentifierString(apiIdentifier));
    }


    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Store for admin in same domain ",
            dependsOnMethods = "testVisibilityForAdminInSameDomainInPublisher")
    public void testVisibilityForAdminInSameDomainInStore() throws ApiException {
        org.wso2.am.integration.clients.store.api.v1.dto.APIListDTO apiListDTO =
                restAPIStoreCarbonSuperAdmin.getAllPublishedAPIs();
        assertTrue(APIMTestCaseUtils.isAPIAvailableInStore(apiIdentifier, apiListDTO),
                "API is not visible to admin in same domain in API Store. When Visibility is private. " +
                        getAPIIdentifierString(apiIdentifier));
    }


    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Publisher for another user in same domain",
            dependsOnMethods = "testVisibilityForAdminInSameDomainInStore")
    public void testVisibilityForAnotherUserInSameDomainInPublisher() throws APIManagerIntegrationTestException {
        try {
            APIListDTO apiListDTO = restAPIPublisherCarbonSuperUser2.getAllAPIs("carbon.super");
            assertTrue(APIMTestCaseUtils.isAPIAvailable(apiIdentifier, apiListDTO),
            "API is not visible to another user in same domain in API Publisher. When Visibility is private." +
                    getAPIIdentifierString(apiIdentifier));
        } catch (org.wso2.am.integration.clients.publisher.api.ApiException e) {
            throw new APIManagerIntegrationTestException("Error while retrieving all APIs. " + e.getMessage(), e);
        }
    }

    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Store for another user in same domain",
            dependsOnMethods = "testVisibilityForAnotherUserInSameDomainInPublisher")
    public void testVisibilityForAnotherUserInSameDomainInStore() throws APIManagerIntegrationTestException {
        try {
            org.wso2.am.integration.clients.store.api.v1.dto.APIListDTO apiListDTO =
                    restAPIStoreCarbonSuperUser2.getAllPublishedAPIs();
            assertTrue(APIMTestCaseUtils.isAPIAvailableInStore(apiIdentifier, apiListDTO),
                    "API is not visible to another user in same domain in API Store. When Visibility is private." +
                            getAPIIdentifierString(apiIdentifier));
        } catch (ApiException e) {
            throw new APIManagerIntegrationTestException("Error while retrieving all APIs. " + e.getMessage(), e);
        }
    }

    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Publisher for another user in other domain",
            dependsOnMethods = "testVisibilityForAnotherUserInSameDomainInStore")
    public void testVisibilityForAnotherUserInOtherDomainInPublisher() throws APIManagerIntegrationTestException {
        try {
            APIListDTO apiListDTO = restAPIPublisherOtherDomainUser1.getAllAPIs(otherDomain);
            assertFalse(APIMTestCaseUtils.isAPIAvailable(apiIdentifier, apiListDTO),
                    "API is  visible to another user in other domain in API Publisher. When Visibility is private." +
                            getAPIIdentifierString(apiIdentifier));
        } catch (org.wso2.am.integration.clients.publisher.api.ApiException e) {
            throw new APIManagerIntegrationTestException("Error while retrieving all APIs. " + e.getMessage(), e);
        }
    }


    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Store for another user in other domain",
            dependsOnMethods = "testVisibilityForAnotherUserInOtherDomainInPublisher")
    public void testVisibilityForAnotherUserInOtherDomainInStore() throws APIManagerIntegrationTestException {
        try {
            org.wso2.am.integration.clients.store.api.v1.dto.APIListDTO apiListDTO =
                    restAPIStoreOtherDomainUser1.getAllPublishedAPIs();
            assertFalse(APIMTestCaseUtils.isAPIAvailableInStore(apiIdentifier, apiListDTO),
                    "API is  visible to another user in other domain in API Store. When Visibility is private. " +
                            getAPIIdentifierString(apiIdentifier));
        } catch (ApiException e) {
            throw new APIManagerIntegrationTestException("Error while retrieving all APIs. " + e.getMessage(), e);
        }
    }


    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Publisher for admin in other domain",
            dependsOnMethods = "testVisibilityForAnotherUserInOtherDomainInStore")
    public void testVisibilityForAdminInOtherDomainInPublisher() throws APIManagerIntegrationTestException {
        try {
            APIListDTO apiListDTO = restAPIPublisherOtherDomainAdmin.getAllAPIs(otherDomain);
            assertFalse(APIMTestCaseUtils.isAPIAvailable(apiIdentifier, apiListDTO),
                    "API is  visible to admin in other domain in API Publisher. When Visibility is private. " +
                            getAPIIdentifierString(apiIdentifier));
        } catch (org.wso2.am.integration.clients.publisher.api.ApiException e) {
            throw new APIManagerIntegrationTestException("Error while retrieving all APIs. " + e.getMessage(), e);
        }
    }


    @Test(groups = {"wso2.am"}, description = "Test the visibility of API in Store for admin in other domain",
            dependsOnMethods = "testVisibilityForAdminInOtherDomainInPublisher")
    public void testVisibilityForAdminInOtherDomainInStore() throws APIManagerIntegrationTestException {
        try {
            org.wso2.am.integration.clients.store.api.v1.dto.APIListDTO apiListDTO =
                    restAPIStoreOtherDomainAdmin.getAllPublishedAPIs(otherDomain);
            assertFalse(APIMTestCaseUtils.isAPIAvailableInStore(apiIdentifier, apiListDTO),
                    "API is  visible to admin in other domain in API Store. When Visibility is private. " +
                            getAPIIdentifierString(apiIdentifier));
        } catch (ApiException e) {
            throw new APIManagerIntegrationTestException("Error while retrieving all APIs. " + e.getMessage(), e);
        }
    }

    //https://wso2.org/jira/browse/APIMANAGER-4080
    @Test(groups = {"wso2.am"}, description = "Test the visibility for API in Same domainStore for anonymous user",
            dependsOnMethods = "testVisibilityForAdminInOtherDomainInStore")
    public void testVisibilityForAnonymousUserInSameDomainInStore() throws Exception {
//        HttpResponse httpResponse = HttpRequestUtil.sendGetRequest(storeURLHttp + "store-old/site/blocks/api/recently-added/ajax/list.jag"
//                , "action=getRecentlyAddedAPIs&tenant=" + apiCreatorStoreDomain);
//        assertFalse(new JSONObject(httpResponse.getData()).getBoolean("error"), "Error while getting api list");
//        assertFalse(httpResponse.getData().contains(API_NAME), "API  visible to anonymous user in same domain API Store." +
//                                                                    " When Visibility is private.  " + getAPIIdentifierString(apiIdentifier));
//
    }
//
//
//    @Test(groups = {"wso2.am"}, description = "Test the visibility for API in other domainStore for anonymous user",
//            dependsOnMethods = "testVisibilityForAnonymousUserInSameDomainInStore")
//    public void testVisibilityForAnonymousUserInOtherDomainInStore() throws Exception {
//        HttpResponse httpResponse = HttpRequestUtil.sendGetRequest(storeURLHttp + "store-old/site/blocks/api/recently-added/ajax/list.jag"
//                , "action=getRecentlyAddedAPIs&tenant=" + otherDomain);
//        assertFalse(new JSONObject(httpResponse.getData()).getBoolean("error"), "Error while getting api list");
//        assertFalse(httpResponse.getData().contains(API_NAME), "API is visible to anonymous user in other " +
//                                                               "domain API Store. When Visibility is private. " + getAPIIdentifierString(apiIdentifier));
//    }


//    @AfterClass(alwaysRun = true)
//    public void cleanUpArtifacts() throws APIManagerIntegrationTestException {
//        deleteAPI(apiID, apiIdentifier, restAPIPublisher);
//    }

}
