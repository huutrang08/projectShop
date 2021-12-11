package com.shop.ex.Controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.ex.domain.Customer;
import com.shop.ex.model.CustomerDTO;
import com.shop.ex.services.CustomerServices;

@Controller
@RequestMapping("users")
public class UserLoginController {
	@Autowired
	HttpSession session;
	@Autowired
	CustomerServices customerServices;
  @RequestMapping("login")
  public String login(ModelMap map) {
	  map.addAttribute("customer",new CustomerDTO());
		return "user/customers/login";
	}
  
  @PostMapping("customerlogin")
  public ModelAndView login(ModelMap map,CustomerDTO dto) {
	  System.out.println(dto.getEmail());
	  Customer customer = customerServices.login(dto.getEmail(), dto.getPassword());
	  if (customer == null) {
		  map.addAttribute("customer",new CustomerDTO());
		return new ModelAndView("user/customers/login",map);
	}
	  session.setAttribute("user", customer);
	  Object uri = session.getAttribute("uri");
	  if (uri != null) {
		session.removeAttribute("uri");
		return new ModelAndView("redirect:"+uri,map);
	}
	  return new ModelAndView("redirect:/user/homelogged",map);
  }
  
  @RequestMapping("logout")
  public String logout() {
	  session.removeAttribute("user");
	  return "redirect:/users/login";
  }
}
