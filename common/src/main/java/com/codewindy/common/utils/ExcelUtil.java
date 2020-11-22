package com.codewindy.common.utils;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author codewindy
 * @date 2020-06-20 10:28 AM
 * @since 1.0.0
 */
@Slf4j
public class ExcelUtil {
    public static final String ATTACHMENT_PREFIX="attachment;filename=%s";
    public static final String EXCEL_TYPE="application/vnd.ms-excel;chatset=utf-8";

    public static ResponseEntity<byte[]> buildExcelFile(HttpServletResponse response, String sheetName, String filepath) throws UnsupportedEncodingException {
        HttpHeaders httpHeaders= new HttpHeaders();
        String format = ExcelUtil.ATTACHMENT_PREFIX + ExcelTypeEnum.XLS.getValue();
        String encodeDownloadFileName= null;
        URLEncoder.encode(sheetName, StandardCharsets.UTF_8.name());
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, String.format(format, encodeDownloadFileName));
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,ExcelUtil.EXCEL_TYPE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return new ResponseEntity<>(FileUtil.readBytes(filepath), httpHeaders, HttpStatus.OK);

    }
}