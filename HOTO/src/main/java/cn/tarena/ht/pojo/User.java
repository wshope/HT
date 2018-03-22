package cn.tarena.ht.pojo;

public class User extends BaseEntity{
	private String userId; //用户ID
	private Dept dept;  //封装上级所在部门
	private String username; //用户名
	private String password; //密码
	private Integer state;  //用户状态
	
	
	private UserInfo userInfo;	//一对一
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", dept=" + dept + ", username=" + username + ", password=" + password
				+ ", state=" + state + ", userInfo=" + userInfo + "]";
	}
	
	
}
