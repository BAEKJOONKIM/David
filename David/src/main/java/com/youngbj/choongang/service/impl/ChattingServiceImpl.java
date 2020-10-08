package com.youngbj.choongang.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.etc.CharacterEncoding;
import com.youngbj.choongang.mapper.ChattingSQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.ChattingService;
import com.youngbj.choongang.vo.ChattingContentVo;
import com.youngbj.choongang.vo.ChattingMemberVo;
import com.youngbj.choongang.vo.ChattingRoomVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class ChattingServiceImpl implements ChattingService{
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	ChattingSQLMapper chattingSQLMapper;

	@Override
	public ArrayList<HashMap<String, Object>> getChatContent(String room_idx) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		ArrayList<ChattingContentVo> list = chattingSQLMapper.selectContentAllByRoomIdx(room_idx);
		
		for(ChattingContentVo content : list) {
			HashMap<String, Object> con = new HashMap<String, Object>();
			String sender = content.getSender_idx();
			if(sender.equals("0")) {
				MemberVo memberVo = new MemberVo();
				memberVo.setMbr_nick("system");
				con.put("memberVo",memberVo);
			}else {
				MemberVo memberVo = memberSQLMapper.selectIdx(sender);
				con.put("memberVo", memberVo);
			}
			String temp = CharacterEncoding.encode(content.getChat_con());
			content.setChat_con(temp);
			con.put("content", content);
			result.add(con);
		}
		
		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getRoomList(ChattingMemberVo vo) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		String mbr_idx = vo.getMbr_idx();
		ArrayList<ChattingRoomVo> roomList = chattingSQLMapper.selectRoomAll(mbr_idx);
		for(ChattingRoomVo room : roomList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			ArrayList<ChattingMemberVo> memberList = chattingSQLMapper.selectMemberAllByRoomIdx(room.getRoom_idx());
			
			map.put("room", room);
			map.put("memberList", memberList);
			result.add(map);
		}
		return result;
	}

	@Override
	public void chat(ChattingContentVo vo, String nowTime) {
		String rct_chat = vo.getChat_con();
		chattingSQLMapper.insertChattingContent(vo);
		System.out.println("vo : "+rct_chat+" room:" +vo.getRoom_idx());
		chattingSQLMapper.updateRoomLastChat(rct_chat, vo.getRoom_idx());
		System.out.println("vo : "+rct_chat+" room:" +vo.getRoom_idx());
		chattingSQLMapper.updateRoomChattime(nowTime, vo.getRoom_idx());
		chattingSQLMapper.updateMemberChattime(nowTime, vo.getRoom_idx()); 
		
	}

	@Override
	public void enterRoom(ChattingMemberVo vo) {
		MemberVo member = memberSQLMapper.selectIdx(vo.getMbr_idx());
		vo.setMbr_nick(member.getMbr_nick());
		chattingSQLMapper.insertChattingMember(vo);
		ChattingContentVo enter = new ChattingContentVo();
		enter.setRoom_idx(vo.getRoom_idx());
		enter.setSender_idx("0");
		enter.setChat_con(vo.getMbr_nick()+"님이 입장하셨습니다.");
		chattingSQLMapper.insertChattingContent(enter);
		
	}

	@Override
	public void outRoom(ChattingMemberVo vo) {
		chattingSQLMapper.deleteMember(vo);
		ChattingContentVo out = new ChattingContentVo();
		out.setRoom_idx(vo.getRoom_idx());
		out.setSender_idx("0");
		out.setChat_con(vo.getMbr_nick()+"님이 퇴장하셨습니다.");
		chattingSQLMapper.insertChattingContent(out);
	}

	@Override
	public String makeChatRoom() {
		ChattingRoomVo newVo = new ChattingRoomVo();
		String room_idx = chattingSQLMapper.makeChatRoom();
		newVo.setRoom_idx(room_idx);
		chattingSQLMapper.insertChattingRoom(newVo);
		return room_idx;
	}
	
	

}
