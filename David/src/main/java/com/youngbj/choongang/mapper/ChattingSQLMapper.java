package com.youngbj.choongang.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.ChattingContentVo;
import com.youngbj.choongang.vo.ChattingMemberVo;
import com.youngbj.choongang.vo.ChattingRoomVo;

public interface ChattingSQLMapper {
	
	//select
	@Select("SELECT * FROM CHAT_ROOM WHERE room_idx=#{room_idx}")
	public ChattingRoomVo selectRoom(String room_idx);
	
	@Select("SELECT * FROM CHAT_MEMBER WHERE mbr_idx=#{mbr_idx} ORDER BY CHAT_TIME DESC")
	public ArrayList<ChattingRoomVo> selectRoomAll(String mbr_idx);
	
	@Select("SELECT * FROM CHAT_MEMBER WHERE room_idx=#{room_idx}")
	public ArrayList<ChattingMemberVo> selectMemberAllByRoomIdx(String room_idx);
	
	@Select("SELECT * FROM CHAT_CONTENT WHERE room_idx=#{room_idx} ORDER BY chat_time")
	public ArrayList<ChattingContentVo> selectContentAllByRoomIdx(String room_idx);
	
	@Select("SELECT seq_chat_room.nextval FROM DUAL")
	public String makeChatRoom();
	
	
	//insert
	@Insert("INSERT INTO CHAT_ROOM VALUES(#{room_idx},'',SYSDATE,SYSDATE)")
	public void insertChattingRoom(ChattingRoomVo vo);
	
	@Insert("INSERT INTO CHAT_MEMBER VALUES(#{room_idx},#{mbr_idx},#{mbr_nick},SYSDATE)")
	public void insertChattingMember(ChattingMemberVo vo);
	
	@Insert("INSERT INTO CHAT_Content VALUES(#{room_idx},#{sender_idx},#{chat_con},SYSDATE)")
	public void insertChattingContent(ChattingContentVo vo);
	
	
	//update
	@Update("UPDATE CHAT_ROOM SET rct_chat=#{rct_chat} WHERE room_idx=#{room_idx}")
	public void updateRoomLastChat(@Param("rct_chat") String rct_chat ,@Param("room_idx") String room_idx);
	
	@Update("UPDATE CHAT_ROOM SET chat_time=TO_DATE(#{chat_time},'YYYY/MM/DD hh24:mi:ss') WHERE room_idx=#{room_idx}")
	public void updateRoomChattime(@Param("chat_time") String chat_time, @Param("room_idx") String room_idx);
	
	@Update("UPDATE CHAT_MEMBER SET chat_time=TO_DATE(#{chat_time},'YYYY/MM/DD hh24:mi:ss') WHERE room_idx=#{room_idx}")
	public void updateMemberChattime(@Param("chat_time") String chat_time, @Param("room_idx") String room_idx);
	
	//delete
	@Delete("DELETE FROM CHAT_MEMBER WHERE room_idx=#{room_idx} AND mbr_idx=#{mbr_idx}")
	public void deleteMember(ChattingMemberVo vo);
	
	
}
