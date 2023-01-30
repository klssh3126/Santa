package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "QNA")
public class QnA extends BaseDateEntity{
	
	//1차~4차
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long no; // 문의글 등록 순서
		private String content; // 문의글 내용 
		private int depth; // 차수
		
		@JoinColumn//fk: item_no
		@ManyToOne
		private ItemEntity item; //상품번호 가져오기 위함
		
		@JoinColumn//fk: parent_no
		@ManyToOne
		private QnA parent; //상위 문의글

}
