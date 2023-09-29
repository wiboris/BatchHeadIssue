// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.azure.compute.batch;

import com.azure.compute.batch.implementation.BatchClientImpl;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.DateTimeRfc1123;
import java.time.OffsetDateTime;

/** Initializes a new instance of the synchronous BatchClient type. */
@ServiceClient(builder = BatchClientBuilder.class)
public final class BatchClient {
    @Generated private final BatchClientImpl serviceClient;

    /**
     * Initializes an instance of BatchClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    BatchClient(BatchClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Gets basic properties of a Pool.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>timeOut</td><td>Integer</td><td>No</td><td>The maximum number of items to return in the response. A maximum of 1000
     * applications can be returned.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>ocp-date</td><td>OffsetDateTime</td><td>No</td><td>The time the request was issued. Client libraries typically set this to the
     * current system clock time; set it explicitly if you are calling the REST API
     * directly.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param poolId The ID of the Pool to get.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return basic properties of a Pool along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> poolExistsWithResponse(String poolId, RequestOptions requestOptions) {
        return this.serviceClient.poolExistsWithResponse(poolId, requestOptions);
    }

    /**
     * Gets basic properties of a Pool.
     *
     * @param poolId The ID of the Pool to get.
     * @param timeOutInSeconds The maximum number of items to return in the response. A maximum of 1000 applications can
     *     be returned.
     * @param ocpDate The time the request was issued. Client libraries typically set this to the current system clock
     *     time; set it explicitly if you are calling the REST API directly.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return basic properties of a Pool.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean poolExists(String poolId, Integer timeOutInSeconds, OffsetDateTime ocpDate) {
        // Generated convenience method for poolExistsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (timeOutInSeconds != null) {
            requestOptions.addQueryParam("timeOut", String.valueOf(timeOutInSeconds), false);
        }
        if (ocpDate != null) {
            requestOptions.setHeader(
                    HttpHeaderName.fromString("ocp-date"), String.valueOf(new DateTimeRfc1123(ocpDate)));
        }
        return poolExistsWithResponse(poolId, requestOptions).getValue();
    }

    /**
     * Gets basic properties of a Pool.
     *
     * @param poolId The ID of the Pool to get.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return basic properties of a Pool.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean poolExists(String poolId) {
        // Generated convenience method for poolExistsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return poolExistsWithResponse(poolId, requestOptions).getValue();
    }
}
