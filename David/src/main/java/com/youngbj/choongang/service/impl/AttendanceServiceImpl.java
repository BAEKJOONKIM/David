package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.AttendanceSQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.AttendanceService;
import com.youngbj.choongang.vo.AttendanceVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class AttendanceServiceImpl implements AttendanceService{

	@Autowired
	AttendanceSQLMapper attendanceSQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	
	@Override
	public void insertAttendace(AttendanceVo vo) {
		// TODO Auto-generated method stub
		attendanceSQLMapper.insertAttendance(vo);
	}
	@Override
	public List<HashMap<String, Object>> getAttendList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> attendDataList = new ArrayList<HashMap<String,Object>>();
		
		List<AttendanceVo> attendList = attendanceSQLMapper.selectByIdx(mbr_idx);
		
		for(AttendanceVo attendanceVo : attendList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(attendanceVo.getMbr_idx());
			
			HashMap<String, Object> attendanceMap = new HashMap<String, Object>();
			attendanceMap.put("memberVo", memberVo);
			attendanceMap.put("attendanceVo", attendanceVo);
			
			attendDataList.add(attendanceMap);
		}
		
		return attendDataList;
	}

}
