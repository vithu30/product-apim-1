/*
 * WSO2 API Manager - Store
 * This document specifies a **RESTful API** for WSO2 **API Manager** - Store.  It is written with [swagger 2](http://swagger.io/). 
 *
 * The version of the OpenAPI document: v1.1
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.store.api.v1.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.wso2.am.integration.clients.store.api.v1.dto.SettingsIdentityProviderDTO;

/**
 * SettingsDTO
 */

public class SettingsDTO {
  public static final String SERIALIZED_NAME_GRANT_TYPES = "grantTypes";
  @SerializedName(SERIALIZED_NAME_GRANT_TYPES)
  private List<String> grantTypes = null;

  public static final String SERIALIZED_NAME_SCOPES = "scopes";
  @SerializedName(SERIALIZED_NAME_SCOPES)
  private List<String> scopes = null;

  public static final String SERIALIZED_NAME_APPLICATION_SHARING_ENABLED = "applicationSharingEnabled";
  @SerializedName(SERIALIZED_NAME_APPLICATION_SHARING_ENABLED)
  private Boolean applicationSharingEnabled = false;

  public static final String SERIALIZED_NAME_MAP_EXISTING_AUTH_APPS = "mapExistingAuthApps";
  @SerializedName(SERIALIZED_NAME_MAP_EXISTING_AUTH_APPS)
  private Boolean mapExistingAuthApps = false;

  public static final String SERIALIZED_NAME_API_GATEWAY_ENDPOINT = "apiGatewayEndpoint";
  @SerializedName(SERIALIZED_NAME_API_GATEWAY_ENDPOINT)
  private String apiGatewayEndpoint;

  public static final String SERIALIZED_NAME_MONETIZATION_ENABLED = "monetizationEnabled";
  @SerializedName(SERIALIZED_NAME_MONETIZATION_ENABLED)
  private Boolean monetizationEnabled = false;

  public static final String SERIALIZED_NAME_RECOMMENDATION_ENABLED = "recommendationEnabled";
  @SerializedName(SERIALIZED_NAME_RECOMMENDATION_ENABLED)
  private Boolean recommendationEnabled = false;

  public static final String SERIALIZED_NAME_IS_UNLIMITED_TIER_PAID = "IsUnlimitedTierPaid";
  @SerializedName(SERIALIZED_NAME_IS_UNLIMITED_TIER_PAID)
  private Boolean isUnlimitedTierPaid = false;

  public static final String SERIALIZED_NAME_IDENTITY_PROVIDER = "identityProvider";
  @SerializedName(SERIALIZED_NAME_IDENTITY_PROVIDER)
  private SettingsIdentityProviderDTO identityProvider;

  public static final String SERIALIZED_NAME_IS_ANONYMOUS_MODE_ENABLED = "IsAnonymousModeEnabled";
  @SerializedName(SERIALIZED_NAME_IS_ANONYMOUS_MODE_ENABLED)
  private Boolean isAnonymousModeEnabled = true;

  public static final String SERIALIZED_NAME_IS_PASSWORD_CHANGE_ENABLED = "IsPasswordChangeEnabled";
  @SerializedName(SERIALIZED_NAME_IS_PASSWORD_CHANGE_ENABLED)
  private Boolean isPasswordChangeEnabled = true;

  public static final String SERIALIZED_NAME_USER_STORE_PASSWORD_PATTERN = "userStorePasswordPattern";
  @SerializedName(SERIALIZED_NAME_USER_STORE_PASSWORD_PATTERN)
  private String userStorePasswordPattern;

  public static final String SERIALIZED_NAME_PASSWORD_POLICY_PATTERN = "passwordPolicyPattern";
  @SerializedName(SERIALIZED_NAME_PASSWORD_POLICY_PATTERN)
  private String passwordPolicyPattern;

  public static final String SERIALIZED_NAME_PASSWORD_POLICY_MIN_LENGTH = "passwordPolicyMinLength";
  @SerializedName(SERIALIZED_NAME_PASSWORD_POLICY_MIN_LENGTH)
  private Integer passwordPolicyMinLength;

  public static final String SERIALIZED_NAME_PASSWORD_POLICY_MAX_LENGTH = "passwordPolicyMaxLength";
  @SerializedName(SERIALIZED_NAME_PASSWORD_POLICY_MAX_LENGTH)
  private Integer passwordPolicyMaxLength;


