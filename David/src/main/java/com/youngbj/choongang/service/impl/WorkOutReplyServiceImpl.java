package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.WorkOutReplySQLMapper;
import com.youngbj.choongang.service.WorkOutReplyService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.WorkOutReplyVo;
import com.youngbj.choongang.vo.WorkOutVo;

@Service
public class WorkOutReplyServiceImpl implements WorkOutReplyService{

	@Autowired
	WorkOutReplySQLMapper workOutReplySQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	

	@Override
	public void register(WorkOutReplyVo vo) {
		// TODO Auto-generated method stub
		workOutReplySQLMapper.insertWorkOutReply(vo);
	}


	@Override
	public void deleteWorkOutReply(String wrpl_idx) {
		// TODO Auto-generated method stub
		workOutReplySQLMapper.deleteByReply(wrpl_idx);
	}


	@Override
	public List<HashMap<String, Object>> replyAndImg(String wrk_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> replyImg = workOutReplySQLMapper.selectByReplyAndImg(wrk_idx);
		
		return replyImg;
	}
	
	

}
