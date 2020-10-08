package com.youngbj.choongang.service;

import java.util.HashMap;

import com.youngbj.choongang.vo.BoardLikeVo;

public interface BoardLikeService {

	public void insertBoardLike(BoardLikeVo vo);

	public HashMap<String, Object> getBoardLike(String brd_idx);
	
	public BoardLikeVo selectByMbrANDBrdidx(BoardLikeVo vo);
	
	public void deleteByMbrANDBrdidx(BoardLikeVo vo);
	
	public String countBoardLike(BoardLikeVo vo);
	
	
	
}
