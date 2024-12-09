package com.xk.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author yuan Created by yuan on 2022/05/24
 */
@Controller
public class BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	public static final String DIR_INDEX = "/index/index";
	public static final String ADMIN_INDEX = "/admin/index";
	public static final String ERROR_MSG = "/error/msg";
	public static final String REDIRECT_ADMIN_INDEX = "redirect:/admin/index"; // REDIRECT_ADDR
	private static final String R_AUTH_LOGOUT = "redirect:/admin/logout";

	@Value("${app.baseUrl:defaultBaseUrl}") // 如果配置没找到，使用 defaultBaseUrl
	private String baseUrl;

	public Model info(Model model) {
		model.addAttribute("baseUrl", baseUrl);

		return model;
	}

	/**
	 * Helper method to add fragment attributes to the model.
	 *
	 * @param model       the model to pass attributes
	 * @param system      the system name for the fragment
	 * @param viewPackage the package name for the fragment
	 * @param viewName    the specific fragment name
	 */
	public void addFragmentAttributes(Model model, String system, String viewPackage, String viewName) {
		model.addAttribute("fragmentSystem", system);
		model.addAttribute("fragmentPackage", viewPackage);
		model.addAttribute("fragmentName", viewName);
	}

}
