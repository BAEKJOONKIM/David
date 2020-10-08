package com.youngbj.choongang.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.youngbj.choongang.service.MemberService;
import com.youngbj.choongang.service.WorkOutLikeMemberService;
import com.youngbj.choongang.service.WorkOutPickMemberService;
import com.youngbj.choongang.service.WorkOutReplyService;
import com.youngbj.choongang.service.WorkoutService;
import com.youngbj.choongang.vo.MemberImgVo;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.WorkOutLikeMemberVo;
import com.youngbj.choongang.vo.WorkOutPickMemberVo;
import com.youngbj.choongang.vo.WorkOutVo;

@Controller
public class WorkoutController {

	
	@Autowired
	MemberService memberService;
	@Autowired
	WorkoutService workOutService;
	@Autowired
	WorkOutReplyService workOutReplyService;
	@Autowired
	WorkOutLikeMemberService workOutLikeMemberService;
	@Autowired
	WorkOutPickMemberService workOutPickMemberService;

	@RequestMapping("/workout_page")
	public String searchWorkOutPage(Model model, String result, String searchType, String wrk_cp, String wrk_cat, HttpSession session ) {
		System.out.println("운동합시다 페이지");

		System.out.println("result" + result);
		System.out.println("wrk_cp" + wrk_cp);
		System.out.println("wrk_cat" + wrk_cat);

		model.addAttribute("result", result);
		model.addAttribute("searchType", searchType);
		model.addAttribute("wrk_cp", wrk_cp);
		model.addAttribute("wrk_cat", wrk_cat);

		if (result != null && result.equals("")) {
			result = null;
		}
		if (wrk_cat != null && wrk_cat.equals("")) {
			wrk_cat = null;
		}
		
		if (session.getAttribute("sessionUserData") != null) {
			MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
			
			MemberVo usertrnrInfo = memberService.selectMemberByIdx(sessionUser.getMbr_idx());
			System.out.println("usertrnrInfo"+usertrnrInfo);
			model.addAttribute("usertrnrInfo", usertrnrInfo);
			}


		String workOutCount = workOutService.getWorkOutCount();
		System.out.println("workOutCount" + workOutCount);
		model.addAttribute("workOutCount", workOutCount);

		if (wrk_cp == null && result == null && wrk_cat == null) {

			/*
			 * List<HashMap<String, Object>> list = workOutService.getWorkOutList();
			 * model.addAttribute("dataList", list); return "workout_page";
			 */

			List<HashMap<String, Object>> pagelist = workOutService.getWorkOutPageList("1");
			model.addAttribute("pageList", pagelist);
			return "workout_page";

		} else if (wrk_cp != null && result == null && wrk_cat == null) {
			List<HashMap<String, Object>> list = workOutService.getWorkOutPageList(wrk_cp);
			model.addAttribute("pageList", list);
			return "workout_page";
		} else if (wrk_cp == null && result == null && wrk_cat != null) {
			if (wrk_cat.equals("0")) {
				List<HashMap<String, Object>> pagelist = workOutService.getWorkOutPageList("1");
				model.addAttribute("pageList", pagelist);
				return "workout_page";
			} else {
				List<HashMap<String, Object>> categoryList = workOutService.getWorkOutCategoryList(wrk_cat);
				model.addAttribute("pageList", categoryList);
				return "redirect:./workout_page?wrk_cat=" + wrk_cat + "&wrk_cp=" + "1";
			}

		} else if (wrk_cp != null && result == null && wrk_cat != null) {
			if (wrk_cat.equals("0")) {
				List<HashMap<String, Object>> pagelist = workOutService.getWorkOutPageList(wrk_cp);
				model.addAttribute("pageList", pagelist);
				return "workout_page";
			} else {
				List<HashMap<String, Object>> pagelist = workOutService.getWorkOutCategoryPageList(wrk_cat, wrk_cp);
				model.addAttribute("pageList", pagelist);
				return "workout_page";
			}

		} else {

			if(searchType.equals("w")) {
				List<HashMap<String, Object>> searchList = workOutService.getSearchWriterWorkOutPageList(wrk_cp, result,searchType);
				model.addAttribute("dataList", searchList);
				return "workout_page";
			} else {
				List<HashMap<String, Object>> searchList = workOutService.getSearchWorkOutPageList(wrk_cp, result,searchType);
				model.addAttribute("searchList", searchList);
				return "workout_page";
			}
			
			
		}

	}

