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
import org.wso2.am.integration.clients.store.api.v1.dto.APIBusinessInformationDTO;
import org.wso2.am.integration.clients.store.api.v1.dto.AdvertiseInfoDTO;

/**
 * APIInfoDTO
 */

public class APIInfoDTO {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_CONTEXT = "context";
  @SerializedName(SERIALIZED_NAME_CONTEXT)
  private String context;

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;

  public static final String SERIALIZED_NAME_PROVIDER = "provider";
  @SerializedName(SERIALIZED_NAME_PROVIDER)
  private String provider;

  public static final String SERIALIZED_NAME_LIFE_CYCLE_STATUS = "lifeCycleStatus";
  @SerializedName(SERIALIZED_NAME_LIFE_CYCLE_STATUS)
  private String lifeCycleStatus;

  public static final String SERIALIZED_NAME_THUMBNAIL_URI = "thumbnailUri";
  @SerializedName(SERIALIZED_NAME_THUMBNAIL_URI)
  private String thumbnailUri;

  public static final String SERIALIZED_NAME_AVG_RATING = "avgRating";
  @SerializedName(SERIALIZED_NAME_AVG_RATING)
  private String avgRating;

  public static final String SERIALIZED_NAME_THROTTLING_POLICIES = "throttlingPolicies";
  @SerializedName(SERIALIZED_NAME_THROTTLING_POLICIES)
  private List<String> throttlingPolicies = null;

  public static final String SERIALIZED_NAME_ADVERTISE_INFO = "advertiseInfo";
  @SerializedName(SERIALIZED_NAME_ADVERTISE_INFO)
  private AdvertiseInfoDTO advertiseInfo;

  public static final String SERIALIZED_NAME_BUSINESS_INFORMATION = "businessInformation";
  @SerializedName(SERIALIZED_NAME_BUSINESS_INFORMATION)
  private APIBusinessInformationDTO businessInformation;

  public static final String SERIALIZED_NAME_IS_SUBSCRIPTION_AVAILABLE = "isSubscriptionAvailable";
  @SerializedName(SERIALIZED_NAME_IS_SUBSCRIPTION_AVAILABLE)
  private Boolean isSubscriptionAvailable;

  public static final String SERIALIZED_NAME_MONETIZATION_LABEL = "monetizationLabel";
  @SerializedName(SERIALIZED_NAME_MONETIZATION_LABEL)
  private String monetizationLabel;


