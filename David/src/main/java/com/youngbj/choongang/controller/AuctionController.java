package com.youngbj.choongang.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.youngbj.choongang.service.AuctionService;
import com.youngbj.choongang.vo.AuctionImgVo;
import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionPickMemberVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BidInfoVo;
import com.youngbj.choongang.vo.BiddingInfoVo;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.ReportVo;

@Controller
public class AuctionController {

	@Autowired
	AuctionService auctionService;

	@RequestMapping("auction_list_page")
	public String auctionMainPage(Model model, String n_p, String search, String n_r) throws ParseException {
		// 현재시간
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis();
		String now = dateForm.format(time);
		// Date nowTime = dateForm.parse(now);
		System.out.println(now);
		model.addAttribute("nowTime", now);
		if (search == null) {
			if (n_p == null & n_p == null) {
				n_p = "1";
				n_r = "1";
			}
			ArrayList<HashMap<String, Object>> list = auctionService.getAuctionList(n_p);
			String count = auctionService.getAuctionListCount();
			model.addAttribute("auctionList", list);
			model.addAttribute("search", search);
			model.addAttribute("nowRound", n_r);
			model.addAttribute("count", count);

		} else {
			if (n_p == null & n_p == null) {
				n_p = "1";
				n_r = "1";
			}
			System.out.println(search);
			ArrayList<HashMap<String, Object>> listBySearch = auctionService.getAuctionListBySearch(search, n_p);

			// System.out.println(listBySearch.get(0).get("auctionInfo"));
			String count = auctionService.getAuctionSearchCount(search);
			model.addAttribute("auctionList", listBySearch);
			model.addAttribute("search", search);
			model.addAttribute("nowRound", n_r);
			model.addAttribute("count", count);
		}
		return "auction_list_page";

	}

	@RequestMapping("write_auction_info_page")
	public String writeAuctionInfo(HttpSession session) {
		MemberVo ssmbr = ((MemberVo) session.getAttribute("sessionUserData"));
		if (ssmbr != null) {
			return "write_auction_info_page";
		} else {
			return "redirect:./auction_list_page";
		}
	}

	@RequestMapping("write_auction_action")
	public String writeAuctionAction(AuctionInfoVo param, HttpSession session, MultipartFile[] uploadImg, Model model,
			String auc_sdate, String auc_stime, String auc_edate, String auc_etime) {

		ArrayList<AuctionImgVo> imgList = new ArrayList<AuctionImgVo>();

		// 이미지 저장 폴더 생성
		String uploadRootFolder = "C:\\David_upload\\auction_upload\\";

		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String folder = dateFormat.format(time);

		folder = folder.replace("-", File.separator);

		File uploadFolder = new File(uploadRootFolder + folder);

		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}

