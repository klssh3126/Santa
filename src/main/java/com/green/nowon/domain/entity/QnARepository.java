package com.green.nowon.domain.entity;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface QnARepository extends JpaRepository<QnA, Long>{

	@Query(value = "WITH RECURSIVE CTE AS (\n"
			+ "    SELECT no\n"
			+ "           ,content\n"
			+ "           ,parent_no\n"
			+ "           ,depth\n"
			+ "           ,CAST(no as CHAR(100)) lvl\n"
			+ "           ,item_no\n"
			+ "           ,created_date\n"
			+ "           ,updated_date\n"
			+ "    FROM qna\n"
			+ "    WHERE parent_no IS NULL\n"
			+ "    UNION ALL\n"
			+ "    SELECT  b.no\n"
			+ "           ,b.content\n"
			+ "           ,b.parent_no\n"
			+ "           ,b.depth\n"
			+ "           ,CONCAT(c.lvl, \",\", b.no) lvl\n"
			+ "           ,b.item_no\n"
			+ "           ,b.created_date\n"
			+ "           ,b.updated_date\n"
			+ "    FROM qna b\n"
			+ "    INNER JOIN CTE c\n"
			+ "    ON b.parent_no = c.no\n"
			+ ")\n"
			+ "SELECT no\n"
			+ "      ,CONCAT(REPEAT(\"   \", depth),REPEAT(\"ㄴ\", depth), content) as content\n"
			+ "      ,parent_no\n"
			+ "      ,depth\n"
			+ "      ,lvl\n"
			+ "      ,item_no\n"
			+ "      ,created_date\n"
			+ "      ,updated_date\n"
			+ "from CTE\n"
			+ "WHERE item_no = :item\n"
			+ "ORDER BY lvl", nativeQuery = true)
	List<QnA> getListOfQnA(@Param("item")ItemEntity item);

	QnA findByNo(long no);

	QnA findByParent(long parent);
	
	@Transactional
	@Modifying
	@Query("UPDATE FROM QNA q SET q.content = '삭제된글입니다.' WHERE q.no = :no")
	void updateContent(@Param("no") long no);

}
