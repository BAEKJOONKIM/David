package com.youngbj.choongang.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.youngbj.choongang.vo.BodyVo;

@Service
public interface BodyService {
	public List<HashMap<String, Object>> getBodylist();
	public void updateBodyInfo(BodyVo vo);
	public void insertBody(BodyVo vo);
	public BodyVo confirmBodyInfo(String mbr_idx);
	public HashMap<String, Object> getBody(String mbr_idx);
}
