package com.youngbj.choongang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youngbj.choongang.service.My_flex_like_memberService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.My_flex_like_memberVo;

@Controller
public class My_flex_like_memberController {
	@Autowired
	private My_flex_like_memberService my_flex_like_memberService;
	
	@RequestMapping("/like_in_action")
	public String likeintAction(My_flex_like_memberVo param, HttpSession session) {

		System.out.println("param"+param);
	 MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

	 param.setMbr_idx(sessionUser.getMbr_idx());

	 my_flex_like_memberService.likein(param);

	 return "redirect:./flex_readcontent_page?flx_idx=" + param.getFlx_idx();
	 }
	
	@RequestMapping("/like_delete_action")
	public String deleteContentAction(String flx_idx) {
		
		my_flex_like_memberService.likeout(flx_idx);
		return "redirect:./flex_readcontent_page?flx_idx="+ flx_idx;
	}

}
