package com.youngbj.choongang.service;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.youngbj.choongang.vo.BoardReplyVo;

public interface BoardReplyService {
	
	public List<HashMap<String, Object>> getBoardReplyList(String brd_idx, String mbr_idx);
	
	public HashMap<String, Object> getBoardReply(String brpl_idx);
	
	public void boardWriteReply(BoardReplyVo vo);
	
	public void boardDeleteReply(BoardReplyVo vo);
	
	public void boardUpdateReply(BoardReplyVo vo);
	
}
