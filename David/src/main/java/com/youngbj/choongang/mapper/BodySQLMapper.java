package com.youngbj.choongang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.BodyVo;

public interface BodySQLMapper {
	
	@Insert("INSERT INTO Body_info VALUES (#{mbr_idx},#{bd_height},#{bd_weight},#{bd_age})")
	public void insertBodyInfo(BodyVo vo);
	
	@Select("SELECT * FROM Body_info")
	public List<BodyVo> selectAll(); 
	
	@Select("SELECT * FROM Body_info WHERE mbr_idx=#{mbr_idx}")
	public BodyVo selectByIdx(String mbr_idx); 
	
	@Update("UPDATE Body_info SET bd_height=#{bd_height}, bd_weight=#{bd_weight}, bd_age=#{bd_age} WHERE mbr_idx=#{mbr_idx}")
	public void updateBodyInfo(BodyVo vo);

}
