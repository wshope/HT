package cn.tarena.ht.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
@RequestMapping("/sysadmin/dept/")
public class DeptController extends BaseController{
//继承BaseController目的就是实现日期格式转化
	
	@Resource
	public DeptService deptService;
	
	//为了转向部门列表页面
	@RequestMapping("list")
	public String toDeptList(Model model){
		//准备页面数据deptList
		List<Dept> deptList=deptService.findDeptList();
		model.addAttribute("deptList",deptList);
		return "/sysadmin/dept/jDeptList";
	}
	
	//状态启用
	@RequestMapping("start")
	public String toStart(@RequestParam("deptId") String[] deptIds){
		
		deptService.updateState(deptIds,1);
		return "redirect:/sysadmin/dept/list";
	}
	
	
	//状态停用
	@RequestMapping("stop")
	public String toStop(@RequestParam("deptId") String[] deptIds){
		//状态为0 表示停止
		deptService.updateState(deptIds,0);
		//实现页面跳转
		return "redirect:/sysadmin/dept/list";
	}
	
	//批量删除
	@RequestMapping("delete")
	public String deleteDepts(@RequestParam("deptId") String[] deptIds){
		//实现删除
		deptService.deleteDepts(deptIds);
		
		//实现跳转
		return "redirect:/sysadmin/dept/list";
	}
	
	//部门的新增
	@RequestMapping("tocreate")
	public String toCreateDept(Model model){
		//转到页面数据 父级部门
		List<Dept> deptList = deptService.findDeptList();
		model.addAttribute("deptList",deptList);
		
		//转向新页面
		return "/sysadmin/dept/jDeptCreate";
		
	}
	
	//部门新增保存
	@RequestMapping("save")
	public String saveDept(Dept dept){
		deptService.saveDept(dept);
		return "redirect:/sysadmin/dept/list";
	}
	
	//查看部门信息
	@RequestMapping("toview")
	public String findDept(Model model){
		List<Dept> deptList=deptService.findDeptList();
		model.addAttribute("deptList",deptList);
		
		return "/sysadmin/dept/jDeptfind";
	}
	
	
	
	
	
}
