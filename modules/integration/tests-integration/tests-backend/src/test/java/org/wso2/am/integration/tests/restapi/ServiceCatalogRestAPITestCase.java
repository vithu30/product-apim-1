/*
 * Copyright (c) 2021, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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

package org.wso2.am.integration.tests.restapi;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.wso2.am.integration.clients.service.catalog.api.ApiException;
import org.wso2.am.integration.clients.service.catalog.api.ApiResponse;
import org.wso2.am.integration.clients.service.catalog.api.v1.dto.ServiceDTO;
import org.wso2.am.integration.clients.service.catalog.api.v1.dto.ServiceInfoListDTO;
import org.wso2.am.integration.clients.service.catalog.api.v1.dto.ServiceListDTO;
import org.wso2.am.integration.test.impl.RestAPIServiceCatalogImpl;
import org.wso2.am.integration.test.utils.base.APIMIntegrationBaseTest;
import org.wso2.am.integration.tests.api.lifecycle.APIManagerConfigurationChangeTest;
import org.wso2.carbon.automation.engine.context.TestUserMode;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ServiceCatalogRestAPITestCase extends APIMIntegrationBaseTest {

    private RestAPIServiceCatalogImpl restAPIServiceCatalogImpl;
    private String employeeServiceID;
    private String sseServiceID;

    @Factory(dataProvider = "userModeDataProvider")
    public ServiceCatalogRestAPITestCase(TestUserMode userMode) {
        this.userMode = userMode;
    }

    @DataProvider
    public static Object[][] userModeDataProvider() {
        return new Object[][]{new Object[]{TestUserMode.SUPER_TENANT_ADMIN},
                new Object[]{TestUserMode.SUPER_TENANT_USER},
                new Object[]{TestUserMode.TENANT_ADMIN},
                new Object[]{TestUserMode.TENANT_USER},
        };
    }

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        super.init(userMode);
        restAPIServiceCatalogImpl = new RestAPIServiceCatalogImpl(user.getUserName(), user.getPassword(),
                user.getUserDomain(), publisherURLHttps);
    }

    @Test(groups = {"wso2.am"}, description = "Add Single Service Test case")
    public void addSingleService() throws Exception {
        String name = "PizzaShackService";
        String version = "1.0.0";
        String description = "Sample Pizza-shack Service";
        String key = "PizzaShackService-1.0.0";
        String serviceUrl = "";
        String serviceDefinition = IOUtils.toString(
                ServiceCatalogRestAPITestCase.class.getClassLoader()
                        .getResourceAsStream("oas" + File.separator + "v3" + File.separator
                                + "pizza-shack-service-definition.yaml"), StandardCharsets.UTF_8.name());
        ApiResponse apiResponse = restAPIServiceCatalogImpl.addService(name, version, description, key, serviceUrl,
                null, serviceDefinition, ServiceDTO.DefinitionTypeEnum.OAS3);
        Assert.assertNotNull(apiResponse);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertTrue(apiResponse.getData() instanceof ServiceDTO);
    }

    @Test(groups = {"wso2.am"}, description = "Import Multiple Services Test case with overwrite set as true")
    public void importServicesWithOverwriteTrue() throws Exception {
        String servicesArchivePath = ServiceCatalogRestAPITestCase.class.getClassLoader().getResource("services"
                + File.separator + "services.zip").getPath();
        File servicesArchive = new File(servicesArchivePath);
        ApiResponse<ServiceInfoListDTO> apiResponse = restAPIServiceCatalogImpl.importServices(servicesArchive,
                true, null);
        Assert.assertNotNull(apiResponse);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(apiResponse.getData().getList().size(), 3);
        // Search service by name
        ServiceListDTO servicesByName = restAPIServiceCatalogImpl.searchServices("Employee-service", null,
                null, null, null, null, null, null, null);
        Assert.assertEquals(servicesByName.getList().size(), 2);

        // Search service by name and version when shrink is true
        ServiceListDTO servicesByNameAndVersion = restAPIServiceCatalogImpl.searchServices("Employee-service",
                "1.0.0", null, null, true, null, null, null, null);
        Assert.assertNotNull(servicesByNameAndVersion.getList());
        Assert.assertEquals(servicesByNameAndVersion.getList().size(), 1);
        ServiceDTO serviceDTO = servicesByNameAndVersion.getList().get(0);
        employeeServiceID = serviceDTO.getId();
        Assert.assertEquals(serviceDTO.getName(), "Employee-service");
        Assert.assertEquals(serviceDTO.getVersion(), "1.0.0");
        Assert.assertEquals(serviceDTO.getServiceKey(), "Employee-service-1.0.0");
        Assert.assertNotNull(employeeServiceID);
        Assert.assertNull(serviceDTO.getDefinitionType());

        // Search service by definition type
        ServiceListDTO servicesByDefinitionType = restAPIServiceCatalogImpl.searchServices(null, null, ServiceDTO
                .DefinitionTypeEnum.ASYNC_API.toString(), null, false, null, null, null, null);
        Assert.assertNotNull(servicesByDefinitionType.getList());
        Assert.assertEquals(servicesByDefinitionType.getList().size(), 1);
        serviceDTO = servicesByDefinitionType.getList().get(0);
        Assert.assertEquals(serviceDTO.getServiceKey(), "SSE-Service-1.0.0");
        sseServiceID = serviceDTO.getId();
        Assert.assertNotNull(sseServiceID);

        // Get service by serviceId
        ServiceDTO retrievedService = restAPIServiceCatalogImpl.getServiceByUUID(sseServiceID);
        Assert.assertNotNull(retrievedService);
        Assert.assertEquals(retrievedService.getName(), "SSE-Service");
        Assert.assertEquals(retrievedService.getServiceKey(), "SSE-Service-1.0.0");
        Assert.assertEquals(retrievedService.getDefinitionType(), ServiceDTO.DefinitionTypeEnum.ASYNC_API);
    }

    @Test(groups = {"wso2.am"}, description = "Update existing services test case with overwrite set as false")
    public void importServicesWithOverwriteFalse() throws Exception {
        String servicesArchivePath = ServiceCatalogRestAPITestCase.class.getClassLoader()
                .getResource("services" + File.separator + "employee-service-v1.0.1.zip").getPath();
        File servicesArchive = new File(servicesArchivePath);

        // Update an existing service when overwrite is set as false and without verifier
        try {
            restAPIServiceCatalogImpl.importServices(servicesArchive, false, null);
        } catch (ApiException exception) {
            Assert.assertEquals(exception.getCode(), 400);
            Assert.assertTrue(exception.getResponseBody().contains("Cannot update existing services when overwrite " +
                    "is false"));
        }

        // Update an existing service when overwrite is set as true and with verifier
        try {
            ServiceListDTO serviceList = restAPIServiceCatalogImpl.searchServices("Employee-service", "1.0.0", null,
                    null, false, null, null, null, null);
            Assert.assertNotNull(serviceList);
            Assert.assertEquals(serviceList.getList().size(), 1);
            ServiceDTO serviceDTO = serviceList.getList().get(0);
            StringBuilder verifierSb = new StringBuilder();
            verifierSb.append("[\n {\n \"key\": \"")
                    .append(serviceDTO.getServiceKey())
                    .append("\", \n \"md5\": \"")
                    .append(serviceDTO.getMd5())
                    .append("\" \n } \n ]");
            restAPIServiceCatalogImpl.importServices(servicesArchive, false, verifierSb.toString());
        } catch (ApiException exception) {
            Assert.assertEquals(exception.getCode(), 400);
            Assert.assertTrue(exception.getResponseBody().contains("Cannot update existing services when overwrite " +
                    "is false"));
        }
    }

    @Test
    public void deleteServices() throws Exception {

    }

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.keyStore",
                "/home/vithursa/Downloads/wso2am-4.0.0-SNAPSHOT/repository/resources/security/wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStore",
                "/home/vithursa/Downloads/wso2am-4.0.0-SNAPSHOT/repository/resources/security/client-truststore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "wso2carbon");

        System.setProperty("framework.resource.location", "/home/vithursa/Documents/APIManagementProject/new-product-apim/product-apim/modules/integration/tests-integration/tests-backend/src/test/resources/");
        System.setProperty("user.dir",
                "/home/vithursa/Documents/APIManagementProject/new-product-apim/product-apim/modules/integration/tests-integration/tests-backend/src");
        APIManagerConfigurationChangeTest con = new APIManagerConfigurationChangeTest();
        con.configureEnvironment();
        ServiceCatalogRestAPITestCase aCase = new ServiceCatalogRestAPITestCase(TestUserMode.SUPER_TENANT_ADMIN);
        aCase.init();
        aCase.setEnvironment();

        try {
//            aCase.addSingleService();
//            aCase.importServicesWithOverwriteTrue();
            aCase.importServicesWithOverwriteFalse();
        } finally {
            System.out.println("completed");
        }
    }
}
