package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.QnA;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QnADTO {
	
	private Long no;
	
	private String content;
	
	private Long ParentNo;
	
	private int Depth;
	
	private Long ProductPNum;
	
	private LocalDateTime regDate;
	
	private LocalDateTime modDate;
}
