/**
 * 文件名    :IdentifyHostNameIP.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月15日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter31;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author CycloneBoy
 *
 */
public class IdentifyHostNameIP {

	/**
	 * 
	 */
	public IdentifyHostNameIP() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("begin");
		for (int i = 0; i < args.length; i++) {
			try {
				System.out.println("args[" + i + "]: " + args[i]);
				InetAddress address = InetAddress.getByName(args[i]);
				System.out.print("Host name:" + address.getHostName() + " ");
				System.out.println("IP address:" + address.getHostAddress());
			} catch (UnknownHostException e) {
				System.err.println("Unknown host or IP address " + args[i]);
			}
		}
		System.out.println("end");

	}

}
