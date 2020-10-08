package com.youngbj.choongang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.WorkOutPickMemberSQLMapper;
import com.youngbj.choongang.service.WorkOutPickMemberService;
import com.youngbj.choongang.vo.WorkOutPickMemberVo;

@Service
public class WorkOutPickMemberServiceImpl implements WorkOutPickMemberService{

	@Autowired
	WorkOutPickMemberSQLMapper workOutPickMemberSQLMapper;
	
	@Override
	public WorkOutPickMemberVo isPickWorkOut(WorkOutPickMemberVo vo) {
		// TODO Auto-generated method stub
		WorkOutPickMemberVo result = workOutPickMemberSQLMapper.selectWorkOutPickMember(vo);
		return result;
	}

	@Override
	public void pickWorkOut(WorkOutPickMemberVo vo) {
		// TODO Auto-generated method stub
		workOutPickMemberSQLMapper.insertWorkOutPickMember(vo);
	}

	@Override
	public void deletepickWorkOut(WorkOutPickMemberVo vo) {
		// TODO Auto-generated method stub
		workOutPickMemberSQLMapper.deleteWorkOutPickMember(vo);
	}

}
