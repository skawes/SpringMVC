package com.awes.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.awes.entity.Ipl_Scheduler;
import com.awes.entity.Player;
import com.awes.entity.Points;
@Repository
@Transactional
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	@Query(value = "Select * from player p where p.userId=:userId",nativeQuery = true)
	public Player fetchPlayerById(@Param("userId")String userId);
	/*
	 * @Query(value = "select * from points po where po.userId=:userId",nativeQuery
	 * = true) public Object getPrediction(@Param("userId")String userId);
	 */
	/*
	 * @Query(value = "Select * from ipl_scheduler") public List<Ipl_Scheduler>
	 * getFixtures();
	 */
}
