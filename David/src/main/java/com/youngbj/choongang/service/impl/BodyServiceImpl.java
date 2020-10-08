package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.BodySQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.BodyService;
import com.youngbj.choongang.vo.BodyVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class BodyServiceImpl implements BodyService {
	
	@Autowired
	private BodySQLMapper bodySQLMapper;
	@Autowired
	private MemberSQLMapper memberSQLMapper;

	@Override
	public List<HashMap<String, Object>> getBodylist() {
		// TODO Auto-generated method stub
		
		List<HashMap<String, Object>> resultDataList = new ArrayList<HashMap<String,Object>>();
		
		List<BodyVo> bodyList = bodySQLMapper.selectAll();
		
		for(BodyVo bodyVo : bodyList){
			
			MemberVo memberVo = memberSQLMapper.selectIdx(bodyVo.getMbr_idx());
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("bodyVo", bodyVo);
			
			resultDataList.add(map);
		}
		
		return resultDataList;
	}

	@Override
	public void updateBodyInfo(BodyVo vo) {
		// TODO Auto-generated method stub
		bodySQLMapper.updateBodyInfo(vo);
	}

	@Override
	public void insertBody(BodyVo vo) {
		// TODO Auto-generated method stub
		bodySQLMapper.insertBodyInfo(vo);
	}

	@Override
	public BodyVo confirmBodyInfo(String mbr_idx) {
		// TODO Auto-generated method stub
		BodyVo body = bodySQLMapper.selectByIdx(mbr_idx);
		return body;
	}

	@Override
	public HashMap<String, Object> getBody(String mbr_idx) {
		// TODO Auto-generated method stub
		BodyVo bodyVo = bodySQLMapper.selectByIdx(mbr_idx);
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVo", memberVo);
		map.put("bodyVo", bodyVo);
		
		return map;
	}

}
