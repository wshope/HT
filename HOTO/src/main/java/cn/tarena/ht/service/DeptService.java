package cn.tarena.ht.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptService {
	public List<Dept> findDeptList();
	
	public void updateState(String[] deptIds,int state);
	
	void deleteDepts(String[] deptIds);
	
	void saveDept(Dept dept);
	
}
