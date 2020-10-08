package com.youngbj.choongang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.InterestVo;

public interface InterestSQLMapper {
	
	@Select("SELECT * FROM Interest_part WHERE mbr_idx=#{mbr_idx}")
	public InterestVo selectIdx(InterestVo vo);
	
	@Insert("INSERT INTO Interest_part VALUES(#{mbr_idx},#{wrk_cat})")
	public void insertInterest(InterestVo vo);
	
	@Update("UPDATE Interest_parte SET wrk_cat=#{wrk_cat} WHERE mbr_idx=#{mbr_idx}")
	public void updateInterest(String mbr_idx);
	
	//추가
	@Delete("DELETE FROM Interest_part WHERE mbr_idx=#{mbr_idx}")
	public void deleteInterest(InterestVo vo);
	
	@Select("SELECT * FROM Interest_part WHERE mbr_idx=#{mbr_idx}")
	public List<InterestVo> listByIdx(String mbr_idx);
}
