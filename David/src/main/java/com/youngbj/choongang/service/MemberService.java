package com.youngbj.choongang.service;

import com.youngbj.choongang.vo.MemberImgVo;
import com.youngbj.choongang.vo.MemberVo;

import java.util.*;

public interface MemberService {

	// 민은지
	public MemberVo login(MemberVo vo);

	public void signUp(MemberVo vo);

	public void modifyInfo(MemberVo vo);

	public void changePw(MemberVo vo);

	public MemberVo confirmPw(MemberVo vo);

	// 0224추가
	public MemberVo getMbrIdx(String mbr_idx);

	// 추가
	public void insertImg(MemberImgVo vo);

	public HashMap<String, Object> getMemberImg(String mbr_idx);

	public void deleteImg(MemberImgVo vo);

	public void changeImg(MemberImgVo vo);

	public MemberImgVo confirmUrl(String mbri_url);

	// 임소청
	public MemberVo selectMemberByIdx(String mbr_idx);
	
	//양성민
		public boolean confirmId(String mbr_id);
}
