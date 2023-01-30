package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.service.GoodsService;
import com.green.nowon.service.LogService;
import com.green.nowon.service.ReviewService;

@Controller
public class LogController {
	
	
	
	
//	private final MemberService memberService;
	
	@Autowired
	private LogService service;
	
	@GetMapping("/member/login")
	public String login() {
		return "sign/signin";
	}
	
	@GetMapping("/member/signup")
	public String join() {
		return "/signup";
	}
	
	@GetMapping("/member/signup2")
	public String join2() {
		return "/signup2";
	}
	
	//회원가입처리
	@PostMapping("/member/signup")
	public String join(MemberInsertDTO dto) {
		service.save(dto);
		return "redirect:/member/login";//회원가입후 로그인페이지로
	}
	
	@ResponseBody
	@GetMapping("/member/login-check")
	public boolean loginCheck(Authentication auth) {
		//로그인했을때는 인증정보확인가능
		//비로그인시 는 null
		return auth==null? false:true;
	}

	
	
	@GetMapping("/common/signup")
	public String signup() {
		return "/signup";
	}
	
	@GetMapping("/common/signup2")
	public String signup2() {
		return "/signup2";
	}
	
//	@PostMapping("/common/signup2")
//	public String MemberregisterPost(MemberDTO memberdto, RedirectAttributes redirectAttributes) {
//		log.info("dto..." + memberdto);
//		
//		//새로 추가된 엔티티의 번호
//		Long mno= memberService.register(memberdto);
//		log.info("mno: "+mno);
//		redirectAttributes.addFlashAttribute("msg",mno);
//		return "redirect:/";
//	}
	
}
