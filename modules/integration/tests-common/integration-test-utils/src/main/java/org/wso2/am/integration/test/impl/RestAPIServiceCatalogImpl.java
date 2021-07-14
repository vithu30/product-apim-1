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

package org.wso2.am.integration.test.impl;

import org.wso2.am.integration.clients.service.catalog.api.ApiClient;
import org.wso2.am.integration.clients.service.catalog.api.ApiException;
import org.wso2.am.integration.clients.service.catalog.api.ApiResponse;
import org.wso2.am.integration.clients.service.catalog.api.v1.ServicesApi;
import org.wso2.am.integration.clients.service.catalog.api.v1.SettingsApi;
import org.wso2.am.integration.clients.service.catalog.api.v1.dto.APIListDTO;
import org.wso2.am.integration.clients.service.catalog.api.v1.dto.ServiceDTO;
import org.wso2.am.integration.clients.service.catalog.api.v1.dto.ServiceInfoListDTO;
import org.wso2.am.integration.clients.service.catalog.api.v1.dto.ServiceListDTO;
import org.wso2.am.integration.test.ClientAuthenticator;

import java.io.File;

public class RestAPIServiceCatalogImpl {
    private ServicesApi servicesApi = new ServicesApi();
    private SettingsApi settingsApi = new SettingsApi();
    public static final String appName = "Integration_Test_App_Service_Catalog";
    public static final String callBackURL = "test.com";
    public static final String tokenScope = "Production";
    public static final String appOwner = "admin";
    public static final String grantType = "password";
    public static final String password = "admin";
    private ApiClient serviceCatalogApiClient = new ApiClient();
    private String tenantDomain;

    public RestAPIServiceCatalogImpl(String username, String password, String tenantDomain,
                                     String webappUrl) {
        String tokenURL = webappUrl + "oauth2/token";
        String dcrURL = webappUrl + "client-registration/v0.17/register";
        String accessToken = ClientAuthenticator
                .getAccessToken("openid service_catalog:service_view service_catalog:service_write", appName,
                    callBackURL, tokenScope, appOwner, grantType, dcrURL, username, password, tenantDomain, tokenURL);

        serviceCatalogApiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);
        serviceCatalogApiClient.setBasePath(webappUrl + "api/am/service-catalog/v0");
        serviceCatalogApiClient.setDebugging(true);
        serviceCatalogApiClient.setReadTimeout(600000);
        serviceCatalogApiClient.setConnectTimeout(600000);
        serviceCatalogApiClient.setWriteTimeout(600000);
        servicesApi.setApiClient(serviceCatalogApiClient);
        settingsApi.setApiClient(serviceCatalogApiClient);
        this.tenantDomain = tenantDomain;
    }

    public ServiceListDTO searchServices(String serviceName, String serviceVersion, String definitionType,
                                         String serviceKey, Boolean shrink, String sortBy, String sortOrder,
                                         Integer limit, Integer offset) throws ApiException {
        return servicesApi.searchServices(serviceName, serviceVersion, definitionType, serviceKey, shrink, sortBy,
                sortOrder, limit, offset);
    }

    public ApiResponse<ServiceDTO> addService(String serviceName, String serviceVersion, String description, String serviceKey,
                                              String serviceUrl, File definitionFile, String inlineContent, ServiceDTO
                                         .DefinitionTypeEnum definitionType) throws ApiException {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setName(serviceName);
        serviceDTO.setVersion(serviceVersion);
        serviceDTO.setDescription(description);
        serviceDTO.serviceKey(serviceKey);
        serviceDTO.serviceUrl(serviceUrl);
        serviceDTO.setDefinitionType(definitionType);
        return servicesApi.addServiceWithHttpInfo(serviceDTO, definitionFile, inlineContent);
    }

    public ServiceDTO getServiceByUUID(String serviceUUID) throws ApiException {
        return servicesApi.getServiceById(serviceUUID);
    }

    public ServiceDTO updateService(String serviceUUID, String name, String version, String description, String key,
                                    String serviceUrl, File definitionFile, String inlineContent) throws ApiException {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(serviceUUID);
        serviceDTO.setName(name);
        serviceDTO.setVersion(version);
        serviceDTO.setDescription(description);
        serviceDTO.serviceKey(key);
        serviceDTO.setServiceUrl(serviceUrl);
        return servicesApi.updateService(serviceUUID, serviceDTO, definitionFile, inlineContent);
    }

    public void deleteService(String serviceUUID) throws ApiException {
        servicesApi.deleteService(serviceUUID);
    }

    public String getServiceDefinition(String serviceUUID) throws ApiException {
        return servicesApi.getServiceDefinition(serviceUUID);
    }

    public APIListDTO getServiceUsageCount(String serviceUUID) throws ApiException {
        return servicesApi.getServiceUsage(serviceUUID);
    }

    public ApiResponse<ServiceInfoListDTO> importServices(File servicesArchive, Boolean overwrite, String verifier)
            throws ApiException {
        return servicesApi.importServiceWithHttpInfo(servicesArchive, overwrite, verifier);
    }
}
