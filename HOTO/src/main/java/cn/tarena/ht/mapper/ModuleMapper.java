package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {
	//查询所有的模块信息
	public List<Module> findModuleList();
	
	//保存模块信息
	public void saveModule(Module module);
	
	//根据模块查询模块信息
	public Module findModuleById(String moduleId);
	
	
	public List<Module> findParentModule(String moduleId);

	public void updateModule(Module module);
	
	@Select("select module_Id from role_module_p where role_id=#{roleId}")
	public List<String> findRoleModuleByRoleId(String roleId);

	
	public void deleteModules(String[] moduleIds);
	
	
	
	public void deleteRoleModules(String[] moduleIds);
	
	public void updateState(@Param("moduleIds")String[] moduleIds, @Param("state")int state);

	public List<String> ModuleNamesfindById(List<String> moduleIds);
	

}
