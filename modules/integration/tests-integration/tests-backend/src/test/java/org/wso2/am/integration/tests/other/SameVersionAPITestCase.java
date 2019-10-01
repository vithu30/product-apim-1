/*
 *
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.am.integration.tests.other;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.wso2.am.integration.clients.publisher.api.ApiException;
import org.wso2.am.integration.test.utils.base.APIMIntegrationBaseTest;
import org.wso2.am.integration.test.utils.base.APIMIntegrationConstants;
import org.wso2.am.integration.test.utils.bean.APIRequest;
import org.wso2.carbon.automation.engine.context.TestUserMode;
import org.wso2.carbon.automation.test.utils.http.client.HttpResponse;

import javax.ws.rs.core.Response;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class SameVersionAPITestCase extends APIMIntegrationBaseTest {

    private static final Log log = LogFactory.getLog(SameVersionAPITestCase.class);
    private static final String API_NAME = "SameVersionAPITest";
    private static final String API_CONTEXT = "SameVersionAPI";
    private String version = "1.0.0";
    private String newVersion = "1.0.0";
    private String TAGS = "testtag1, testtag2";
    private String providerName;
    private String visibility = "public";
    private String description = "Test Description";
    private String tier = APIMIntegrationConstants.API_TIER.GOLD;
    private String resTier = APIMIntegrationConstants.RESOURCE_TIER.TENK_PER_MIN;
    private String endPointType = "http";
    private String apiId;
    private String resourceMethodAuthType = "Application & Application User";
    private String uriTemplate = "customers/{id}/";

    @Factory(dataProvider = "userModeDataProvider")
    public SameVersionAPITestCase(TestUserMode userMode) {
        this.userMode = userMode;
    }

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        super.init(userMode);
    }

    @Test(groups = "webapp", description = "Copy Same Version")
    public void copySameVersion() throws Exception {
        String gatewayUrl;
        if (gatewayContextWrk.getContextTenant().getDomain().equals("carbon.super")) {
            gatewayUrl = gatewayUrlsWrk.getWebAppURLNhttp();
        } else {
            gatewayUrl = gatewayUrlsWrk.getWebAppURLNhttp() + "t/" + gatewayContextWrk.getContextTenant().getDomain() +
                    "/";
        }

        String endpointUrl = gatewayUrl + "jaxrs_basic/services/customers/customerservice";
        providerName = publisherContext.getContextTenant().getContextUser().getUserName();

        //API request
        APIRequest apiRequest = new APIRequest(API_NAME, API_CONTEXT, new URL(endpointUrl));
        apiRequest.setTags(TAGS);
        apiRequest.setDescription(description);
        apiRequest.setVersion(version);
        apiRequest.setProvider(providerName);
        apiRequest.setEndpointType(endPointType);
        apiRequest.setResourceMethodAuthType(resourceMethodAuthType);
        apiRequest.setTier(tier);
        apiRequest.setResourceMethodThrottlingTier(resTier);
        apiRequest.setUriTemplate(uriTemplate);
        apiRequest.setVisibility(visibility);

        //Add API
        HttpResponse serviceResponse = restAPIPublisher.addAPI(apiRequest);
        apiId = serviceResponse.getData();
        assertEquals(serviceResponse.getResponseCode(), Response.Status.CREATED.getStatusCode(),
                "Invalid Response Code");

        //try to copy api with same version
        try {
            restAPIPublisher.copyAPI(newVersion, apiId, false);
            assertTrue(false, "Same version API test case failed");
        } catch (Exception e) {
            if (((ApiException) e).getResponseBody().contains("Resource Already Exists")) {
                log.info("Same version API test case passed");
            } else {
                assertTrue(false, "Same version API test case failed");
            }
        }
    }

    @AfterClass(alwaysRun = true)
    public void destroy() throws Exception {
        restAPIPublisher.deleteAPI(apiId);
        super.cleanUp();
    }

    @DataProvider
    public static Object[][] userModeDataProvider() {
        return new Object[][]{
                new Object[]{TestUserMode.SUPER_TENANT_ADMIN},
                new Object[]{TestUserMode.TENANT_ADMIN},
        };
    }
}
