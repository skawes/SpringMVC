package com.awes.service;

import com.awes.entity.Player;

public interface PlayerService {
	public void savePlayer(Player player);
	public Player fetchPlayerById(String userId);

}
