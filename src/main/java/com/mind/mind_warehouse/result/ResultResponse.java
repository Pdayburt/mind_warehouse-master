package com.mind.mind_warehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResultResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;
    private String token;

    public ResultResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
