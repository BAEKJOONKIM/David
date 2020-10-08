package com.youngbj.choongang.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.youngbj.choongang.service.My_flex_replyService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.My_flex_replyVo;

@Controller
public class My_flex_replyController {
	@Autowired
	private My_flex_replyService my_flex_replyService;
	

	@RequestMapping("/write_flex_reply_action")
	public String writeContentAction(My_flex_replyVo param, HttpSession session) {

	 MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

	 param.setMbr_idx(sessionUser.getMbr_idx());

	 my_flex_replyService.replywriteContent(param);

	 return "redirect:./flex_readcontent_page?flx_idx=" + param.getFlx_idx();
	 }
	
	@RequestMapping("/delete_flex_reply_action")
	public String deleteContentAction(String frpl_idx, String flx_idx) {
		
		

		my_flex_replyService.replydeleteContent(frpl_idx);
		return "redirect:./flex_readcontent_page?flx_idx="+ flx_idx;
	}


}
