package com.green.nowon.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.nowon.domain.dto.goods.ReviewDTO;
import com.green.nowon.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/common/replies/")
@RestController
@Log4j2
@RequiredArgsConstructor
public class ReviewController {
private final  ReviewService reviewService; //자동주입을 위해 fianl
	
	@GetMapping(value = "/goods/{no}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReviewDTO>> getListByItemEntity(@PathVariable("no") Long no){
		log.info("no: " +no);
		return new ResponseEntity<>(reviewService.getReviewListOfItemEntity(no),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody ReviewDTO reviewDTO){
		log.info(reviewDTO);
		Long r_num = reviewService.register(reviewDTO);
		return new ResponseEntity<>(r_num,HttpStatus.OK);
	}
	
	@DeleteMapping("/{r_num}")
	public ResponseEntity<String> remove(@PathVariable("r_num") Long r_num){
		log.info("r_num:" +r_num);
		
		reviewService.remove(r_num);
		
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	
	@PutMapping("/{r_num}")
	public ResponseEntity<String> modify(@RequestBody ReviewDTO reviewDTO){
		log.info(reviewDTO);
		reviewService.modify(reviewDTO);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	
	
}
