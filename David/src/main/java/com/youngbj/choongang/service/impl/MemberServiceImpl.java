package com.youngbj.choongang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.MemberService;
import com.youngbj.choongang.vo.MemberImgVo;
import com.youngbj.choongang.vo.MemberVo;

import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberSQLMapper memberSQLMapper;

	@Override
	public MemberVo login(MemberVo vo) {
		// TODO Auto-generated method stub
		MemberVo result = memberSQLMapper.selectIdAndPw(vo);
		return result;
	}

	@Override
	public void signUp(MemberVo vo) {
		// TODO Auto-generated method stub

		memberSQLMapper.insertMemberInfo(vo);
	}

	@Override
	public void modifyInfo(MemberVo vo) {
		// TODO Auto-generated method stub

		memberSQLMapper.modifyInfo(vo);

	}

	@Override
	public void changePw(MemberVo vo) {
		// TODO Auto-generated method stub

		memberSQLMapper.changePw(vo);
	}

	@Override
	public MemberVo confirmPw(MemberVo vo) {
		// TODO Auto-generated method stub

		MemberVo change = memberSQLMapper.selectIdxAndPw(vo);

		return change;
	}

	// 회원프로필사진
	@Override
	public void insertImg(MemberImgVo vo) {
		// TODO Auto-generated method stub

		memberSQLMapper.insertMemberImg(vo);
	}

	@Override
	public HashMap<String, Object> getMemberImg(String mbr_idx) {
		// TODO Auto-generated method stub
		MemberImgVo memberImgVo = memberSQLMapper.selectMemberImgByIdx(mbr_idx);
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);

		HashMap<String, Object> memberImgMap = new HashMap<String, Object>();
		memberImgMap.put("memberVo", memberVo);
		memberImgMap.put("memberImgVo", memberImgVo);

		return memberImgMap;
	}

	@Override
	public void deleteImg(MemberImgVo vo) {
		// TODO Auto-generated method stub

		memberSQLMapper.deleteMemberImg(vo);

	}

	@Override
	public void changeImg(MemberImgVo vo) {
		// TODO Auto-generated method stub
		memberSQLMapper.changeMemberImg(vo);
	}

	@Override
	public MemberImgVo confirmUrl(String mbri_url) {
		// TODO Auto-generated method stub
		MemberImgVo confirmUrl = memberSQLMapper.selectMemberImgByUrl(mbri_url);
		return confirmUrl;
	}

	@Override
	public MemberVo getMbrIdx(String mbr_idx) {
		// TODO Auto-generated method stub
		MemberVo getMbrIdx = memberSQLMapper.selectIdx(mbr_idx);
		return getMbrIdx;
	}

	@Override
	public MemberVo selectMemberByIdx(String mbr_idx) {

		return memberSQLMapper.selectIdx(mbr_idx);
	}

	// 양성민
	@Override
	public boolean confirmId(String mbr_id) {
		// TODO Auto-generated method stub
		if (memberSQLMapper.selectConfirmId(mbr_id) == null) {
			return false;
		} else {
			return true;
		}
	}

}
