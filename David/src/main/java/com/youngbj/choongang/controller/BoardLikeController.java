package com.youngbj.choongang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youngbj.choongang.service.BoardLikeService;
import com.youngbj.choongang.vo.BoardLikeVo;
import com.youngbj.choongang.vo.MemberVo;

@Controller
public class BoardLikeController {
	
	@Autowired
	BoardLikeService boardLikeService;
	
	@RequestMapping("/board_like_action")
	public String boardLikeAction(BoardLikeVo param, HttpSession session) {
		
		MemberVo sessionUser= (MemberVo) session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		BoardLikeVo selectByMbrANDBrdidx = boardLikeService.selectByMbrANDBrdidx(param);
		
		if(selectByMbrANDBrdidx == null) {
			boardLikeService.insertBoardLike(param);		
		} else {
			boardLikeService.deleteByMbrANDBrdidx(param);
		}
		
		return "redirect:./board_read_page?brd_idx=" + param.getBrd_idx();
	}

}
