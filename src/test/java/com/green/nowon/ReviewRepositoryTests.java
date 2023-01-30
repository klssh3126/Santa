package com.green.nowon;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.Review;
import com.green.nowon.domain.entity.ReviewRepository;

@SpringBootTest
public class ReviewRepositoryTests {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	//@Test
	public void insertItemEntityReviews() {
		
		//200개의 리뷰를 등록
		IntStream.rangeClosed(1, 200).forEach(i->{
			
			//상품 번호
			Long no = (long)(Math.random()*100) +1;
			
			//리뷰어 번호
			Long j = ((long)(Math.random()*100)+1);
			MemberEntity member = MemberEntity.builder().mno(j).build();
			
			Review itemEntityReview=Review.builder()
					.member(member)
					.itemEntity(ItemEntity.builder().no(no).build())
					.r_content("이 상품에 대한 리뷰..."+i)
					.grade((int)(Math.random()*5)+1)
					.build();
			reviewRepository.save(itemEntityReview);
		});
	}
	
	
	//@Test
	public void testGetItemEntityReviews() {
		ItemEntity itemEntity = ItemEntity.builder().no(97L).build();
		
		List<Review> result = reviewRepository.findByItemEntity(itemEntity);
		
		result.forEach(itemEntityReview ->{
			System.out.print(itemEntityReview.getR_num());
			System.out.print("\t"+itemEntityReview.getGrade());
			System.out.print("\t"+itemEntityReview.getR_content());
			System.out.print("\t"+itemEntityReview.getMember().getEmail());
			System.out.print("\t"+itemEntityReview.getMember().getNickName());
			System.out.println("------------------------------");
		});
		
	}

}
