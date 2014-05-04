package edu.cdu.fpt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cdu.fpt.model.AlgorithmInfo;
import edu.cdu.fpt.service.AlgorithmInfoService;

/**
 * a controller for requests in algorithm info list page
 * 
 * @author Kai Wang
 * 
 */
@Controller
public class AlgorithmInfoListController {

	@Autowired
	AlgorithmInfoService algorithmInfoSerive;

	/**
	 * response for the request of "runAlgorithm"
	 * 
	 * @param request
	 *            , request
	 * @param algorithmName
	 *            , algorithm name
	 * @return the request page name
	 */
	@RequestMapping("runAlgorithm")
	public String runAlgorithm(HttpServletRequest request,
			@ModelAttribute("algorithmName") String algorithmName) {
		String path = request.getSession().getServletContext().getRealPath("/");

		algorithmInfoSerive.runAlgorithm(path, algorithmName);
		// algorithmInfoSerive.runAlgorithm();

		return "algorithmInfo";
	}

	/**
	 * response for the request of "compareAlgorithm"
	 * 
	 * @return the request page name
	 */
	@RequestMapping("compareAlgorithm")
	public String compareAlg() {
		algorithmInfoSerive.compareAlg();

		return "algorithmInfo";
	}

	/**
	 * response for the request of "uploadAlgorithm"
	 * 
	 * @param model
	 *            , the model of current page
	 * @return the request page name
	 */
	@RequestMapping(value = "uploadAlgorithm", method = RequestMethod.GET)
	public String setupFormFormUploadAlgorithm(ModelMap model) {
		AlgorithmInfo algorithmInfo = new AlgorithmInfo();
		model.addAttribute("algorithmInfo", algorithmInfo);
		return "uploadAlgorithm";
	}

}
