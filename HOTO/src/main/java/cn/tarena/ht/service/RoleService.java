package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Role;

public interface RoleService {

	public List<Role> findRoleList();

	public void saveRole(Role role);
	
	//根据ID查询角色信息
	public Role findRoleById(String roleId);

	public void saveUpdate(Role role);

	public void deleteRole(String[] roleIds);

	public void saveRoleModule(String roleId, String[] moduleIds);
	
	
}
