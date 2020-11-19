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

/**
 * KeyManagerApplicationConfigurationDTO
 */

public class KeyManagerApplicationConfigurationDTO {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_LABEL = "label";
  @SerializedName(SERIALIZED_NAME_LABEL)
  private String label;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;

  public static final String SERIALIZED_NAME_REQUIRED = "required";
  @SerializedName(SERIALIZED_NAME_REQUIRED)
  private Boolean required;

  public static final String SERIALIZED_NAME_MASK = "mask";
  @SerializedName(SERIALIZED_NAME_MASK)
  private Boolean mask;

  public static final String SERIALIZED_NAME_MULTIPLE = "multiple";
  @SerializedName(SERIALIZED_NAME_MULTIPLE)
  private Boolean multiple;

  public static final String SERIALIZED_NAME_TOOLTIP = "tooltip";
  @SerializedName(SERIALIZED_NAME_TOOLTIP)
  private String tooltip;

  public static final String SERIALIZED_NAME_DEFAULT = "default";
  @SerializedName(SERIALIZED_NAME_DEFAULT)
  private Object _default;

  public static final String SERIALIZED_NAME_VALUES = "values";
  @SerializedName(SERIALIZED_NAME_VALUES)
  private List<Object> values = null;


  public KeyManagerApplicationConfigurationDTO name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "consumer_key", value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public KeyManagerApplicationConfigurationDTO label(String label) {
    
    this.label = label;
    return this;
  }

   /**
   * Get label
   * @return label
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Consumer Key", value = "")

  public String getLabel() {
    return label;
  }


  public void setLabel(String label) {
    this.label = label;
  }


  public KeyManagerApplicationConfigurationDTO type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "select", value = "")

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public KeyManagerApplicationConfigurationDTO required(Boolean required) {
    
    this.required = required;
    return this;
  }

   /**
   * Get required
   * @return required
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "true", value = "")

  public Boolean getRequired() {
    return required;
  }


  public void setRequired(Boolean required) {
    this.required = required;
  }


  public KeyManagerApplicationConfigurationDTO mask(Boolean mask) {
    
    this.mask = mask;
    return this;
  }

   /**
   * Get mask
   * @return mask
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "true", value = "")

  public Boolean getMask() {
    return mask;
  }


  public void setMask(Boolean mask) {
    this.mask = mask;
  }


  public KeyManagerApplicationConfigurationDTO multiple(Boolean multiple) {
    
    this.multiple = multiple;
    return this;
  }

   /**
   * Get multiple
   * @return multiple
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "true", value = "")

  public Boolean getMultiple() {
    return multiple;
  }


  public void setMultiple(Boolean multiple) {
    this.multiple = multiple;
  }


  public KeyManagerApplicationConfigurationDTO tooltip(String tooltip) {
    
    this.tooltip = tooltip;
    return this;
  }

   /**
   * Get tooltip
   * @return tooltip
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Enter username to connect to key manager", value = "")

  public String getTooltip() {
    return tooltip;
  }


  public void setTooltip(String tooltip) {
    this.tooltip = tooltip;
  }


  public KeyManagerApplicationConfigurationDTO _default(Object _default) {
    
    this._default = _default;
    return this;
  }

   /**
   * Get _default
   * @return _default
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "\"admin\"", value = "")

  public Object getDefault() {
    return _default;
  }


  public void setDefault(Object _default) {
    this._default = _default;
  }


  public KeyManagerApplicationConfigurationDTO values(List<Object> values) {
    
    this.values = values;
    return this;
  }

  public KeyManagerApplicationConfigurationDTO addValuesItem(Object valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<>();
    }
    this.values.add(valuesItem);
    return this;
  }

   /**
   * Get values
   * @return values
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<Object> getValues() {
    return values;
  }


  public void setValues(List<Object> values) {
    this.values = values;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyManagerApplicationConfigurationDTO keyManagerApplicationConfiguration = (KeyManagerApplicationConfigurationDTO) o;
    return Objects.equals(this.name, keyManagerApplicationConfiguration.name) &&
        Objects.equals(this.label, keyManagerApplicationConfiguration.label) &&
        Objects.equals(this.type, keyManagerApplicationConfiguration.type) &&
        Objects.equals(this.required, keyManagerApplicationConfiguration.required) &&
        Objects.equals(this.mask, keyManagerApplicationConfiguration.mask) &&
        Objects.equals(this.multiple, keyManagerApplicationConfiguration.multiple) &&
        Objects.equals(this.tooltip, keyManagerApplicationConfiguration.tooltip) &&
        Objects.equals(this._default, keyManagerApplicationConfiguration._default) &&
        Objects.equals(this.values, keyManagerApplicationConfiguration.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, label, type, required, mask, multiple, tooltip, _default, values);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyManagerApplicationConfigurationDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("    mask: ").append(toIndentedString(mask)).append("\n");
    sb.append("    multiple: ").append(toIndentedString(multiple)).append("\n");
    sb.append("    tooltip: ").append(toIndentedString(tooltip)).append("\n");
    sb.append("    _default: ").append(toIndentedString(_default)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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