	@RequestMapping("/workout1_page")
	public String WorkOutPage(Model model, String wrk_cp) {
		System.out.println("운동합시다 페이지");

		List<HashMap<String, Object>> list = workOutService.getWorkOutPageList("1");
		model.addAttribute("dataList", list);
		return "workout_page";

	}

	@RequestMapping("read_workout_page")
	public String readWorkOutPage(String wrk_idx, Model model, WorkOutVo param, HttpSession session, MemberImgVo img) {
		workOutService.updateReadcount(wrk_idx);
		if (session.getAttribute("sessionUserData") != null) {
			MemberVo memberVo = (MemberVo) session.getAttribute("sessionUserData");
			param.setMbr_idx(memberVo.getMbr_idx());
			
			
			img.setMbr_idx(memberVo.getMbr_idx());
			HashMap<String, Object> memberImg = memberService.getMemberImg(img.getMbr_idx());
			model.addAttribute("memberImg", memberImg);
			/*
			 * HashMap<String, Object> map = workOutService.getWorkOuty(wrk_idx,
			 * param.getMbr_idx());
			 */			
			
			List<HashMap<String, Object>> replyimg = workOutReplyService.replyAndImg(wrk_idx);
			model.addAttribute("replyimg", replyimg);
			
			String result = workOutLikeMemberService.countLikeWorkOut(wrk_idx);
			model.addAttribute("like",result);
			
			HashMap<String, Object> map = workOutService.getWorkOut(param);
			model.addAttribute("data", map);

			return "read_workout_page";
		} else {
			String result = workOutLikeMemberService.countLikeWorkOut(wrk_idx);
			model.addAttribute("like",result);
			
			HashMap<String, Object> map = workOutService.getWorkOutx(param);
			model.addAttribute("data", map);

			return "read_workout_page";
		}

	}

	@RequestMapping("/write_workout_page")
	public String writeWorkOutPage(HttpSession session, Model model) {
		System.out.println("운동합시다 글쓰기 페이지");
		
		if (session.getAttribute("sessionUserData") != null) {
			MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
			
			MemberVo usertrnrInfo = memberService.selectMemberByIdx(sessionUser.getMbr_idx());
			System.out.println("usertrnrInfo"+usertrnrInfo);
			model.addAttribute("usertrnrInfo", usertrnrInfo);
			}
		
		return "write_workout_page";
	}

	@RequestMapping("/write_workout_action")
	public String writeWorkOutAction(WorkOutVo param, HttpSession session, MultipartFile[] vurl, MultipartFile[] surl) {
		System.out.println(
				param.getWrk_surl() + " " + ((MemberVo) session.getAttribute("sessionUserData")).getMbr_nick());

		String uploadRootFolder = "C:\\\\David_upload\\\\spring_David_workout_upload\\\\";

		// 폴더 생성
		Date time = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String folder = dateformat.format(time);

		folder = folder.replace("-", File.separator);

		File uploadFolder = new File(uploadRootFolder + folder);

		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}

		// 파일을 서버에 보내서 저장
		for (MultipartFile file : vurl) {
			if (file.getSize() <= 0) {
				continue;
			}
			// 파일명 짓기... 중복 피하기 위해... : 시간 + 랜덤값 조합...
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + System.currentTimeMillis();

			String oriFilename = file.getOriginalFilename();

			fileName += oriFilename.substring(oriFilename.lastIndexOf("."));
			// 업로드...
			File uploadFile = new File(uploadFolder, fileName);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String folderTemp = folder.replace(File.separator, "/");
			param.setWrk_vurl(folderTemp + "/" + fileName);

		}
		for (MultipartFile file : surl) {
			if (file.getSize() <= 0) {
				continue;
			}
			// 파일명 짓기... 중복 피하기 위해... : 시간 + 랜덤값 조합...
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + System.currentTimeMillis();

			String oriFilename = file.getOriginalFilename();

			fileName += oriFilename.substring(oriFilename.lastIndexOf("."));
			// 업로드...
			File uploadFile = new File(uploadFolder, fileName);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String folderTemp = folder.replace(File.separator, "/");
			param.setWrk_surl("spring_David_workout_upload/" + folderTemp + "/" + fileName);

		}

		// DB에 저장
		System.out.println("데이터 저장");
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		workOutService.upLoad(param);

