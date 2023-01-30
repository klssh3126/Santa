package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EntityListeners(value= {AuditingEntityListener.class})
@ToString
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long r_num; //댓글번호
	
	@ManyToOne
	private ItemEntity itemEntity; //no을 가져올 수 있습니다.
	
	@ManyToOne
	private MemberEntity member; //회원의 nickname을 가져올 수 있습니다.
	
	private int grade;
	private String r_content; // 내용  //상품에 대한 리뷰내용을 의미합니다.
	
	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime r_reg_date; //작성날짜
	
	@LastModifiedDate
	@Column
	private LocalDateTime r_mod_date; //수정날짜
	
	public void changeGrade(int grade) {
		this.grade = grade;
	}
	
	public void changeRContent(String r_content) {
		this.r_content=r_content;
	}
}
