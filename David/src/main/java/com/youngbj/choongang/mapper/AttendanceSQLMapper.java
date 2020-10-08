package com.youngbj.choongang.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.youngbj.choongang.vo.AttendanceVo;

public interface AttendanceSQLMapper {

	@Insert("INSERT INTO Attendance VALUES(#{mbr_idx}, SYSDATE)")
	public void insertAttendance(AttendanceVo vo);

	@Select("SELECT DISTINCT att_date, mbr_idx FROM (SELECT TO_CHAR(att_date, 'YYYY-MM-DD') att_date, Attendance.mbr_idx FROM Member_info, Attendance WHERE Member_info.mbr_idx = Attendance.mbr_idx AND Attendance.mbr_idx = #{mbr_idx}) ORDER BY att_date DESC")
	public List<AttendanceVo> selectByIdx(String mbr_idx);
}
