# pylint: disable=too-many-lines
# coding=utf-8
# --------------------------------------------------------------------------
# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License. See License.txt in the project root for license information.
# Code generated by Microsoft (R) Python Code Generator.
# Changes may cause incorrect behavior and will be lost if the code is regenerated.
# --------------------------------------------------------------------------
import datetime
from typing import Any, Callable, Dict, Optional, TypeVar

from azure.core.exceptions import (
    ClientAuthenticationError,
    HttpResponseError,
    ResourceExistsError,
    ResourceNotFoundError,
    ResourceNotModifiedError,
    map_error,
)
from azure.core.pipeline import PipelineResponse
from azure.core.rest import HttpRequest, HttpResponse
from azure.core.tracing.decorator import distributed_trace
from azure.core.utils import case_insensitive_dict

from .. import models as _models
from .._model_base import _deserialize
from .._serialization import Serializer
from .._vendor import BatchClientMixinABC

T = TypeVar("T")
ClsType = Optional[Callable[[PipelineResponse[HttpRequest, HttpResponse], T, Dict[str, Any]], Any]]

_SERIALIZER = Serializer()
_SERIALIZER.client_side_validation = False


def build_batch_pool_exists_request(
    pool_id: str,
    *,
    time_out_in_seconds: Optional[int] = None,
    ocp_date: Optional[datetime.datetime] = None,
    **kwargs: Any
) -> HttpRequest:
    _headers = case_insensitive_dict(kwargs.pop("headers", {}) or {})
    _params = case_insensitive_dict(kwargs.pop("params", {}) or {})

    api_version: str = kwargs.pop("api_version", _params.pop("api-version", "2023-05-01.17.0"))
    # Construct URL
    _url = "/pools/{poolId}"
    path_format_arguments = {
        "poolId": _SERIALIZER.url("pool_id", pool_id, "str"),
    }

    _url: str = _url.format(**path_format_arguments)  # type: ignore

    # Construct parameters
    _params["api-version"] = _SERIALIZER.query("api_version", api_version, "str")
    if time_out_in_seconds is not None:
        _params["timeOut"] = _SERIALIZER.query("time_out_in_seconds", time_out_in_seconds, "int")

    # Construct headers
    if ocp_date is not None:
        _headers["ocp-date"] = _SERIALIZER.header("ocp_date", ocp_date, "iso-8601")

    return HttpRequest(method="HEAD", url=_url, params=_params, headers=_headers, **kwargs)


class BatchClientOperationsMixin(BatchClientMixinABC):
    @distributed_trace
    def pool_exists(
        self,
        pool_id: str,
        *,
        time_out_in_seconds: Optional[int] = None,
        ocp_date: Optional[datetime.datetime] = None,
        **kwargs: Any
    ) -> bool:
        """Gets basic properties of a Pool.

        :param pool_id: The ID of the Pool to get. Required.
        :type pool_id: str
        :keyword time_out_in_seconds: The maximum number of items to return in the response. A maximum
         of 1000
         applications can be returned. Default value is None.
        :paramtype time_out_in_seconds: int
        :keyword ocp_date: The time the request was issued. Client libraries typically set this to the
         current system clock time; set it explicitly if you are calling the REST API
         directly. Default value is None.
        :paramtype ocp_date: ~datetime.datetime
        :keyword bool stream: Whether to stream the response of this operation. Defaults to False. You
         will have to context manage the returned stream.
        :return: bool
        :rtype: bool
        :raises ~azure.core.exceptions.HttpResponseError:
        """
        error_map = {
            401: ClientAuthenticationError,
            404: ResourceNotFoundError,
            409: ResourceExistsError,
            304: ResourceNotModifiedError,
        }
        error_map.update(kwargs.pop("error_map", {}) or {})

        _headers = kwargs.pop("headers", {}) or {}
        _params = kwargs.pop("params", {}) or {}

        cls: ClsType[None] = kwargs.pop("cls", None)

        request = build_batch_pool_exists_request(
            pool_id=pool_id,
            time_out_in_seconds=time_out_in_seconds,
            ocp_date=ocp_date,
            api_version=self._config.api_version,
            headers=_headers,
            params=_params,
        )
        request.url = self._client.format_url(request.url)

        _stream = kwargs.pop("stream", False)
        pipeline_response: PipelineResponse = self._client._pipeline.run(  # pylint: disable=protected-access
            request, stream=_stream, **kwargs
        )

        response = pipeline_response.http_response

        if response.status_code not in [200, 404]:
            if _stream:
                response.read()  # Load the body in memory and close the socket
            map_error(status_code=response.status_code, response=response, error_map=error_map)
            error = _deserialize(_models.BatchError, response.json())
            raise HttpResponseError(response=response, model=error)

        response_headers = {}
        if response.status_code == 200:
            response_headers["client-request-id"] = self._deserialize("str", response.headers.get("client-request-id"))
            response_headers["request-id"] = self._deserialize("str", response.headers.get("request-id"))
            response_headers["etag"] = self._deserialize("str", response.headers.get("etag"))
            response_headers["last-modified"] = self._deserialize("iso-8601", response.headers.get("last-modified"))

        if cls:
            return cls(pipeline_response, None, response_headers)
        return 200 <= response.status_code <= 299
