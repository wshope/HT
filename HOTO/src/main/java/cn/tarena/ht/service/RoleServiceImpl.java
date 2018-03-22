package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;
@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleMapper roleMapper;
	
	
	
	public List<Role> findRoleList() {
		
		return roleMapper.findRoleList();
	}



	public void saveRole(Role role) {
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateTime(new Date());
		roleMapper.saveRole(role);
	}



	public Role findRoleById(String roleId) {
		return roleMapper.findRoleById(roleId);
	}



	public void saveUpdate(Role role) {
			role.setUpdateTime(new Date());
		 roleMapper.saveUpdate(role);
	}


	//删除角色
	public void deleteRole(String[] roleIds) {
		roleMapper.deleteRole(roleIds);
	}



	public void saveRoleModule(String roleId, String[] moduleIds) {
		
		//在保存信息之前先删除
		roleMapper.deleteRoleModule(roleId);
		
		//插入操作
		for(String moduleId : moduleIds){
			
			roleMapper.saveRoleModule(roleId,moduleId);
			
			
		}
		
	
	
	
	}

}













