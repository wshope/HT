package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.utils.Encrypt;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserInfoMapper userInfoMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private ModuleMapper moduleMapper;
	
	
	//查询所有用户信息
	public List<User> findUserList() {
		
		return userMapper.findUserList();
	}

	//改变用户的状态
	public void updateState(String[] userIds,int state) {
		userMapper.updateState(userIds,state);
	}
	
	
	//用户的删除，关联删除userInfo表中的数据
	public void deleteUsers(String[] userIds) {
		System.out.println(userIds);
		userInfoMapper.deleteUserInfos(userIds);
		userMapper.deleteUsers(userIds);
	}
	
	public void saveUser(User user) {
		String uuid = UUID.randomUUID().toString();
		//保存时需要同时保存两张表的数据
		UserInfo userInfo = user.getUserInfo();
		//为userInfo赋值
		userInfo.setUserInfoId(uuid);
		userInfo.setCreateTime(new Date());
		
		user.setUserId(uuid);
		user.setCreateTime(new Date());
		user.setState(1);
		//通过md5Hash加密
		user.setPassword(Encrypt.getMd5(user.getPassword(),user.getUsername()));
		
		
		
		//分别保存
		userMapper.saveUser(user);
		userInfoMapper.saveUserInfo(userInfo);
		
		
		
	}

	public User findUserById(String userId) {
		return userMapper.findUserById(userId);
	}

	public void saveRoleUser(String userId, String[] roleIds) {
		
		//多对多中间表 先删除  后插入
		userMapper.deleteRoleUser(userId);
		
		
		//因为中间表没有实际意思，所以不会写Mapper，
		//调用userMapper进行
		for(String roleId : roleIds){
		userMapper.saveRoleUser(userId,roleId);
		}
	}

	public List<String> findUserRoleById(String userId) {
		
		return userMapper.findUserRoleById(userId);
	}

	//根据用户名和密码查询用户
	public User findUserByU_P(String username, String password) {
		
		
		return userMapper.findUserByU_P(username,password);
	}
	
	
	//根据用户Id查询userInFo信息
	public UserInfo findUserInfoById(String userId) {
		
		return userMapper.findUserInfoById(userId);
	}
	
	
	//shiro中根据username查询用户信息
	public User findUserByUserName(String username) {
		return userMapper.findUserByName(username);
	}

	
	//根据用户所关联的模块控制权限管理
	public List<String> ModulefindUserById(String userId) {
		List<String> moduleIds = null;
		List<String> names = null;
		//根据用户Id查询所关联的角色Id
		List<String> roleIds = userMapper.findUserRoleById(userId);
		System.out.println("roleIds"+roleIds);
		
		//根据角色Id查询所关联的模块Id
		if(!roleIds.isEmpty()){
		 moduleIds = roleMapper.ModuleIdfindRoleIds(roleIds);
		}
		
		//根据模块Id查询模块名
		if(!(moduleIds == null)){
		  names = moduleMapper.ModuleNamesfindById(moduleIds);
		}
		
		//最后返回模块名
		return names;
	}

}








