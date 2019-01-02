/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.am.scenario.tests.rest.api.restrictedVisibility;

import org.apache.axis2.AxisFault;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.wso2.am.scenario.test.common.APIPublisherRestClient;
import org.wso2.am.scenario.test.common.APIRequest;
import org.wso2.am.scenario.test.common.APIStoreRestClient;
import org.wso2.am.scenario.test.common.ScenarioTestBase;
import org.wso2.carbon.automation.test.utils.http.client.HttpResponse;
import org.wso2.carbon.integration.common.admin.client.UserManagementClient;

import org.wso2.am.integration.test.utils.APIManagerIntegrationTestException;
import org.wso2.am.integration.test.utils.bean.APILifeCycleState;
import org.wso2.am.integration.test.utils.bean.APILifeCycleStateRequest;

import java.net.URL;
import java.util.Properties;
import javax.ws.rs.core.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestApiVisibilityRestrictedByRolesTestCase extends ScenarioTestBase {

    private APIPublisherRestClient apiPublisher;
    private String publisherURL;
    private String storeURL;
    private String keyManagerURL;
    private Properties infraProperties;

    private String apiName;
    private String apiContext;
    private String apiVersion = "1.0.0";
    private String apiResource = "/find";
    private String apiVisibility = "restricted";
    private String tierCollection = "Gold,Bronze";
    private String backendEndPoint = "http://ws.cdyne.com/phoneverify/phoneverify.asmx";

    private String userName;
    private String password;
    private String subscribeRole;
    private String creatorRole;

    private final String ADMIN_LOGIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin";

    private UserManagementClient userManagementClient;
    private APIStoreRestClient apiStoreClient;

    @BeforeClass(alwaysRun = true)
    public void init() throws APIManagerIntegrationTestException {

        infraProperties = getDeploymentProperties();
        publisherURL = infraProperties.getProperty(PUBLISHER_URL);
        storeURL = infraProperties.getProperty(STORE_URL);
        keyManagerURL = infraProperties.getProperty(KEYAMANAGER_URL);

        if (publisherURL == null) {
            publisherURL = "https://localhost:9443/publisher";
        }

        if (storeURL == null) {
            storeURL = "https://localhost:9443/store";
        }

        if (keyManagerURL == null) {
            keyManagerURL = "https://localhost:9443/services/";
        }

        setKeyStoreProperties();
        apiPublisher = new APIPublisherRestClient(publisherURL);
        apiPublisher.login(ADMIN_LOGIN_USERNAME, ADMIN_PASSWORD);

        try {
            userManagementClient = new UserManagementClient(keyManagerURL, ADMIN_LOGIN_USERNAME, ADMIN_PASSWORD);
        } catch (AxisFault e) {
            throw new APIManagerIntegrationTestException("Unable to create UserManagementClient", e);
        }
    }

    @Test(description = "1.5.2.1")
    public void testVisibilityOfAPISRestrictedByRoles() throws Exception {

        subscribeRole = "Health-Subscriber";
        userName = "SubscriberUser";
        password = "password123$";
        apiName = "PhoneVerificationAdd";
        apiContext = "/phoneVerifyAdd";

        createRole(userManagementClient, subscribeRole);
        createUser(userManagementClient, userName, password, subscribeRole);

        APIRequest apiRequest = new APIRequest(apiName, apiContext, apiVisibility, subscribeRole, apiVersion, apiResource,
                tierCollection, new URL(backendEndPoint));
        apiPublisher.validateRoles(subscribeRole);

        createAPI(apiRequest, subscribeRole);
        getAPI();
        publishAPI(apiName, ADMIN_LOGIN_USERNAME);
        loginToStore(userName, password);
        checkAPIInStore(apiName);
    }

    @Test(description = "1.5.2.2")
    public void testVisibilityOfAPISRestrictedByMultipleRoles() throws Exception {

        subscribeRole = "NewRole1";
        creatorRole = "NewRole2";
        userName = "MultipleRoleUser";
        password = "password123$";
        apiName = "APIWildCardApi";
        apiContext = "/AddApiWildCardApi";

        createRole(userManagementClient, subscribeRole);
        createRole(userManagementClient, creatorRole);
        createUser(userManagementClient, userName, password, subscribeRole);

        String multipleRoles = subscribeRole + "," + creatorRole;
        APIRequest apiRequest = new APIRequest(apiName, apiContext, apiVisibility, multipleRoles, apiVersion, apiResource,
                tierCollection, new URL(backendEndPoint));

        validateRoles(multipleRoles);
        createAPI(apiRequest, multipleRoles);
        getAPI();
        publishAPI(apiName, ADMIN_LOGIN_USERNAME);
        loginToStore(userName, password);
        checkAPIInStore(apiName);
    }

    private void loginToStore(String userName, String password) throws Exception {

        setKeyStoreProperties();
        apiStoreClient = new APIStoreRestClient(storeURL);
        apiStoreClient.login(userName, password);
    }

    private void checkAPIInStore(String apiName) throws Exception {

        HttpResponse apiResponseStore = apiStoreClient.getAllPublishedAPIs();
        assertTrue(apiResponseStore.getData().contains(apiName), apiName + " is not visible in store");
        verifyResponse(apiResponseStore);
    }

    private void createRole(UserManagementClient userManagementClient, String role) throws Exception {

        userManagementClient.addRole(role,
                new String[]{},
                new String[]{"/permission/admin/login",
                        "/permission/admin/manage/api/subscribe"});
    }

    private void createUser(UserManagementClient userManagementClient, String userName, String password, String role)
            throws Exception {

        if (userManagementClient.userNameExists(role, userName)) {
            userManagementClient.deleteUser(userName);
        }
        userManagementClient.addUser(userName, password, new String[]{role}, "");
    }

    private void validateRoles(String roles) throws APIManagerIntegrationTestException {

        HttpResponse checkValidationRole = apiPublisher.validateRoles(roles);
        assertTrue(checkValidationRole.getData().contains("true"));
        verifyResponse(checkValidationRole);
    }

    private void createAPI(APIRequest apiCreationRequest, String apiName) throws APIManagerIntegrationTestException {

        HttpResponse apiCreationResponse = apiPublisher.addAPI(apiCreationRequest);
        verifyResponse(apiCreationResponse);
    }

    private void publishAPI(String apiName, String username) throws APIManagerIntegrationTestException {

        APILifeCycleStateRequest updateRequest =
                new APILifeCycleStateRequest(apiName, username, APILifeCycleState.PUBLISHED);
        HttpResponse apiResponsePublisher = apiPublisher.changeAPILifeCycleStatus(updateRequest);
        verifyResponse(apiResponsePublisher);
        assertTrue(apiResponsePublisher.getData().contains("PUBLISHED"), "API has not been created in publisher");
    }

    public void getAPI() throws APIManagerIntegrationTestException {

        HttpResponse apiResponseGetAPI = apiPublisher.getAPI(apiName, ADMIN_LOGIN_USERNAME, apiVersion);
        verifyResponse(apiResponseGetAPI);
        assertTrue(apiResponseGetAPI.getData().contains(apiName), apiName + " is not visible in publisher");
    }

    @AfterTest(alwaysRun = true)
    public void destroy() throws Exception {

        apiPublisher.deleteAPI("PhoneVerificationAdd", apiVersion, ADMIN_LOGIN_USERNAME);
        apiPublisher.deleteAPI("APIWildCardApi", apiVersion, ADMIN_LOGIN_USERNAME);

        userManagementClient.deleteUser("SubscriberUser");
        userManagementClient.deleteRole("Health-Subscriber");
        userManagementClient.deleteUser("MultipleRoleUser");
        userManagementClient.deleteRole("NewRole1");
        userManagementClient.deleteRole("NewRole2");
    }
}

