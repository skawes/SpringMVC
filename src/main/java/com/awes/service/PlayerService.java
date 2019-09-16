package com.awes.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.awes.entity.Player;
import com.awes.entity.Points;
import com.awes.entity.Ipl_Scheduler;

public interface PlayerService {
	public void savePlayer(Player player);
	public Player fetchPlayerById(String userId);
	public Optional<Player> findById(Integer id);
	public List<Ipl_Scheduler> getFixture();
    public Points getPrediction(String userId);
	public List<Ipl_Scheduler> getTodaysMatch(String str);

}
