package com.youngbj.choongang.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.youngbj.choongang.service.MemberService;
import com.youngbj.choongang.service.TrainerService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.TrainerVerificationVo;

@Controller
public class TrainerController {

	
	@Autowired
	MemberService memberService;

	@Autowired
	TrainerService trainerService;	
	
	
	//트레이너 승급신청 페이지
		@RequestMapping("/trainer_verification")
		public String trainerVerificationPage(HttpSession session, Model model, TrainerVerificationVo param) {
			//MemberVo sessionUser = (MemberVo)session.getAttribute("sessionUserData");
			
			TrainerVerificationVo userVrfInfo = trainerService.getAplByMbrIdx(param.getMbr_idx());
			MemberVo usertrnrInfo = memberService.selectMemberByIdx(param.getMbr_idx());
		
			model.addAttribute("userVrfInfo", userVrfInfo);
			model.addAttribute("usertrnrInfo", usertrnrInfo);
			
			
			return "trainer_verification";
		}

		@RequestMapping("/trainer_verification_action")
		public String trainerVerificationAction(HttpSession session, TrainerVerificationVo param,
				MultipartFile[] upload_file, Model model) {

			String uploadRootFolder = "C:\\david_upload\\David_trainer_application\\";

			// 시간을 가져와서 문자열로 만들기
			Date time = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String folder = dateFormat.format(time);
			// yyyy-mm-dd를 yyyy/mm/dd로 바꾸기
			folder = folder.replace("-", File.separator);

			// 폴더 주소를 주소+시간으로 지정하기
			File uploadFolder = new File(uploadRootFolder + folder);

			// 폴더가 없으면 폴더를 만든다
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			// 파일들을 하나씩 가져와서 배열에다 담기
			for (MultipartFile file : upload_file) {    
				if (file.getSize() <= 0) {
					continue;
				}

			// 한번 돌때마다 램덤으로 파일명을 만들기 --> 중복 피하기 위해(시간+ 랜덤값 조합)
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + System.currentTimeMillis();

			// 확장자 이름까지 붙이기
			String oriFileName = file.getOriginalFilename();
			fileName += oriFileName.substring(oriFileName.lastIndexOf("."));
			
			File uploadFile = new File(uploadFolder, fileName);
			
			try {
				file.transferTo(uploadFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			String folderTemp = folder.replace(File.separator, "/");
			param.setTrn_turl("David_trainer_application/"+folderTemp+ "/" + fileName);
			
			}
			
			MemberVo sessionUser = (MemberVo)session.getAttribute("sessionUserData");
			
			TrainerVerificationVo userVrfInfo = trainerService.getAplByMbrIdx(sessionUser.getMbr_idx());
			MemberVo usertrnrInfo = memberService.selectMemberByIdx(sessionUser.getMbr_idx());
		
			model.addAttribute("userVrfInfo", userVrfInfo);
			model.addAttribute("usertrnrInfo", usertrnrInfo);
			
			//mbr_idx붙이기
			param.setMbr_idx(sessionUser.getMbr_idx());
			
			
			//신청내역 저장-->DB에서 신청내역을 저장할때 default로 W로 되어 있음 
			trainerService.insertTrainerinfo(param);
			//[옛] 신청한 회원의 mbr_vrf를 'W'로 바꾸기 /trainerService.putIntoWaiting(param.getMbr_idx());
			
			//param에 mbr_idx밖에 없어서 memberVo객체를 다시 가져오서 trnr랑 vrf를 사용하기
			MemberVo vo = memberService.selectMemberByIdx(param.getMbr_idx());
			sessionUser.setMbr_trnr(vo.getMbr_trnr());
//			TrainerVerificationVo vo1 = trainerService.getAplByMbrIdx(param.getMbr_idx());
//			sessionUserT.setMbr_vrf(vo1.getMbr_vrf());	
			
			return "trainer_verification_complete";
		}

		
		@RequestMapping("/trainer_verification_complete")
		public String trainerVertificationComplete() {
			return "trainer_verification_complete";
		}

//		@RequestMapping("/trainer_verification_cancel")
//		public String trainerVrfCancel() {
//			return "trainer_verification_cancel";
//		}
		
		//승급신청 취소하기
		@RequestMapping("/trainer_verification_cancel_action")
		public String trainerVrfCancelAction(MemberVo param, HttpSession session) {
			MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
			
			TrainerVerificationVo Aplvo = trainerService.getAplByMbrIdx(param.getMbr_idx());
			trainerService.deleteTrnrApl(Aplvo.getTrn_idx());
			
			MemberVo vo = memberService.selectMemberByIdx(param.getMbr_idx());
			sessionUser.setMbr_trnr(vo.getMbr_trnr());


			return "redirect:./mypage_update";
		}
		
		//일반회원으로 다시 되돌리기
		@RequestMapping("/trainer_cancel_action")
		public String trainerCancelAction(MemberVo param, HttpSession session) {
			
			
			//trainerService.defaultTrainerApl(param.getMbr_idx());
			trainerService.returnNormalMember(param.getMbr_idx());
			
			//TrainerVerificationVo Aplvo = trainerService.getAplByMbrIdx(param.getMbr_idx());
			trainerService.deleteTrainerAplAfterReturnToNormalMember(param.getMbr_idx());
			
			
			MemberVo sessionUser = (MemberVo)session.getAttribute("sessionUserData");
			
			MemberVo vo = memberService.selectMemberByIdx(param.getMbr_idx());
			sessionUser.setMbr_trnr(vo.getMbr_trnr());

			return "redirect:./mypage_update";
		}
		
		
//		@RequestMapping("/trainer_reapply_page")
//		public String trainerReapplyPage() {
//			return "trainer_reapply_page";
//		}
	//	
//		@RequestMapping("/trainer_reapply_action")
//		public String trainerReapplyAction(MemberVo param, HttpSession session) {
//			
//			MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUserData");
//			
//			//mbr_vrf상태를 'R'에서 'RA'으로 바꾸기
//			//trainerService.reapplyTrainerApl(param.getMbr_idx());
//			
//			//param에 mbr_idx밖에 없어서 memberVo객체를 다시 가져오서 trnr랑 vrf를 사용하기
//			MemberVo vo = memberService.selectMemberByIdx(param.getMbr_idx());
//			sessionUser.setMbr_trnr(vo.getMbr_trnr());
//			sessionUser.setMbr_vrf(vo.getMbr_vrf());
//			
//			
//			return "trainer_verification_action";
//		}
	
	
	
}
