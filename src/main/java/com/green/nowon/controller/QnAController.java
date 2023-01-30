package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.QnADTO;
import com.green.nowon.service.GoodsService;
import com.green.nowon.service.QnAService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class QnAController {

	
	@Autowired
	private QnAService qnaService;
	
	@GetMapping("/qna/reg")// 상품문의 작성 페이지로 이동
	public void qnaReg(long itemNo, Model model) { // item_no
		model.addAttribute("itemNo", itemNo);
	}
	
	@PostMapping("/qna/reg")// 상품문의 저장
	public String qnaReg(long itemNo, QnADTO qnaDTO) { // itemNo=상품번호
		qnaService.save(itemNo ,qnaDTO);
		return "redirect:/common/goods/" +itemNo;
	}
	
	
	@GetMapping("/qna/answer")// 상품문의답변 작성 페이지로 이동
	public void qnaReg2(long itemNo, long no, Model model) { // itemNo=상품번호, no=문의글번호
		model.addAttribute("itemNo",itemNo);
		model.addAttribute("qnaNo", no);
	}
	
	@PostMapping("/qna/answer")// 상품문의 저장
	public String qnaReg2(long itemNo, long no, QnADTO qnaDTO) { // itemNo=상품번호, no=문의글번호
		qnaService.saveAnswer(itemNo, no, qnaDTO);
		return "redirect:/common/goods/" +itemNo;
	}
	
	@GetMapping("/qna/delete")// 상품문의 삭제
	public String qnaDelete(long itemNo, long no) { // item_no
		qnaService.delete(no);
		return "redirect:/common/goods/" +itemNo;
	}
	
}
