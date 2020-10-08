package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import com.youngbj.choongang.vo.InquiryVo;

public interface InquiryService {
	
	public void insertInquiry(InquiryVo vo);
	public List<HashMap<String, Object>> getMyInquiryList(String mbr_idx);
	public HashMap<String, Object> getMyInquiry(String inq_idx);
	public void updateInquiry(InquiryVo inquiryVo);
	public void deleteInquiry(String inq_idx);
	public List<HashMap<String, Object>> getInquiryList(String keyword, String searchType);
	public void updateInqVrf(InquiryVo vo);
	public void updateInqVrfN(InquiryVo vo);

}
