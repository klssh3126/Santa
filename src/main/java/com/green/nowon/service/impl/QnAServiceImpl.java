package com.green.nowon.service.impl;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.QnADTO;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.QnA;
import com.green.nowon.domain.entity.QnARepository;
import com.green.nowon.service.QnAService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnARepository qnaRepository;
	
	//문의글저장
	@Override
	public void save(long itemNo, QnADTO qnaDTO) {
		
		ItemEntity item = ItemEntity.builder().no(itemNo).build();
		
		QnA qna = QnA.builder()
				.depth(0)//처음 문의글 depth0으로 변경, 답글은 +1 해서 depth표현 가능하도록
				.content(qnaDTO.getContent())
				.item(item)
				.build();
		qnaRepository.save(qna);
	}

	//문의글, 문의글답변 출력
	@Override
	public Object getListOfQnA(long no) {
		
		ItemEntity item = ItemEntity.builder().no(no).build();
		
		List<QnA> qnaResult = qnaRepository.getListOfQnA(item);
		
		return qnaResult.stream().map(qnaE -> entityToDTO(qnaE)).collect(Collectors.toList());
	}
	
	//문의글답변저장
	@Override
	public void saveAnswer(long itemNo, long no, QnADTO qnaDTO) {
		ItemEntity item = ItemEntity.builder().no(itemNo).build();
		
		QnA parent = qnaRepository.findByNo(no);// 부모의 정보를 찾아서
		
		log.info("parent" + parent);
		
			QnA qna = QnA.builder()
					.content(qnaDTO.getContent())
					.depth(parent.getDepth()+1)//부모의 Depth +1 로 답글 Depth저장
					.parent(parent)//parent에 부모의 PK값(no) 입력
					.item(item)
					.build();
			qnaRepository.save(qna);
		
	}

	@Override
	public void delete(long no) {
	
//		QnA qna = QnA.builder()
//				.no(qnaDTO.getNo())
//				.content("삭제된글입니다.")
//				.build();
		qnaRepository.updateContent(no);
		
	}

	
	
	


}
