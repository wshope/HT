package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	
	//查询所有的角色信息
	@Select("select * from role_p")
	public List<Role> findRoleList();
	
	//增加角色
	
	public void saveRole(Role role);
	
	@Select("select * from role_p where role_id=#{roleId}")
	public Role findRoleById(String roleId);
	
	
	public void saveUpdate(Role role);

	public void deleteRole(String[] roleIds);
	
	@Delete("delete from role_module_p where role_id = #{roleId}")
	public void deleteRoleModule(String roleId);
	
	
	@Insert("insert into role_module_p values(#{moduleId},#{roleId})")
	public void saveRoleModule(@Param("roleId")String roleId,@Param("moduleId") String moduleId);
	
	
	//通过RoleIds查询出所包含的MmoduleIds
	public List<String> ModuleIdfindRoleIds(List<String> roleIds);
	
	
}
