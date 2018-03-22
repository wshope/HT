package cn.tarena.ht.shiro;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;
//shiro中特定的类继承了它shiro才会自动的识别
public class AuthRealm extends AuthorizingRealm {
	
	@Resource
	private UserService userService;
	
	//权限管理
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pri) {
		//session中存放了用户的信息---->角色信息---->模块信息
		//标签中的名称不要重复
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User)session.getAttribute("session_user");
		//调用userService.ModulefindUserById查询出用户所关联的模块名
		List<String> names =userService.ModulefindUserById(user.getUserId());
		
		//权限的认证
		List<String> roleList = new ArrayList<String>();
		if(!(names == null)){
		for(String moduleName : names){
			roleList.add(moduleName);
		 }
		}
		System.out.println("----------------------roleList"+roleList);
		//权限管理器
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//向管理器添加权限
		info.addStringPermissions(roleList);
		return info;
	}

	
	//登录认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*realm的左右为shiro准备资料 真实用户信息
		 * 1.通过用户提交的username到数据库中查询信息
		 * 2.得到一个user对象
		 * 3.把用户对象user，用户的真实密码，当前的realm名称返回
		 * 4.shiro会自动的根据提供的资料与传入的信息做比较
		*/
		UsernamePasswordToken loginToken = (UsernamePasswordToken)token;
		
		//1.获取username,这是用户传入的username
		String username = loginToken.getUsername();
		
		//2.获取user对象
		User user = userService.findUserByUserName(username);
		
		//3.返回对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		
		
		return info;
	}

	

}
