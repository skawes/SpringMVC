package com.awes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awes.entity.Player;
import com.awes.repository.PlayerRepository;
@Service
public class PlayerServiceImp implements PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	public void savePlayer(Player player) {
		// TODO Auto-generated method stub
		playerRepository.save(player);

	}
	public Player fetchPlayerById(String userId) {
		Player player=playerRepository.getOne(userId);
		return player;
	}
	

}
