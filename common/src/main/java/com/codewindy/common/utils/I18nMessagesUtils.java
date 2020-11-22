package com.codewindy.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author jkwindy@126.com
 * @date 2019-05-28 18:45
 */
@Component
public class I18nMessagesUtils {

    @Autowired
    private  MessageSource messageSource;

    /**
     * 获取单个国际化翻译值
     */
    public  String getMessage(String msgKey) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            locale.setDefault(Locale.US);
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }
}