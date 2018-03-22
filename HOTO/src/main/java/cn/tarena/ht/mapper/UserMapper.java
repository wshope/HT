package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserMapper {
	
	//查询所有用户信息
	public List<User> findUserList();
	
	//修改状态
	public void updateState(@Param("userIds")String[] userIds,@Param("state")int state);
	
	//批量删除用户
	public void deleteUsers(String[] userIds);

	public void saveUser(User user);

	public User findUserById(String userId);

	public void saveRoleUser(@Param("userId")String userId,@Param("roleId") String roleId);
	
	@Select("select role_id from role_user_p where auth_user_id=#{userId}")
	public List<String> findUserRoleById(String userId);

	@Delete("delete from role_user_p where auth_user_id=#{userId}")
	public void deleteRoleUser(String userId);

	
	
	public User findUserByU_P(@Param("username")String username,@Param("password") String password);
	
	@Select("select * from user_info_p where user_info_id=#{userId}")
	public UserInfo findUserInfoById(String userId);

	public User findUserByName(String username);
	
	
}
