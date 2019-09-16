package com.awes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;

import com.awes.entity.Points;

public interface PointsRepository extends JpaRepository<Points,Integer> {
     @Query(value = "Select * from points p where p.userId=:userId",nativeQuery = true)
	Points getPrediction(@Param("userId")String userId);
    @Transactional
    @Modifying
    @Query(value = "Update points p set p.match1=:match where p.userId=:userId",nativeQuery = true)
	void savePrediction1(@Param("match")String match,@Param("userId")String userId);
    @Transactional
    @Modifying
    @Query(value = "Update points p set p.match2=:match where p.userId=:userId",nativeQuery = true)
	void savePrediction2(@Param("match")String match,@Param("userId")String userId);
	

}
