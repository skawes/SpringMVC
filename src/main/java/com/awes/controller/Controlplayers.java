package com.awes.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.awes.entity.Player;
import com.awes.entity.Points;
import com.awes.entity.Ipl_Scheduler;
import com.awes.repository.PlayerRepository;
import com.awes.repository.PointsRepository;
import com.awes.service.PlayerService;
import com.mysql.cj.Session;

@SessionAttributes("player")
@Controller
public class Controlplayers {
	@Autowired
	PlayerService playerService;
	@Autowired
	PlayerRepository repo;
	@Autowired
	PointsRepository pointsRepository;

	@RequestMapping(value = "/")
	public String loginPage() {
		return "login";
	}
	/*
	 * @RequestMapping(value = "/login") public String loginPage1() { return
	 * "login"; }
	 */

	@RequestMapping(value = "/validate")
	public ModelAndView userValidation(@RequestParam("userId") String userId,
			@RequestParam("userPassword") String userPassword) {
		/*
		 * try { Player player=playerService.fetchPlayerById(userId); ModelAndView model
		 * = new ModelAndView("home"); return model;
		 * 
		 * } catch (EntityNotFoundException e) { // TODO: handle exception
		 * 
		 */

		Player player = playerService.fetchPlayerById(userId);
		// session.setAttribute("player", player);
//		    System.out.println(player.getUserPassword()+" "+userPassword);
		if (player == null || !(player.getUserPassword().equals(userPassword))) {
			ModelAndView model1 = new ModelAndView("redirect:/");
			return model1;
		} else {
			/*
			 * HttpSession session=null; session.setAttribute("player",player);
			 */
			ModelAndView model = new ModelAndView("redirect:/home");
			model.addObject("player", player);

			return model;
		}

	}

	@RequestMapping(value = "/register")
	public String registrationPage(Model model) {

		model.addAttribute("player", new Player());
		return "registration";
	}

	@InitBinder
	public void InitBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);

		webDataBinder.registerCustomEditor(String.class, ste);
		webDataBinder.registerCustomEditor(Integer.class, ste);

	}

	@RequestMapping(value = "/registerPlayer")
	public ModelAndView registerPlayer(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult,
			@SessionAttribute("player") Player player2) {
		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("registration");
			model.addObject("player", player);
			return model;

		} else {
			// System.out.println(player.getAge());
			playerService.savePlayer(player);
			ModelAndView model = new ModelAndView("login");
			// model.addObject("play",player2);
			return model;
		}
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

	@RequestMapping(value = "/updatePlayer")
	public ModelAndView registerPlayer(@RequestParam("id") Integer id) {
		ModelAndView model = new ModelAndView("registration");

		Optional<Player> player = playerService.findById(id);
		model.addObject("player", player);
		return model;
	}

	@GetMapping("/home")
	public ModelAndView home(@SessionAttribute("player") Player player) {
		ModelAndView mav = new ModelAndView("home");
		// HttpSession session=;
		// Session session=new S
		// Player player = (Player) session.getAttribute("player");
		// mav.addObject("player",player);
		// System.out.println(player);
		// HttpSession session=request.getSess
		// Player player=(Player) session.getAttribute("player");
		Date date=new Date();
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		String str=fm.format(date);
		List<Ipl_Scheduler> list = playerService.getTodaysMatch(str);
		System.out.println(list);
		Points points = playerService.getPrediction(player.getUserId());
		mav.addObject("pred", points);
		mav.addObject("predict",list);
		System.out.println(points);
		return mav;
	}

	@GetMapping("/leaderboard")
	public ModelAndView leaderboard() {
		ModelAndView mav = new ModelAndView("leaderboard");
		return mav;
	}

	@GetMapping("/fixtures")
	public ModelAndView fixture() throws ParseException {
		List<Ipl_Scheduler> list = playerService.getFixture();
		ModelAndView mav = new ModelAndView("fixtures");
		mav.addObject("fixtures", list);
		return mav;
	}
	
	
	@GetMapping("/getPrediction1")
	public ModelAndView getPrediction1(@SessionAttribute("player")Player player,@RequestParam("val")String match) {
		ModelAndView mav = new ModelAndView("redirect:/home");
//		System.out.println("Test:"+match);
	//	System.out.println(player);
//		mav.addObject("fixtures", list);
	    pointsRepository.savePrediction1(match,player.getUserId());
		 
		return mav;
	}
	@GetMapping("/getPrediction2")
	public ModelAndView getPrediction2(@SessionAttribute("player")Player player,@RequestParam("val")String match) {
		ModelAndView mav = new ModelAndView("redirect:/home");
//		System.out.println("Test:"+match);
	//	System.out.println(player);
//		mav.addObject("fixtures", list);
	    pointsRepository.savePrediction2(match,player.getUserId());
		 
		return mav;
	}
}