package com.youngbj.choongang.vo;

public class ChattingRoomVo {
	private String room_idx;
	private String rct_chat;
	private String chat_time;
	private String room_rdat;
	//생성자
	public ChattingRoomVo() {
		super();
	}
	public ChattingRoomVo(String room_idx, String rct_chat, String chat_time, String room_rdat) {
		super();
		this.room_idx = room_idx;
		this.rct_chat = rct_chat;
		this.chat_time = chat_time;
		this.room_rdat = room_rdat;
	}
	//게터세터
	public String getRoom_idx() {
		return room_idx;
	}
	public void setRoom_idx(String room_idx) {
		this.room_idx = room_idx;
	}
	public String getRct_chat() {
		return rct_chat;
	}
	public void setRct_chat(String rct_chat) {
		this.rct_chat = rct_chat;
	}
	public String getChat_time() {
		return chat_time;
	}
	public void setChat_time(String chat_time) {
		this.chat_time = chat_time;
	}
	public String getRoom_rdat() {
		return room_rdat;
	}
	public void setRoom_rdat(String room_rdat) {
		this.room_rdat = room_rdat;
	}
	
	
	
}
