package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youngbj.choongang.vo.InquiryReplyVo;

@Service
public interface InquiryReplyService {
	
	public void insertInquiryReply(InquiryReplyVo vo);
	public List<HashMap<String, Object>> getInqReplyList(String inq_idx);
	public void deleteInquiryReply(String irpl_idx);
	public void updateInquiryReply(InquiryReplyVo vo);
	
}
