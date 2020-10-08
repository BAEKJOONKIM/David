package com.youngbj.choongang.controller;

import java.io.UnsupportedEncodingException;
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

import com.youngbj.choongang.service.AuctionService;
import com.youngbj.choongang.service.MemberService;
import com.youngbj.choongang.service.ReportService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.ReportVo;

@Controller
public class ReportController {

	@Autowired
	ReportService reportService;
	
	@Autowired
	AuctionService auctionService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JavaMailSender mailSender;

	// 신고글모임 페이지에서 신고글 가져오기
	@RequestMapping("/reportlist_page")
	public String reportListPage(Model model, String searchType, String keyword) {

		List<HashMap<String, Object>> ReportList = reportService.getReportList(keyword, searchType);

		model.addAttribute("reportlist", ReportList);
		model.addAttribute("keyword", keyword);

		return "reportlist_page";
	}

	@RequestMapping("/read_report_page")

  	public String readReportPage(String rpt_idx, Model model) {

		HashMap<String, Object> reportContent = reportService.getDetailReport(rpt_idx);

		model.addAttribute("reportcontent", reportContent);

		return "read_report_page";
	}

	// 신고당한 글 강제삭제하기
	@RequestMapping("/admin_delete")
	public String deleteBasedOnReportAction(String rpt_idx) {

		// ReportVo contentToBoDeleted = reportService.getReportByIdx(rpt_idx);
		// =>reportVo가져와서
		// reportService.deleteBasedOnReport(contentToBoDeleted.getB_idx()); => 삭제할
		// report를 통해 b_idx를 찾아서 넘긴다 --> rpt_idx로 삭제하는 게 아니고 b_idx의 값으로 삭제를 들어가는것

		reportService.deleteBasedOnReport(rpt_idx);

		return "redirect:./reportlist_page";

	}

	// 신고당한 회원에 경고이메일 보내기--> send만 하면 됨
	@RequestMapping("/send_warning_email_action")
	public void sendWarningMail(MemberVo vo) throws MessagingException, UnsupportedEncodingException {
		
		
		MimeMessage message = null;
		MimeMessageHelper messageHelper = null;
		message = mailSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");

		// 제목
		messageHelper.setSubject("2222222");

		// 내용
		messageHelper.setText("111111", true);
 
		// 보내는 사람의 이름
		messageHelper.setFrom("123", "123");
		messageHelper.setTo("s001352@gmail.com");

		// messageHelper.addInline(contentId, dataSource);
		mailSender.send(message);
		
		
		
		
		
		//warninglist에 추가하기
/*		
		reportService.addIntoWarningList(vo.getMbr_idx());
		
		String email = memberService.selectMemberByIdx(vo.getMbr_idx()).getMbr_emil();
	
		
		MimeMessage message = null;
		MimeMessageHelper messageHelper = null;
		message = mailSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");

		// 제목
		messageHelper.setSubject("DAVID관리자입니다");

		// 내용
		messageHelper.setText(vo.getMbr_nick() + "님, 안녕하세요? 회원님께서 올리신 내용이 부적절한 내용으로 판정되여 경고이메일 보내게 되었습니다. 해당내용은 확인하시려면 아래 링크를 클릭해주세요.  앞으로도 본 사이트를 애용해주시기 바랍니다. 감사합니다!", true);
 
		// 보내는 사람의 이름
		messageHelper.setFrom("DAVID관리자", "123");
		messageHelper.setTo(email);

		// messageHelper.addInline(contentId, dataSource);
		mailSender.send(message);
*/
		



		
	}

}
