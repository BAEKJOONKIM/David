package com.youngbj.choongang.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.youngbj.choongang.service.AttendanceService;
import com.youngbj.choongang.service.AuctionService;
import com.youngbj.choongang.service.BodyService;
import com.youngbj.choongang.service.HistoryService;
import com.youngbj.choongang.service.InterestService;
import com.youngbj.choongang.service.ManagerService;
import com.youngbj.choongang.service.MemberService;
import com.youngbj.choongang.service.MyPageAuctionService;
import com.youngbj.choongang.service.ReportService;
import com.youngbj.choongang.service.TrainerService;
import com.youngbj.choongang.service.WorkOutPickMemberService;
import com.youngbj.choongang.vo.AttendanceVo;
import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BidInfoVo;
import com.youngbj.choongang.vo.BoardReplyVo;
import com.youngbj.choongang.vo.BoardVo;
import com.youngbj.choongang.vo.BodyVo;
import com.youngbj.choongang.vo.InterestVo;
import com.youngbj.choongang.vo.MemberImgVo;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_replyVo;
import com.youngbj.choongang.vo.TrainerVerificationVo;
import com.youngbj.choongang.vo.WorkOutPickMemberVo;
import com.youngbj.choongang.vo.WorkOutReplyVo;
import com.youngbj.choongang.vo.WorkOutVo;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	ManagerService managerService;
	@Autowired
	BodyService bodyservice;
	@Autowired
	InterestService interestService;
	@Autowired
	ReportService reportService;
	@Autowired
	TrainerService trainerService;
	@Autowired
	HistoryService historyService; // 히스토리서비스 추가
	@Autowired
	AttendanceService attendanceService; // 히스토리서비스 추가
	@Autowired
	MyPageAuctionService myPageAuctionService; // 경매내역서비스추가

	@Autowired
	WorkOutPickMemberService workOutPickMemberService; // 추가
	@Autowired
	AuctionService auctionService; //0227추가

	@Autowired
	JavaMailSender mailSender;

	// 수정
	@RequestMapping("/login_action")
	public String loginAction(MemberVo param, HttpSession session, AttendanceVo vo) {

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

		// MemberVo Manager = managerService.ManagerLogin(param);

		// System.out.println("22"+ Manager.getMbr_id());

		MemberVo user = memberService.login(param);

		if (user == null) {
			return "redirect:./login_page";
		} else {
			session.setAttribute("sessionUserData", user);
			vo.setMbr_idx(user.getMbr_idx());
			attendanceService.insertAttendace(vo);
		}

		return "redirect:./main_page";
	}

	@RequestMapping("logout_action")
	public String logoutAction(HttpSession session) {
		session.invalidate();
		return "login_page";
	}

	@RequestMapping("/main_page")
	public String mainPage(HttpSession session, Model model) {
		model.addAttribute("sessionUserData", session.getAttribute("sessionUserData"));

		return "main_page";
	}

	// 수정
	@RequestMapping("/signup_action")
	public String signupAction(MemberVo param, String mbr_year, String mbr_month, String mbr_day) {

		param.setMbr_bth(mbr_year + mbr_month + mbr_day);

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

		memberService.signUp(param);

		return "redirect:./login_page";
	}

