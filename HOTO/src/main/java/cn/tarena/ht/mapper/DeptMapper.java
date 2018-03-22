package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	public List<Dept> findDeptList();
	
	public void updateState(@Param("deptIds") String[] deptIds,@Param("state") int state);

	public void deleteDepts(String[] deptIds);

	public void saveDept(Dept dept);
	
	
}
