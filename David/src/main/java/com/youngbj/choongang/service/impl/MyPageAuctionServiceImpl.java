package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.AuctionSQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.MyPageAuctionSQLMapper;
import com.youngbj.choongang.service.MyPageAuctionService;
import com.youngbj.choongang.vo.AuctionImgVo;
import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BidInfoVo;
import com.youngbj.choongang.vo.BiddingInfoVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class MyPageAuctionServiceImpl implements MyPageAuctionService {
	
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	MyPageAuctionSQLMapper myPageAuctionSQLMapper;
	@Autowired
	AuctionSQLMapper auctionSQLMapper;
	
	@Override
	public List<HashMap<String, Object>> getAuctionContentList(AuctionInfoVo vo) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> auctionInfoMyWritingDataList = new ArrayList<HashMap<String,Object>>();
		
		List<AuctionInfoVo> auctionInfoList = myPageAuctionSQLMapper.selectAuctionByMbrIdxAndAucIdx(vo);
		
		for(AuctionInfoVo auctionInfoVo : auctionInfoList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(auctionInfoVo.getMbr_idx());
			ArrayList<AuctionImgVo> auctionImgVo = auctionSQLMapper.selectAuctionImgAll(auctionInfoVo.getAuc_idx());
			
			//System.out.println("aa" + list.get(0).getI_imgname());
			//System.out.println("bb" + list.get(0));
			
			HashMap<String, Object> auctionInfoMap = new HashMap<String, Object>();
			auctionInfoMap.put("memberVo", memberVo);
			auctionInfoMap.put("auctionInfoVo", auctionInfoVo);
			auctionInfoMap.put("auctionImgVo", auctionImgVo.get(0)); //첫장 사진
			
			auctionInfoMyWritingDataList.add(auctionInfoMap);
		}
		return auctionInfoMyWritingDataList;
	}
	
	@Override
	public List<HashMap<String, Object>> getAuctionReplyList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> auctionReplyDataList = new ArrayList<HashMap<String,Object>>();
		
		List<AuctionReplyVo> auctionReplyList = myPageAuctionSQLMapper.selectAuctionReplyByIdx(mbr_idx);
		
		for(AuctionReplyVo auctionReplyVo : auctionReplyList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(auctionReplyVo.getMbr_idx());
			
			HashMap<String, Object> auctionReplyMap = new HashMap<String, Object>();
			auctionReplyMap.put("memberVo", memberVo);
			auctionReplyMap.put("auctionReplyVo", auctionReplyVo);
			
			auctionReplyDataList.add(auctionReplyMap);
		}
		return auctionReplyDataList;
	}

	@Override
	public List<HashMap<String, Object>> getBidList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> bidDataList = new ArrayList<HashMap<String,Object>>();
		
		List<BidInfoVo> bidList = myPageAuctionSQLMapper.selectBidByIdx(mbr_idx);
		
		for(BidInfoVo bidInfoVo : bidList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(bidInfoVo.getMbr_idx());
			BiddingInfoVo biddingInfoVo = myPageAuctionSQLMapper.selectBiddingByBdngIdx(bidInfoVo.getBdng_idx());
			AuctionInfoVo auctionInfoVo = myPageAuctionSQLMapper.selectAuctionByAucIdx(biddingInfoVo.getAuc_idx());
			ArrayList<AuctionImgVo> list = auctionSQLMapper.selectAuctionImgAll(biddingInfoVo.getAuc_idx());
			
			
			HashMap<String, Object> bidMap = new HashMap<String, Object>();
			bidMap.put("memberVo", memberVo);
			bidMap.put("biddingInfoVo", biddingInfoVo);
			bidMap.put("auctionInfoVo", auctionInfoVo);
			bidMap.put("auctionImgVo", list.get(0).getI_imgname());
			bidMap.put("bidInfoVo", bidInfoVo);
			
			bidDataList.add(bidMap);
			
		}
		return bidDataList;
	}




}
