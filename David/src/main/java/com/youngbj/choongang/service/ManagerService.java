package com.youngbj.choongang.service;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youngbj.choongang.vo.MemberVo;

@Service
public interface ManagerService {


	//임소청
	public MemberVo ManagerLogin(MemberVo vo);
	public List<HashMap<String, Object>> getMemberListLatest();
	public HashMap<String, Object> getMemberNumList(MemberVo vo);
	public void putIntoBlacklist(String mbr_idx);
	
	//추가
	public List<HashMap<String, Object>> getListFromBlacklist();
	
	
}
