package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserService {
	
	public List<User> findUserList();
	
	public void updateState(String[] userIds,int state);
	
	public void deleteUsers(String[] userIds);
	
	
	public void saveUser(User user);

	public User findUserById(String userId);

	public void saveRoleUser(String userId, String[] roleIds);

	public List<String> findUserRoleById(String userId);

	public User findUserByU_P(String username, String password);

	public UserInfo findUserInfoById(String userId);
	
	//通过用户名查询用户信息
	public User findUserByUserName(String username);

	public List<String> ModulefindUserById(String userId);
	
}
