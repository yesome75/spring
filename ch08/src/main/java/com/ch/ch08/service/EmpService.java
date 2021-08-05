package com.ch.ch08.service;
import java.util.List;
import com.ch.ch08.model.Emp;
public interface EmpService {
	List<Emp> list(int deptno);
	Emp select(int empno);
	List<Emp> empList();
	int insert(Emp emp);
	int update(Emp emp);
	int delete(int empno);
	List<Emp> list();


}