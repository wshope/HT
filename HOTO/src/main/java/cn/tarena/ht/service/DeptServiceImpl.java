package cn.tarena.ht.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.pojo.Dept;

@Service
public class DeptServiceImpl implements DeptService{
	
	@Resource
	private DeptMapper deptMapper;
	
	//查询部门列表
	public List<Dept> findDeptList() {
		return deptMapper.findDeptList();
	}

	//改变部门的状态
	public void updateState(String[] deptIds,int state) {
		deptMapper.updateState(deptIds,state);
		
	}
	//批量删除部门信息
	public void deleteDepts(String[] deptIds) {
		deptMapper.deleteDepts(deptIds);
	}

	public void saveDept(Dept dept) {
		
		//设置状态
		dept.setState(1);
		//设置创建时间
		dept.setCreateTime(new Date());
		deptMapper.saveDept(dept);
		
		
	}

}







