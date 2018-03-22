package cn.tarena.ht.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
//用来执行安全算法的工具类
public class Encrypt{

	public static String getMd5(String password,String username){
		
	return new Md5Hash(password,username,3).toString();	
		
	}
	/**
	 * md5hash算法是在md5的基础上改进的，安全性更加高级
	 * 参数的含义：
	 * 1密码 2盐 salt（随便定义） 3.哈希次数
	 * @param args
	 */
	public static void main(String[] args) {
		String password = new Md5Hash("123456","admin",3).toString();
		System.out.println(password);
		
		
		
	}
	
}
