package cn.tarena.ht.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserService;

@Controller
@RequestMapping("/sysadmin/user/")
public class UserController extends BaseController {
	
	@Resource
	private UserService userService;
	@Resource 
	private DeptService deptService;
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping("list")
	public String toUserList(Model model){
		//获取列表数据
		List<User> userList = userService.findUserList();
		model.addAttribute("dataList",userList);
		return "sysadmin/user/jUserList";
	}
	
	//状态的启用
	@RequestMapping("start")
	public String toStart(@RequestParam("userId")String[] userIds){
		userService.updateState(userIds,1);
		
		//返回这个页面
		return "redirect:/sysadmin/user/list";
	}
	
	//状态的停用
		@RequestMapping("stop")
		public String toStop(@RequestParam("userId")String[] userIds){
			userService.updateState(userIds,0);
			
			//返回这个页面
			return "redirect:/sysadmin/user/list";
		}
		
		
	//用户的删除
	@RequestMapping("delete")
	public String deleteUsers(@RequestParam("userId") String[] userIds){
		userService.deleteUsers(userIds);
		
		return "redirect:/sysadmin/user/list";
	}		
		
	//实现页面的转向
	@RequestMapping("tocreate")
	public String toCreate(Model model){
		//准备部门下拉列表
		List<Dept> deptList = deptService.findDeptList();
		
		//准备上级领导的下拉框
		List<User> userList = userService.findUserList();
		
		model.addAttribute("deptList",deptList);
		model.addAttribute("userList",userList);
		
		return "sysadmin/user/jUserCreate";
	}
	
	//用户保存一个from表单提交了两张表的数据
	@RequestMapping("save")
	public String saveUser(User user){
		userService.saveUser(user);
		
		//实现页面转向 
		return "redirect:/sysadmin/user/list";
		
	}
	
	@RequestMapping("toview")
	public String toView(String userId,Model model){
		//准备数据
		User user = userService.findUserById(userId);
		model.addAttribute("user",user);
		return "/sysadmin/user/jUserView";
		
	}
	
	
	//为用户分配角色
	@RequestMapping("toRoleUser")
	public String toRoleUser(Model model,String userId) throws JsonProcessingException{
		
		//为页面准备数据，所有的角色信息
		List<Role> roleList = roleService.findRoleList();
		//[{id:1,name:"节点名称"}]
//		String zTreeJson="[";
//		
//		for(Role role : roleList){
//			String begin = "{";
//			begin = begin+"id:\""+role.getRoleId();
//			begin= begin+"\",name:\""+role.getName();
//			begin =begin+ "\"},";
//			zTreeJson=zTreeJson+begin;
//		}
//		zTreeJson  = zTreeJson.substring(0,zTreeJson.length()-1);
//		zTreeJson = zTreeJson+"]";
		
		
		/**数据的回显
		*1.根据用户的ID查询用户的角色
		*2.该角色信息与列表信息匹配，如果匹配成功，设置属性checked=true
		*3.将角色列表信息转化为json串
		*
		*/
		//查询用户角色ID
		List<String> userRoleList = userService.findUserRoleById(userId);
		//如果用户列表中有该Id那么设置checked为true
		for(Role role : roleList){
			if(userRoleList.contains(role.getRoleId())){
				role.setChecked("true");
			}
		}
		
		
		
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		//能够将对象的格式转化为Json串
		String zTreeJson = mapper.writeValueAsString(roleList);
		//将json串返回给页面
		model.addAttribute("zTreeJson",zTreeJson);
		System.out.println(zTreeJson);
		
		//将用户的ID转向
		model.addAttribute("userId",userId);
		
		//转向用户的角色分配页面
		return "/sysadmin/user/jRoleUser";
		
	
	}
	
	
	@RequestMapping("saveUserRole")
	public String saveUserRole(String userId,String[] roleIds){
		System.out.println(userId);
		System.out.println(Arrays.toString(roleIds));
		
		//得到用户的Id和角色的ID进行插入操作
		userService.saveRoleUser(userId,roleIds);
		
		
		//重定向用户列表页面
		return "redirect:/sysadmin/user/list";
		
	}
	
	
}













