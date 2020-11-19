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
import org.wso2.am.integration.clients.store.api.v1.dto.ApplicationTokenDTO;

/**
 * ApplicationKeyDTO
 */

public class ApplicationKeyDTO {
  public static final String SERIALIZED_NAME_KEY_MAPPING_ID = "keyMappingId";
  @SerializedName(SERIALIZED_NAME_KEY_MAPPING_ID)
  private String keyMappingId;

  public static final String SERIALIZED_NAME_KEY_MANAGER = "keyManager";
  @SerializedName(SERIALIZED_NAME_KEY_MANAGER)
  private String keyManager;

  public static final String SERIALIZED_NAME_CONSUMER_KEY = "consumerKey";
  @SerializedName(SERIALIZED_NAME_CONSUMER_KEY)
  private String consumerKey;

  public static final String SERIALIZED_NAME_CONSUMER_SECRET = "consumerSecret";
  @SerializedName(SERIALIZED_NAME_CONSUMER_SECRET)
  private String consumerSecret;

  public static final String SERIALIZED_NAME_SUPPORTED_GRANT_TYPES = "supportedGrantTypes";
  @SerializedName(SERIALIZED_NAME_SUPPORTED_GRANT_TYPES)
  private List<String> supportedGrantTypes = null;

  public static final String SERIALIZED_NAME_CALLBACK_URL = "callbackUrl";
  @SerializedName(SERIALIZED_NAME_CALLBACK_URL)
  private String callbackUrl;

  public static final String SERIALIZED_NAME_KEY_STATE = "keyState";
  @SerializedName(SERIALIZED_NAME_KEY_STATE)
  private String keyState;

  /**
   * Describes to which endpoint the key belongs
   */
  @JsonAdapter(KeyTypeEnum.Adapter.class)
  public enum KeyTypeEnum {
    PRODUCTION("PRODUCTION"),
    
    SANDBOX("SANDBOX");

    private String value;

    KeyTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static KeyTypeEnum fromValue(String value) {
      for (KeyTypeEnum b : KeyTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<KeyTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final KeyTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public KeyTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return KeyTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_KEY_TYPE = "keyType";
  @SerializedName(SERIALIZED_NAME_KEY_TYPE)
  private KeyTypeEnum keyType;

  public static final String SERIALIZED_NAME_GROUP_ID = "groupId";
  @SerializedName(SERIALIZED_NAME_GROUP_ID)
  private String groupId;

  public static final String SERIALIZED_NAME_TOKEN = "token";
  @SerializedName(SERIALIZED_NAME_TOKEN)
  private ApplicationTokenDTO token;

  public static final String SERIALIZED_NAME_ADDITIONAL_PROPERTIES = "additionalProperties";
  @SerializedName(SERIALIZED_NAME_ADDITIONAL_PROPERTIES)
  private Object additionalProperties;


  public ApplicationKeyDTO keyMappingId(String keyMappingId) {
    
    this.keyMappingId = keyMappingId;
    return this;
  }

   /**
   * Key Manager Mapping UUID
   * @return keyMappingId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Key Manager Mapping UUID")

  public String getKeyMappingId() {
    return keyMappingId;
  }


  public void setKeyMappingId(String keyMappingId) {
    this.keyMappingId = keyMappingId;
  }


  public ApplicationKeyDTO keyManager(String keyManager) {
    
    this.keyManager = keyManager;
    return this;
  }

   /**
   * Key Manager Name
   * @return keyManager
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Key Manager Name")

  public String getKeyManager() {
    return keyManager;
  }


  public void setKeyManager(String keyManager) {
    this.keyManager = keyManager;
  }


  public ApplicationKeyDTO consumerKey(String consumerKey) {
    
    this.consumerKey = consumerKey;
    return this;
  }

   /**
   * Consumer key of the application
   * @return consumerKey
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "vYDoc9s7IgAFdkSyNDaswBX7ejoa", value = "Consumer key of the application")

  public String getConsumerKey() {
    return consumerKey;
  }


  public void setConsumerKey(String consumerKey) {
    this.consumerKey = consumerKey;
  }


  public ApplicationKeyDTO consumerSecret(String consumerSecret) {
    
    this.consumerSecret = consumerSecret;
    return this;
  }

   /**
   * Consumer secret of the application
   * @return consumerSecret
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "TIDlOFkpzB7WjufO3OJUhy1fsvAa", value = "Consumer secret of the application")

  public String getConsumerSecret() {
    return consumerSecret;
  }


  public void setConsumerSecret(String consumerSecret) {
    this.consumerSecret = consumerSecret;
  }


  public ApplicationKeyDTO supportedGrantTypes(List<String> supportedGrantTypes) {
    
    this.supportedGrantTypes = supportedGrantTypes;
    return this;
  }

  public ApplicationKeyDTO addSupportedGrantTypesItem(String supportedGrantTypesItem) {
    if (this.supportedGrantTypes == null) {
      this.supportedGrantTypes = new ArrayList<>();
    }
    this.supportedGrantTypes.add(supportedGrantTypesItem);
    return this;
  }

   /**
   * The grant types that are supported by the application
   * @return supportedGrantTypes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "[client_credentials, password]", value = "The grant types that are supported by the application")

  public List<String> getSupportedGrantTypes() {
    return supportedGrantTypes;
  }


  public void setSupportedGrantTypes(List<String> supportedGrantTypes) {
    this.supportedGrantTypes = supportedGrantTypes;
  }


  public ApplicationKeyDTO callbackUrl(String callbackUrl) {
    
    this.callbackUrl = callbackUrl;
    return this;
  }

   /**
   * Callback URL
   * @return callbackUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "http://sample.com/callback/url", value = "Callback URL")

  public String getCallbackUrl() {
    return callbackUrl;
  }


  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }


  public ApplicationKeyDTO keyState(String keyState) {
    
    this.keyState = keyState;
    return this;
  }

   /**
   * Describes the state of the key generation.
   * @return keyState
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "APPROVED", value = "Describes the state of the key generation.")

  public String getKeyState() {
    return keyState;
  }


  public void setKeyState(String keyState) {
    this.keyState = keyState;
  }


  public ApplicationKeyDTO keyType(KeyTypeEnum keyType) {
    
    this.keyType = keyType;
    return this;
  }

   /**
   * Describes to which endpoint the key belongs
   * @return keyType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "PRODUCTION", value = "Describes to which endpoint the key belongs")

  public KeyTypeEnum getKeyType() {
    return keyType;
  }


  public void setKeyType(KeyTypeEnum keyType) {
    this.keyType = keyType;
  }


  public ApplicationKeyDTO groupId(String groupId) {
    
    this.groupId = groupId;
    return this;
  }

   /**
   * Application group id (if any).
   * @return groupId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2", value = "Application group id (if any).")

  public String getGroupId() {
    return groupId;
  }


  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }


  public ApplicationKeyDTO token(ApplicationTokenDTO token) {
    
    this.token = token;
    return this;
  }

   /**
   * Get token
   * @return token
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ApplicationTokenDTO getToken() {
    return token;
  }


  public void setToken(ApplicationTokenDTO token) {
    this.token = token;
  }


  public ApplicationKeyDTO additionalProperties(Object additionalProperties) {
    
    this.additionalProperties = additionalProperties;
    return this;
  }

   /**
   * additionalProperties (if any).
   * @return additionalProperties
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "additionalProperties (if any).")

  public Object getAdditionalProperties() {
    return additionalProperties;
  }


  public void setAdditionalProperties(Object additionalProperties) {
    this.additionalProperties = additionalProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationKeyDTO applicationKey = (ApplicationKeyDTO) o;
    return Objects.equals(this.keyMappingId, applicationKey.keyMappingId) &&
        Objects.equals(this.keyManager, applicationKey.keyManager) &&
        Objects.equals(this.consumerKey, applicationKey.consumerKey) &&
        Objects.equals(this.consumerSecret, applicationKey.consumerSecret) &&
        Objects.equals(this.supportedGrantTypes, applicationKey.supportedGrantTypes) &&
        Objects.equals(this.callbackUrl, applicationKey.callbackUrl) &&
        Objects.equals(this.keyState, applicationKey.keyState) &&
        Objects.equals(this.keyType, applicationKey.keyType) &&
        Objects.equals(this.groupId, applicationKey.groupId) &&
        Objects.equals(this.token, applicationKey.token) &&
        Objects.equals(this.additionalProperties, applicationKey.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyMappingId, keyManager, consumerKey, consumerSecret, supportedGrantTypes, callbackUrl, keyState, keyType, groupId, token, additionalProperties);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationKeyDTO {\n");
    sb.append("    keyMappingId: ").append(toIndentedString(keyMappingId)).append("\n");
    sb.append("    keyManager: ").append(toIndentedString(keyManager)).append("\n");
    sb.append("    consumerKey: ").append(toIndentedString(consumerKey)).append("\n");
    sb.append("    consumerSecret: ").append(toIndentedString(consumerSecret)).append("\n");
    sb.append("    supportedGrantTypes: ").append(toIndentedString(supportedGrantTypes)).append("\n");
    sb.append("    callbackUrl: ").append(toIndentedString(callbackUrl)).append("\n");
    sb.append("    keyState: ").append(toIndentedString(keyState)).append("\n");
    sb.append("    keyType: ").append(toIndentedString(keyType)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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

