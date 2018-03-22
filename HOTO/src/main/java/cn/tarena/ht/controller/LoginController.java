package cn.tarena.ht.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.chainsaw.Main;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.UserService;
import cn.tarena.ht.utils.Encrypt;

@Controller
public class LoginController extends BaseController {
	
	@Resource
	private UserService userService;
	
	//转向用户登录页面
	@RequestMapping("tologin")
	public String toLogin(){
		
		return "/sysadmin/login/login";
	}
	
	
	
	//登录页面的验证
	@RequestMapping("login")
	public String login(String username,String password,Model model,HttpSession session){
		
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			//如果数据为空，跳转到登录页面
			model.addAttribute("errorInfo","有为空的！");
			return "/sysadmin/login/login";
		}
		//使用shiro进行用户的校验
		
		
		//步骤1.创建subject
		Subject subject  = SecurityUtils.getSubject();
		
		//创建一个token 一个令牌 需要传入两个参数：1.用户名 2.密码
		//shiro拿到用户的自己输入的用户名和密码  拿到自定义的realm中做校验
		//通过realm为shiro准备真实的用户
		//shiro自己把两者做匹配 如果匹配成功 证明用户名和密码正确 然后放行
		//如果两者不匹配，则拦截
		//第二步 生成令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		
		
		try{
			//第三步：实现登录操作
			/*
			 * subject.login(token);如果校验失败就会抛出异常
			 */
			
		subject.login(token);
		
		//如果登录成功，那么subject中就会保存当前用户信息
		User session_user = (User)subject.getPrincipal();
		
		session.setAttribute("session_user",session_user);
		
		//如果校验正确则正确登录
		return "redirect:/home";
		}catch(Exception e){
			//如果登录失败，则跳转到登录页面
			model.addAttribute("errorInfo","用户名或者密码错误！");
			return "/sysadmin/login/login";
			
			
		}
		
		
		
		
		
		//密码需要加密
//		password = Encrypt.getMd5(password, username);
//		
//		//根据用户名和密码查询数据库
//		User user = userService.findUserByU_P(username,password);
//		
//		if(user == null){
//			model.addAttribute("errorInfo","用户名或者密码错误！");
//			return "/sysadmin/login/login";
//		}
		
		//查询userInfo信息
//		UserInfo userInfo = userService.findUserInfoById(user.getUserId());
//		System.out.println(userInfo);
//		
//		session.setAttribute("session_user",userInfo);
		
		
//		return "redirect:/home";
	}
	
	
	
	
	
	
}
