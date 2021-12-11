package com.shop.ex.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.ex.domain.Acc;
import com.shop.ex.model.AdminLoginDTO;
import com.shop.ex.services.AccountServices;



@Controller
public class AminLoginController {
	@Autowired
	private AccountServices accountServices;
	@Autowired
	private HttpSession session;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
     
	@GetMapping("login")
	public String login(ModelMap map) {
		System.out.println("bat dau login");
		map.addAttribute("Acc",new AdminLoginDTO() );
		return "admin/accs/login";
	}
	
	@PostMapping("adminlogin")
	public ModelAndView login(ModelMap modelMap,@Valid @ModelAttribute("Acc") AdminLoginDTO dto,BindingResult result) {
		System.out.println("vao tranglogin");
		if (result.hasErrors()) {
			System.out.println("that bai 1");
			return new ModelAndView("admin/accs/login",modelMap);	
		}
		Acc acc = accountServices.login(dto.getUsername(), dto.getPassword());
		if (acc == null) {
			System.out.println("that bai 2");
			return new ModelAndView("/admin/accs/login",modelMap);
		}
		session.setAttribute("username",acc.getUsername());
		Object ruri = session.getAttribute("redirect-uri");
		if (ruri!=null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:"+ruri,modelMap);
		}
		System.out.println("thanh cong");
		
		return new ModelAndView("redirect:/admin/products",modelMap);
	}
	@RequestMapping("logout")
	  public String logout() {
		  session.removeAttribute("username");
		  return "redirect:/login";
	  }
}
