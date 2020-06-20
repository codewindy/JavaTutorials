package com.codewindy.mongodb.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author codewindy
 * @date 2020-06-20 9:48 AM
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends BaseRowModel {

    @ExcelProperty(value = "用户id", index = 0)
    private String id;
    @ExcelProperty(value = "用户名", index = 1)
    private String name;
    @ExcelProperty(value = "年龄", index = 2)
    private int age;
    @ExcelProperty(value = "性别", index = 3)
    private String sex;
    @ExcelProperty(value = "详情", index = 4)
    private String desc;
}