  public APIInfoDTO id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "01234567-0123-0123-0123-012345678901", value = "")

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public APIInfoDTO name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "CalculatorAPI", value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public APIInfoDTO description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "A calculator API that supports basic operations", value = "")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public APIInfoDTO context(String context) {
    
    this.context = context;
    return this;
  }

   /**
   * Get context
   * @return context
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "CalculatorAPI", value = "")

  public String getContext() {
    return context;
  }


  public void setContext(String context) {
    this.context = context;
  }


  public APIInfoDTO version(String version) {
    
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1.0.0", value = "")

  public String getVersion() {
    return version;
  }


  public void setVersion(String version) {
    this.version = version;
  }


  public APIInfoDTO type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "WS", value = "")

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public APIInfoDTO provider(String provider) {
    
    this.provider = provider;
    return this;
  }

   /**
   * If the provider value is not given, the user invoking the API will be used as the provider. 
   * @return provider
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "admin", value = "If the provider value is not given, the user invoking the API will be used as the provider. ")

  public String getProvider() {
    return provider;
  }


  public void setProvider(String provider) {
    this.provider = provider;
  }


  public APIInfoDTO lifeCycleStatus(String lifeCycleStatus) {
    
    this.lifeCycleStatus = lifeCycleStatus;
    return this;
  }

   /**
   * Get lifeCycleStatus
   * @return lifeCycleStatus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "PUBLISHED", value = "")

  public String getLifeCycleStatus() {
    return lifeCycleStatus;
  }


  public void setLifeCycleStatus(String lifeCycleStatus) {
    this.lifeCycleStatus = lifeCycleStatus;
  }


  public APIInfoDTO thumbnailUri(String thumbnailUri) {
    
    this.thumbnailUri = thumbnailUri;
    return this;
  }

   /**
   * Get thumbnailUri
   * @return thumbnailUri
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "/apis/01234567-0123-0123-0123-012345678901/thumbnail", value = "")

  public String getThumbnailUri() {
    return thumbnailUri;
  }


  public void setThumbnailUri(String thumbnailUri) {
    this.thumbnailUri = thumbnailUri;
  }


  public APIInfoDTO avgRating(String avgRating) {
    
    this.avgRating = avgRating;
    return this;
  }

   /**
   * Average rating of the API
   * @return avgRating
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "4.5", value = "Average rating of the API")

  public String getAvgRating() {
    return avgRating;
  }


  public void setAvgRating(String avgRating) {
    this.avgRating = avgRating;
  }


  public APIInfoDTO throttlingPolicies(List<String> throttlingPolicies) {
    
    this.throttlingPolicies = throttlingPolicies;
    return this;
  }

  public APIInfoDTO addThrottlingPoliciesItem(String throttlingPoliciesItem) {
    if (this.throttlingPolicies == null) {
      this.throttlingPolicies = new ArrayList<>();
    }
    this.throttlingPolicies.add(throttlingPoliciesItem);
    return this;
  }

   /**
   * List of throttling policies of the API
   * @return throttlingPolicies
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "[Unlimited, Bronze]", value = "List of throttling policies of the API")

  public List<String> getThrottlingPolicies() {
    return throttlingPolicies;
  }


  public void setThrottlingPolicies(List<String> throttlingPolicies) {
    this.throttlingPolicies = throttlingPolicies;
  }


  public APIInfoDTO advertiseInfo(AdvertiseInfoDTO advertiseInfo) {
    
    this.advertiseInfo = advertiseInfo;
    return this;
  }

   /**
   * Get advertiseInfo
   * @return advertiseInfo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public AdvertiseInfoDTO getAdvertiseInfo() {
    return advertiseInfo;
  }


  public void setAdvertiseInfo(AdvertiseInfoDTO advertiseInfo) {
    this.advertiseInfo = advertiseInfo;
  }


  public APIInfoDTO businessInformation(APIBusinessInformationDTO businessInformation) {
    
    this.businessInformation = businessInformation;
    return this;
  }

   /**
   * Get businessInformation
   * @return businessInformation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public APIBusinessInformationDTO getBusinessInformation() {
    return businessInformation;
  }


  public void setBusinessInformation(APIBusinessInformationDTO businessInformation) {
    this.businessInformation = businessInformation;
  }


  public APIInfoDTO isSubscriptionAvailable(Boolean isSubscriptionAvailable) {
    
    this.isSubscriptionAvailable = isSubscriptionAvailable;
    return this;
  }

   /**
   * Get isSubscriptionAvailable
   * @return isSubscriptionAvailable
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "false", value = "")

  public Boolean getIsSubscriptionAvailable() {
    return isSubscriptionAvailable;
  }


  public void setIsSubscriptionAvailable(Boolean isSubscriptionAvailable) {
    this.isSubscriptionAvailable = isSubscriptionAvailable;
  }


  public APIInfoDTO monetizationLabel(String monetizationLabel) {
    
    this.monetizationLabel = monetizationLabel;
    return this;
  }

   /**
   * Get monetizationLabel
   * @return monetizationLabel
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Free", value = "")

  public String getMonetizationLabel() {
    return monetizationLabel;
  }


  public void setMonetizationLabel(String monetizationLabel) {
    this.monetizationLabel = monetizationLabel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    APIInfoDTO apIInfo = (APIInfoDTO) o;
    return Objects.equals(this.id, apIInfo.id) &&
        Objects.equals(this.name, apIInfo.name) &&
        Objects.equals(this.description, apIInfo.description) &&
        Objects.equals(this.context, apIInfo.context) &&
        Objects.equals(this.version, apIInfo.version) &&
        Objects.equals(this.type, apIInfo.type) &&
        Objects.equals(this.provider, apIInfo.provider) &&
        Objects.equals(this.lifeCycleStatus, apIInfo.lifeCycleStatus) &&
        Objects.equals(this.thumbnailUri, apIInfo.thumbnailUri) &&
        Objects.equals(this.avgRating, apIInfo.avgRating) &&
        Objects.equals(this.throttlingPolicies, apIInfo.throttlingPolicies) &&
        Objects.equals(this.advertiseInfo, apIInfo.advertiseInfo) &&
        Objects.equals(this.businessInformation, apIInfo.businessInformation) &&
        Objects.equals(this.isSubscriptionAvailable, apIInfo.isSubscriptionAvailable) &&
        Objects.equals(this.monetizationLabel, apIInfo.monetizationLabel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, context, version, type, provider, lifeCycleStatus, thumbnailUri, avgRating, throttlingPolicies, advertiseInfo, businessInformation, isSubscriptionAvailable, monetizationLabel);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class APIInfoDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    context: ").append(toIndentedString(context)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    lifeCycleStatus: ").append(toIndentedString(lifeCycleStatus)).append("\n");
    sb.append("    thumbnailUri: ").append(toIndentedString(thumbnailUri)).append("\n");
    sb.append("    avgRating: ").append(toIndentedString(avgRating)).append("\n");
    sb.append("    throttlingPolicies: ").append(toIndentedString(throttlingPolicies)).append("\n");
    sb.append("    advertiseInfo: ").append(toIndentedString(advertiseInfo)).append("\n");
    sb.append("    businessInformation: ").append(toIndentedString(businessInformation)).append("\n");
    sb.append("    isSubscriptionAvailable: ").append(toIndentedString(isSubscriptionAvailable)).append("\n");
    sb.append("    monetizationLabel: ").append(toIndentedString(monetizationLabel)).append("\n");
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

