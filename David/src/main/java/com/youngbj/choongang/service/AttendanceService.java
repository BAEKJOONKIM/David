package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import com.youngbj.choongang.vo.AttendanceVo;

public interface AttendanceService {
	
	public void insertAttendace(AttendanceVo vo);
	public List<HashMap<String, Object>> getAttendList(String mbr_idx);
}
