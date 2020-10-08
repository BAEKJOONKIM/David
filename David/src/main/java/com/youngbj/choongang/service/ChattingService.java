package com.youngbj.choongang.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.youngbj.choongang.vo.ChattingContentVo;
import com.youngbj.choongang.vo.ChattingMemberVo;
import com.youngbj.choongang.vo.ChattingRoomVo;

public interface ChattingService {
	public ArrayList<HashMap<String, Object>> getChatContent(String room_idx);
	
	public ArrayList<HashMap<String, Object>> getRoomList(ChattingMemberVo vo);
	
	public void chat(ChattingContentVo vo, String nowTime);
	
	public void enterRoom(ChattingMemberVo vo);
	
	public void outRoom(ChattingMemberVo vo);
	
	public String makeChatRoom();
	
	
}
