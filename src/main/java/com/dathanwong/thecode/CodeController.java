package com.dathanwong.thecode;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CodeController {
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping(value="/tryCode", method=RequestMethod.POST)
	public String checkCode(@RequestParam(value="code") String code, HttpSession session, RedirectAttributes redirectAttributes) {
		if(code.equals("bushido")) {
			session.setAttribute("success", true);
			return "redirect:/code";
		}else {
			redirectAttributes.addFlashAttribute("error", "You must train harder!");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/code")
	public String showCode(HttpSession session) {
		Boolean success = (Boolean) session.getAttribute("success");
		if(success) {
			return "code.jsp";
		}else {
			return "redirect:/";
		}
	}
}
