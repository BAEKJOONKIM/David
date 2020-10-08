package com.youngbj.choongang.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.youngbj.choongang.service.InquiryReplyService;
import com.youngbj.choongang.service.InquiryService;
import com.youngbj.choongang.service.MemberService;
import com.youngbj.choongang.vo.InquiryVo;
import com.youngbj.choongang.vo.MemberVo;

@Controller
public class InquiryController {

	@Autowired
	InquiryService inquiryService;
	@Autowired
	MemberService memberService;
	@Autowired
	InquiryReplyService inquiryReplyService;

	@RequestMapping("/write_inquiry_page")
	public String InquiryWrite(HttpSession session, Model model) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		MemberVo user = memberService.getMbrIdx(sessionUser.getMbr_idx());
		model.addAttribute("user", user);
		return "write_inquiry_page";
	}

	@RequestMapping("/write_inquiry_action")
	public String InquiryWriteAction(InquiryVo vo, HttpSession session, MultipartFile[] imgurl) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		vo.setMbr_idx(sessionUser.getMbr_idx());
		inquiryService.insertInquiry(vo);

		return "redirect:./write_inquiry_complete";
	}

	@RequestMapping("/write_inquiry_complete")
	public String InquiryWriteComplete() {
		return "write_inquiry_complete";
	}
	
	@RequestMapping("/mypage_inquiry_list")
	public String MyInquiryWriteList(InquiryVo inuquiry, HttpSession session, Model model) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		inuquiry.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> myInquiryList = inquiryService.getMyInquiryList(inuquiry.getMbr_idx());
		model.addAttribute("myInquiryList", myInquiryList);
		
		System.out.println("fdf" + myInquiryList);
		
	return "mypage_inquiry_list";
	}
	
	@RequestMapping("/customer_center")
	public String Customer_center(InquiryVo inuquiry, HttpSession session, Model model){
		if(session.getAttribute("sessionUserData")!=null) {
			MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
			inuquiry.setMbr_idx(sessionUser.getMbr_idx());
			List<HashMap<String, Object>> myInquiryList = inquiryService.getMyInquiryList(inuquiry.getMbr_idx());
			model.addAttribute("myInquiryList", myInquiryList);
			return "customer_center";
		}
		
		return "customer_center";
	}
	
	@RequestMapping("/read_inquiry")
	public String ReadInquiry(String inq_idx, Model model, HttpSession session, InquiryVo inq, MemberVo vo) {
		
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		MemberVo sessionManager = (MemberVo) session.getAttribute("sessionManagerData ");

		HashMap<String, Object> inquiryMap = inquiryService.getMyInquiry(inq_idx);
		model.addAttribute("inquiry", inquiryMap);
		
//		if(! ((MemberVo)inquiryMap.get("memberVo")).getMbr_idx().equals(sessionUser.getMbr_idx()) || sessionManager == null) {
//			
//			return "redirect:./customer_center";
//		} 
		
		//댓글
		List<HashMap<String, Object>> inqReplyMap = inquiryReplyService.getInqReplyList(inq_idx);
		model.addAttribute("inqReply", inqReplyMap);
		return "read_inquiry";
	}
	
	@RequestMapping("/update_inquiry")
	public String UpdateInquiry(HttpSession session, Model model, String inq_idx) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		MemberVo user = memberService.getMbrIdx(sessionUser.getMbr_idx());
		model.addAttribute("user", user);
		
		model.addAttribute("inquiry", inquiryService.getMyInquiry(inq_idx));
		System.out.println("Asdf"+model);
		System.out.println("zzzzzzz" + inquiryService.getMyInquiry(inq_idx));
		
		return "update_inquiry";
	}
	
	@RequestMapping("/update_inquiry_action")
	public String UpdateInquiryAction(InquiryVo inquiryVo) {
		
		inquiryService.updateInquiry(inquiryVo);
		
		return "redirect:./read_inquiry?inq_idx="+inquiryVo.getInq_idx();
	}
	
	@RequestMapping("/delete_inquiry_aciton")
	public String DelteInquiry(String inq_idx) {
		inquiryService.deleteInquiry(inq_idx);
		return "redirect:./mypage_inquiry_list";
	}
}