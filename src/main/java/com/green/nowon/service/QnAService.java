package com.green.nowon.service;

import com.green.nowon.domain.dto.QnADTO;
import com.green.nowon.domain.entity.QnA;

public interface QnAService {

	void save(long itemNo ,QnADTO qnaDTO);

	Object getListOfQnA(long no);
	
	default QnADTO entityToDTO(QnA qnaE) {
		QnADTO qnaDTO = QnADTO.builder()
				.no(qnaE.getNo())
				.Depth(qnaE.getDepth())
				.content(qnaE.getContent())
				.ProductPNum(qnaE.getItem().getNo())
				.regDate(qnaE.getCreatedDate())
				.modDate(qnaE.getUpdatedDate())
				.build();
		
		return qnaDTO;
	}

	void saveAnswer(long itemNo, long no, QnADTO qnaDTO);
	void delete(long no);

}
