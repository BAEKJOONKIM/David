package com.youngbj.choongang.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.youngbj.choongang.mapper.My_flex_like_memberSQLMapper;
import com.youngbj.choongang.service.My_flexContentService;
import com.youngbj.choongang.service.My_flex_like_memberService;
import com.youngbj.choongang.service.My_flex_replyService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_like_memberVo;
import com.youngbj.choongang.vo.My_flex_replyVo;

@Controller
public class My_flexContentController {

	@Autowired
	private My_flexContentService contentService;

	@Autowired
	private My_flex_replyService replycontentService;
	@Autowired
	private My_flex_like_memberService myFlexLikeService;

	@RequestMapping("/")
	public String main(Model model) {
		return mainPage(model, null, null, null);
	}

	@RequestMapping("/flex_main_page")
	public String mainPage(Model model, String searchType, String searchData, String flx_page) {

		if (searchData != null && searchData.equals("")) {
			searchData = null;
		}
		if (flx_page == null && searchData == null) {
			List<HashMap<String, Object>> page = contentService.page("1");
			model.addAttribute("page", page);			

		} else if (flx_page != null && searchData == null) {
			List<HashMap<String, Object>> page = contentService.page(flx_page);
			model.addAttribute("page", page);

		}

		if (searchType == null) {
			List<HashMap<String, Object>> list = contentService.getContentList();

			model.addAttribute("dataList", list);

		} else {
			List<HashMap<String, Object>> searchlist = contentService.getsearchList(searchType, searchData);

			model.addAttribute("searchList", searchlist);
			model.addAttribute("searchData", searchData);

		}

		return "flex_main_page";

	}

	@RequestMapping("/flex_writecontent_page")
	public String writeContentPage() {

		return "flex_writecontent_page";
	}

	@RequestMapping("/write_content_action")
	public String writeContentAction(My_flexContentVo param, HttpSession session, MultipartFile[] iurl) {

		// ArrayList<My_flexContentVo> listContentUploadFileVo = new
		// ArrayList<My_flexContentVo>();

		// 폴더 생성

		String uploadRootFolder = "C:\\David_upload\\my_flex_upload\\";

		Date time = new Date();

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		String folder = dateformat.format(time);

		folder = folder.replace("-", File.separator);

		File uploadFolder = new File(uploadRootFolder + folder);

		if (!uploadFolder.exists()) {

			uploadFolder.mkdirs();

		}

		// 파일을 서버에 보내서 저장

		for (MultipartFile file : iurl) {

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

			param.setFlx_iurl("my_flex_upload/" + folderTemp + "/" + fileName);
			param.setFlx_vurl("null");

		}

		// DB에 저장

		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

		param.setMbr_idx(sessionUser.getMbr_idx());

		contentService.writeContent(param);

		return "redirect:./flex_main_page";
	}

	// @RequestMapping("/write_content_action")
	// public String writeContentAction(My_flexContentVo param, HttpSession session,
	// MultipartFile[] upload_file) {

	// MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

	// param.setMbr_idx(sessionUser.getMbr_idx());

	// contentService.writeContent(param);

	// return "redirect:./flex_main_page";
	// }

	@RequestMapping("/flex_readcontent_page")
	public String readContentPage(String flx_idx, Model model, HttpSession session) {

		contentService.updateReadCount(flx_idx);

		HashMap<String, Object> map = contentService.getContent(flx_idx);

		model.addAttribute("data", map);

		// -----------------------------------------------------------
		if (session.getAttribute("sessionUserData") != null) {
			My_flex_like_memberVo likeMember = new My_flex_like_memberVo();
			likeMember.setFlx_idx(flx_idx);
			likeMember.setMbr_idx(((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx());

			model.addAttribute("like", myFlexLikeService.getContent(likeMember));

			List<HashMap<String, Object>> list = replycontentService.getContentList(flx_idx);

			model.addAttribute("replydataList", list);
		}
		// ----------------------------------------------------------------
		My_flex_like_memberVo vo = new My_flex_like_memberVo();
		vo.setFlx_idx(flx_idx);

		String count = myFlexLikeService.likecount(vo);
		model.addAttribute("likeData", count);
		// ---------------------------------------------------------------
		My_flexContentVo rnum = new My_flexContentVo();
		rnum.setFlx_idx(flx_idx);

		model.addAttribute("rnum", rnum);

		return "flex_readcontent_page";
	}

	@RequestMapping("/delete_content_action")
	public String deleteContentAction(String flx_idx) {

		contentService.deleteContent(flx_idx);

		return "redirect:./flex_main_page";
	}

	@RequestMapping("/flex_updatecontent_page")
	public String updateContentPage(String flx_idx, Model model) {

		model.addAttribute("data", contentService.getContent(flx_idx));

		return "flex_updatecontent_page";
	}

	@RequestMapping("/update_content_action")
	public String updateContentAction(My_flexContentVo param, HttpSession session, MultipartFile[] iurl) {

		// 폴더 생성

		String uploadRootFolder = "C:\\David_upload\\my_flex_upload\\";

		Date time = new Date();

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		String folder = dateformat.format(time);

		folder = folder.replace("-", File.separator);

		File uploadFolder = new File(uploadRootFolder + folder);

		if (!uploadFolder.exists()) {

			uploadFolder.mkdirs();

		}

		// 파일을 서버에 보내서 저장

		for (MultipartFile file : iurl) {

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

			param.setFlx_iurl("my_flex_upload/" + folderTemp + "/" + fileName);
			param.setFlx_vurl("null");

		}

		// DB에 저장

		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

		param.setMbr_idx(sessionUser.getMbr_idx());
		contentService.updateContent(param);

		return "redirect:./flex_readcontent_page?flx_idx="+param.getFlx_idx();
	}

}
