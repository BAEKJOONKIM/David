package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

public interface HistoryService {

	// 게시글
	public List<HashMap<String, Object>> getBoardContentList(String mbr_idx);

	public List<HashMap<String, Object>> getMyFlexContentList(String mbr_idx);

	public List<HashMap<String, Object>> getWorkOutContentList(String mbr_idx);

	// 댓글
	public List<HashMap<String, Object>> getBoardReplyList(String mbr_idx);

	public List<HashMap<String, Object>> getMyFlexReplyList(String mbr_idx);

	public List<HashMap<String, Object>> getWorkOutReplyList(String mbr_idx);

	// 찜
	public List<HashMap<String, Object>> getWorkOutPickList(String mbr_idx);

	// 페이징추가
	//자게
	public List<HashMap<String, Object>> getBoardContentListByPaging(String mbr_idx, String n_p);

	public String getMyBoardCount(String mbr_idx);
	
	//마이플렉스
	public List<HashMap<String, Object>> getMyFlexContentListByPaging(String mbr_idx, String n_p);

	public String getMyFlexCount(String mbr_idx);
	
	//운동합시다
	public List<HashMap<String, Object>> getWorkOutContentListByPaging(String mbr_idx, String n_p);

	public String getMyWorkOutCount(String mbr_idx);
	
	//자게 댓글
	public List<HashMap<String, Object>> getBoardReplyListByPaging(String mbr_idx, String n_p);
	public String getMyBoardReplyCount(String mbr_idx);
	
	//마이플렉스 댓글
	public List<HashMap<String, Object>> getMyFlexReplyListByPaging(String mbr_idx, String n_p);
	public String getMyFelxReplyCount(String mbr_idx);
	
	//운동합시다 댓글
	public List<HashMap<String, Object>> getWorkOutReplyListByPaging(String mbr_idx, String n_p);
	public String getMyWorkOutReplyCount(String mbr_Idx);
}

