package com.green.nowon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.goods.ReviewDTO;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.Review;
import com.green.nowon.domain.entity.ReviewRepository;
import com.green.nowon.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewRepository reviewRepository;
	
	public List<ReviewDTO> getReviewListOfItemEntity(Long no) {
		
		ItemEntity itemEntity = ItemEntity.builder().no(no).build();
		List<Review> result = reviewRepository.findByItemEntity(itemEntity);
		
		return result.stream().map(itemEntityReview ->
		entityToDto(itemEntityReview)).collect(Collectors.toList());
	}

	@Override
	public Long register(ReviewDTO itemEntityReviewDTO) {
		Review itemEntityReview = dtoToEntity(itemEntityReviewDTO);
		reviewRepository.save(itemEntityReview);
		return itemEntityReview.getR_num();
	}

	
	@Override
	public void modify(ReviewDTO itemEntityReviewDTO) {
		Optional<Review> result = reviewRepository.findById(itemEntityReviewDTO.getR_num());
		
		if(result.isPresent()) {
			
			Review itemEntityReview  = result.get();
			itemEntityReview.changeGrade(itemEntityReviewDTO.getGrade());
			itemEntityReview.changeRContent(itemEntityReviewDTO.getR_content());
			
			reviewRepository.save(itemEntityReview);
			
		}
		
	}

	@Override
	public void remove(Long r_num) {
		
		reviewRepository.deleteById(r_num);
	}
	
	@Override
	public long GetReviewCnt(Long no) {
		return reviewRepository.getReviewCount(no);
	}
	
}
