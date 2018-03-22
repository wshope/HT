package cn.tarena.ht.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;

@Controller
@RequestMapping("/sysadmin/role/")
public class RoleController extends BaseController{
	@Resource
	private RoleService roleService;
	
	@Resource
	private ModuleService moduleService;
	
	@RequestMapping("list")
	public String toRoleList(Model model){
		List<Role> roleList = roleService.findRoleList();
		model.addAttribute("dataList",roleList);
		//直接访问页面
		return "/sysadmin/role/jRoleList";
		
	}
	
	@RequestMapping("tocreate")
	public String toCreate(){
		
		//直接页面转向
		return "/sysadmin/role/jRoleCreate";
	}

	@RequestMapping("save")
	public String saveRole(Role role){
		roleService.saveRole(role);
		
		//重定向发送链接
		return "redirect:/sysadmin/role/list";
	}
	
	
	//角色的修改
	@RequestMapping("update")
	public String toUpdate(String roleId,Model model){
		Role role = roleService.findRoleById(roleId);
		
		model.addAttribute("role",role);
		//转向角色修改页面
		return "/sysadmin/role/jRoleUpdate";
	}
	
	@RequestMapping("saveupdate")
	public String saveUpdate(Role role){
		
		roleService.saveUpdate(role);
		return "redirect:/sysadmin/role/list";
	}
	
	//角色的查看
	@RequestMapping("toview")
	public String toView(String roleId,Model model){
		//准备查看的数据
		Role role = roleService.findRoleById(roleId);
		
		model.addAttribute("role",role);
		//转向角色查看页面
		return "/sysadmin/role/jRoleView";
	}
	
	@RequestMapping("delete")
	public String deleteRole(@RequestParam("roleId")String[] roleIds){
		
		roleService.deleteRole(roleIds);
		return "redirect:/sysadmin/role/list";
	}
	
	//转向模块选择页面
	@RequestMapping("toRoleModule")
	public String toRoleUser(String roleId,Model model) throws JsonProcessingException{
		
		//1.查询所有的模块信息
		List<Module> moduleList = moduleService.findModuleList();
		
		
		//数据的回显
		List<String> roleModuleList = moduleService.findRoleModuleByRoleId(roleId);
		for(Module module : moduleList){
			if(roleModuleList.contains(module.getModuleId())){
				module.setChecked("true");
			}
		}
		
		
		//将数据转化为json串
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonZtree = objectMapper.writeValueAsString(moduleList);
		
		//3.为页面准备数据
		model.addAttribute("roleId",roleId);
		model.addAttribute("jsonZtree",jsonZtree);
		//转向模块分配页面
		return "/sysadmin/role/jRoleModule";
		
		
	}
	
	//保存角色所对应对的模块信息
	@RequestMapping("saveRoleModule")
	public String saveRoleModule(String roleId,String[] moduleIds){
		
		roleService.saveRoleModule(roleId,moduleIds);
		//重定向到角色列表
		return "redirect:/sysadmin/role/list";
		
	}
	
	
	public static void main(String[] args) {
		System.out.println();
	}
	
	
}









