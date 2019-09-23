package com.awes.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.SessionAttributesHandler;
import org.springframework.web.servlet.ModelAndView;

import com.awes.entity.Player;
import com.awes.entity.Points;
import com.awes.entity.Ipl_Scheduler;
import com.awes.repository.PlayerRepository;
import com.awes.repository.PointsRepository;
import com.awes.service.PlayerService;
import com.mysql.cj.Session;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
	public ModelAndView loginPage(HttpSession session) {

		Player player = (Player) session.getAttribute("player");
		if (player != null) {
			ModelAndView model1 = new ModelAndView("redirect:/home");
			return model1;
		} else {
			ModelAndView model = new ModelAndView("login");
			return model;
		}
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
		String url = "http://worldtimeapi.org/api/timezone/Asia/Kolkata";
		Client client = Client.create();
		WebResource resource = client.resource(url);
		ClientResponse clientResponse = resource.accept("application/json").get(ClientResponse.class);
		String output = clientResponse.getEntity(String.class);
		System.out.println("test::::\n" + output);

		output = output.replaceAll(".+datetime*", "");
		String str = output.substring(3, 13);
		String t = output.substring(14, 19);
		System.out.println(str + " " + t);

		/*
		 * System.out.println("test::::\n" + output); output = output.replaceAll(".+",
		 * "");
		 */
		System.out.println(output);

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
	public ModelAndView registerPlayer(@Valid @ModelAttribute("player") Player player, WebRequest request,
			SessionStatus status, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("registration");
			// model.addObject("player", player);
			return model;

		} else {
			// System.out.println(player.getAge());
			playerService.savePlayer(player);
			Points points = new Points();
			points.setMatch1(null);
			points.setMatch2(null);
			points.setPoints(0);
			points.setUserId(player.getUserId());
			pointsRepository.save(points);
			ModelAndView model = new ModelAndView("login");
			// model.addObject("play",player2);
			status.setComplete();
			request.removeAttribute("player", WebRequest.SCOPE_SESSION);
			return model;
		}
	}

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
		Points points = null;
		List<Ipl_Scheduler> list = null;
		List<Points> listPoints = null;
		String url = "http://worldtimeapi.org/api/timezone/Asia/Kolkata";
		Client client = Client.create();
		WebResource resource = client.resource(url);
		ClientResponse clientResponse = resource.accept("application/json").get(ClientResponse.class);
		String output = clientResponse.getEntity(String.class);
		System.out.println("test::::\n" + output);
		output = output.replaceAll(".+datetime*", "");
		String da = output.substring(3, 13);
		String t = output.substring(14, 16);
		int ti = Integer.parseInt(t);
		if (ti < 16 && ti >= 2) {
			// Player player=(Player) session.getAttribute("player");
			Date date = new Date();
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			String str = fm.format(date);
			list = playerService.getTodaysMatch(str);
			// System.out.println(list);
			points = playerService.getPrediction(player.getUserId());
			mav.addObject("pred", points);
			mav.addObject("predict", list);
			// System.out.println(points);
			return mav;

		} else {
			listPoints = pointsRepository.findAll();
			mav.addObject("pred", points);
			mav.addObject("predict", list);
			mav.addObject("listPoints", listPoints);
			return mav;

		}

	}

	@GetMapping("/leaderboard")
	public ModelAndView leaderboard(@SessionAttribute("player") Player player) {
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
	public ModelAndView getPrediction1(@SessionAttribute("player") Player player, @RequestParam("val") String match) {
		ModelAndView mav = new ModelAndView("redirect:/home");
//		System.out.println("Test:"+match);
		// System.out.println(player);
//		mav.addObject("fixtures", list);
		pointsRepository.savePrediction1(match, player.getUserId());

		return mav;
	}

	@GetMapping("/getPrediction2")
	public ModelAndView getPrediction2(@SessionAttribute("player") Player player, @RequestParam("val") String match) {
		ModelAndView mav = new ModelAndView("redirect:/home");
//		System.out.println("Test:"+match);
		// System.out.println(player);
//		mav.addObject("fixtures", list);
		pointsRepository.savePrediction2(match, player.getUserId());

		return mav;
	}

	@GetMapping("/logout")
	public ModelAndView logout(@SessionAttribute Player player, WebRequest request, SessionStatus status) {
		// HttpSession httpSession=request.getSession();
		status.setComplete();
		request.removeAttribute("player", WebRequest.SCOPE_SESSION);
		/// request.removeAttribute("player",WebRequest.SCOPE_SESSION);
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;

	}
}