package com.youngbj.choongang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.youngbj.choongang.vo.BoardVo;


public interface BoardService {
	
	public List<HashMap<String,Object>> getBoardList();	
	public HashMap<String,Object> getContent(String brd_idx);
	
	public void insertContent(BoardVo vo);
	
	public void updateReadCount(String brd_idx);
	public void updateLikeCount(String brd_idx);
	
	public void deleteContent(BoardVo vo);
	public void updateContent(BoardVo vo);
	
	public List<HashMap<String,Object>> getBoardListBySearch(String search);
	

}
