package com.youngbj.choongang.service;

import java.util.*;

import com.youngbj.choongang.vo.AuctionImgVo;
import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionPickMemberVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BidInfoVo;
import com.youngbj.choongang.vo.BiddingInfoVo;
import com.youngbj.choongang.vo.ReportVo;

public interface AuctionService {
	//auctionInfo가져오기
	public ArrayList<HashMap<String, Object>> getAuctionList(String n_p);
	
	public String getAuctionListCount();
	
	public ArrayList<HashMap<String, Object>> getAuctionListBySearch(String search, String n_p);
	
	public String getAuctionSearchCount(String search);
	
	public ArrayList<HashMap<String,Object>> getAuctionReply(String auc_idx);
	
	public ArrayList<AuctionImgVo> getAuctionImgList(String auc_idx);
	
	public void deleteImg(AuctionImgVo vo);
	
	public void deleteAuction(AuctionInfoVo vo);
	
	public HashMap<String, Object> getAuctionInfo(AuctionInfoVo vo);
	
	public ArrayList<AuctionImgVo> getImgListToDelete(String auc_idx);
	
	public AuctionImgVo getImgToDelete(String i_idx);
	
	public void deleteImgOne(String i_idx);
	
	//auction쓰기
	public String writeAuction(AuctionInfoVo auction, ArrayList<AuctionImgVo> imgList);
	//auction수정
	public String updateAuctionInfo(AuctionInfoVo auction, ArrayList<AuctionImgVo> imgList);
	
	
	//댓글 쓰기
	public void writeAuctionReply(AuctionReplyVo vo);
	
	public void deleteReply(AuctionReplyVo vo);
	
	public void deleteReplyAll(AuctionReplyVo vo);
	
	public void auctionPick(AuctionPickMemberVo vo);
	
	public void deleteAuctionPick(AuctionPickMemberVo vo);
	
	public AuctionPickMemberVo isPickExist(AuctionPickMemberVo vo);

	public void bidding(BiddingInfoVo vo);
	
	public BiddingInfoVo getBiddingInfoByAucAndMbridx(BiddingInfoVo vo);
	
	public void updateBidding(BiddingInfoVo vo);
	
	public HashMap<String, Object> getFirstBiddingInfo(String auc_idx);
	
	public void bidAction(BidInfoVo vo);
	
	public BidInfoVo getBidInfo(String auc_idx);
	
	public void reportAuction(ReportVo vo);
	
	public ReportVo getReported(ReportVo vo);
	
}
