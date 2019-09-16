package com.awes.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awes.entity.Ipl_Scheduler;
import com.awes.entity.Player;
import com.awes.entity.Points;
import com.awes.repository.Ipl_SchedulerRepository;
import com.awes.repository.PlayerRepository;
import com.awes.repository.PointsRepository;
@Service
public class PlayerServiceImp implements PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	Ipl_SchedulerRepository ipl_SchedulerRepository;
	@Autowired
	PointsRepository pointsRepository;
	public void savePlayer(Player player) {
		// TODO Auto-generated method stub
		playerRepository.save(player);

	}
	public Player fetchPlayerById(String userId) {
		Player player=playerRepository.fetchPlayerById(userId);
		return player;
	}
	public Optional<Player> findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Player> player=playerRepository.findById(id);
		return player;
	}
	public List<Ipl_Scheduler> getFixture() {
		// TODO Auto-generated method stub
		List<Ipl_Scheduler> fixtures=ipl_SchedulerRepository.findAll();
		return fixtures;
	}
	
	  public Points getPrediction(String userId) { // TODO Auto-generated method
	  Points points=pointsRepository.getPrediction(userId);
	  return points;
	  }
	public List<Ipl_Scheduler> getTodaysMatch(String date) {
		// TODO Auto-generated method stub
		List<Ipl_Scheduler> matchList=ipl_SchedulerRepository.getTodaysMatch(date);
		return matchList;
	}
	 

}
