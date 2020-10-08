package com.youngbj.choongang.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.youngbj.choongang.vo.AuctionImgVo;
import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BidInfoVo;
import com.youngbj.choongang.vo.BiddingInfoVo;

public interface MyPageAuctionSQLMapper {
	
	//경매내역
	@Select("SELECT * From Member_info, Auction_info, Auction_img WHERE Member_info.mbr_idx = Auction_info.mbr_idx AND Auction_info.auc_idx = Auction_img.auc_idx AND auction_info.mbr_idx=#{mbr_idx} AND Auction_img.i_idx= (SELECT MIN(I_IDX) FROM Auction_img WHERE Auction_img.auc_idx=auction_info.auc_idx) ORDER BY auc_rdat DESC")	
	public List<AuctionInfoVo> selectAuctionByMbrIdxAndAucIdx(AuctionInfoVo vo);
	
	//낙찰경매물품
	@Select("SELECT * FROM Member_info, Auction_info, Auction_img, Bidding_info, Bid_info WHERE Member_info.mbr_idx = Bid_info.mbr_idx AND Bid_info.bdng_idx = Bidding_info.bdng_idx AND Auction_info.auc_idx = Bidding_info.auc_idx And Auction_info.auc_idx = Auction_img.auc_idx AND Bid_info.mbr_idx=#{mbr_idx} ORDER BY bid_dat DESC")
	public List<BidInfoVo> selectBidByIdx(String mbr_idx);
	
	//경매댓글
	@Select("SELECT * FROM Member_info, Auction_reply WHERE Member_info.mbr_idx = Auction_reply.mbr_idx AND Auction_reply.mbr_idx=#{mbr_idx} ORDER BY arpl_idx DESC")
	public List<AuctionReplyVo> selectAuctionReplyByIdx(String mbr_idx);
	
	//필요한 부분 추가
	@Select("SELECT * FROM Bid_info WHERE bdng_idx=#{bdng_idx}")
	public BidInfoVo selectBidInfoByBdngIdx(String bdng_idx);
	
	@Select("SELECT * FROM Bidding_info WHERE bdng_idx=#{bdng_idx}")
	public BiddingInfoVo selectBiddingByBdngIdx(String bdng_idx);
	
	@Select("SELECT * FROM Auction_info WHERE auc_idx=#{auc_idx}")
	public AuctionInfoVo selectAuctionByAucIdx(String auc_idx);
	
	@Select("SELECT * FROM Auction_img WHERE auc_idx=#{auc_idx}")
	public AuctionImgVo selectAuctionImgByAucIdx(String auc_idx);
	
}