//	@RequestMapping("/signup_complete")
//	public String signupComplete() {
//		return "signup_complete";
//	}

	@RequestMapping("/login_page")
	public String loginPage() {
		return "login_page";
	}

	@RequestMapping("/signup_page")
	public String signUp() {
		return "signup_page";
	}

	@RequestMapping("/pw_check")
	public String pwCheck(HttpSession session) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

		if (sessionUser == null) {
			return "/login_page";
		}
		
		return "pw_check";
	}

	// 수정
	@RequestMapping("/pw_check_action")
	public String pwCheckAction(MemberVo param, HttpSession session) {

		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());

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

		MemberVo user = memberService.confirmPw(param);

		if (user == null) {
			return "pw_check";
		}
		return "redirect:./mypage_update";
	}

	@RequestMapping("/mypage_update")
	public String mypageUpdateInfo(Model model, HttpSession session, MemberVo param, MemberImgVo img) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

		if (sessionUser == null) {
			return "login_page";
		}
		
		MemberVo usertrnrInfo = memberService.selectMemberByIdx(sessionUser.getMbr_idx());
		TrainerVerificationVo userVrfInfo = trainerService.getAplByMbrIdx(sessionUser.getMbr_idx());
		List<InterestVo> interestList = interestService.listInterest(sessionUser.getMbr_idx());

		// 체크박스 구현을 위한 해쉬맵 추가
		HashMap<String, String> interestMap = new HashMap<String, String>();
		for (InterestVo vo : interestList) {
			System.out.println("[teesest]" + vo.getWrk_cat());
			interestMap.put("wrk_cat" + vo.getWrk_cat(), "xxx");
		}

		// 프로필사진불러오기
		img.setMbr_idx(sessionUser.getMbr_idx());
		HashMap<String, Object> memberImg = memberService.getMemberImg(img.getMbr_idx());
		model.addAttribute("memberImg", memberImg);
		// System.out.println("jjjjjjjjjjjjjjjj"+((MemberImgVo)memberImg.get("memberImgVo")).getMbri_url());

		model.addAttribute("bodyData", bodyservice.getBody(sessionUser.getMbr_idx()));
		model.addAttribute("userVrfInfo", userVrfInfo);
		model.addAttribute("usertrnrInfo", usertrnrInfo);
		model.addAttribute("interestMap", interestMap);

		return "mypage_update";
	}

	@RequestMapping("/mypage_update_action")
	public String mypageUpdateAction(MemberVo param, HttpSession session, BodyVo vo, InterestVo iv, String[] wrk_cat,
			MultipartFile[] url, MemberImgVo img) {

		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());

		memberService.modifyInfo(param);
		sessionUser.setMbr_nick(param.getMbr_nick());
		sessionUser.setMbr_emil(param.getMbr_emil());

		BodyVo body = bodyservice.confirmBodyInfo(sessionUser.getMbr_idx());
		vo.setMbr_idx(sessionUser.getMbr_idx());

		if (body == null) {
			bodyservice.insertBody(vo);
		}

		bodyservice.updateBodyInfo(vo);

		iv.setMbr_idx(sessionUser.getMbr_idx());
		interestService.deleteInterest(iv); // 추가

		if (wrk_cat != null) {
			for (String interest : wrk_cat) {

				iv.setWrk_cat(interest);
				interestService.insertInterest(iv);

//				System.out.println("kkkk:" + wrk_cat.length);
//				System.out.println("mbr_idx" + iv.getMbr_idx());
//				System.out.println("interest" + iv.getWrk_cat());

			}
		}

		// 프로필추가
		String uploadRootFolder = "C:\\David_upload\\David_member_profile\\";

		Date time = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String folder = dateformat.format(time);

		folder = folder.replace("-", File.separator);

		File uploadFolder = new File(uploadRootFolder + folder);

		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}

		int count = 0;
		for (MultipartFile file : url) {

			if (file.getSize() <= 0) {
				continue;
			}
			count++;
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + System.currentTimeMillis();

			String oriFilename = file.getOriginalFilename();

			fileName += oriFilename.substring(oriFilename.lastIndexOf("."));

			File uploadFile = new File(uploadFolder, fileName);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String folderTemp = folder.replace(File.separator, "/");
			img.setMbri_url("David_member_profile/" + folderTemp + "/" + fileName);
		}

		img.setMbr_idx(sessionUser.getMbr_idx());
		System.out.println("ddd" + img.getMbri_url());

		if (count != 0) {
			memberService.deleteImg(img);
			memberService.insertImg(img);
		}

		return "redirect:./mypage_update_complete";

	}

	@RequestMapping("/delete_member_img_action")
	public String deleteMemberImg(MemberImgVo img, HttpSession session) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

		img.setMbr_idx(sessionUser.getMbr_idx());
		memberService.deleteImg(img);

		return "mypage_update";
	}

	@RequestMapping("/mypage_update_complete")
	public String modifyInfoComplete() {
		return "mypage_update_complete";
	}

	@RequestMapping("/pw_change")
	public String changePw() {
		return "pw_change";
	}

	// 수정
	@RequestMapping("/pw_change_action")
	public String changePwAction(MemberVo param, HttpSession session, String new_pw, String curr_pw, String mbr_idx) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());

		/////////////////////////
		// 비밀번호 보안처리
		curr_pw = curr_pw + "@DAVID";// aaaa+@DAVID

		String hashCode = null;

		StringBuilder sb = new StringBuilder();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(curr_pw.getBytes("UTF-8"));

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

		MemberVo confirm = memberService.confirmPw(param);

		/////////
		if (confirm == null) {
			// ��й�ȣ Ʋ�����
			System.out.println("���� ��� Ʋ��");
			return "pw_change";
		}

		MemberVo newPwMember = new MemberVo();
		newPwMember.setMbr_idx(sessionUser.getMbr_idx());
		newPwMember.setMbr_pw(new_pw);

		memberService.changePw(newPwMember);

		sessionUser.setMbr_pw(newPwMember.getMbr_pw());

		return "redirect:./mypage_update";
	}

	// 히스토리 수정
	@RequestMapping("/mypage_history")
	public String history(Model model, HttpSession session, BoardVo board, WorkOutVo work, My_flexContentVo myFlex,
			BoardReplyVo boardReply, WorkOutReplyVo workReply, My_flex_replyVo myFlexReply,
			WorkOutPickMemberVo workOutPick, AttendanceVo attendance, String r_num) {
		// 일반게시판
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		board.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> boardMyWritingList = historyService.getBoardContentList(board.getMbr_idx());
		model.addAttribute("boardMyWritingList", boardMyWritingList);

		// 운동합시다 게시판
		work.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> workOutMyWritingList = historyService.getWorkOutContentList(work.getMbr_idx());
		model.addAttribute("workOutMyWritingList", workOutMyWritingList);
		System.out.println("11" + model);

		// 마이플렉스 게시판
		myFlex.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> myFlexMyWritingList = historyService.getMyFlexContentList(myFlex.getMbr_idx());
		model.addAttribute("myFlexMyWritingList", myFlexMyWritingList);

		// 댓글
		// 일반게시판 댓글
		boardReply.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> boardMyReplyList = historyService.getBoardReplyList(boardReply.getMbr_idx());
		model.addAttribute("boardMyReplyList", boardMyReplyList);

		// 운동합시다 댓글
		workReply.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> workOutMyReplyList = historyService.getWorkOutReplyList(workReply.getMbr_idx());
		model.addAttribute("workOutMyReplyList", workOutMyReplyList);

		// 마이플랙스 댓글
		myFlexReply.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> myFlexMyReplyList = historyService.getMyFlexReplyList(myFlexReply.getMbr_idx());
		model.addAttribute("myFlexMyReplyList", myFlexMyReplyList);

		// 운동합시다 찜 목록
		workOutPick.setMbr_idx(sessionUser.getMbr_idx());
		workOutPick.setWrk_idx(workOutPick.getWrk_idx());
		List<HashMap<String, Object>> workOutPickList = historyService.getWorkOutPickList(workOutPick.getMbr_idx());
		model.addAttribute("workOutPickList", workOutPickList);

		// 출석현황리스트추가
		attendance.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> attendanceList = attendanceService.getAttendList(attendance.getMbr_idx());
		model.addAttribute("attendanceList", attendanceList);
		System.out.println("fff" + model);

		return "mypage_history";
	}

	// 경매내역 추가
	@RequestMapping("/mypage_auction_info")
	public String ActionList(Model model, HttpSession session, AuctionInfoVo auction, AuctionReplyVo auctionReply,
			BidInfoVo bid) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

		// 쇼핑합시다 게시판
		auction.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> auctionInfoMyWritingList = myPageAuctionService.getAuctionContentList(auction);
		model.addAttribute("auctionInfoMyWritingList", auctionInfoMyWritingList);

		// 쇼핑합시다 댓글
		auctionReply.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> auctionMyReplyList = myPageAuctionService
				.getAuctionReplyList(auctionReply.getMbr_idx());
		model.addAttribute("auctionMyReplyList", auctionMyReplyList);

		System.out.println("dff" + auctionReply.getMbr_idx());

		System.out.println("dd" + model);
		System.out.println("ff" + auctionMyReplyList);

		// 낙찰물품
		bid.setMbr_idx(sessionUser.getMbr_idx());
		List<HashMap<String, Object>> myBidInfoList = myPageAuctionService.getBidList(bid.getMbr_idx());
		model.addAttribute("myBidInfoList", myBidInfoList);
		System.out.println("hhh" + model);

		return "mypage_auction_info";
	}

	// 추가
	@RequestMapping("/my_pick_video")
	public String MyPickVideo(Model model, HttpSession session, WorkOutPickMemberVo workOutPick) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		// 운동합시다 찜 목록
		workOutPick.setMbr_idx(sessionUser.getMbr_idx());
		workOutPick.setWrk_idx(workOutPick.getWrk_idx());
		List<HashMap<String, Object>> workOutPickList = historyService.getWorkOutPickList(workOutPick.getMbr_idx());
		model.addAttribute("workOutPickList", workOutPickList);
		return "my_pick_video";
	}

	@RequestMapping("/my_pick_delete")
	public String MyPickDelete(WorkOutPickMemberVo param, HttpSession session) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		HashMap<String, Object> isPick = new HashMap<String, Object>();
		workOutPickMemberService.deletepickWorkOut(param);
		isPick.put("isPick","unPick");

		return "redirect:./my_pick_video";
	}
	
	//0227추가
	@RequestMapping("/my_auction_reply_delete")
	public String myReplyDeleteAction(AuctionReplyVo param, HttpSession session) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		auctionService.deleteReply(param);

		return "redirect:./mypage_auction_info";
	}
	
	@RequestMapping("my_board_detail_list")
	public String myBoardListDetail(HttpSession session, Model model, String n_r, String n_p) {
		String mbr_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		
		if (n_p == null & n_r == null) {
			n_p = "1";
			n_r = "1";
		}
		List<HashMap<String, Object>> list = historyService.getBoardContentListByPaging(mbr_idx, n_p);
		String count = historyService.getMyBoardCount(mbr_idx);
		model.addAttribute("boardMyWritingList", list);
		model.addAttribute("nowRound", n_r);
		model.addAttribute("count", count);
		
		
		return "my_board_detail_list";
	}
	
	@RequestMapping("/my_flex_detail_list")
	public String myFlexListDetail(HttpSession session, Model model, String n_r, String n_p) {
		String mbr_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		
		if(n_p == null & n_r == null) {
			n_p = "1";
			n_r = "1";
		}
		List<HashMap<String, Object>> list = historyService.getMyFlexContentListByPaging(mbr_idx, n_p);
		String count = historyService.getMyFlexCount(mbr_idx);
		model.addAttribute("myFlexMyWritingList", list);
		model.addAttribute("nowRound", n_r);
		model.addAttribute("count", count);
		return "my_flex_detail_list";
		
	}
	
	@RequestMapping("/my_work_detail_list")
	public String workOutListDetail(HttpSession session, Model model, String n_r, String n_p) {
		String mbr_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		
		if(n_p == null && n_r == null) {
			n_p = "1";
			n_r = "1";
		}
		
		List<HashMap<String, Object>> list = historyService.getWorkOutContentListByPaging(mbr_idx, n_p);
		String count = historyService.getMyWorkOutCount(mbr_idx);
		model.addAttribute("workOutMyWritingList", list);
		model.addAttribute("nowRound", n_r);
		model.addAttribute("count", count);
		return null;
	}
	
	@RequestMapping("my_board_reply_detail")
	public String myBoardReplyListDetail(HttpSession session, Model model, String n_r, String n_p) {
		String mbr_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		
		if (n_p == null & n_r == null) {
			n_p = "1";
			n_r = "1";
		}
		List<HashMap<String, Object>> list = historyService.getBoardReplyListByPaging(mbr_idx, n_p);
		String count = historyService.getMyBoardReplyCount(mbr_idx);
		model.addAttribute("boardReplyMyWritingList", list);
		model.addAttribute("nowRound", n_r);
		model.addAttribute("count", count);
		
		
		return "my_board_reply_detail";
	}
	
	@RequestMapping("/my_flex_reply_detail")
	public String myFelxReplyListDetail(HttpSession session, Model model, String n_r, String n_p) {
		String mbr_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		
		if(n_p == null && n_r == null) {
			n_p = "1";
			n_r = "1";
		}
		List<HashMap<String, Object>> list = historyService.getMyFlexReplyListByPaging(mbr_idx, n_p);
		String count = historyService.getMyFelxReplyCount(mbr_idx);
		model.addAttribute("myFlexReplyMyWritingList", list);
		model.addAttribute("nowRound", n_r);
		model.addAttribute("count", count);
		
		return "my_flex_reply_detail";
	}
	
	@RequestMapping("/my_work_reply_detail")
	public String myWorkOutReplyListDetail(HttpSession session, Model model, String n_r, String n_p) {
		String mbr_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		
		if(n_p == null && n_r == null) {
			n_p = "1";
			n_r = "1";
		}
		
		List<HashMap<String, Object>> list = historyService.getWorkOutReplyListByPaging(mbr_idx, n_p);
		String count = historyService.getMyWorkOutCount(mbr_idx);
		model.addAttribute("wokrOutReplyMyWritingList", list);
		model.addAttribute("nowRound", n_r);
		model.addAttribute("count", count);
		
		return "my_work_reply_detail";
	}


	// 회원가입 아이디 중복확인
	@RequestMapping("/id_check_action")
	@ResponseBody
	public String signUpIdCheckAction(String mbr_id) {
		System.out.println("mbr_id" + mbr_id);

		if (memberService.confirmId(mbr_id)) {
			return "true";
		} else {
			return "false";
		}

	}

	// email 인증
	@RequestMapping("/confirm_email_action")
	@ResponseBody
	public void test() throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = null;
		MimeMessageHelper messageHelper = null;
		message = mailSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");

		messageHelper.setSubject("123");
		messageHelper.setText("123", true);
		messageHelper.setFrom("123", "123");

		messageHelper.setTo("ysmin9503@naver.com");

		// messageHelper.addInline(contentId, dataSource);

		mailSender.send(message);

	}

}
