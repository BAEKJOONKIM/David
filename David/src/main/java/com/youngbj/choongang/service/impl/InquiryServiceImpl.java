package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.InquiryReplySQLMapper;
import com.youngbj.choongang.mapper.InquirySQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.InquiryService;
import com.youngbj.choongang.vo.InquiryReplyVo;
import com.youngbj.choongang.vo.InquiryVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class InquiryServiceImpl implements InquiryService{
	
	@Autowired
	InquirySQLMapper inquirySQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	InquiryReplySQLMapper inquiryReplySQLMapper;
	

	@Override
	public void insertInquiry(InquiryVo vo) {
		// TODO Auto-generated method stub
		inquirySQLMapper.insertInquiry(vo);
	}

	@Override
	public List<HashMap<String, Object>> getMyInquiryList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> myInquiryDataList = new ArrayList<HashMap<String,Object>>();
		
		List<InquiryVo> myInquiryList = inquirySQLMapper.selectByIdx(mbr_idx);
		
		for(InquiryVo inquiryVo : myInquiryList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(inquiryVo.getMbr_idx());
			List<InquiryReplyVo> inquiryReplyVo = inquiryReplySQLMapper.selectByInqIdx(inquiryVo.getInq_idx());
			
			HashMap<String, Object> myInquiryMap = new HashMap<String, Object>();
			myInquiryMap.put("memberVo", memberVo);
			myInquiryMap.put("inquiryVo", inquiryVo);
			myInquiryMap.put("inquiryReplyVo", inquiryReplyVo);
			
			
			myInquiryDataList.add(myInquiryMap);
		}
		return myInquiryDataList;
	}

	@Override
	public HashMap<String, Object> getMyInquiry(String inq_idx) {
		// TODO Auto-generated method stub
		InquiryVo inquiryVo = inquirySQLMapper.selectByInqIdx(inq_idx);
		MemberVo memberVo = memberSQLMapper.selectIdx(inquiryVo.getMbr_idx());
		
		HashMap<String, Object> inquiryMap = new HashMap<String, Object>();
		inquiryMap.put("memberVo", memberVo);
		inquiryMap.put("inquiryVo", inquiryVo);
		
		return inquiryMap;
	}

	@Override
	public void updateInquiry(InquiryVo inquiryVo) {
		// TODO Auto-generated method stub
		
		inquirySQLMapper.updateInquiry(inquiryVo);
	}

	@Override
	public void deleteInquiry(String inq_idx) {
		// TODO Auto-generated method stub
		
		inquirySQLMapper.deleteByIdx(inq_idx);
	}

	@Override
	public List<HashMap<String, Object>> getInquiryList(String keyword, String searchType) {
		// TODO Auto-generated method stub
//		List<HashMap<String, Object>> allInquiryListData = new ArrayList<HashMap<String,Object>>();
//		List<InquiryVo> inquiryList = inquirySQLMapper.selectByAll();
//		
//		for(InquiryVo inquiryVo : inquiryList) {
//			MemberVo memberVo = memberSQLMapper.selectIdx(inquiryVo.getMbr_idx());
//			//InquiryReplyVo inquiryReplyVo = inquiryReplySQLMapper.selectByInq_Idx(inquiryVo.getInq_idx());
//			
//			HashMap<String, Object> allinquiryMap = new HashMap<String, Object>();
//			allinquiryMap.put("memberVo", memberVo);
//			allinquiryMap.put("inquiryVo", inquiryVo);
//			//allinquiryMap.put("inquiryReplyVo", inquiryReplyVo);
//			
//			allInquiryListData.add(allinquiryMap);
//		}
		
		List<HashMap<String, Object>> result = null;
		
	
		if (keyword != null) {
			if (searchType.equals("mbr_nick")) {
				result = inquirySQLMapper.selectAllInquiryInfoSearchMbrNick(keyword);

			} else if (searchType.equals("inq_vrf")) {

				
				if(keyword.contains("처")||keyword.contains("중")) {
					keyword = "N";
				}else if(keyword.contains("완") || keyword.contains("답")) {
					keyword = "T";
				}
					
				
				result = inquirySQLMapper.selectAllInquiryInfoSearchInqVrf(keyword);

			}
		}else {
			result = inquirySQLMapper.selectByAll();
		}
		
		return result;
	}

	@Override
	public void updateInqVrf(InquiryVo vo) {
		// TODO Auto-generated method stub
		inquirySQLMapper.updateInqVrf(vo);
	}

	@Override
	public void updateInqVrfN(InquiryVo vo) {
		// TODO Auto-generated method stub
		inquirySQLMapper.updateInqVrfN(vo);
	}

	


}