		// 파일을 서버에 보내서 저장
		for (MultipartFile file : uploadImg) {
			if (file.getSize() <= 0) {
				continue;
			}
			System.out.println("서버저장");
			// 파일명 짓기..중복 피하기
			String filename = UUID.randomUUID().toString();
			filename += "_" + System.currentTimeMillis();

			String oriFilename = file.getOriginalFilename();
			filename += oriFilename.substring(oriFilename.lastIndexOf("."));

			File uploadFile = new File(uploadFolder, filename);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			AuctionImgVo imgVo = new AuctionImgVo();
			String folderTemp = folder.replace(File.separator, "/");
			imgVo.setI_imgname("auction_upload/" + folderTemp + "/" + filename);
			imgVo.setI_oriname(oriFilename);
			imgList.add(imgVo);

		}
		String auc_sdat = auc_sdate + auc_stime;
		String auc_edat = auc_edate + auc_etime;
		param.setAuc_sdat(auc_sdat);
		param.setAuc_edat(auc_edat);
		param.setMbr_idx(((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx());
		String temp_auc_idx = auctionService.writeAuction(param, imgList);

		model.addAttribute(temp_auc_idx);

		return "write_auction_complete";

	}

	@RequestMapping("/read_auction_page")
	public String readAuctionPage(AuctionInfoVo param, Model model, HttpSession session) throws ParseException {
		HashMap<String, Object> data = auctionService.getAuctionInfo(param);
		// 현재시간
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis();
		String now = dateForm.format(time);
		// Date nowTime = dateForm.parse(now);
		model.addAttribute("nowTime", now);

		if (session.getAttribute("sessionUserData") != null) {
			AuctionPickMemberVo isPicked = new AuctionPickMemberVo();
			isPicked.setAuc_idx(param.getAuc_idx());
			isPicked.setMbr_idx(((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx());
			isPicked = auctionService.isPickExist(isPicked);
			model.addAttribute("pick", isPicked);
		}
		model.addAttribute("data", data);
		// System.out.println(((AuctionInfoVo)data.get("auctionInfo")).getAuc_sdat());
		return "read_auction_page";
	}

	@RequestMapping("/read_reply")
	@ResponseBody
	public HashMap<String, Object> readAuctionReply(String auc_idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		List<HashMap<String, Object>> replyList = auctionService.getAuctionReply(auc_idx);
		map.put("replyList", replyList);

		return map;
	}

	@RequestMapping("update_auction_page")
	public String updateAuctionPage(AuctionInfoVo param, Model model) {
		HashMap<String, Object> data = auctionService.getAuctionInfo(param);
		model.addAttribute("data", data);

		return "update_auction_page";
	}

	@RequestMapping("update_auction_action")
	public String updateAutionAction(AuctionInfoVo param, HttpSession session, MultipartFile[] uploadImg, Model model, String[] deletingFile,
			String auc_sdate, String auc_stime, String auc_edate, String auc_etime) {
		ArrayList<AuctionImgVo> imgList = new ArrayList<AuctionImgVo>();
		String sessionUesrIdx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		HashMap<String, Object> info = auctionService.getAuctionInfo(param);
		String tempMbr = ((MemberVo)info.get("memberVo")).getMbr_idx();
		// 이미지 저장 폴더 생성
		String newUploadRootFolder = "C:\\David_upload\\";

		ArrayList<AuctionImgVo> oriImgList = auctionService.getAuctionImgList(param.getAuc_idx());
		String newFolder = oriImgList.get(0).getI_imgname().substring(0, 26).replace("/", File.separator);

		File newUploadFolder = new File(newUploadRootFolder + newFolder);
		
		//삭제할 파일 지우기
		if(tempMbr.equals(sessionUesrIdx) && deletingFile != null) {
			
			for(String i_idx : deletingFile) {
				
				System.out.println("i_idx: "+i_idx);
				AuctionImgVo imgToDelete = auctionService.getImgToDelete(i_idx);
				String toDeleteFolder = newUploadRootFolder + imgToDelete.getI_imgname();
				toDeleteFolder = toDeleteFolder.replace("/", File.separator);
				File file = new File(toDeleteFolder);
				
				if (file.exists()) {
					file.delete();
					auctionService.deleteImgOne(i_idx);
				}		
			}
	
		}
		
		//추가파일 
		// 파일을 서버에 보내서 저장
		for (MultipartFile file : uploadImg) {
			if (file.getSize() <= 0) {
				continue;
			}
			System.out.println("서버저장");
			// 파일명 짓기..중복 피하기
			String filename = UUID.randomUUID().toString();
			filename += "_" + System.currentTimeMillis();

			String oriFilename = file.getOriginalFilename();
			filename += oriFilename.substring(oriFilename.lastIndexOf("."));

			File uploadFile = new File(newUploadFolder, filename);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			AuctionImgVo imgVo = new AuctionImgVo();
			String folderTemp = newFolder.replace(File.separator, "/");
			imgVo.setI_imgname(folderTemp + filename);
			imgVo.setI_oriname(oriFilename);
			imgList.add(imgVo);

		}
		String auc_sdat = auc_sdate + auc_stime;
		String auc_edat = auc_edate + auc_etime;
		param.setAuc_sdat(auc_sdat);
		param.setAuc_edat(auc_edat);
		param.setMbr_idx(((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx());
		
		
		String temp_auc_idx = auctionService.updateAuctionInfo(param, imgList);

		return "redirect:./read_auction_page?auc_idx=" + temp_auc_idx;
	}

	@RequestMapping("delete_auction_action")
	@ResponseBody
	public HashMap<String, Object> auctionDeleteAction(String auc_idx, HttpSession session) {
		HashMap<String, Object> deleteResult = new HashMap<String, Object>();
		AuctionInfoVo infoVo = new AuctionInfoVo();
		AuctionImgVo imgVo = new AuctionImgVo();
		AuctionReplyVo replyVo = new AuctionReplyVo();
		ArrayList<AuctionImgVo> imgList = auctionService.getImgListToDelete(auc_idx);
		infoVo.setAuc_idx(auc_idx);
		imgVo.setAuc_idx(auc_idx);
		replyVo.setAuc_idx(auc_idx);
		HashMap<String, Object> object = auctionService.getAuctionInfo(infoVo);
		String tempMbr = ((MemberVo) object.get("memberVo")).getMbr_idx();
		String sessionMbr = ((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx();
		String uploadRootFolder = "C:\\David_upload\\";
		if (sessionMbr.equals(tempMbr)) {

			for (AuctionImgVo img : imgList) {

				String imgUrl = img.getI_imgname();
				String folder = uploadRootFolder+imgUrl;
				folder = folder.replace("/", File.separator);
				File file = new File(folder);
				if (file.exists()) {
					file.delete();
				}
			}

			auctionService.deleteAuction(infoVo);
			auctionService.deleteImg(imgVo);
			auctionService.deleteReplyAll(replyVo);

			deleteResult.put("result", "success");

			return deleteResult;
		} else {
			deleteResult.put("result", "fail");
			deleteResult.put("reason", "글쓴이가 아닙니다.");

			return deleteResult;
		}

	}

	@RequestMapping("auction_delete_complete")
	public String deleteComplete() {
		return "auction_delete_complete";
	}

	@RequestMapping("write_reply_action")
	@ResponseBody
	public String writeReply(HttpSession session, AuctionReplyVo param) {
		param.setMbr_idx(((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx());

		auctionService.writeAuctionReply(param);

		return "registered";
	}

	@RequestMapping("reply_delete_action")
	@ResponseBody
	public String replyDeleteAction(AuctionReplyVo param) {
		auctionService.deleteReply(param);

		return "deleted";
	}

	@RequestMapping("auction_pick_action")
	@ResponseBody
	public String auctionPick(AuctionPickMemberVo param) {
		AuctionPickMemberVo pick = auctionService.isPickExist(param);

		if (pick == null) {
			auctionService.auctionPick(param);
			return "clickLike";
		} else {
			auctionService.deleteAuctionPick(param);
			return "clickNotlike";
		}

	}

	@RequestMapping("bidding_action")
	@ResponseBody
	public HashMap<String, Object> bidding(BiddingInfoVo param, String auc_idx, HttpSession session, String auc_sdat,
			String auc_edat) throws ParseException {
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		System.out.println("qqqqqqq");
		resultData.put("result", "fail");
		resultData.put("reason", "없음");

		String mbr_idx = ((MemberVo) session.getAttribute("sessionUserData")).getMbr_idx();
		param.setMbr_idx(mbr_idx);
		param.setAuc_idx(auc_idx);
		// 현재시간
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis();
		String now = dateForm.format(time);
		Date nowTime = dateForm.parse(now);

		if (auctionService.getBidInfo(auc_idx) == null) {
			if (dateForm.parse(auc_sdat).compareTo(nowTime) > 0 || dateForm.parse(auc_edat).compareTo(nowTime) < 0) {
				System.out.println("입찰안됨");
				System.out.println(nowTime.toString());
				resultData.put("result", "fail");
				resultData.put("reason", "경매 시간이 아닙니다.");

			} else if (auctionService.getBiddingInfoByAucAndMbridx(param) == null) {
				auctionService.bidding(param);
				System.out.println("입찰됨");
				resultData.put("result", "success");
			} else {
				Integer tempInt = Integer.parseInt(auctionService.getBiddingInfoByAucAndMbridx(param).getBdng_cst());
				Integer paramInt = Integer.parseInt(param.getBdng_cst());

				if (tempInt < paramInt) {
					auctionService.updateBidding(param);
					System.out.println("입찰됨");
					resultData.put("result", "success");
				} else {
					resultData.put("result", "fail");
					resultData.put("reason", "더 큰 가격을 적어주세요.");
				}

			}
		} else {
			resultData.put("result", "fail");
			resultData.put("reason", "이미 낙찰되었습니다.");

		}

		AuctionInfoVo gotoPage = new AuctionInfoVo();
		gotoPage.setAuc_idx(auc_idx);
		HashMap<String, Object> currentAuctionInfo = auctionService.getAuctionInfo(gotoPage);

		if (currentAuctionInfo != null) {
			((BiddingInfoVo) currentAuctionInfo.get("biddingFirst")).getBdng_bdat();

			resultData.put("currentAuctionInfo", currentAuctionInfo);

			String endTime = ((BiddingInfoVo) currentAuctionInfo.get("biddingFirst")).getBdng_bdat();
			// String endTime =
			// ((AuctionInfoVo)currentAuctionInfo.get("auctionInfo")).getAuc_edat();

			// Date dddd = DateFormat.getInstance().parse(endTime);

			Date dddd = dateForm.parse(endTime);

			long end_time = dddd.getTime();
			System.out.println("eeee" + endTime);
			System.out.println("endt" + end_time);
			long remainingTime = end_time - System.currentTimeMillis();
			resultData.put("remainingTime", remainingTime);
		}

		return resultData;
	}

	@RequestMapping("check_bidding")
	@ResponseBody
	public HashMap<String, Object> checkBidding(String auc_idx) throws ParseException {
		HashMap<String, Object> currentBidding = auctionService.getFirstBiddingInfo(auc_idx);

		if (!currentBidding.get("firstVo").equals("null")) {
			SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endTime = ((BiddingInfoVo) currentBidding.get("firstVo")).getBdng_bdat();
			Date et = dateForm.parse(endTime);
			long end_time = et.getTime();
			long remainingTime = end_time - System.currentTimeMillis();
			currentBidding.put("remainingTime", remainingTime);
		}
		return currentBidding;
	}

	@RequestMapping("bid_action")
	@ResponseBody
	public HashMap<String, Object> bid_action(BidInfoVo param) throws ParseException {
		HashMap<String, Object> bidReturn = new HashMap<String, Object>();
		HashMap<String, Object> currentBidding = auctionService.getFirstBiddingInfo(param.getAuc_idx());
		// 현재시간
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis();
		String now = dateForm.format(time);
		Date nowTime = dateForm.parse(now);

		String endTime = ((BiddingInfoVo) currentBidding.get("firstVo")).getBdng_bdat();
		Date et = dateForm.parse(endTime);

		System.out.println("bdng : " + param.getBdng_idx());
		System.out.println("auc : " + param.getAuc_idx());
		System.out.println("mbr : " + param.getMbr_idx());
		System.out.println("cst : " + param.getBid_cst());
		BidInfoVo bid_product = auctionService.getBidInfo(param.getAuc_idx());
		if (bid_product == null) {
			if (nowTime.compareTo(et) > 0) {
				auctionService.bidAction(param);
				bidReturn.put("success", "완료했습니다.");
			}
			bidReturn.put("fail", "시간이 지나지 않았습니다.");
		} else {
			bidReturn.put("fail", "이미 낙찰된 제품입니다.");
		}

		return bidReturn;

	}
	
	@RequestMapping("report_auction")
	@ResponseBody
	public HashMap<String, Object> reportAuction(ReportVo param, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String memberIdx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		param.setRpt_bcd("7");
		if(memberIdx != null) {
			param.setMbr_idx(memberIdx);
			ReportVo temp = auctionService.getReported(param);
			if(temp == null) {
				auctionService.reportAuction(param);
				result.put("result", "success");
			}else {
				result.put("result","fail");
			}
		}else {
			result.put("result", "fail");
		}
		return result;
		
	}
	
	@RequestMapping("report_reply_auction")
	@ResponseBody
	public HashMap<String, Object> reportAuctionReply(ReportVo param, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String memberIdx = ((MemberVo)session.getAttribute("sessionUserData")).getMbr_idx();
		param.setRpt_bcd("8");
		if(memberIdx != null) {
			param.setMbr_idx(memberIdx);
			ReportVo temp = auctionService.getReported(param);
			
			if(temp==null) {
				auctionService.reportAuction(param);
				result.put("result", "success");
			} else {
				result.put("result", "success");
			}
			
		}else {
			result.put("result", "fail");
		}
		return result;
		
	}
	
}
