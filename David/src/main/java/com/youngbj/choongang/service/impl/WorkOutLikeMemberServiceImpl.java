package com.youngbj.choongang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.WorkOutLikeMemberSQLMapper;
import com.youngbj.choongang.service.WorkOutLikeMemberService;
import com.youngbj.choongang.vo.WorkOutLikeMemberVo;

@Service
public class WorkOutLikeMemberServiceImpl implements WorkOutLikeMemberService{

	@Autowired
	WorkOutLikeMemberSQLMapper workOutLikeMemberSQLMapper;

	@Override
	public WorkOutLikeMemberVo isLikeWorkOut(WorkOutLikeMemberVo vo) {
		// TODO Auto-generated method stub
		
		WorkOutLikeMemberVo result = workOutLikeMemberSQLMapper.selectWorkOutLikeMember(vo);
		return result;
	}

	@Override
	public void likeWorkOut(WorkOutLikeMemberVo vo) {
		// TODO Auto-generated method stub
		workOutLikeMemberSQLMapper.insertWorkOutLikeMember(vo);
	}

	@Override
	public void deleteLikeWorkOut(WorkOutLikeMemberVo vo) {
		// TODO Auto-generated method stub
		workOutLikeMemberSQLMapper.deleteWorkOutLikeMember(vo);
	}

	@Override
	public String countLikeWorkOut(String wrk_idx) {
		// TODO Auto-generated method stub
		String result = workOutLikeMemberSQLMapper.selectCountWorkOutLike(wrk_idx);
		return result;
	}

	@Override
	public void updateLikePuls(String wrk_idx) {
		// TODO Auto-generated method stub
		workOutLikeMemberSQLMapper.updateLikeCountPlus(wrk_idx);
		
	}

	@Override
	public void updateLikeMinus(String wrk_idx) {
		// TODO Auto-generated method stub
		workOutLikeMemberSQLMapper.updateLikeCountMinus(wrk_idx);
	}

	
	


}
