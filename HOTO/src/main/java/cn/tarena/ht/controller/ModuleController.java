package cn.tarena.ht.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;

@Controller
@RequestMapping("/sysadmin/module/")
public class ModuleController extends BaseController{
	
	@Resource
	private ModuleService moduleService;
	
	//转向列表页面
	@RequestMapping("list")
	public String toModuleList(Model model){
		//准备页面数据
		
		model.addAttribute("dataList",moduleService.findModuleList());
		System.out.println(moduleService.findModuleList());
		
		
		//直接转向页面
		return "/sysadmin/module/jModuleList";
	}
	
	@RequestMapping("tocreate")
	public String toCreate(Model model){
		//准备父级模块
		List<Module> moduleList = moduleService.findModuleList();
		
		model.addAttribute("moduleList",moduleList);
		
		//转向新增页面
		return "/sysadmin/module/jModuleCreate";
	}
	
	@RequestMapping("save")
	public String saveModule(Module module){
		moduleService.saveModule(module);
		
		
		//重定向回到模块列表页面
		return "redirect:/sysadmin/module/list";
		
	}
	
	
	//转向修改页面
	@RequestMapping("toupdate")
	public String toUpdate(String moduleId,Model model){
		//转向要修改的数据
		Module module = moduleService.findModuleById(moduleId);
		
		
		//准备上级模块
		List<Module> moduleList  = moduleService.findParentModule(moduleId);
		model.addAttribute("moduleList",moduleList);
		model.addAttribute("module",module);
		
		return "/sysadmin/module/jModuleUpdate";
	}
	
	@RequestMapping("update")
	public String updateModule(Module module){
		
		moduleService.updateModule(module);
		//System.out.println(module);
		//重定向到模块列表中
		return "redirect:/sysadmin/module/list";
		
	}
	
	@RequestMapping("toview")
	public String toView(String moduleId,Model model){
		//准备页面数据
		Module module = moduleService.findModuleById(moduleId);
		model.addAttribute("module",module);
		
		//准备转向用户查看页面
		return "/sysadmin/module/jModuleView";
		
	}
	
	@RequestMapping("delete")
	public String toDelete(@RequestParam("moduleId") String[] moduleIds){
		
		
		moduleService.deleteModule(moduleIds);
		return "redirect:/sysadmin/module/list";
		
	}
	
	
	
	//启用
	@RequestMapping("start")
	public String start(@RequestParam("moduleId")String[] moduleIds){
		moduleService.updateState(moduleIds,1);
		
		//实现重定向
		return "redirect:/sysadmin/module/list";
	}
	
	
	
	//停用
	@RequestMapping("stop")
	public String stop(@RequestParam("moduleId")String[] moduleIds){
		
		
		//实现重定向
		return "redirect:/sysadmin/module/list";
	}
	
	
	
	
	
	
}


















