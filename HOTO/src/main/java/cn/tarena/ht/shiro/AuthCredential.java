package cn.tarena.ht.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import cn.tarena.ht.utils.Encrypt;
//这个类就是用来做加密的
public class AuthCredential extends SimpleCredentialsMatcher {
//shiro中没有指定必须要做加密 如果不做也没错
	//如果想做加密，必须重写一个方法
	
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//专门做加密的
		/*
		 * 这个加密管理是用户传入的password进行加密
		 * 之后shiro才能做校验
		 * 
		 */
		
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
		//获取用户传入的密码 token直接拿到的是char类型的数组 需要
		//进行转化为string
		String password = String.valueOf(loginToken.getPassword());
		String username = loginToken.getUsername();
		
		//密码加密
		password = Encrypt.getMd5(password, username);
		
		//加密后密码放入到token中
		loginToken.setPassword(password.toCharArray());

		return super.doCredentialsMatch(loginToken, info);
		
	}

	
	
	
	
}
