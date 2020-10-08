package com.youngbj.choongang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youngbj.choongang.service.BoardLikeService;
import com.youngbj.choongang.service.BoardReplyService;
import com.youngbj.choongang.service.BoardService;
import com.youngbj.choongang.vo.BoardLikeVo;
import com.youngbj.choongang.vo.BoardVo;
import com.youngbj.choongang.vo.MemberVo;

@Controller
public class BoardController {

	@Autowired
	public BoardService boardService;
	@Autowired
	BoardReplyService boardReplyService;
	@Autowired
	BoardLikeService boardLikeService;

	
//	public String boardMainPage(Model model) {
//		List<HashMap<String, Object>> list = boardService.getBoardList();
//
//		model.addAttribute("dataList", list);
//
//		return "board_main_page";
//	}

	@RequestMapping("/board_read_page")
	public String boardReadPage(String brd_idx, Model model, HttpSession session) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		String aaa = null;
		
		if(sessionUser != null) {
			aaa= sessionUser.getMbr_idx();
					
		}
		
		
		HashMap<String, Object> map = boardService.getContent(brd_idx);
		List<HashMap<String, Object>> replyMap = boardReplyService.getBoardReplyList(brd_idx, aaa);
		
		BoardLikeVo vo = new BoardLikeVo();
		vo.setBrd_idx(brd_idx);
		
		if (session.getAttribute("sessionUserData") != null) {
			vo.setMbr_idx(((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx());
			BoardLikeVo mapLike = boardLikeService.selectByMbrANDBrdidx(vo);
			if (mapLike == null) {
			} else {
				model.addAttribute("dataLike", mapLike);
			}

		}

		String countLike = boardLikeService.countBoardLike(vo);

		model.addAttribute("data", map);
		model.addAttribute("replyData", replyMap);
		model.addAttribute("countLikeData", countLike);

		boardService.updateReadCount(brd_idx);

		return "board_read_page";
	}

	//임소청 수정
	@RequestMapping("/read_new_reply")
	@ResponseBody
	public HashMap<String, Object> readNewReply(String brd_idx,HttpSession session){
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
		String aaa = null;
		
		if(sessionUser != null) {
			aaa= sessionUser.getMbr_idx();
					
		}
		
		HashMap<String, Object> newReply = new HashMap<String, Object>();
		List<HashMap<String, Object>> replyData = boardReplyService.getBoardReplyList(brd_idx,aaa);
	
		newReply.put("replyData", replyData);
		
		return newReply;
	}
	
	@RequestMapping("/delete_Inbox")
	public HashMap<String, Object> deleteInBox(String brd_con){
		
		return null;
		
	}
	
	
	
	@RequestMapping("/board_write_page")
	public String boardWritePage() {

		return "board_write_page";
	}

	@RequestMapping("/board_write_action")
	public String boardWriteAction(BoardVo param, HttpSession session) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");

		param.setMbr_idx(sessionUser.getMbr_idx());

		boardService.insertContent(param);

		return "redirect:./board_main_page";

	}

	@RequestMapping("/board_delete_content_action")
	public String deleteContentAction(BoardVo vo) {

		boardService.deleteContent(vo);

		return "redirect:./board_main_page";
	}

	@RequestMapping("/board_update_page")
	public String boardUpdatePage(BoardVo param, Model model) {

		String brd_idx = param.getBrd_idx();

		HashMap<String, Object> data = boardService.getContent(brd_idx);

		model.addAttribute("data", data);

		return "board_update_page";
	}

	@RequestMapping("board_update_action")
	public String boardUpdateAction(BoardVo param) {
		System.out.println(param.getBrd_idx());
		boardService.updateContent(param);

		return "redirect:./board_read_page?brd_idx="+param.getBrd_idx();
	}
	
	@RequestMapping("board_main_page")
	public String boardMainPage(Model model, String search) {
		
		if (search == null) {
			List<HashMap<String, Object>> list = boardService.getBoardList();
			model.addAttribute("dataList", list);
			model.addAttribute("search", search);
		} else {
			List<HashMap<String, Object>> listBySearch = boardService.getBoardListBySearch(search);
			
			model.addAttribute("dataList", listBySearch);
			model.addAttribute("search", search);
			
		}
		return "board_main_page";
	}

}
