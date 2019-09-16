package com.awes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.awes.entity.Ipl_Scheduler;

public interface Ipl_SchedulerRepository extends JpaRepository<Ipl_Scheduler, Integer> {
    @Query(value="Select * from ipl_scheduler i where i.date=:date",nativeQuery = true)
	List<Ipl_Scheduler> getTodaysMatch(@Param("date")String date);

}
