package com.youngbj.choongang.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.InquiryVo;

public interface InquirySQLMapper {

	@Insert("INSERT INTO Inquiry VALUES(seq_inquiry.nextval, #{mbr_idx}, #{inq_ttl}, #{inq_con}, 'N', SYSDATE)")
	public void insertInquiry(InquiryVo vo);

	@Select("SELECT * FROM Member_info, Inquiry WHERE Member_info.mbr_idx = Inquiry.mbr_idx AND Inquiry.mbr_idx=#{mbr_idx} ORDER BY Inquiry.inq_idat DESC")
	public List<InquiryVo> selectByIdx(String mbr_idx);

	@Select("SELECT * FROM Inquiry WHERE inq_idx=#{inq_idx}")
	public InquiryVo selectByInqIdx(String inq_idx);

	@Update("UPDATE Inquiry SET inq_ttl=#{inq_ttl}, inq_con=#{inq_con} WHERE inq_idx = #{inq_idx}")
	public void updateInquiry(InquiryVo inquiryVo);

	@Delete("DELETE FROM Inquiry WHERE inq_idx = #{inq_idx}")
	public void deleteByIdx(String inq_idx);

	//수정
	@Select("SELECT * FROM Member_info MI, Inquiry IQ WHERE MI.mbr_idx = IQ.mbr_idx ORDER BY IQ.inq_idat DESC")
	public List<HashMap<String, Object>> selectByAll();

	@Update("UPDATE Inquiry Set inq_vrf='T' WHERE inq_idx=#{inq_idx}")
	public void updateInqVrf(InquiryVo vo);

	@Update("Update Inquiry Set inq_vrf='N' WHERE inq_idx=#{inq_idx}")
	public void updateInqVrfN(InquiryVo vo);

	// ----------------------------------------------- 검색 ------------------------------------------------
	// mbr_nick으로 검색하면
	@Select("SELECT * FROM  Inquiry IQ, member_info MI WHERE MI.mbr_idx = IQ.mbr_idx AND MI.mbr_nick LIKE '%'||#{keyword}||'%'")
	public List<HashMap<String, Object>> selectAllInquiryInfoSearchMbrNick(String keyword);

	// mbr_vrf으로 검색하면
	@Select("SELECT * FROM  Inquiry IQ, member_info MI WHERE  MI.mbr_idx = IQ.mbr_idx AND IQ.inq_vrf LIKE '%'||#{keyword}||'%'")
	public List<HashMap<String, Object>> selectAllInquiryInfoSearchInqVrf(String keyword);

}
