package com.codewindy.common.utils;

import java.util.Date;
import java.util.UUID;

public class UUIDUtils {
	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 *  <p>并发在2000左右</p>
	 * Java 使用 UUID 生成唯一不重复的订单编号的方法
	 * @return
	 */
	public static String createOrderId() {  
	    int machineId = 1;//最大支持1-9个集群机器部署  
	    int hashCodeV = UUID.randomUUID().toString().hashCode();  
	    if(hashCodeV < 0) {//有可能是负数  
	        hashCodeV = - hashCodeV;  
	    }  
	    // 0 代表前面补充0       
	    // 4 代表长度为4       
	    // d 代表参数为正数型  
	    return machineId+String.format("%015d", hashCodeV)+new Date().getTime();  
	}
	public static void main(String[] args) {
		System.err.println(getId());
		System.err.println(createOrderId());
	}
}
