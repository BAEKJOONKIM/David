package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.BidInfoVo;

public interface MyPageAuctionService {
	
	//내가 작성한 경매내역
	public List<HashMap<String,Object>> getAuctionContentList(AuctionInfoVo vo);
	
	//낙찰물건
	public List<HashMap<String, Object>> getBidList(String mbr_idx);
	
	//경매댓글
	public List<HashMap<String, Object>> getAuctionReplyList(String mbr_idx);
	

}
