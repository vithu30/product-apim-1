package org.wso2.am.scenario.test.common;

import org.testng.annotations.DataProvider;
import org.wso2.am.integration.test.utils.base.APIMIntegrationConstants;

public class ScenarioDataProvider {

    // TODO: Add {"电话验证"} to apiNames data set once fix done.

    @DataProvider(name = "apiNames")
    public static Object[][] ApiDataProvide() {
        return new Object[][]{
                {"PhoneVerification"}, {"123567890"}, {"eñe"}, {"Pho_ne-verify?api."}
        };
    }

    @DataProvider(name = "OASDocsWithJSONFiles")
    public static Object[][] OASDocJsonFilesProvide() {
        return new Object[][]{
                {"swaggerFiles/OAS2Document.json"}, {"swaggerFiles/OAS3Document.json"}
        };
    }

    @DataProvider(name = "OASDocsWithYAMLFiles")
    public static Object[][] OASDocYamlFilesProvide() {
        return new Object[][]{
                {"swaggerFiles/OAS2Document.yaml"}, {"swaggerFiles/OAS3Document.yaml"}
        };
    }

    @DataProvider(name = "OASDocsWithJsonURL")
    public static Object[][] OASDocJsonURLProvide() {
        return new Object[][]{
                {"https://raw.githubusercontent.com/wso2/product-apim/product-scenarios/product-scenarios/1-manage-public-partner-private-apis/1.1-expose-service-as-rest-api/1.1.2-create-rest-api-by-import-oas-document/src/test/resources/swaggerFiles/OAS2Document.json"},
                {"https://raw.githubusercontent.com/wso2/product-apim/product-scenarios/product-scenarios/1-manage-public-partner-private-apis/1.1-expose-service-as-rest-api/1.1.2-create-rest-api-by-import-oas-document/src/test/resources/swaggerFiles/OAS3Document.json"}
        };
    }

    @DataProvider(name = "OASDocsWithYamlURL")
    public static Object[][] OASDocYamlURLProvide() {
        return new Object[][]{
                {"https://raw.githubusercontent.com/wso2/product-apim/product-scenarios/product-scenarios/1-manage-public-partner-private-apis/1.1-expose-service-as-rest-api/1.1.2-create-rest-api-by-import-oas-document/src/test/resources/swaggerFiles/OAS2Document.yaml"},
                {"https://raw.githubusercontent.com/wso2/product-apim/product-scenarios/product-scenarios/1-manage-public-partner-private-apis/1.1-expose-service-as-rest-api/1.1.2-create-rest-api-by-import-oas-document/src/test/resources/swaggerFiles/OAS3Document.yaml"}
        };
    }

    @DataProvider(name = "ValidApplicationNameAndTierDataProvider")
    public static Object[][] validApplicationNameAndTiersDataProvider() {
        return new Object[][]{
                {"App", APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED},
                {"Application_-.", APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED},
                {"1234", APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED},
        };
    }

    @DataProvider(name = "InvalidMandatoryApplicationValuesDataProvider")
    public static Object[][] invalidMandatoryApplicationValuesDataProvider() {
        return new Object[][]{
                {" App 1 ", APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED,
                        "Application name cannot contain leading or trailing white spaces"},
                {"App !@#$%^", APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED, "Invalid inputs"},
                {" ", APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED, "Application Name is empty."},
                {"App 3", "", "Specified application tier does not exist."},
                {"App 4", "TierAbc", "Specified application tier does not exist."},
        };
    }

    @DataProvider(name = "OptionalApplicationValuesDataProvider")
    public static Object[][] optionalApplicationValuesDataProvider() {
        return new Object[][]{
                {"JWT"},
                {"DEFAULT"},
                {"OAuth"}
        };
    }

    @DataProvider(name = "MissingMandatoryApplicationValuesDataProvider")
    public static Object[][] missingMandatoryApplicationValuesDataProvider() {
        String urlPrefix = "{{backendURL}}/site/blocks/application/application-add/ajax/application-add.jag?" +
                "action=addApplication";
        return new Object[][]{
                {"", urlPrefix + "&tier=" + APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED +
                        "&callbackUrl=&description=description", "Missing parameters."},
                {"application-missingMandatory1", urlPrefix + "&tier="
                        + APIMIntegrationConstants.APPLICATION_TIER.UNLIMITED +
                        "&callbackUrl=&application=application-missingMandatory1", "Missing parameters."},
                {"application-missingMandatory2", urlPrefix + "&callbackUrl=&description=description" +
                        "&application=application-missingMandatory2", "Specified application tier does not exist."}
        };
    }
}
