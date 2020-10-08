package com.youngbj.choongang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.InquiryReplyVo;

public interface InquiryReplySQLMapper {
	
	@Insert("INSERT INTO Inquiry_Reply VALUES(seq_inquiry_reply.nextval, #{inq_idx}, #{mbr_idx}, #{irpl_con}, SYSDATE)")
	public void insertInquiryReply(InquiryReplyVo vo);
	
	@Select("SELECT * FROM Inquiry_Reply WHERE inq_idx=#{inq_idx} ORDER BY irpl_idx ")
	public List<InquiryReplyVo> selectByInqIdx(String inq_idx);
	
	@Delete("DELETE FROM Inquiry_reply WHERE irpl_idx=#{irpl_idx} ")
	public void deleteByidx(String irpl_idx);
	
	@Update("UPDATE Inquiry_reply SET irpl_ttl=#{irpl_ttl}, irpl_con=#{irpl_con} WHERE irpl_idx=${irpl_idx}")
	public void updateByidx(InquiryReplyVo vo);
}
