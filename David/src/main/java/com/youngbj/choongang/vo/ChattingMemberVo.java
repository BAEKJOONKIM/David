package com.youngbj.choongang.vo;

public class ChattingMemberVo {
	private String room_idx;
	private String mbr_idx;
	private String mbr_nick;
	private String chat_time;
	
	//생성자
	public ChattingMemberVo() {
		super();
	}
	public ChattingMemberVo(String room_idx, String mbr_idx, String mbr_nick, String chat_time) {
		super();
		this.room_idx = room_idx;
		this.mbr_idx = mbr_idx;
		this.mbr_nick = mbr_nick;
		this.chat_time = chat_time;
	}
	//게터세터
	public String getRoom_idx() {
		return room_idx;
	}
	public void setRoom_idx(String room_idx) {
		this.room_idx = room_idx;
	}
	public String getMbr_idx() {
		return mbr_idx;
	}
	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	public String getMbr_nick() {
		return mbr_nick;
	}
	public void setMbr_nick(String mbr_nick) {
		this.mbr_nick = mbr_nick;
	}
	public String getChat_time() {
		return chat_time;
	}
	public void setChat_time(String chat_time) {
		this.chat_time = chat_time;
	}
	
	
}
