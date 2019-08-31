package com.awes.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.awes.entity.Player;
import com.awes.service.PlayerService;

@Controller
public class Controlplayers {
	@Autowired
	PlayerService playerService;

	@RequestMapping(value = "/")
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/validation")
	public ModelAndView userValidation(@RequestParam("userId") String userId,
			@RequestParam("userPassword") String userPassword) {
		try {
			Player player=playerService.fetchPlayerById(userId);
			ModelAndView model = new ModelAndView("home");
			return model;

		} catch (EntityNotFoundException e) {
			// TODO: handle exception

			ModelAndView model = new ModelAndView("login");
			model.addObject("Invalid_name", userId);
			return model;

		}

	}

	@RequestMapping(value = "/register")
	public String registrationPage() {
		return "registration";
	}

	@RequestMapping(value = "/registerPlayer")
	public ModelAndView registerPlayer(@ModelAttribute("player") Player player) {

		// System.out.println(player.getAge());
		playerService.savePlayer(player);
		ModelAndView model = new ModelAndView("login");
		// model.addObject("player",player);
		return model;
	}

	/*
	 * @RequestMapping(value = "/registerPlayer2") public ModelAndView
	 * registerPlayer2(@ModelAttribute("player")Player player) {
	 * 
	 * System.out.println(player.getAge()); ModelAndView model=new
	 * ModelAndView("home"); model.addObject("player",player); return model;
	 * 
	 * }
	 */
}