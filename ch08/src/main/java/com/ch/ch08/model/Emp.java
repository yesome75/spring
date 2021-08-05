package com.ch.ch08.model;
import java.sql.Date;
import lombok.Data;
@Data
public class Emp {
//	hibernate 또는 JPA에서 숫자부분에 null이 있으면 에러이고 Integer가 정상처리 됨
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private int sal;
	private Integer comm;
	private int deptno;
	// 관리자 이름
	private String mgrName;
	// join용
	private Dept dept;
}