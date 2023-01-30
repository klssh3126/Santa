package com.green.nowon.controller;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.nowon.domain.dto.goods.GoodsInsertDTO;
import com.green.nowon.domain.dto.goods.ReviewDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.GoodsService;
import com.green.nowon.service.QnAService;
import com.green.nowon.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
@Controller
public class GoodsController {
	
	private final ReviewService reviewService;	
	@Autowired
	private GoodsService service;
	
	@Autowired
	private QnAService qnaService;
	
	//제품 상세페이지
	@GetMapping("/common/goods/{no}")
	public String detail(@PathVariable long no, Model model,@AuthenticationPrincipal MyUserDetails myUserDetails ) {
		service.detail(no, model);
		
		
		model.addAttribute("reviewCnt", reviewService.GetReviewCnt(no));
		model.addAttribute("mno",myUserDetails.getMno());
		model.addAttribute("email",myUserDetails.getEmail());
		model.addAttribute("qnaResult", qnaService.getListOfQnA(no));
		return "goods/goods";
	}
	
	
	
	@PostMapping("/common/goods")
	public String goodsPost(Long no, ReviewDTO Rdto, RedirectAttributes redirectAttributes) {
		log.info("dto...."+Rdto);
		//새로 추가된 엔티티의 번호
		Long r_num = reviewService.register(Rdto);
		log.info("R_num: " +r_num);
		redirectAttributes.addFlashAttribute("msg",r_num);
		return "redirect:/common/goods/"+no;
	}
	
	
	
	
	
	@GetMapping("/common/category/{no}/goods")
	public String goodsOfCategory(@PathVariable long no, Model model) {
		service.goodsOfCategory(no, model);
		return "goods/category-list";
	}
	
	@PostMapping("/admin/goods")
	public String goods(GoodsInsertDTO dto) {
		service.save(dto);
		return "goods/reg";
	}

	@GetMapping("/admin/goods")
	public String goods() {
		return "goods/reg";
	}
	
	@ResponseBody//응답데이터를 json타입으로 리턴합니다. 
	@PostMapping("/admin/temp-upload")
	public Map<String,String> tempUpload(MultipartFile gimg) {
		return service.fileTempUpload(gimg);
	}
}
