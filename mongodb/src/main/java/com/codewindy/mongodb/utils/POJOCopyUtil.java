package com.codewindy.mongodb.utils;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * PO和DTO之间相互转换用工具类
 * <p>
 * Created  on 2017/3/16 20:43
 * </p>
 */
public class POJOCopyUtil {
    private static Logger logger = LoggerFactory.getLogger(POJOCopyUtil.class);

    private POJOCopyUtil() {

    }
    
    /**
     * 
     * @param list 返回的list
     * @param targetClass  目标类文件
     * @return
     */
    public static <T> List<T> copyList(List<?> list, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for (Object obj : list) {
            T target = copyObject(obj, targetClass);

            if (target != null) {
                targetList.add(target);
            }
        }
        return targetList;
    }

    public static <T> T copyObject(Object obj, Class<T> targetClass) {
        if (obj == null) {
            return null;
        }

        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException e) {
            logger.error("POJO相互转换过程出现无法实例化对象：Source=" + obj.getClass().getName() + ", Target=" + targetClass.getName(),
                    e);
        } catch (IllegalAccessException e) {
            logger.error("POJO相互转换过程出现非法访问：Source=" + obj.getClass().getName() + ", Target=" + targetClass.getName(),
                    e);
        }
        if (target != null) {
            BeanUtils.copyProperties(obj, target);
        }

        return target;
    }
}
