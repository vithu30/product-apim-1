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


package org.wso2.am.integration.clients.store.api.v1;

import org.wso2.am.integration.clients.store.api.ApiCallback;
import org.wso2.am.integration.clients.store.api.ApiClient;
import org.wso2.am.integration.clients.store.api.ApiException;
import org.wso2.am.integration.clients.store.api.ApiResponse;
import org.wso2.am.integration.clients.store.api.Configuration;
import org.wso2.am.integration.clients.store.api.Pair;
import org.wso2.am.integration.clients.store.api.ProgressRequestBody;
import org.wso2.am.integration.clients.store.api.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.wso2.am.integration.clients.store.api.v1.dto.CommentDTO;
import org.wso2.am.integration.clients.store.api.v1.dto.CommentListDTO;
import org.wso2.am.integration.clients.store.api.v1.dto.ErrorDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsApi {
    private ApiClient localVarApiClient;

    public CommentsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CommentsApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for addCommentToAPI
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param body Comment object that should to be added  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body. Location header contains URL of newly created entity.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Location - Location to the newly created Comment.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to add the comment.  </td><td>  -  </td></tr>
        <tr><td> 415 </td><td> Unsupported media type. The entity of the request was in a not supported format.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call addCommentToAPICall(String apiId, CommentDTO body, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = body;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/comments"
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call addCommentToAPIValidateBeforeCall(String apiId, CommentDTO body, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling addCommentToAPI(Async)");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling addCommentToAPI(Async)");
        }
        

        okhttp3.Call localVarCall = addCommentToAPICall(apiId, body, _callback);
        return localVarCall;

    }

    /**
     * Add an API comment
     * 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param body Comment object that should to be added  (required)
     * @return CommentDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body. Location header contains URL of newly created entity.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Location - Location to the newly created Comment.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to add the comment.  </td><td>  -  </td></tr>
        <tr><td> 415 </td><td> Unsupported media type. The entity of the request was in a not supported format.  </td><td>  -  </td></tr>
     </table>
     */
    public CommentDTO addCommentToAPI(String apiId, CommentDTO body) throws ApiException {
        ApiResponse<CommentDTO> localVarResp = addCommentToAPIWithHttpInfo(apiId, body);
        return localVarResp.getData();
    }

    /**
     * Add an API comment
     * 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param body Comment object that should to be added  (required)
     * @return ApiResponse&lt;CommentDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body. Location header contains URL of newly created entity.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Location - Location to the newly created Comment.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to add the comment.  </td><td>  -  </td></tr>
        <tr><td> 415 </td><td> Unsupported media type. The entity of the request was in a not supported format.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CommentDTO> addCommentToAPIWithHttpInfo(String apiId, CommentDTO body) throws ApiException {
        okhttp3.Call localVarCall = addCommentToAPIValidateBeforeCall(apiId, body, null);
        Type localVarReturnType = new TypeToken<CommentDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Add an API comment (asynchronously)
     * 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param body Comment object that should to be added  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body. Location header contains URL of newly created entity.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Location - Location to the newly created Comment.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to add the comment.  </td><td>  -  </td></tr>
        <tr><td> 415 </td><td> Unsupported media type. The entity of the request was in a not supported format.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call addCommentToAPIAsync(String apiId, CommentDTO body, final ApiCallback<CommentDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = addCommentToAPIValidateBeforeCall(apiId, body, _callback);
        Type localVarReturnType = new TypeToken<CommentDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteComment
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to delete the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Resource to be deleted does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteCommentCall(String commentId, String apiId, String ifMatch, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/comments/{commentId}"
            .replaceAll("\\{" + "commentId" + "\\}", localVarApiClient.escapeString(commentId.toString()))
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (ifMatch != null) {
            localVarHeaderParams.put("If-Match", localVarApiClient.parameterToString(ifMatch));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteCommentValidateBeforeCall(String commentId, String apiId, String ifMatch, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new ApiException("Missing the required parameter 'commentId' when calling deleteComment(Async)");
        }
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling deleteComment(Async)");
        }
        

        okhttp3.Call localVarCall = deleteCommentCall(commentId, apiId, ifMatch, _callback);
        return localVarCall;

    }

    /**
     * Delete an API comment
     * Remove a Comment 
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to delete the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Resource to be deleted does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public void deleteComment(String commentId, String apiId, String ifMatch) throws ApiException {
        deleteCommentWithHttpInfo(commentId, apiId, ifMatch);
    }

    /**
     * Delete an API comment
     * Remove a Comment 
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to delete the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Resource to be deleted does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteCommentWithHttpInfo(String commentId, String apiId, String ifMatch) throws ApiException {
        okhttp3.Call localVarCall = deleteCommentValidateBeforeCall(commentId, apiId, ifMatch, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete an API comment (asynchronously)
     * Remove a Comment 
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to delete the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Resource to be deleted does not exist.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteCommentAsync(String commentId, String apiId, String ifMatch, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteCommentValidateBeforeCall(commentId, apiId, ifMatch, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAllCommentsOfAPI
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comments list is returned.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comments.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAllCommentsOfAPICall(String apiId, String xWSO2Tenant, Integer limit, Integer offset, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/comments"
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xWSO2Tenant != null) {
            localVarHeaderParams.put("X-WSO2-Tenant", localVarApiClient.parameterToString(xWSO2Tenant));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getAllCommentsOfAPIValidateBeforeCall(String apiId, String xWSO2Tenant, Integer limit, Integer offset, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling getAllCommentsOfAPI(Async)");
        }
        

        okhttp3.Call localVarCall = getAllCommentsOfAPICall(apiId, xWSO2Tenant, limit, offset, _callback);
        return localVarCall;

    }

    /**
     * Retrieve API comments
     * Get a list of Comments that are already added to APIs 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @return CommentListDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comments list is returned.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comments.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public CommentListDTO getAllCommentsOfAPI(String apiId, String xWSO2Tenant, Integer limit, Integer offset) throws ApiException {
        ApiResponse<CommentListDTO> localVarResp = getAllCommentsOfAPIWithHttpInfo(apiId, xWSO2Tenant, limit, offset);
        return localVarResp.getData();
    }

    /**
     * Retrieve API comments
     * Get a list of Comments that are already added to APIs 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @return ApiResponse&lt;CommentListDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comments list is returned.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comments.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CommentListDTO> getAllCommentsOfAPIWithHttpInfo(String apiId, String xWSO2Tenant, Integer limit, Integer offset) throws ApiException {
        okhttp3.Call localVarCall = getAllCommentsOfAPIValidateBeforeCall(apiId, xWSO2Tenant, limit, offset, null);
        Type localVarReturnType = new TypeToken<CommentListDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve API comments (asynchronously)
     * Get a list of Comments that are already added to APIs 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comments list is returned.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comments.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAllCommentsOfAPIAsync(String apiId, String xWSO2Tenant, Integer limit, Integer offset, final ApiCallback<CommentListDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllCommentsOfAPIValidateBeforeCall(apiId, xWSO2Tenant, limit, offset, _callback);
        Type localVarReturnType = new TypeToken<CommentListDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getCommentOfAPI
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comment returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested comment does not exist.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getCommentOfAPICall(String commentId, String apiId, String xWSO2Tenant, String ifNoneMatch, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/comments/{commentId}"
            .replaceAll("\\{" + "commentId" + "\\}", localVarApiClient.escapeString(commentId.toString()))
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xWSO2Tenant != null) {
            localVarHeaderParams.put("X-WSO2-Tenant", localVarApiClient.parameterToString(xWSO2Tenant));
        }

        if (ifNoneMatch != null) {
            localVarHeaderParams.put("If-None-Match", localVarApiClient.parameterToString(ifNoneMatch));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getCommentOfAPIValidateBeforeCall(String commentId, String apiId, String xWSO2Tenant, String ifNoneMatch, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new ApiException("Missing the required parameter 'commentId' when calling getCommentOfAPI(Async)");
        }
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling getCommentOfAPI(Async)");
        }
        

        okhttp3.Call localVarCall = getCommentOfAPICall(commentId, apiId, xWSO2Tenant, ifNoneMatch, _callback);
        return localVarCall;

    }

    /**
     * Get details of an API comment
     * Get the individual comment given by a username for a certain API. 
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @return CommentDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comment returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested comment does not exist.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public CommentDTO getCommentOfAPI(String commentId, String apiId, String xWSO2Tenant, String ifNoneMatch) throws ApiException {
        ApiResponse<CommentDTO> localVarResp = getCommentOfAPIWithHttpInfo(commentId, apiId, xWSO2Tenant, ifNoneMatch);
        return localVarResp.getData();
    }

    /**
     * Get details of an API comment
     * Get the individual comment given by a username for a certain API. 
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @return ApiResponse&lt;CommentDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comment returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested comment does not exist.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CommentDTO> getCommentOfAPIWithHttpInfo(String commentId, String apiId, String xWSO2Tenant, String ifNoneMatch) throws ApiException {
        okhttp3.Call localVarCall = getCommentOfAPIValidateBeforeCall(commentId, apiId, xWSO2Tenant, ifNoneMatch, null);
        Type localVarReturnType = new TypeToken<CommentDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get details of an API comment (asynchronously)
     * Get the individual comment given by a username for a certain API. 
     * @param commentId Comment Id  (required)
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param xWSO2Tenant For cross-tenant invocations, this is used to specify the tenant domain, where the resource need to be   retirieved from.  (optional)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Comment returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. The user is not authorized to view the comment.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested comment does not exist.  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getCommentOfAPIAsync(String commentId, String apiId, String xWSO2Tenant, String ifNoneMatch, final ApiCallback<CommentDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = getCommentOfAPIValidateBeforeCall(commentId, apiId, xWSO2Tenant, ifNoneMatch, _callback);
        Type localVarReturnType = new TypeToken<CommentDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
