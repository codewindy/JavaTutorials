package com.codewindy.common.vo;

import lombok.Data;

/**
 * @author codewindy
 * @date 2020-11-14 10:39 AM
 * @since 1.0.0
 */
@Data
public class WebLogVO {

    private String method;


    private Object params;

    private String desc;

}