package com.green.nowon.domain.dto.goods;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

	
	private Long r_num; //리뷰번호
	
	private Long no; //상품의 no을 가져올 수 있습니다.
	
	private Long mno; //회원의 mno
	
	private String nickname;  //회원의 닉네임
	
	private String email; //회원의 이메일
	
	private int grade; //평점
	
	private String r_content; //상품에 대한 리뷰내용
	
	private LocalDateTime r_reg_date; //작성날짜
	private LocalDateTime r_mod_date; //수정날짜
}
			