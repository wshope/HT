package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//转向欢迎页面
	@RequestMapping("/home")
	public String home(){
		return "/home/fmain";
	}
	
	//转向tilte标题栏页面
	@RequestMapping("/title")
	public String title(){
		return "/home/title";
	}
	
	//转向home的左侧页面
	/*@RequestMapping("/homeLeft")
	public String homeLeft(){
		return "/home/left";
	}
	
	//转向home的操作页面
	@RequestMapping("/homeMain")
	public String homeMain(){
		return "/home/main";
	}*/
	
	/*@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/sysadmin/left";
	}
	
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "/sysadmin/main";
	}*/
	
	//src=/home/left.action
	//RequestMapping也可以接收参数，方法的参数名和RequestMapping的
	//参数名要一致然后加上@PathVariable这个注解就可以接收这个参数
	@RequestMapping("/{name}/left")
	public String sysadminLeft(@PathVariable String name){
		return "/"+name+"/left";
	}
	
	@RequestMapping("/{name}/main")
	public String sysadminMain(@PathVariable String name){
		return "/"+name+"/main";
	}
	
	
	
	
	
	
}






















