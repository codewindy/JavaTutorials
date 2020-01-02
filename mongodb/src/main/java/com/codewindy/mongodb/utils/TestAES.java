package com.codewindy.mongodb.utils;

import java.net.URLDecoder;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * aes测试
 * <p>
 * Created on 2017/8/23.
 * </p>
 **/
public class TestAES {

	public static void main(String args[]) throws Exception {

		Map<String, String> params = RSAUtils.generateKeyPair();
		String content = "{\n" + "    \"org_name\": \"XXX\",\n" + "    \"batch_no\": \"PNO201609091538356723789\",\n"
				+ "    \"super_borrower_name\": \"张珊\",\n" + "    \"super_borrower_cert_type\": 1,\n"
				+ "    \"super_borrower_cert_id\": \"441900199111251234\",\n" + "    \"debtList\": [\n" + "        {\n"
				+ "            \"phone\": \"15625863060\",\n" + "            \"education\": \"本科\",\n"
				+ "            \"nation\": \"汉族\",\n" + "            \"relationship\": [\n" + "                {\n"
				+ "                    \"relationship_name\": \"陈xx\",\n"
				+ "                    \"relationship_type\": \"父子\",\n"
				+ "                    \"relationship_phone\": \"15888888888\"\n" + "                },\n"
				+ "                {\n" + "                    \"relationship_name\": \"xxx\",\n"
				+ "                    \"relationship_type\": \"xx\",\n"
				+ "                    \"relationship_phone\": \"15999999999\"\n" + "                }\n"
				+ "            ],\n" + "            \"salary\": 1000000,\n" + "            \"position\": \"xx\",\n"
				+ "            \"register_phone\": \"15625863060\",\n" + "            \"register_name\": \"陈yy\",\n"
				+ "            \"user_channel\": 1,\n" + "            \"register_cert_type\": 1,\n"
				+ "            \"register_cert_number\": \"441900199111251234\",\n"
				+ "            \"receive_open\": \"招商银行\",\n" + "            \"receive_branch\": \"深圳支行\",\n"
				+ "            \"bind_bank_number\": \"CMB\",\n"
				+ "            \"bind_bank_card\": \"6121214124114141441\",\n"
				+ "            \"bind_phone\": \"15625863060\",\n" + "            \"bind_card_province\": \"广东\",\n"
				+ "            \"bind_card_city\": \"深圳\",\n" + "            \"borrow_type\": 1,\n"
				+ "            \"borrow_contract_amount\": 100000,\n" + "            \"loan_time_type\": 1,\n"
				+ "            \"loan_time_count\": 20,\n" + "            \"borrow_payment_mode\": 1,\n"
				+ "            \"product_type\": \"贷款产品类型\",\n" + "            \"contract_code\": \"as1234162\",\n"
				+ "            \"contract_amount\": 100000,\n" + "            \"deposit_amount\": 100000,\n"
				+ "            \"request_time_count\": 1,\n" + "            \"loan_purpose\": \"买手机\",\n"
				+ "            \"borrower_name\": \"陈yy\",\n" + "            \"cert_id\": \"441900199111251234\",\n"
				+ "            \"cert_type\": 1,\n" + "            \"borrower_address\": \"广东省深圳市南山区xxx\",\n"
				+ "            \"borrower_sex\": 1,\n" + "            \"is_married\": 2,\n"
				+ "            \" parter_id\": 342341,\n" + "            \"annual_income\": 1000000,\n"
				+ "            \"car_asset\": \"没有车\",\n" + "            \"local_house_asset\": \"没有房\",\n"
				+ "            \"house_situation\": \"与父母同住\",\n" + "            \"house_time\": 25,\n"
				+ "            \"child_situation\": \"没有子女\",\n" + "            \"annual_rate\": 0.0863,\n"
				+ "            \"customer_level\": 3,\n" + "            \"customer_channel\": \"xx\",\n"
				+ "            \"payment_mode\": 1,\n" + "            \"work_unit\": \"随手科技\",\n"
				+ "            \"work_time\": \"1\",\n" + "            \"work_sum_time\": \"2\",\n"
				+ "            \"gjj_radix\": 0.08,\n" + "            \"gjj_payment_term\": 8,\n"
				+ "            \"gjj_payment_radio\": 0.08,\n" + "            \"is_first_request\": 1,\n"
				+ "            \"prior_loan_status\": \"优秀\",\n" + "            \"history_paid_count\": 1,\n"
				+ "            \"bank_open_name\": \"陈xx\",\n"
				+ "            \"receive_bank_card\": \"6121214124114141441\",\n"
				+ "            \"receive_bank_name\": \"招商银行\",\n" + "            \"receive_country\": \"深圳\"\n"
				+ "        },\n" + "        {\n" + "            \"phone\": \"15625863060\",\n"
				+ "            \"education\": \"本科\",\n" + "            \"nation\": \"汉族\",\n"
				+ "            \"relationship\": [\n" + "                {\n"
				+ "                    \"relationship_name\": \"陈xx\",\n"
				+ "                    \"relationship_type\": \"父子\",\n"
				+ "                    \"relationship_phone\": \"15888888888\"\n" + "                },\n"
				+ "                {\n" + "                    \"relationship_name\": \"xxx\",\n"
				+ "                    \"relationship_type\": \"xx\",\n"
				+ "                    \"relationship_phone\": \"15999999999\"\n" + "                }\n"
				+ "            ],\n" + "            \"salary\": 1000000,\n" + "            \"position\": \"xx\",\n"
				+ "            \"register_phone\": \"15625863060\",\n" + "            \"register_name\": \"陈yy\",\n"
				+ "            \"user_channel\": 1,\n" + "            \"register_cert_type\": 1,\n"
				+ "            \"register_cert_number\": \"441900199111251234\",\n"
				+ "            \"receive_open\": \"招商银行\",\n" + "            \"receive_branch\": \"深圳支行\",\n"
				+ "            \"bind_bank_number\": \"CMB\",\n"
				+ "            \"bind_bank_card\": \"6121214124114141441\",\n"
				+ "            \"bind_phone\": \"15625863060\",\n" + "            \"bind_card_province\": \"广东\",\n"
				+ "            \"bind_card_city\": \"深圳\",\n" + "            \"borrow_type\": 1,\n"
				+ "            \"borrow_contract_amount\": 100000,\n" + "            \"loan_time_type\": 1,\n"
				+ "            \"loan_time_count\": 20,\n" + "            \"borrow_payment_mode\": 1,\n"
				+ "            \"product_type\": \"贷款产品类型\",\n" + "            \"contract_code\": \"as1234161\",\n"
				+ "            \"contract_amount\": 100000,\n" + "            \"deposit_amount\": 100000,\n"
				+ "            \"request_time_count\": 1,\n" + "            \"loan_purpose\": \"买手机\",\n"
				+ "            \"borrower_name\": \"陈yy\",\n" + "            \"cert_id\": \"441900199111251234\",\n"
				+ "            \"cert_type\": 1,\n" + "            \"borrower_address\": \"广东省深圳市南山区xxx\",\n"
				+ "            \"borrower_sex\": 1,\n" + "            \"is_married\": 2,\n"
				+ "            \"iqianjin_id\": 342341,\n" + "            \"annual_income\": 1000000,\n"
				+ "            \"car_asset\": \"没有车\",\n" + "            \"local_house_asset\": \"没有房\",\n"
				+ "            \"house_situation\": \"与父母同住\",\n" + "            \"house_time\": 25,\n"
				+ "            \"child_situation\": \"没有子女\",\n" + "            \"annual_rate\": 0.0863,\n"
				+ "            \"customer_level\": 3,\n" + "            \"customer_channel\": \"xx\",\n"
				+ "            \"payment_mode\": 1,\n" + "            \"work_unit\": \"随手科技\",\n"
				+ "            \"work_time\": \"1\",\n" + "            \"work_sum_time\": \"2\",\n"
				+ "            \"gjj_radix\": 0.08,\n" + "            \"gjj_payment_term\": 8,\n"
				+ "            \"gjj_payment_radio\": 0.08,\n" + "            \"is_first_request\": 1,\n"
				+ "            \"prior_loan_status\": \"优秀\",\n" + "            \"history_paid_count\": 1,\n"
				+ "            \"bank_open_name\": \"陈xx\",\n"
				+ "            \"receive_bank_card\": \"6121214124114141441\",\n"
				+ "            \"receive_bank_name\": \"招商银行\",\n" + "            \"receive_country\": \"深圳\"\n"
				+ "        }\n" + "    ]\n" + "}";

		String modulus = params.get("modulus");
		System.out.println("公钥:" + params.get("publicKey"));
		System.out.println("私钥:" + params.get("privateKey"));
		System.out.println("modulus:" + modulus);

		// resultKey进行RSA加密
//		String key = "3799578391123917";
		String key = RandomStringUtils.randomNumeric(16);
		System.out.println("明文key：" + key);
		String encryptkey = RSAUtils.encrypt(key, params.get("publicKey"));
		System.out.println("加密后key：" + encryptkey);

		String contentAes = AESUtils.encrypt(content, key);
		System.out.println("加密后的内容：" + contentAes);
		System.out.println("解密-----------------------------------------------------");

		String dekey = RSAUtils.decrypt(encryptkey, params.get("privateKey"));
		System.out.println("解密后的key：" + dekey);

		String decontent = AESUtils.decrypt(contentAes, dekey);

		System.out.println("解密后的内容：" + URLDecoder.decode(decontent, "UTF-8"));

		// 签名信息
		// String signString =
		// MySecurityMD5Utils.md5Hex(URLEncoder.encode(resultContentStr, "UTF-8"));
		// System.out.println("签名信息:"+signString);
	}
}
