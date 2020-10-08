package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.InquiryReplySQLMapper;
import com.youngbj.choongang.mapper.InquirySQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.InquiryReplyService;
import com.youngbj.choongang.vo.InquiryReplyVo;
import com.youngbj.choongang.vo.InquiryVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class InquiryReplyServiceImpl implements InquiryReplyService{

	@Autowired
	InquiryReplySQLMapper inquiryReplySQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	InquirySQLMapper inquirySQLMapper;
	
	
	@Override
	public void insertInquiryReply(InquiryReplyVo vo) {
		// TODO Auto-generated method stub
		inquiryReplySQLMapper.insertInquiryReply(vo);
	}


	@Override
	public List<HashMap<String, Object>> getInqReplyList(String inq_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> inqReplyDataList = new ArrayList<HashMap<String,Object>>();
		
		List<InquiryReplyVo> inqReplyList = inquiryReplySQLMapper.selectByInqIdx(inq_idx);
		
		for(InquiryReplyVo inqReplyVo : inqReplyList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(inqReplyVo.getMbr_idx());
			InquiryVo inquiryVo = inquirySQLMapper.selectByInqIdx(inqReplyVo.getInq_idx());
			
			HashMap<String, Object> inqReplyMap = new HashMap<String, Object>();
			inqReplyMap.put("memberVo", memberVo);
			inqReplyMap.put("inquiryVo", inquiryVo);
			inqReplyMap.put("inqReplyVo", inqReplyVo);
			
			inqReplyDataList.add(inqReplyMap);
			
		}
		
		return inqReplyDataList;
	}


	@Override
	public void deleteInquiryReply(String irpl_idx) {
		// TODO Auto-generated method stub
		inquiryReplySQLMapper.deleteByidx(irpl_idx);
	}


	@Override
	public void updateInquiryReply(InquiryReplyVo vo) {
		// TODO Auto-generated method stub
		inquiryReplySQLMapper.updateByidx(vo);
	}

}
