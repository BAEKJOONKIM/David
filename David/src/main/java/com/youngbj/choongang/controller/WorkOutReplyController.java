package com.youngbj.choongang.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youngbj.choongang.service.WorkOutReplyService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.WorkOutReplyVo;

@Controller
public class WorkOutReplyController {

	@Autowired
	WorkOutReplyService workOutReplyService;
	
	@RequestMapping("/write_workout_reply_action")
	@ResponseBody
	public String writeWorkOutReplyAction(WorkOutReplyVo param, HttpSession session) {
		
		MemberVo sessionUser = (MemberVo)session.getAttribute("sessionUserData");
		param.setMbr_idx(sessionUser.getMbr_idx());
		param.setMbr_nick(sessionUser.getMbr_nick());
		workOutReplyService.register(param);
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis();
		String now = date.format(time);
		return now;
	}
	
	@RequestMapping("delete_workout_reply_action")
	public List<HashMap<String, Object>>  deleteWorkOutReplyAction(String wrpl_idx, String wrk_idx) {
		
		
		workOutReplyService.deleteWorkOutReply(wrpl_idx);
		List<HashMap<String, Object>> replyList = workOutReplyService.replyAndImg(wrk_idx);
		
		return replyList;
	}
	
	
}
