package com.codewindy.mongodb.controller;/*
package com.jkwindy.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jkwindy.demo.pojo.ApiResponseJson;
import com.jkwindy.demo.pojo.User;
import com.jkwindy.demo.service.UserService;
import com.jkwindy.demo.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
//import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;

@RestController
@Api(value = "用户服务入口", tags = "用户服务入口")
@Slf4j // using need install plugins
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedissonClient redissonClient;

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", response = ApiResponseJson.class)
    @GetMapping(value = "/getUser/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @ApiOperation(value = "查询用户列表", notes = "查询用户列表", response = ApiResponseJson.class)
    @GetMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseJson getList() {
        ApiResponseJson apiJson = new ApiResponseJson();
        ArrayList<User> list = new ArrayList<User>();
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        //user.setName("jack");
        user.setSex("male");
        user.setAge(25);
        user.setBirthday(new Date());
        user.setBirthdayStr(new Date().getTime() + "");
        User user2 = new User();
        user2.setId(UUID.randomUUID().toString().replace("-", ""));
        user2.setSex("female");
        user2.setName("rose");
        user2.setAge(22);
        user2.setBirthday(new Date());
        user2.setBirthdayStr(new Date().getTime() + "");
        logger.info("user2==={}", user2);

        //RBucket<Object> rBucket = redissonClient.getBucket("foo");
        //rBucket.set("test_using_redisson");
        list.add(user);
        list.add(user2);
        boolean validate = false;
        for (User u : list) {
            validate = ValidatorUtils.validate(u);

        }

        logger.info("======================validate=================={}" + validate);
        if (validate) {
            JSONArray ja = new JSONArray();
            ja.add(list);
            JSONObject param = new JSONObject();


            param.put("data", ja);
            apiJson.setData(param);
        }
        apiJson.setError("校验失败");
        return apiJson;
    }

    */
/**
     * 保存接口做参数校验
     *
     * @param user
     * @param bindingResult
     * @return
     *//*

    @ApiOperation(value = "保存用户信息", notes = "保存用户信息", response = ApiResponseJson.class)
    @PostMapping(value = "/saveUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseJson saveUser(@Validated @RequestBody User user, BindingResult bindingResult) {
        ApiResponseJson apiResponseJson = new ApiResponseJson();
        ArrayList<Object> errList = Lists.newArrayList();
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError ae : allErrors) {
                errList.add(ae.getDefaultMessage());
            }
        }
        if (CollectionUtils.isNotEmpty(errList)) {
            apiResponseJson.setData(errList);
            apiResponseJson.setErrorCode(400);
            return apiResponseJson;
        }
        logger.info("user==={}", user);
        if (!StringUtils.isNotBlank(user.getBirthdayStr())) {
            DateTime beginOfDay = DateUtil.beginOfDay(new Date());
            DateTime endOfDay = DateUtil.endOfDay(new Date());
            DateTime parse = DateUtil.parse(beginOfDay.toDateStr());
            user.setBirthdayStr(parse.getTime() + "");
        }
        userService.saveUser(user);
        return apiResponseJson;
    }

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "<h2>jkwindy index page using springboot 2.0.4.RELEASE</h2>";
    }

    @GetMapping("/sendEmail")
    public ApiResponseJson sendEmail() throws MessagingException {


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("jkwindy@126.com");

        helper.setTo("bravohex@gmail.com");

        helper.setSubject("Subject：send attachment email to jkwindy");

        helper.setText("test email send to jkwindy。");


        FileSystemResource file1 = new FileSystemResource(new File("F:\\resumev0.1\\周鹏_resumeV0.9.pdf"));

        helper.addAttachment("附件-resume.pdf", file1);

        try {

            javaMailSender.send(mimeMessage);
            //发送成功记录到表中  发送失败三次后重试
            logger.info("附件的邮件已发送。");

        } catch (Exception e) {

            logger.error("带附件邮件时发生异常了！", e);

        }
        return new ApiResponseJson();
    }
}
*/