  public SettingsDTO grantTypes(List<String> grantTypes) {
    
    this.grantTypes = grantTypes;
    return this;
  }

  public SettingsDTO addGrantTypesItem(String grantTypesItem) {
    if (this.grantTypes == null) {
      this.grantTypes = new ArrayList<>();
    }
    this.grantTypes.add(grantTypesItem);
    return this;
  }

   /**
   * Get grantTypes
   * @return grantTypes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<String> getGrantTypes() {
    return grantTypes;
  }


  public void setGrantTypes(List<String> grantTypes) {
    this.grantTypes = grantTypes;
  }


  public SettingsDTO scopes(List<String> scopes) {
    
    this.scopes = scopes;
    return this;
  }

  public SettingsDTO addScopesItem(String scopesItem) {
    if (this.scopes == null) {
      this.scopes = new ArrayList<>();
    }
    this.scopes.add(scopesItem);
    return this;
  }

   /**
   * Get scopes
   * @return scopes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<String> getScopes() {
    return scopes;
  }


  public void setScopes(List<String> scopes) {
    this.scopes = scopes;
  }


  public SettingsDTO applicationSharingEnabled(Boolean applicationSharingEnabled) {
    
    this.applicationSharingEnabled = applicationSharingEnabled;
    return this;
  }

   /**
   * Get applicationSharingEnabled
   * @return applicationSharingEnabled
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getApplicationSharingEnabled() {
    return applicationSharingEnabled;
  }


  public void setApplicationSharingEnabled(Boolean applicationSharingEnabled) {
    this.applicationSharingEnabled = applicationSharingEnabled;
  }


  public SettingsDTO mapExistingAuthApps(Boolean mapExistingAuthApps) {
    
    this.mapExistingAuthApps = mapExistingAuthApps;
    return this;
  }

   /**
   * Get mapExistingAuthApps
   * @return mapExistingAuthApps
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getMapExistingAuthApps() {
    return mapExistingAuthApps;
  }


  public void setMapExistingAuthApps(Boolean mapExistingAuthApps) {
    this.mapExistingAuthApps = mapExistingAuthApps;
  }


  public SettingsDTO apiGatewayEndpoint(String apiGatewayEndpoint) {
    
    this.apiGatewayEndpoint = apiGatewayEndpoint;
    return this;
  }

   /**
   * Get apiGatewayEndpoint
   * @return apiGatewayEndpoint
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getApiGatewayEndpoint() {
    return apiGatewayEndpoint;
  }


  public void setApiGatewayEndpoint(String apiGatewayEndpoint) {
    this.apiGatewayEndpoint = apiGatewayEndpoint;
  }


  public SettingsDTO monetizationEnabled(Boolean monetizationEnabled) {
    
    this.monetizationEnabled = monetizationEnabled;
    return this;
  }

   /**
   * Get monetizationEnabled
   * @return monetizationEnabled
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getMonetizationEnabled() {
    return monetizationEnabled;
  }


  public void setMonetizationEnabled(Boolean monetizationEnabled) {
    this.monetizationEnabled = monetizationEnabled;
  }


  public SettingsDTO recommendationEnabled(Boolean recommendationEnabled) {
    
    this.recommendationEnabled = recommendationEnabled;
    return this;
  }

   /**
   * Get recommendationEnabled
   * @return recommendationEnabled
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getRecommendationEnabled() {
    return recommendationEnabled;
  }


  public void setRecommendationEnabled(Boolean recommendationEnabled) {
    this.recommendationEnabled = recommendationEnabled;
  }


  public SettingsDTO isUnlimitedTierPaid(Boolean isUnlimitedTierPaid) {
    
    this.isUnlimitedTierPaid = isUnlimitedTierPaid;
    return this;
  }

   /**
   * Get isUnlimitedTierPaid
   * @return isUnlimitedTierPaid
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsUnlimitedTierPaid() {
    return isUnlimitedTierPaid;
  }


  public void setIsUnlimitedTierPaid(Boolean isUnlimitedTierPaid) {
    this.isUnlimitedTierPaid = isUnlimitedTierPaid;
  }


  public SettingsDTO identityProvider(SettingsIdentityProviderDTO identityProvider) {
    
    this.identityProvider = identityProvider;
    return this;
  }

   /**
   * Get identityProvider
   * @return identityProvider
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SettingsIdentityProviderDTO getIdentityProvider() {
    return identityProvider;
  }


  public void setIdentityProvider(SettingsIdentityProviderDTO identityProvider) {
    this.identityProvider = identityProvider;
  }


  public SettingsDTO isAnonymousModeEnabled(Boolean isAnonymousModeEnabled) {
    
    this.isAnonymousModeEnabled = isAnonymousModeEnabled;
    return this;
  }

   /**
   * Get isAnonymousModeEnabled
   * @return isAnonymousModeEnabled
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsAnonymousModeEnabled() {
    return isAnonymousModeEnabled;
  }


  public void setIsAnonymousModeEnabled(Boolean isAnonymousModeEnabled) {
    this.isAnonymousModeEnabled = isAnonymousModeEnabled;
  }


  public SettingsDTO isPasswordChangeEnabled(Boolean isPasswordChangeEnabled) {
    
    this.isPasswordChangeEnabled = isPasswordChangeEnabled;
    return this;
  }

   /**
   * Get isPasswordChangeEnabled
   * @return isPasswordChangeEnabled
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsPasswordChangeEnabled() {
    return isPasswordChangeEnabled;
  }


  public void setIsPasswordChangeEnabled(Boolean isPasswordChangeEnabled) {
    this.isPasswordChangeEnabled = isPasswordChangeEnabled;
  }


  public SettingsDTO userStorePasswordPattern(String userStorePasswordPattern) {
    
    this.userStorePasswordPattern = userStorePasswordPattern;
    return this;
  }

   /**
   * The &#39;PasswordJavaRegEx&#39; cofigured in the UserStoreManager
   * @return userStorePasswordPattern
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The 'PasswordJavaRegEx' cofigured in the UserStoreManager")

  public String getUserStorePasswordPattern() {
    return userStorePasswordPattern;
  }


  public void setUserStorePasswordPattern(String userStorePasswordPattern) {
    this.userStorePasswordPattern = userStorePasswordPattern;
  }


  public SettingsDTO passwordPolicyPattern(String passwordPolicyPattern) {
    
    this.passwordPolicyPattern = passwordPolicyPattern;
    return this;
  }

   /**
   * The regex configured in the Password Policy property &#39;passwordPolicy.pattern&#39;
   * @return passwordPolicyPattern
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The regex configured in the Password Policy property 'passwordPolicy.pattern'")

  public String getPasswordPolicyPattern() {
    return passwordPolicyPattern;
  }


  public void setPasswordPolicyPattern(String passwordPolicyPattern) {
    this.passwordPolicyPattern = passwordPolicyPattern;
  }


  public SettingsDTO passwordPolicyMinLength(Integer passwordPolicyMinLength) {
    
    this.passwordPolicyMinLength = passwordPolicyMinLength;
    return this;
  }

   /**
   * If Password Policy Feature is enabled, the property &#39;passwordPolicy.min.length&#39; is returned as the &#39;passwordPolicyMinLength&#39;. If password policy is not enabled, default value -1 will be returned. And it should be noted that the regex pattern(s) returned in &#39;passwordPolicyPattern&#39; and &#39;userStorePasswordPattern&#39; properties too will affect the minimum password length allowed and an intersection of all conditions will be considered finally to validate the password.
   * @return passwordPolicyMinLength
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "If Password Policy Feature is enabled, the property 'passwordPolicy.min.length' is returned as the 'passwordPolicyMinLength'. If password policy is not enabled, default value -1 will be returned. And it should be noted that the regex pattern(s) returned in 'passwordPolicyPattern' and 'userStorePasswordPattern' properties too will affect the minimum password length allowed and an intersection of all conditions will be considered finally to validate the password.")

  public Integer getPasswordPolicyMinLength() {
    return passwordPolicyMinLength;
  }


  public void setPasswordPolicyMinLength(Integer passwordPolicyMinLength) {
    this.passwordPolicyMinLength = passwordPolicyMinLength;
  }


  public SettingsDTO passwordPolicyMaxLength(Integer passwordPolicyMaxLength) {
    
    this.passwordPolicyMaxLength = passwordPolicyMaxLength;
    return this;
  }

   /**
   * If Password Policy Feature is enabled, the property &#39;passwordPolicy.max.length&#39; is returned as the &#39;passwordPolicyMaxLength&#39;. If password policy is not enabled, default value -1 will be returned. And it should be noted that the regex pattern(s) returned in &#39;passwordPolicyPattern&#39; and &#39;userStorePasswordPattern&#39; properties too will affect the maximum password length allowed and an intersection of all conditions will be considered finally to validate the password.
   * @return passwordPolicyMaxLength
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "If Password Policy Feature is enabled, the property 'passwordPolicy.max.length' is returned as the 'passwordPolicyMaxLength'. If password policy is not enabled, default value -1 will be returned. And it should be noted that the regex pattern(s) returned in 'passwordPolicyPattern' and 'userStorePasswordPattern' properties too will affect the maximum password length allowed and an intersection of all conditions will be considered finally to validate the password.")

  public Integer getPasswordPolicyMaxLength() {
    return passwordPolicyMaxLength;
  }


  public void setPasswordPolicyMaxLength(Integer passwordPolicyMaxLength) {
    this.passwordPolicyMaxLength = passwordPolicyMaxLength;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettingsDTO settings = (SettingsDTO) o;
    return Objects.equals(this.grantTypes, settings.grantTypes) &&
        Objects.equals(this.scopes, settings.scopes) &&
        Objects.equals(this.applicationSharingEnabled, settings.applicationSharingEnabled) &&
        Objects.equals(this.mapExistingAuthApps, settings.mapExistingAuthApps) &&
        Objects.equals(this.apiGatewayEndpoint, settings.apiGatewayEndpoint) &&
        Objects.equals(this.monetizationEnabled, settings.monetizationEnabled) &&
        Objects.equals(this.recommendationEnabled, settings.recommendationEnabled) &&
        Objects.equals(this.isUnlimitedTierPaid, settings.isUnlimitedTierPaid) &&
        Objects.equals(this.identityProvider, settings.identityProvider) &&
        Objects.equals(this.isAnonymousModeEnabled, settings.isAnonymousModeEnabled) &&
        Objects.equals(this.isPasswordChangeEnabled, settings.isPasswordChangeEnabled) &&
        Objects.equals(this.userStorePasswordPattern, settings.userStorePasswordPattern) &&
        Objects.equals(this.passwordPolicyPattern, settings.passwordPolicyPattern) &&
        Objects.equals(this.passwordPolicyMinLength, settings.passwordPolicyMinLength) &&
        Objects.equals(this.passwordPolicyMaxLength, settings.passwordPolicyMaxLength);
  }

  @Override
  public int hashCode() {
    return Objects.hash(grantTypes, scopes, applicationSharingEnabled, mapExistingAuthApps, apiGatewayEndpoint, monetizationEnabled, recommendationEnabled, isUnlimitedTierPaid, identityProvider, isAnonymousModeEnabled, isPasswordChangeEnabled, userStorePasswordPattern, passwordPolicyPattern, passwordPolicyMinLength, passwordPolicyMaxLength);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettingsDTO {\n");
    sb.append("    grantTypes: ").append(toIndentedString(grantTypes)).append("\n");
    sb.append("    scopes: ").append(toIndentedString(scopes)).append("\n");
    sb.append("    applicationSharingEnabled: ").append(toIndentedString(applicationSharingEnabled)).append("\n");
    sb.append("    mapExistingAuthApps: ").append(toIndentedString(mapExistingAuthApps)).append("\n");
    sb.append("    apiGatewayEndpoint: ").append(toIndentedString(apiGatewayEndpoint)).append("\n");
    sb.append("    monetizationEnabled: ").append(toIndentedString(monetizationEnabled)).append("\n");
    sb.append("    recommendationEnabled: ").append(toIndentedString(recommendationEnabled)).append("\n");
    sb.append("    isUnlimitedTierPaid: ").append(toIndentedString(isUnlimitedTierPaid)).append("\n");
    sb.append("    identityProvider: ").append(toIndentedString(identityProvider)).append("\n");
    sb.append("    isAnonymousModeEnabled: ").append(toIndentedString(isAnonymousModeEnabled)).append("\n");
    sb.append("    isPasswordChangeEnabled: ").append(toIndentedString(isPasswordChangeEnabled)).append("\n");
    sb.append("    userStorePasswordPattern: ").append(toIndentedString(userStorePasswordPattern)).append("\n");
    sb.append("    passwordPolicyPattern: ").append(toIndentedString(passwordPolicyPattern)).append("\n");
    sb.append("    passwordPolicyMinLength: ").append(toIndentedString(passwordPolicyMinLength)).append("\n");
    sb.append("    passwordPolicyMaxLength: ").append(toIndentedString(passwordPolicyMaxLength)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

