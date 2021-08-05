package com.ch.mybatis.dao;
import java.util.List;
import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;
public interface MemberDao {
	Member select(String id);
	int insert(Member member);
	int update(Member member);
	int delete(String id);
	void insert(MemberPhoto mp);
	List<MemberPhoto> listPhoto(String id);
	void insertPh(List<MemberPhoto> photos);

}