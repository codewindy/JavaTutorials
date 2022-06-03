package com.codewindy.h2;

import cn.hutool.core.lang.Assert;
import com.codewindy.h2.controller.SeigeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author codewindy
 * @date 2022-06-03 16:31
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2Application.class)
public class Test1 {

    @Autowired
    SeigeController seigeController;

    @Test
    public void testgetInfo(){

        String info = seigeController.getInfo();
        Assert.isNull(info);
    }
}