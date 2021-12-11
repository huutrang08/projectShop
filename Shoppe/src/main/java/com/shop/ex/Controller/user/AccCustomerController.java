package com.shop.ex.Controller.user;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.ex.domain.Customer;
import com.shop.ex.model.AccDTO;
import com.shop.ex.model.CustomerDTO;
import com.shop.ex.services.CustomerServices;

@Controller
@RequestMapping("customer")
public class AccCustomerController {
    
	@Autowired
	CustomerServices customerServices;
	
	
	@RequestMapping("add")
	public String add(ModelMap modelMap) {
		modelMap.addAttribute("customer",new CustomerDTO());
		return "user/customers/create";
	}
	
	@PostMapping("create")
	public ModelAndView create(ModelMap map, CustomerDTO dto) {
		Customer customer = new Customer();
		Customer customer2 = customerServices.findByEmailContaining(dto.getEmail());
		if (customer2 != null) {
			map.addAttribute("customer",new CustomerDTO());
			map.addAttribute("mess", "email is exist");
			return new ModelAndView("user/customers/create",map);
		}
		BeanUtils.copyProperties(dto, customer);
		customerServices.save(customer);
	    return new ModelAndView("redirect:/users/login",map);
	}
}
