package com.youngbj.choongang.service;

import java.util.HashMap;

import com.youngbj.choongang.vo.My_flex_like_memberVo;


public interface My_flex_like_memberService {
	
	public My_flex_like_memberVo getContent(My_flex_like_memberVo vo);
	
	public void likein(My_flex_like_memberVo vo);
	
	public void likeout(String flx_idx);
	
	public String likecount(My_flex_like_memberVo vo);
	
	//public void likeupdateReadCount(String flx_idx);

}
