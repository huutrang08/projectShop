package com.shop.ex.Controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shop.ex.domain.Acc;
import com.shop.ex.domain.Customer;
import com.shop.ex.model.AccDTO;
import com.shop.ex.services.AccountServices;
import com.shop.ex.services.CustomerServices;

@Controller
@RequestMapping("admin/accs")
public class AccController {
	@Autowired
	AccountServices accountServices;
	@Autowired
	CustomerServices customerServices;
     
	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("Acc", new AccDTO());
		return "admin/accs/addOrEdit";
	}
	
	@RequestMapping("customers")
	public String list(ModelMap map) {
		List<Customer> list = customerServices.findAll();
		map.addAttribute("customers", list);
		return "admin/accs/customers";
	}
	@RequestMapping("edit/{username}")
	public ModelAndView edit(ModelMap model,@PathVariable("username") String username) {
		Optional<Acc> optional = accountServices.findById(username);
		AccDTO dto =new AccDTO();
		if (optional.isPresent()) {
			Acc entity = optional.get();
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("Acc", dto);
			dto.setPassword("");
			return new ModelAndView("admin/accs/addOrEdit",model);
		}
		model.addAttribute("mess", "Acc is not existed");
		return new ModelAndView("redirect:/admin/accs/addOrEdit",model);
	}
	
	@RequestMapping("delete/{username}")
	public ModelAndView delete(ModelMap modelMap,@PathVariable("username") String usename) {
		accountServices.deleteById(usename);
		return new ModelAndView("redirect:/admin/accs",modelMap);
	}
	
	@PostMapping("saveOrupdate")
	public ModelAndView saveOrupdate(ModelMap model,@Valid @ModelAttribute("Acc") AccDTO dto,BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/accs/addOrEdit",model);
		}
		Acc entity = new Acc();
		BeanUtils.copyProperties(dto, entity);
		accountServices.save(entity);
		return new ModelAndView("redirect:/admin/accs", model);
	}
	
	
}
