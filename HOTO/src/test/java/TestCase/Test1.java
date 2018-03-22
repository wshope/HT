package TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zaxxer.hikari.HikariDataSource;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.Dept;

public class Test1 {
	private ApplicationContext app;
	@Before
	public void init(){
		String[] str = {"spring/applicationContext.xml","spring/applicationContext-mybatis.xml"};
		app = new ClassPathXmlApplicationContext(str);
	}
	
	@Test
	public void test2() throws SQLException{
		HikariDataSource dataSource = app.getBean("dataSource",HikariDataSource.class);
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
		
	}
	@Test
	public void test3(){
		DeptMapper mp = app.getBean("deptMapper",DeptMapper.class);
		List<Dept> list = mp.findDeptList();
		System.out.println(list);
	}
	
	
	@Test
	public void test4(){
		DeptMapper mp = app.getBean("deptMapper",DeptMapper.class);
		String[] str={"100","100100","100200"};
		mp.updateState(str,1);
		System.out.println("修改成功");
	}
	
	@Test
	public void test5(){
		RoleMapper mp = app.getBean("roleMapper",RoleMapper.class);
		List<String> list = new ArrayList<String>();
		list.add("2");
		list.add("8b647c95-6b40-4f14-ac13-cf64cb426c43");
		List<String> moduleIds = mp.ModuleIdfindRoleIds(list);
		System.out.println(moduleIds);
	}
	
	@Test
	public void test06(){
		ModuleMapper mp = app.getBean("moduleMapper",ModuleMapper.class);
		List<String> list = new ArrayList<String>();
		
		System.out.println(list.isEmpty());
		
		
	}
	

}
