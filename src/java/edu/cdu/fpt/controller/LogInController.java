package edu.cdu.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import edu.cdu.fpt.model.AlgorithmInfo;
import edu.cdu.fpt.model.User;
import edu.cdu.fpt.service.AlgorithmInfoService;
import edu.cdu.fpt.service.UserService;

/**
 * Handles requests for the log in page.
 * 
 * @author Kai Wang
 * 
 */
@Controller
public class LogInController {
	@Autowired
	UserService userService;
	@Autowired
	AlgorithmInfoService algorithmInfoSerive;

	/**
	 * response the request of "logIn"
	 * 
	 * @param model
	 *            , the model of current page
	 * @return the request page
	 */
	@RequestMapping(value = "logIn", method = RequestMethod.GET)
	public String setupForm(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "logIn";
	}

	/**
	 * response the request of "sign up"
	 * 
	 * @param model
	 *            , the model of current page
	 * @return the request page
	 */
	@RequestMapping(value = "signUp", method = RequestMethod.GET)
	public String setupFormForSignUp(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signUp";
	}

	/**
	 * response the request of "logInSubmit"
	 * 
	 * @param user
	 *            , the log in user
	 * @param result
	 *            , binding result
	 * @param status
	 *            , session status
	 * @return the request page
	 */
	@RequestMapping(value = "logInSubmit", method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status) {
		boolean logInSuccess = userService.logIn(user);
		System.out.println("LogInSubmit");
		if (logInSuccess) {
			List<AlgorithmInfo> list = algorithmInfoSerive
					.getAlgorithmInfoList();
			return new ModelAndView("algorithmInfoList").addObject(
					"varAlgorithmInfoList", list);

		} else {
			return new ModelAndView("logIn");
		}

	}
}
