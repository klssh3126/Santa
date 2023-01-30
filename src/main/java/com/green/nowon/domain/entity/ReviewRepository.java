package com.green.nowon.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	List<Review> findByItemEntity(ItemEntity itemEntity);

	
	//@Modifying
	//@Query("delete from Review mr where mr.member = :member")
	void deleteByMember(MemberEntity member);
	
	
	@Modifying
	@Query("delete from Review r where r.member.mno =:mno")
	void deleteByMno(@Param("mno")Long mno);
	
	
	@Query("select count(distinct r) from ItemEntity i " 
			+ "left outer join Review r on r.itemEntity = i "
			+ " where i.no = :no group by i ")
					long getReviewCount(@Param("no") Long no);
	
	
}
