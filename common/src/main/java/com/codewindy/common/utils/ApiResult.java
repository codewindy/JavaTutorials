package com.codewindy.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * api 通用返回json格式数据
 *
 * @author yui
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -8134781164681812987L;
    private String code;
    private String message;
    private T data;

    public ApiResult() {
        this.message = ErrorEnum.SUCCESS.getValue();
        this.code = ErrorEnum.SUCCESS.getKey();
        this.data = null;
    }

    public ApiResult(T data) {
        this.message = ErrorEnum.SUCCESS.getValue();
        this.code = ErrorEnum.SUCCESS.getKey();
        this.data = data;
    }

    public ApiResult(String code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ApiResult<T> error(T data) {
        this.code = ErrorEnum.ERROR.getKey();;
        this.message = ErrorEnum.ERROR.getValue();
        this.data = data;
        return this;
    }
    public ApiResult<T> success(T data) {
        this.code = ErrorEnum.SUCCESS.getKey();;
        this.message = ErrorEnum.SUCCESS.getValue();
        this.data = data;
        return this;
    }
}
