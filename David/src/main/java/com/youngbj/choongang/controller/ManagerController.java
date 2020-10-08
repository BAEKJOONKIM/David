package com.youngbj.choongang.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youngbj.choongang.service.InquiryService;
import com.youngbj.choongang.service.ManagerService;
import com.youngbj.choongang.service.MemberService;
import com.youngbj.choongang.service.ReportService;
import com.youngbj.choongang.service.TrainerService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.TrainerVerificationVo;

@Controller
public class ManagerController {

	@Autowired
	MemberService memberService;
	@Autowired
	ReportService reportService;
	@Autowired
	TrainerService trainerService;
	@Autowired
	ManagerService managerService;
	@Autowired
	InquiryService inquiryService;

	@Autowired
	JavaMailSender mailSender;

	@RequestMapping("/managerlogin_action")
	public String ManagerLoginAction(MemberVo param, HttpSession session, String r_num) {

		// 비밀번호 보안처리
		String mbr_pw = param.getMbr_pw() + "@DAVID";

		String hashCode = null;

		StringBuilder sb = new StringBuilder();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(mbr_pw.getBytes("UTF-8"));

			byte[] chars = messageDigest.digest();

			for (int i = 0; i < chars.length; i++) {
				String tmp = Integer.toHexString(0xff & chars[i]);
				if (tmp.length() == 1)
					sb.append("0");
				sb.append(tmp);
			}

			hashCode = sb.toString();

			param.setMbr_pw(hashCode);

		} catch (Exception e) {
			e.printStackTrace();
		}

		MemberVo Manager = managerService.ManagerLogin(param);

	
		if (Manager == null) {
			return "redirect:./login_page";
		}

		session.setAttribute("sessionManagerData", Manager);
		session.setAttribute("sessionUserData", Manager);

		r_num = "1";

		return "redirect:./manager_page?r_num=" + r_num;
	}

	@RequestMapping("/managerlogin_page")
	public String managerLoginPage() {
		return "managerlogin_page";
	}

	@RequestMapping("/manager_page")
	public String managerPage(Model model, MemberVo vo, String keyword, String searchType, String r_num) {

		if (r_num == null) {
			r_num = "1";
		}

		// managerPage에 나온 썸네일 식 신고 리스트
		List<HashMap<String, Object>> reportsmlist = reportService.getReportList(keyword, searchType);
		// managerPage에 나온 썸네일 식 새로 가입멤버 리스트
		List<HashMap<String, Object>> newMemberList = managerService.getMemberListLatest();
		// managerPage에 나온 썸네일 식 트레이너승급 신청 리스트
		List<HashMap<String, Object>> trnrsmList = trainerService.getTrnrApplicationList(keyword, searchType, r_num);
		// 회원수 통계 리스트
		HashMap<String, Object> allMemberList = managerService.getMemberNumList(vo);
		//고객문의글가져오기
		List<HashMap<String, Object>> allInquiry = inquiryService.getInquiryList(keyword, searchType);

		// 블랙리스트가져오기*********
		List<HashMap<String, Object>> blsmList = managerService.getListFromBlacklist();

		model.addAttribute("reportsmlist", reportsmlist);
		model.addAttribute("newMemberList", newMemberList);
		model.addAttribute("trnrsmList", trnrsmList);
		model.addAttribute("allMemberList", allMemberList);
		model.addAttribute("allInquiry", allInquiry);
		model.addAttribute("blsmList", blsmList);

		model.addAttribute("pageNum", trnrsmList.size() / 5);

		return "manager_page";
	}

	@RequestMapping("check_new_member")
	@ResponseBody
	public HashMap<String, Object> checkNewMember() {
		HashMap<String, Object> newMember = new HashMap<String, Object>();
		List<HashMap<String, Object>> newMemberList = managerService.getMemberListLatest();
		newMember.put("newMemberList", newMemberList);

		return newMember;
	}

	// 신청글 목록
	@RequestMapping("/trnr_applicationlist_page")
	public String trnrApplicationListPage(Model model, String searchType, String keyword, String r_num) {

		if (r_num == null) {
			r_num = "1";
		}

		List<HashMap<String, Object>> trnrAplList = trainerService.getTrnrApplicationList(keyword, searchType, r_num);
		model.addAttribute("trnrAplList", trnrAplList);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageNum", trnrAplList.size() / 5);

		return "trnr_applicationlist_page";
	}

	// 신청글 자세히 보기
	@RequestMapping("/read_trnrAplDetail_page")
	public String trnrAplDetailPage(Model model, String trn_idx) {

		HashMap<String, Object> trnrAplInfo = trainerService.getTrainerApplicationDetail(trn_idx);

		model.addAttribute("trnrAplInfo", trnrAplInfo);

		return "read_trnrAplDetail_page";
	}

	// 신청 거절
	@RequestMapping("/reject_trnrApl_action")
	public String rejectTrnrAplAction(String mbr_idx) {

		trainerService.rejectTrnrApl(mbr_idx);

		return "redirect:./trnr_applicationlist_page";
	}

	// 신청 승인--> member_info에 mbr_trnr를 T로; trainer_vrf에 mbr_vrf를 C로
	@RequestMapping("/confirm_trnrApl_action")
	public String confirmTrnrAplAction(String mbr_idx) {

		TrainerVerificationVo AplVo = trainerService.getAplByMbrIdx(mbr_idx);

		trainerService.ConfirmTrnrApl(AplVo.getTrn_idx());
		trainerService.updateTrainer(mbr_idx);

		return "redirect:./trnr_applicationlist_page";
	}

	// 신청글 수동삭제
	@RequestMapping("/deleteApl_action")
	public String deleteAplAction(String trn_idx) {
		trainerService.deleteTrnrApl(trn_idx);

		return "redirect:./trnr_applicationlist_page";
	}


	
//***************금언시키기--> 금언시키기 페이지 따로 필요없고 버튼하나로만?
	@RequestMapping("/put_into_blackList_action")
	public String putIntoBlackList(String mbr_idx) {
		managerService.putIntoBlacklist(mbr_idx);
		return "redirect:./member_feed_page";
	}
	
	//고객문의글 목록
	@RequestMapping("inquiry_list_page")
	public String inquryListDetailPage(Model model, String searchType, String keyword) {
		
		List<HashMap<String, Object>> allInquiryList = inquiryService.getInquiryList(keyword, searchType);
		model.addAttribute("allInquiryList", allInquiryList);
		model.addAttribute("keyword", keyword);
		
		return "inquiry_list_page";
	}

//	
//	//회원가입하고 나서 이메일인증 시키기 
//	@RequestMapping("/send_emil")
//	public void test(MemberVo vo) throws MessagingException, UnsupportedEncodingException {
//
//		MimeMessage message = null;
//		MimeMessageHelper messageHelper = null;
//		message = mailSender.createMimeMessage();
//		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//		messageHelper.setSubject(vo.getMbr_id());
//		messageHelper.setText("123", true);
//		messageHelper.setFrom("DAVID관리자", "123");
//		messageHelper.setTo(vo.getMbr_emil());
//
//		// messageHelper.addInline(contentId, dataSource);
//		mailSender.send(message);
//
//	}

}
