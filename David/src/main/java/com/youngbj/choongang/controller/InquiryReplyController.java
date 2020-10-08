package com.youngbj.choongang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youngbj.choongang.service.InquiryReplyService;
import com.youngbj.choongang.service.InquiryService;
import com.youngbj.choongang.vo.InquiryReplyVo;
import com.youngbj.choongang.vo.InquiryVo;
import com.youngbj.choongang.vo.MemberVo;

@Controller
public class InquiryReplyController {
	
	@Autowired
	InquiryReplyService inquiryReplyService;
	@Autowired
	InquiryService inquiryService;
	
	@RequestMapping("/inquiry_reply_action")
	public String WriteInquiryReplyAction(InquiryReplyVo vo, HttpSession session, InquiryVo inquiry) {
		
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		vo.setMbr_idx(sessionUser.getMbr_idx());
		inquiryReplyService.insertInquiryReply(vo);
		inquiryService.updateInqVrf(inquiry);
		
		return "redirect:./read_inquiry?inq_idx="+vo.getInq_idx();
	}
	
	@RequestMapping("/inquiry_reply_delete_action")
	public String DeleteInquiryReply(String irpl_idx, String inq_idx, InquiryVo inquiry) {
		inquiryReplyService.deleteInquiryReply(irpl_idx);
		inquiryService.updateInqVrfN(inquiry);
		return "redirect:./read_inquiry?inq_idx="+inq_idx;
	}

}
