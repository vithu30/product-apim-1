/*
 *
 *   Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */

package org.wso2.am.integration.tests.publisher;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.wso2.am.integration.clients.publisher.api.ApiException;
import org.wso2.am.integration.clients.publisher.api.v1.dto.APIDTO;
import org.wso2.am.integration.test.impl.RestAPIPublisherImpl;
import org.wso2.am.integration.test.utils.base.APIMIntegrationBaseTest;
import org.wso2.carbon.automation.engine.context.TestUserMode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Create an API through the Publisher Rest API without providing mandatory fields
 * JIRA Issue Id - APIMANAGER-4039
 */

public class APIM514CreateAnAPIWithoutProvidingMandatoryFieldsTestCase extends
        APIMIntegrationBaseTest {
    private final String apiNameTest1 = "APIM514PublisherTest1";
    private final String apiVersion = "1.0.0";
    private final String apiDescription =
            "This is Test API Created by API Manager Integration Test";
    private String apiTag = "tag514-1, tag514-2, tag514-3";
    private String apiProviderName;
    private String apiProductionEndPointUrl;
    private RestAPIPublisherImpl restAPIPublisher;
    private JSONObject endpointURL = new JSONObject();
    private JSONObject endpointConfig = new JSONObject();

    @Factory(dataProvider = "userModeDataProvider")
    public APIM514CreateAnAPIWithoutProvidingMandatoryFieldsTestCase(TestUserMode userMode) {
        this.userMode = userMode;
    }

    @DataProvider
    public static Object[][] userModeDataProvider() {
        return new Object[][]{
                new Object[]{TestUserMode.SUPER_TENANT_ADMIN},
                new Object[]{TestUserMode.TENANT_ADMIN},
        };
    }

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        super.init(userMode);

        String apiProductionEndpointPostfixUrl = "jaxrs_basic/services/customers/customerservice/customers/123";
        restAPIPublisher = new RestAPIPublisherImpl(
                publisherContext.getContextTenant().getContextUser().getUserName(),
                publisherContext.getContextTenant().getContextUser().getPassword(),
                publisherContext.getContextTenant().getDomain(), publisherURLHttps
        );

        apiProductionEndPointUrl = getGatewayURLHttp() +
                apiProductionEndpointPostfixUrl;
        apiProviderName = publisherContext.getContextTenant().getContextUser().getUserName();
    }

    @Test(groups = {"wso2.am"}, description = "Create an API Through the Publisher Rest API" +
            " without proving API Name")
    public void testCreateAnAPIThroughThePublisherRestWithoutName() throws Exception {

        String apiContextTest = "apim514PublisherTestAPI1";
        endpointURL.put("url", apiProductionEndPointUrl);
        endpointConfig.put("endpoint_type", "http");
        endpointConfig.put("sandbox_endpoints", endpointURL);
        endpointConfig.put("production_endpoints", endpointURL);

        APIDTO apiRequest = new APIDTO();
        apiRequest.setName("");
        apiRequest.setContext(apiContextTest);
        apiRequest.setEndpointConfig(endpointConfig);
        apiRequest.setVersion(apiVersion);
        apiRequest.setVisibility(APIDTO.VisibilityEnum.PUBLIC);
        apiRequest.setProvider(apiProviderName);
        apiRequest.setTags(new ArrayList<>(Arrays.asList(apiTag)));
        apiRequest.setDescription(apiDescription);

        try {
            APIDTO apidto = restAPIPublisher.addAPI(apiRequest, "v3");
        } catch (ApiException exception) {
            // TODO: Add assert
            // assertTrue(apiResponse.getBoolean("error"), "can be create API without name");
            // assertTrue(apiResponse.getString("message").contains
            // ("API name is not specified"), "can be create API without name");
            // Currently creating API without name does not give proper error message. Response received as,
            // "Error while adding new API : admin--1.0.0 - Error while performing registry transaction operation"
        }
    }

    @Test(groups = {"wso2.am"}, description = "Create an API Through the Publisher Rest API " +
            "without proving Context")
    public void testCreateAnAPIThroughThePublisherRestWithoutContext() throws Exception {
        APIDTO apiRequest = new APIDTO();
        apiRequest.setName(apiNameTest1);
        apiRequest.setContext("");
        apiRequest.setEndpointConfig(endpointConfig);
        apiRequest.setVersion(apiVersion);
        apiRequest.setVisibility(APIDTO.VisibilityEnum.PUBLIC);
        apiRequest.setProvider(apiProviderName);
        apiRequest.setTags(new ArrayList<>(Arrays.asList(apiTag)));
        apiRequest.setDescription(apiDescription);

        try {
            APIDTO apidto = restAPIPublisher.addAPI(apiRequest, "v3");
        } catch (ApiException exception) {
            // TODO: Add assert
            // assertTrue(apiResponse.getBoolean("error"), apiNameTest1 + "can be create without Context");
            // assertTrue(apiResponse.getString("message").contains
            //           ("Context not defined for API"), apiNameTest1 + "can be create without Context");
            // Currently creating API without name does not give proper error message. Response received as,
            // "Error while adding new API : admin-APIM514PublisherTest1-1.0.0 - Error while creating API"
        }
    }

    @Test(groups = {"wso2.am"}, description = "Create an API Through the Publisher Rest API " +
            "without providing version")
    public void testCreateAnAPIThroughThePublisherRestWithoutVersion() throws Exception {

        String apiContextTest = "apim514PublisherTestAPI2";
        String apiNameTest2 = "APIM514PublisherTest2";

        APIDTO apiRequest = new APIDTO();
        apiRequest.setName(apiNameTest2);
        apiRequest.setContext(apiContextTest);
        apiRequest.setEndpointConfig(endpointConfig);
        apiRequest.setVersion("");
        apiRequest.setVisibility(APIDTO.VisibilityEnum.PUBLIC);
        apiRequest.setProvider(apiProviderName);
        apiRequest.setTags(new ArrayList<>(Arrays.asList(apiTag)));

        try {
            APIDTO apidto = restAPIPublisher.addAPI(apiRequest, "v3");
        } catch (ApiException exception) {
            // TODO: Add assert
            //            assertTrue(apiResponse.getBoolean("error"), apiNameTest2 + "can be create without Version");
            // assertTrue(apiResponse.getString("message").contains
            // ("Version not specified for API " + apiNameTest2), apiNameTest2 + "can be create without Version");
            // Currently creating API without name does not give proper error message. Response received as,
            // "Error while adding new API : admin-APIM514PublisherTest2- - Error while performing registry transaction
            // operation"
        }
    }
}
