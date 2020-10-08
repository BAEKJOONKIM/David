package com.youngbj.choongang.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.My_flex_like_memberSQLMapper;
import com.youngbj.choongang.service.My_flex_like_memberService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.My_flex_like_memberVo;


@Service
public class My_flex_like_memberServiceImpl implements My_flex_like_memberService{
	
	@Autowired
	private My_flex_like_memberSQLMapper My_flex_like_memberSQLMapper;
	@Autowired
	private MemberSQLMapper memberSQLMapper;

	@Override
	public My_flex_like_memberVo getContent(My_flex_like_memberVo vo) {
		// TODO Auto-generated method stub
		
		
		
		
		My_flex_like_memberVo my_flex_like_memberVo = new My_flex_like_memberVo();
		
		System.out.println("vo.getFlx_idx()"+(vo.getFlx_idx()));
		System.out.println("vo.getMbr_idx"+(vo.getMbr_idx()));

		my_flex_like_memberVo.setFlx_idx(vo.getFlx_idx());
		
		my_flex_like_memberVo.setMbr_idx(vo.getMbr_idx());
		
		My_flex_like_memberVo like_memberVo = My_flex_like_memberSQLMapper.likeselectByIdx(my_flex_like_memberVo);
		
		return like_memberVo;
	}
	
	@Override
	public void likein(My_flex_like_memberVo vo) {
		// TODO Auto-generated method stub
		My_flex_like_memberSQLMapper.insert(vo);
	}
	@Override
	public void likeout(String flx_idx) {
		// TODO Auto-generated method stub
		My_flex_like_memberSQLMapper.likedeleteByIdx(flx_idx);
	}

	@Override
	public String likecount(My_flex_like_memberVo vo) {
		// TODO Auto-generated method stub
		String likecount = My_flex_like_memberSQLMapper.likecount(vo);
		
		return likecount;
	}

	

	

}
