package com.codewindy.mongodb.pojo;

import java.util.Date;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 实体类User
 * 
 * @author yui
 *
 */
@Document(collection = "T_User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String id;
	@NotBlank(message="用户名不能为空")
	private String name;
    @NotBlank(message="密码不能为空")
    @Size(min = 6,max = 8,message = "密码长度必须介于6位到8位之间")
	private String password;
	private int age;
	private String sex;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String birthdayStr;
	private boolean afterAdult;
	private String desc;

	@NotBlank(message = "邮箱不能为空")
    @Email
	private String email;


	@Override
	public String toString() {
		/*
		 * return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex
		 * + ", birthday=" + birthday + ", birthdayStr=" + birthdayStr + "]";
		 */
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public static void main(String[] args) {
		User user = new User();
		user.setAge(12);
		user.setId(IdUtil.fastSimpleUUID());
		user.setBirthday(new Date());
		user.setName("jkwindy");
		user.setSex("F");
		user.setAfterAdult(Boolean.TRUE);
		user.setDesc("jkwindy demo project");
		user.setPassword("1234567");
		user.setEmail("jkwindy@126.com");
		String json = JSONObject.toJSONString(user);
		System.out.println("json = " + json);
	}
}