		return "redirect:./workout_page";
	}

	@RequestMapping("delete_workout_action")
	public String deleteWorkOutAction(String wrk_idx, String mbr_idx, HttpSession session) {

		System.out.println("wrk_idx"+wrk_idx);
		System.out.println("mbr_idx"+mbr_idx);
		
		MemberVo sessionUser = (MemberVo)session.getAttribute("sessionUserData");
		System.out.println("sessionUserIdx"+sessionUser.getMbr_idx());
		
		if(sessionUser.getMbr_idx().equals(mbr_idx)) {
			workOutService.deleteWorkOut(wrk_idx);
			return "redirect:./workout_page";

		} else {
			return "redirect:./error";
		}

	}

	@RequestMapping("update_workout_page")
	public String updateWorkOutPage(WorkOutVo param, Model model) {

		model.addAttribute("data", workOutService.getWorkOutx(param));

		return "update_workout_page";
	}

	@RequestMapping("update_workout_action")
	public String updateWorkOutAction(WorkOutVo param, HttpSession session, MultipartFile[] vurl, MultipartFile[] surl) {

		String uploadRootFolder = "C:\\\\David_upload\\\\spring_David_workout_upload\\\\";

		// 폴더 생성
		Date time = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String folder = dateformat.format(time);

		folder = folder.replace("-", File.separator);

		File uploadFolder = new File(uploadRootFolder + folder);

		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}

		// 파일을 서버에 보내서 저장
		for (MultipartFile file : vurl) {
			if (file.getSize() <= 0) {
				continue;
			}
			// 파일명 짓기... 중복 피하기 위해... : 시간 + 랜덤값 조합...
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + System.currentTimeMillis();

			String oriFilename = file.getOriginalFilename();

			fileName += oriFilename.substring(oriFilename.lastIndexOf("."));
			// 업로드...
			File uploadFile = new File(uploadFolder, fileName);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String folderTemp = folder.replace(File.separator, "/");
			param.setWrk_vurl(folderTemp + "/" + fileName);

		}
		for (MultipartFile file : surl) {
			if (file.getSize() <= 0) {
				continue;
			}
			// 파일명 짓기... 중복 피하기 위해... : 시간 + 랜덤값 조합...
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + System.currentTimeMillis();

			String oriFilename = file.getOriginalFilename();

			fileName += oriFilename.substring(oriFilename.lastIndexOf("."));
			// 업로드...
			File uploadFile = new File(uploadFolder, fileName);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String folderTemp = folder.replace(File.separator, "/");
			param.setWrk_surl("spring_David_workout_upload/" + folderTemp + "/" + fileName);

		}

		// DB에 저장
		System.out.println("데이터 저장");
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		workOutService.updateWorkOut(param);

		return "redirect:./workout_page";
	}

	@RequestMapping("/like_workout_action")
	@ResponseBody
	public HashMap<String, Object> likeWorkOutAction(WorkOutLikeMemberVo param, HttpSession session, String wrk_idx) {
		System.out.println("dddd");
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		WorkOutLikeMemberVo isLikeWorkOutUser = workOutLikeMemberService.isLikeWorkOut(param);

		HashMap<String, Object> isLike = new HashMap<String, Object>();

		if (isLikeWorkOutUser == null) {
			workOutLikeMemberService.updateLikePuls(wrk_idx);
			workOutLikeMemberService.likeWorkOut(param);
			isLike.put("isLike", "like");
		} else {
			workOutLikeMemberService.updateLikeMinus(wrk_idx);
			workOutLikeMemberService.deleteLikeWorkOut(param);
			isLike.put("isLike", "unLike");
		}

		return isLike;
	}

	@RequestMapping("/pick_workout_action")
	@ResponseBody
	public HashMap<String, Object> pickWorkOutAction(WorkOutPickMemberVo param, HttpSession session) {

		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		WorkOutPickMemberVo isPickWorkOutUser = workOutPickMemberService.isPickWorkOut(param);

		HashMap<String, Object> isPick = new HashMap<String, Object>();

		
		if (isPickWorkOutUser == null) {
			workOutPickMemberService.pickWorkOut(param);
			isPick.put("isPick","pick");
		} else {
			workOutPickMemberService.deletepickWorkOut(param);
			isPick.put("isPick","unPick");

		}

		return isPick;
	}

	@RequestMapping("/search_workout_action")
	public String searchWorkOutAction(Model model, String result, String searchType, String wrk_cp) {
		System.out.println("페이지 " + wrk_cp);
		System.out.println("검색어 " + result);
		System.out.println("검색유형 " + searchType);

		List<HashMap<String, Object>> searchList = workOutService.getSearchWorkOutPageList(wrk_cp, result, searchType);

		model.addAttribute("searchList", searchList);
		return "workout_page";

	}
	
	@RequestMapping("/report_workout_action")
	public String reportWorkOutAction() {
		return null;
	}
	
	@RequestMapping("/error")
	public String errorPage() {
		return "error";
	}

}
