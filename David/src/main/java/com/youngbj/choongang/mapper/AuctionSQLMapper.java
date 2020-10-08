package com.youngbj.choongang.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.AuctionImgVo;
import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionPickMemberVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BidInfoVo;
import com.youngbj.choongang.vo.BiddingInfoVo;
import com.youngbj.choongang.vo.ReportVo;

public interface AuctionSQLMapper {
	//셀렉트
	@Select("SELECT * FROM Auction_info WHERE auc_idx=#{auc_idx}")
	public AuctionInfoVo selectAuctionInfoByAucidx(AuctionInfoVo vo);
	
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM Rnum FROM (SELECT * FROM auction_info ORDER BY auc_idx DESC) t1) t2 WHERE t2.Rnum <=(#{n_p})*6 AND t2.Rnum >=((#{n_p}-1)*6)+1")
	public ArrayList<AuctionInfoVo> selectAuctionInfoAll(String n_p);
	//@Select("SELECT * FROM Auction_info ORDER BY auc_idx DESC")
	//public ArrayList<AuctionInfoVo> selectAuctionInfoAll();
	
	
	@Select("SELECT COUNT(*) FROM Auction_info")
	public String countAuctionInfoAll();
	
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM Rnum FROM (SELECT * FROM auction_info WHERE auc_pnm LIKE '%' || #{search} || '%' OR auc_pexp LIKE '%' || #{search} || '%' ORDER BY auc_idx DESC) t1) t2 WHERE t2.Rnum <= (#{n_p})*6 AND t2.Rnum >=((#{n_p}-1)*6)+1 ")
	public ArrayList<AuctionInfoVo> selectAuctionSearch(@Param("search") String search,@Param("n_p") String n_p);

	//@Select("SELECT * FROM Auction_info WHERE auc_pnm LIKE '%' || #{search} || '%' OR auc_pexp LIKE '%' || #{search} || '%' ORDER BY auc_idx DESC")
	//public ArrayList<AuctionInfoVo> selectAuctionSearch(String search);
	
	@Select("SELECT COUNT(*) FROM Auction_info WHERE auc_pnm LIKE '%' || #{search} || '%' OR auc_pexp LIKE '%' || #{search} || '%' ORDER BY auc_idx DESC")
	public String countAuctionSearch(String search);
	
	@Select("SELECT seq_auction_info.nextval FROM Dual")
	public String createKey();
	
	@Select("SELECT * FROM Auction_img WHERE auc_idx=#{auc_idx}")
	public ArrayList<AuctionImgVo> selectAuctionImgAll(String auc_idx);
	
	@Select("SELECT * FROM Auction_img WHERE i_idx=#{i_idx}")
	public AuctionImgVo selectAuctionImgByIdx(String i_idx);
	
	@Select("SELECT * FROM Bidding_info WHERE auc_idx=#{auc_idx} ORDER BY bdng_cst DESC")
	public ArrayList<BiddingInfoVo> selectBiddingInfoAllByAucidx(String auc_idx);
	
	@Select("SELECT * FROM Bidding_info WHERE auc_idx=#{auc_idx} AND mbr_idx=#{mbr_idx}")
	public BiddingInfoVo selectBiddingInfoByAucAndMbridx(BiddingInfoVo vo);
	
	@Select("SELECT * FROM Bid_info WHERE bdng_idx=#{bdng_idx}")
	public BidInfoVo selectBidInfoByBdngIdx(BidInfoVo vo);
	
	@Select("SELECT * FROM Bid_info WHERE auc_idx=#{auc_idx}")
	public BidInfoVo selectBidInfoByAucidx(String auc_idx);

	@Select("SELECT * FROM Auction_pick_member WHERE auc_idx=#{auc_idx}")
	public ArrayList<AuctionPickMemberVo> selectPickMemberAll(String auc_idx);
	
	@Select("SELECT * FROM Auction_pick_member WHERE auc_idx=#{auc_idx} AND mbr_idx=#{mbr_idx}")
	public AuctionPickMemberVo selectPickMember(AuctionPickMemberVo vo);
	
	@Select("SELECT * FROM Auction_reply WHERE auc_idx=#{auc_idx} ORDER BY arpl_idx DESC")
	public ArrayList<AuctionReplyVo> selectReplyAll(String auc_idx);
	
	@Select("SELECT * FROM Report WHERE rpt_bcd=#{rpt_bcd} AND mbr_idx=#{mbr_idx} AND b_idx=#{b_idx}")
	public ReportVo selectReported(ReportVo vo);
	
	//인서트
	@Insert("INSERT INTO Auction_info VALUES(#{auc_idx}, #{mbr_idx},#{auc_pnm},#{auc_pexp},#{auc_lcst},#{auc_hcst},SYSDATE,TO_DATE(#{auc_sdat},'YYYY/MM/DD hh24:mi:ss'),TO_DATE(#{auc_edat},'YYYY/MM/DD hh24:mi:ss'), 0)")
	public void insertAuctionInfo(AuctionInfoVo vo);
	
	@Insert("INSERT INTO Auction_img VALUES(seq_auction_img.nextval,#{auc_idx},#{i_imgname},#{i_oriname},SYSDATE)")
	public void insertAuctionImg(AuctionImgVo vo);
	
	@Insert("INSERT INTO Auction_reply VALUES(seq_auction_reply.nextval,#{auc_idx},#{mbr_idx},#{arpl_con},SYSDATE)")
	public void insertAuctionRelpy(AuctionReplyVo vo);
	
	@Insert("INSERT INTO Auction_pick_member VALUES(#{auc_idx},#{mbr_idx})")
	public void insertAuctionPick(AuctionPickMemberVo vo);
	
	@Insert("INSERT INTO Bidding_info VALUES(seq_bidding_info.nextval, #{auc_idx}, #{mbr_idx}, #{bdng_cst}, SYSDATE+0.0002)")
	public void insertBiddingInfo(BiddingInfoVo vo);
	
	@Insert("INSERT INTO Bid_info VALUES(seq_bid_info.nextval, #{bdng_idx},#{auc_idx},#{mbr_idx},#{bid_cst}, SYSDATE)")
	public void insertBidInfo(BidInfoVo vo);
	
	@Insert("INSERT INTO Report VALUES(seq_report.nextval,#{rpt_bcd},#{mbr_idx},#{b_idx},#{rpt_con},SYSDATE)")
	public void insertReport(ReportVo vo);
	
	//업데이트
	
	@Update("UPDATE Auction_info SET auc_rcnt=auc_rcnt+1 WHERE auc_idx=#{auc_idx}")
	public void updateAuctionInfoReadCount(String auc_idx);
	
	@Update("UPDATE Auction_info SET auc_pnm=#{auc_pnm}, auc_pexp=#{auc_pexp}, auc_lcst=#{auc_lcst}, auc_hcst=#{auc_hcst}, auc_sdat=TO_DATE(#{auc_sdat},'YYYY/MM/DD hh24:mi:ss'),auc_edat=TO_DATE(#{auc_edat},'YYYY/MM/DD hh24:mi:ss') WHERE auc_idx=#{auc_idx}")
	public void updateAuctionInfo(AuctionInfoVo vo);
	
	@Update("UPDATE Auction_img SET i_imgname=#{i_imgname})")
	public void updateAuctionImg(AuctionImgVo vo);
	
	@Update("UPDATE Auction_reply SET arpl_con=#{arpl_con} WHERE arpl_idx=#{arpl_idx}")
	public void updateAuctionReply(AuctionReplyVo vo);
	
	@Update("UPDATE Bidding_info SET bdng_cst=#{bdng_cst}, bdng_bdat=SYSDATE+0.0002 WHERE auc_idx=#{auc_idx} AND mbr_idx=#{mbr_idx}")
	public void updateBidding(BiddingInfoVo vo);
	
	
	//딜리트
	@Delete("DELETE FROM Auction_info WHERE auc_idx=#{auc_idx}")
	public void deleteAuctionInfo(AuctionInfoVo vo);
	
	@Delete("DELETE FROM Auction_img WHERE auc_idx=#{auc_idx}")
	public void deleteAuctionImg(AuctionImgVo vo);
	
	@Delete("DELETE FROM Auction_img WHERE i_idx=#{i_idx}")
	public void deleteImgByIIdx(String i_idx);
	
	@Delete("DELETE FROM Auction_reply WHERE arpl_idx=#{arpl_idx}")
	public void deleteAuctionReply(AuctionReplyVo vo);
	
	@Delete("DELETE FROM auction_reply WHERE auc_idx=#{auc_idx}")
	public void deleteAuctionReplyAll(AuctionReplyVo vo);
	
	@Delete("DELETE FROM Auction_pick_member WHERE auc_idx=#{auc_idx} AND mbr_idx=#{mbr_idx}")
	public void deleteAuctionPick(AuctionPickMemberVo vo);
	
	@Delete("DELETE FROM Bidding_info WHERE auc_idx=#{auc_idx}")
	public void deleteBiddingByAucidx(String auc_idx);
	
	@Delete("DELETE FROM Bid_info WHERE auc_idx=#{auc_idx}")
	public void deleteBidInfoByAucidx(String auc_idx);
	
	
	
	
	
	
	
	
	
	
}
