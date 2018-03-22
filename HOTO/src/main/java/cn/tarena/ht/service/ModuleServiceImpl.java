package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.pojo.Module;

@Service
public class ModuleServiceImpl implements ModuleService{
	@Resource
	private ModuleMapper moduleMapper;
	
	
	public List<Module> findModuleList() {
		
		return moduleMapper.findModuleList();
	}


	public void saveModule(Module module) {
		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		//默认都是启用
		module.setState(1);
		moduleMapper.saveModule(module);
		
		
	}


	public Module findModuleById(String moduleId) {
		
		return moduleMapper.findModuleById(moduleId);
	}


	public List<Module> findParentModule(String moduleId) {
		
		return moduleMapper.findParentModule(moduleId);
	}


	public void updateModule(Module module) {
		module.setUpdateTime(new Date());
		moduleMapper.updateModule(module);
	}


	public List<String> findRoleModuleByRoleId(String roleId) {
		
		return moduleMapper.findRoleModuleByRoleId(roleId);
	}


	public void deleteModule(String[] moduleIds) {
		//删除模块表中的信息时，需要将中间表的数据删除
		
		moduleMapper.deleteRoleModules(moduleIds);
		
		moduleMapper.deleteModules(moduleIds);
		
	}


	public void updateState(String[] moduleIds, int state) {
		moduleMapper.updateState(moduleIds,state);
	}
	
}












