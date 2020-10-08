package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.etc.CharacterEncoding;
import com.youngbj.choongang.mapper.AuctionSQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.AuctionService;
import com.youngbj.choongang.vo.AuctionImgVo;
import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionPickMemberVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BidInfoVo;
import com.youngbj.choongang.vo.BiddingInfoVo;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.ReportVo;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	AuctionSQLMapper auctionSQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;

	@Override
	public ArrayList<HashMap<String, Object>> getAuctionList(String n_p) {
		ArrayList<HashMap<String, Object>> auctionList = new ArrayList<HashMap<String, Object>>();

		ArrayList<AuctionInfoVo> auctionInfoList = auctionSQLMapper.selectAuctionInfoAll(n_p);

		for (AuctionInfoVo auction : auctionInfoList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			MemberVo memberVo = memberSQLMapper.selectIdx(auction.getMbr_idx());

			ArrayList<AuctionImgVo> auctionImg = auctionSQLMapper.selectAuctionImgAll(auction.getAuc_idx());
			BidInfoVo isBidded = auctionSQLMapper.selectBidInfoByAucidx(auction.getAuc_idx());
			if (auctionImg.size() > 0) {
				String firstImgName = auctionImg.get(0).getI_imgname();

				map.put("thumbnail", firstImgName);
			}
			if(isBidded != null) {
				map.put("isBidded",isBidded);
			}
			map.put("memberVo", memberVo);
			map.put("auctionInfo", auction);
			map.put("auctionImg", auctionImg);

			auctionList.add(map);
		}
		return auctionList;
	}

	@Override
	public String getAuctionListCount() {
		String count = auctionSQLMapper.countAuctionInfoAll();
		return count;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAuctionListBySearch(String search, String n_p) {
		ArrayList<HashMap<String, Object>> listBySearch = new ArrayList<HashMap<String, Object>>();

		ArrayList<AuctionInfoVo> auctionInfoList = auctionSQLMapper.selectAuctionSearch(search, n_p);
		for (AuctionInfoVo auction : auctionInfoList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			MemberVo memberVo = memberSQLMapper.selectIdx(auction.getMbr_idx());

			ArrayList<AuctionImgVo> auctionImg = auctionSQLMapper.selectAuctionImgAll(auction.getAuc_idx());
			BidInfoVo isBidded = auctionSQLMapper.selectBidInfoByAucidx(auction.getAuc_idx());
			if (auctionImg.size() > 0) {
				String firstImgName = auctionImg.get(0).getI_imgname();

				map.put("thumbnail", firstImgName);
			}
			if(isBidded != null) {
				map.put("isBidded",isBidded);
			}
			map.put("memberVo", memberVo);
			map.put("auctionInfo", auction);
			map.put("auctionImg", auctionImg);

			listBySearch.add(map);
		}

		return listBySearch;
	}

	@Override
	public String getAuctionSearchCount(String search) {
		String count = auctionSQLMapper.countAuctionSearch(search);

		return count;
	}

	@Override
	public HashMap<String, Object> getAuctionInfo(AuctionInfoVo vo) {
		HashMap<String, Object> auction = new HashMap<String, Object>();
		auctionSQLMapper.updateAuctionInfoReadCount(vo.getAuc_idx());
		AuctionInfoVo auctionInfo = auctionSQLMapper.selectAuctionInfoByAucidx(vo);
		auctionInfo.getAuc_idx();
		MemberVo memberVo = memberSQLMapper.selectIdx(auctionInfo.getMbr_idx());

		ArrayList<AuctionImgVo> imgList = auctionSQLMapper.selectAuctionImgAll(auctionInfo.getAuc_idx());
		
		ArrayList<AuctionReplyVo> replyList = auctionSQLMapper.selectReplyAll(auctionInfo.getAuc_idx());
		ArrayList<BiddingInfoVo> biddingList = auctionSQLMapper.selectBiddingInfoAllByAucidx(auctionInfo.getAuc_idx());

		AuctionImgVo firstImg = imgList.remove(0);

		String text = CharacterEncoding.encode(auctionInfo.getAuc_pexp());
		auctionInfo.setAuc_pexp(text);

		if (biddingList.size() > 0) {
			BiddingInfoVo biddingFirst = biddingList.get(0);
			MemberVo bidMember = memberSQLMapper.selectIdx(biddingFirst.getMbr_idx());

			auction.put("biddingFirst", biddingFirst);
			auction.put("bidMember", bidMember);
		}

		auction.put("auctionInfo", auctionInfo);
		auction.put("memberVo", memberVo);
		auction.put("firstImg", firstImg);
		
		if (imgList.size() > 0) {
			auction.put("imgList", imgList);
		}
		ArrayList<HashMap<String, Object>> replyTempList = new ArrayList<HashMap<String, Object>>();
		
		for(AuctionReplyVo reply : replyList) {
			HashMap<String, Object> list = new HashMap<String, Object>();
			String replyMemberNick = memberSQLMapper.selectIdx(reply.getMbr_idx()).getMbr_nick();
			list.put("reply", reply);
			list.put("nick",replyMemberNick);
			
			replyTempList.add(list);
		}
		
		auction.put("replyList", replyTempList);

		return auction;
	}
	@Override
	public ArrayList<AuctionImgVo> getImgListToDelete(String auc_idx){
		ArrayList<AuctionImgVo> list = auctionSQLMapper.selectAuctionImgAll(auc_idx);
		return list;
	}
	
	@Override
	public AuctionImgVo getImgToDelete(String i_idx) {
		AuctionImgVo img = auctionSQLMapper.selectAuctionImgByIdx(i_idx);
		return img;
	}
	
	@Override
	public void deleteImgOne(String i_idx) {
		auctionSQLMapper.deleteImgByIIdx(i_idx);
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAuctionReply(String auc_idx){
		ArrayList<HashMap<String,Object>> replyData = new ArrayList<HashMap<String,Object>>();
		
		ArrayList<AuctionReplyVo> replyList = auctionSQLMapper.selectReplyAll(auc_idx);
		
		for(AuctionReplyVo reply : replyList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(reply.getMbr_idx());
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("reply", reply);
			map.put("memberVo", memberVo);
			
			replyData.add(map);
		}
		
		return replyData;
	}

	@Override
	public String writeAuction(AuctionInfoVo auction, ArrayList<AuctionImgVo> imgList) {

		String auc_idx = auctionSQLMapper.createKey();
		auction.setAuc_idx(auc_idx);
		auctionSQLMapper.insertAuctionInfo(auction);
		for (AuctionImgVo img : imgList) {
			img.setAuc_idx(auc_idx);
			auctionSQLMapper.insertAuctionImg(img);

		}
		return auc_idx;
	}

	@Override
	public void writeAuctionReply(AuctionReplyVo vo) {
		auctionSQLMapper.insertAuctionRelpy(vo);
	}

	@Override
	public void deleteReply(AuctionReplyVo vo) {
		auctionSQLMapper.deleteAuctionReply(vo);
	}
	
	@Override
	public void deleteReplyAll(AuctionReplyVo vo) {
		auctionSQLMapper.deleteAuctionReplyAll(vo);
	}

	@Override
	public void auctionPick(AuctionPickMemberVo vo) {

		auctionSQLMapper.insertAuctionPick(vo);
	}

	@Override
	public void deleteAuctionPick(AuctionPickMemberVo vo) {
		// TODO Auto-generated method stub
		auctionSQLMapper.deleteAuctionPick(vo);
	}

	@Override
	public AuctionPickMemberVo isPickExist(AuctionPickMemberVo vo) {
		// TODO Auto-generated method stub

		AuctionPickMemberVo isPicked = auctionSQLMapper.selectPickMember(vo);
		return isPicked;
	}

	@Override
	public BiddingInfoVo getBiddingInfoByAucAndMbridx(BiddingInfoVo vo) {
		BiddingInfoVo info = auctionSQLMapper.selectBiddingInfoByAucAndMbridx(vo);
		return info;
	}

	@Override
	public void bidding(BiddingInfoVo vo) {
		// TODO Auto-generated method stub
		auctionSQLMapper.insertBiddingInfo(vo);
	}

	@Override
	public void updateBidding(BiddingInfoVo vo) {
		auctionSQLMapper.updateBidding(vo);
	}

	@Override
	public String updateAuctionInfo(AuctionInfoVo auction, ArrayList<AuctionImgVo> imgList) {
		// TODO Auto-generated method stub
		String auc_idx = auction.getAuc_idx();
		auction.setAuc_idx(auc_idx);
		auctionSQLMapper.updateAuctionInfo(auction);
		for (AuctionImgVo img : imgList) {
			img.setAuc_idx(auc_idx);
			auctionSQLMapper.insertAuctionImg(img);

		}
		return auc_idx;
	}

	@Override
	public ArrayList<AuctionImgVo> getAuctionImgList(String auc_idx) {
		// TODO Auto-generated method stub
		ArrayList<AuctionImgVo> imgList = auctionSQLMapper.selectAuctionImgAll(auc_idx);

		return imgList;
	}

	@Override
	public void deleteImg(AuctionImgVo vo) {
		// TODO Auto-generated method stub
		auctionSQLMapper.deleteAuctionImg(vo);
	}

	@Override
	public void deleteAuction(AuctionInfoVo vo) {
		auctionSQLMapper.deleteAuctionInfo(vo);
	}
	
	
	@Override
	public HashMap<String, Object> getFirstBiddingInfo(String auc_idx) {
		ArrayList<BiddingInfoVo> list = auctionSQLMapper.selectBiddingInfoAllByAucidx(auc_idx);
		HashMap<String, Object> first = new HashMap<String, Object>();
		if(list.size()>0) {
			
			BiddingInfoVo firstVo = list.get(0);
			MemberVo memberVo = memberSQLMapper.selectIdx(firstVo.getMbr_idx());
			first.put("firstVo",firstVo);
			first.put("memberVo", memberVo);

		}else {
			first.put("firstVo", "null");
		}
		return first;
	}
	
	@Override
	public void bidAction(BidInfoVo vo) {
		auctionSQLMapper.insertBidInfo(vo);
	}
	
	@Override
	public BidInfoVo getBidInfo(String auc_idx) {
		BidInfoVo vo = auctionSQLMapper.selectBidInfoByAucidx(auc_idx);
		return vo;
	}
	
	@Override
	public ReportVo getReported(ReportVo vo) {
		ReportVo result = auctionSQLMapper.selectReported(vo);
		
		return result;
	}
	
	@Override
	public void reportAuction(ReportVo vo) {
		
		auctionSQLMapper.insertReport(vo);
	}
	
	
}
