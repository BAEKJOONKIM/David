package com.youngbj.choongang.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youngbj.choongang.service.BoardReplyService;
import com.youngbj.choongang.vo.BoardReplyVo;
import com.youngbj.choongang.vo.MemberVo;

@Controller
public class BoardReplyController {

	@Autowired
	BoardReplyService boardReplyService;

	//임소청 수정
	@RequestMapping("board_write_reply_action")
	public String boardWriteReply(BoardReplyVo param, HttpSession session, String brpl_con, String brd_idx ) {
		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");


		param.setMbr_idx(sessionUser.getMbr_idx());
		param.setBrd_idx(brd_idx);
		param.setBrpl_con(brpl_con);
		
		boardReplyService.boardWriteReply(param);

		return "redirect:./board_read_page?brd_idx=" + param.getBrd_idx();

	}

	@RequestMapping("board_delete_reply_action")
	@ResponseBody
	public String boardDeleteReply(BoardReplyVo vo) {
		
		boardReplyService.boardDeleteReply(vo);
		
		return "이미 ajax로 했기 때문에 return이 의미없어졌다";
	}

}
