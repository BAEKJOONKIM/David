package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import com.youngbj.choongang.vo.WorkOutVo;

public interface WorkoutService {

	public void upLoad(WorkOutVo vo);
	
	public HashMap<String, Object> getWorkOut(WorkOutVo vo);
	
	public HashMap<String, Object> getWorkOutx(WorkOutVo vo);
	
	public HashMap<String, Object> getWorkOuty(String wrk_idx, String mbr_idx);
	
	public List<HashMap<String, Object>> getWorkOutList();
	
	public List<HashMap<String, Object>> getWorkOutCategoryList(String wrk_cat);
	
	public List<HashMap<String, Object>> getWorkOutCategoryPageList(String wrk_cat, String wrk_cp);
	
	public List<HashMap<String, Object>> getSearchWorkOutList(String result, String searchType);
		
	public List<HashMap<String, Object>> getWorkOutPageList(String wrk_cp);
	
	public List<HashMap<String, Object>> getSearchWorkOutPageList(String wrk_cp, String result, String searchType);
	
	public List<HashMap<String, Object>> getSearchWriterWorkOutPageList(String wrk_cp, String result, String searchType);

	public String getWorkOutCount();
	
	public void deleteWorkOut(String wrk_idx);
	 
	public void updateReadcount(String wrk_idx);
	public void updateWorkOut(WorkOutVo vo);



}
