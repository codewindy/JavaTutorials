package com.codewindy.mongodb.pojo;

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
public class ApiResult implements Serializable {
    private static final long serialVersionUID = -8134781164681812987L;
    private String code;
    private String message;
    private Object data;

    public ApiResult() {
        this.message = ErrorEnum.SUCCESS.getValue();
        this.code = ErrorEnum.SUCCESS.getKey();
        this.data = null;
    }

    public ApiResult(Object data) {
        this.message = ErrorEnum.SUCCESS.getValue();
        this.code = ErrorEnum.SUCCESS.getKey();
        this.data = data;
    }

    public ApiResult(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
