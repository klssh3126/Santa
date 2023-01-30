package com.green.nowon.service;

import java.util.List;

import com.green.nowon.domain.dto.goods.ReviewDTO;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.Review;

public interface ReviewService {

	
	//상품의 모든 리뷰를 가져온다.
	List<ReviewDTO> getReviewListOfItemEntity(Long no);
	
	//상품 리뷰를 추가
	Long register(ReviewDTO itemEntityReviewDTO);
	
	//특정한 상품리뷰 수정
	void modify(ReviewDTO itemEntityReviewDTO);
	
	//영화 리뷰 삭제
	void remove(Long r_num);
	
	default Review dtoToEntity(ReviewDTO itemEntityReviewDTO) {
		
		Review itemEntityReview = Review.builder()
				.r_num(itemEntityReviewDTO.getR_num())
				.itemEntity(ItemEntity.builder().no(itemEntityReviewDTO.getNo()).build())
				.member(MemberEntity.builder().mno(itemEntityReviewDTO.getMno()).build())
				.grade(itemEntityReviewDTO.getGrade())
				.r_content(itemEntityReviewDTO.getR_content())
				.build();
		
		return itemEntityReview;
	}
	
	default ReviewDTO entityToDto(Review itemEntityReview) {
		
		ReviewDTO itemEntityReviewDTO = ReviewDTO.builder()
				.r_num(itemEntityReview.getR_num())
				.no(itemEntityReview.getItemEntity().getNo())
				.mno(itemEntityReview.getMember().getMno())
				.nickname(itemEntityReview.getMember().getNickName())
				.email(itemEntityReview.getMember().getEmail())
				.grade(itemEntityReview.getGrade())
				.r_content(itemEntityReview.getR_content())
				.r_reg_date(itemEntityReview.getR_reg_date())
				.r_mod_date(itemEntityReview.getR_mod_date())
				.build();
		
		return itemEntityReviewDTO;
	}

	long GetReviewCnt(Long no);
}
