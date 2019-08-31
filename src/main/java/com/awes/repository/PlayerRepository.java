package com.awes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.awes.entity.Player;
@Repository
@Transactional
public interface PlayerRepository extends JpaRepository<Player, String> {

}
