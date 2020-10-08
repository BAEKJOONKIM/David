package com.youngbj.choongang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youngbj.choongang.service.ChattingService;
import com.youngbj.choongang.vo.ChattingContentVo;
import com.youngbj.choongang.vo.ChattingMemberVo;
import com.youngbj.choongang.vo.ChattingRoomVo;
import com.youngbj.choongang.vo.MemberVo;

@Controller
public class ChattingController {
	@Autowired
	ChattingService chattingService;

	@RequestMapping("view_chat_list")
	@ResponseBody
	public HashMap<String, Object> viewChattingList(HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ChattingMemberVo chatMem = new ChattingMemberVo();
		String mbr_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		if (mbr_idx != null) {
			chatMem.setMbr_idx(mbr_idx);
			ArrayList<HashMap<String, Object>> roomList = chattingService.getRoomList(chatMem);
			
			result.put("result", "success");
			result.put("roomList", roomList);
			
		}else {
			result.put("result", "fail");
			result.put("reason", "로그인 해주세요");
		}
		return result;
	}
	
	@RequestMapping("view_chatting")
	@ResponseBody
	public HashMap<String, Object> viewChatting(HttpSession session, String room_idx){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		ArrayList<HashMap<String, Object>> chat = chattingService.getChatContent(room_idx);
		
		result.put("chat", chat);
		
		return result;
	}
	
	@RequestMapping("write_chat")
	@ResponseBody
	public HashMap<String, Object> writeChatting(HttpSession session, ChattingContentVo param) throws ParseException{
		HashMap<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis();
		String now = dateForm.format(time);
		System.out.println("contentnull : "+param.getChat_con());
		String inChat = param.getChat_con();
		if(inChat == null || inChat.equals("")) {
			result.put("result", "fail");
		}else {
			chattingService.chat(param, now);
			result.put("result", "success");
		}
		
		return result;
	}
	
	@RequestMapping("enter_chat")
	@ResponseBody
	public HashMap<String, Object> enterChat(HttpSession session, ChattingMemberVo param){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		chattingService.enterRoom(param);
		
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping("out_chat")
	@ResponseBody
	public HashMap<String, Object> outChat(HttpSession session, ChattingMemberVo param){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String sessionUserIdx=((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		String tempIdx = param.getMbr_idx();
		if(sessionUserIdx.equals(tempIdx)) {
			chattingService.outRoom(param);	
		}
		
		
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping("start_chat")
	@ResponseBody
	public HashMap<String, Object> startChat(HttpSession session, ChattingMemberVo param){
		HashMap<String, Object> result = new HashMap<String, Object>();
		ChattingMemberVo myChat = new ChattingMemberVo();
		String my_idx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		String room_idx = chattingService.makeChatRoom();
		
		param.setRoom_idx(room_idx);
		myChat.setMbr_idx(my_idx);
		myChat.setRoom_idx(room_idx);
		
		chattingService.enterRoom(param);
		chattingService.enterRoom(myChat);
		
		result.put("result", "success");
		return result;
	}
	

}
