package cn.tarena.ht.pojo;

public class Dept extends BaseEntity{
	private String deptId;//部门ID
	private Dept parentDept;//上级部门
	private String deptName;//部门名称
	private Integer state;//状态 0停用 1启用
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Dept getParentDept() {
		return parentDept;
	}
	public void setParentDept(Dept parentDept) {
		this.parentDept = parentDept;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", parentDept=" + parentDept + ", deptName=" + deptName + ", state=" + state
				+ "]";
	}

	
	
	
	
}
