package com.youngbj.choongang.vo;

public class ChattingContentVo {
		private String room_idx;
		private String sender_idx;
		private String chat_con;
		private String chat_time;
		//생성자
		public ChattingContentVo() {
			super();
		}
		public ChattingContentVo(String room_idx, String sender_idx, String chat_con, String chat_time) {
			super();
			this.room_idx = room_idx;
			this.sender_idx = sender_idx;
			this.chat_con = chat_con;
			this.chat_time = chat_time;
		}
		//게터세터
		public String getRoom_idx() {
			return room_idx;
		}
		public void setRoom_idx(String room_idx) {
			this.room_idx = room_idx;
		}
		public String getSender_idx() {
			return sender_idx;
		}
		public void setSender_idx(String sender_idx) {
			this.sender_idx = sender_idx;
		}
		public String getChat_con() {
			return chat_con;
		}
		public void setChat_con(String chat_con) {
			this.chat_con = chat_con;
		}
		public String getChat_time() {
			return chat_time;
		}
		public void setChat_time(String chat_time) {
			this.chat_time = chat_time;
		}
		
}
