package com.simulator.globalModels;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

@RequiredArgsConstructor
//@AllArgsConstructor
public class ResponseApiJson<T> {
    private String idRequest;
    private String RespCode;
    private String RespMsg;
    private T Result;

    public ResponseApiJson(String idRequest, String RespCode, String RespMsg) {
        this.idRequest = idRequest;
        this.RespCode = RespCode;
        this.RespMsg = RespMsg;
    }

    public ResponseApiJson(String idRequest, String RespCode, String RespMsg,T result) {
        this.idRequest = idRequest;
        this.RespCode = RespCode;
        this.RespMsg = RespMsg;
        this.Result = result;
    }
}