package com.shop.ex.Controller.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.ex.domain.Category;
import com.shop.ex.domain.Customer;
import com.shop.ex.domain.Product;
import com.shop.ex.model.ProductDTO;
import com.shop.ex.services.CategoryServices;
import com.shop.ex.services.CustomerServices;
import com.shop.ex.services.ProductServices;
import com.shop.ex.services.StorageServices;

@Controller
@RequestMapping("user")
public class CustomerController {
	@Autowired
	ProductServices productServices;
    
	@Autowired
	HttpSession session;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
     
	@Autowired
	CategoryServices categoryServices;
    @Autowired
    CustomerServices customerServices;
	@Autowired
	StorageServices storageServices;
	@RequestMapping("homelogged")
	public String homepage2() {
		return "user/customers/homelogged";
	}
	
	@GetMapping("profile")
	public String profile(ModelMap map) {
		Customer customer =(Customer) session.getAttribute("user");
		map.addAttribute("user", customer);
		return "user/customers/profile";
	}
    
	@GetMapping("changepass")
	public String change() {
		return "user/customers/changepass";
	}
	
	@PostMapping("change")
	public String changePass(ModelMap map,@RequestParam("new") String newPass,@RequestParam("pass") String pass){
		Customer customer =(Customer) session.getAttribute("user");
		Optional<Customer> optional = customerServices.findById(customer.getCustomerId());
		if (optional.isPresent() && bCryptPasswordEncoder.matches(pass, optional.get().getPassword())) {
			optional.get().setPassword(newPass);
			customerServices.save(optional.get());
			map.addAttribute("mess", "Thay đổi mật khẩu thành công");
			System.out.println("thành công");
			return "redirect:/user/profile";
		}else {
			System.out.println("Thất bại");
			map.addAttribute("mess", "Thay đổi mật khẩu thất bại! Mật khẩu cũ không chính xác");
			return "user/customers/changepass";
		}
		
	}
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageServices.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
    
	
	@RequestMapping("category/{name}")
	public ModelAndView view(ModelMap map, @PathVariable("name") String name) {
		List<Product> products = productServices.findAll();
		List<Product> list = new ArrayList<>();
		System.out.println(products.size());
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCategory().getName().equalsIgnoreCase(name)) {
				list.add(products.get(i));
			}
			}
		map.addAttribute("products", list);
		return new ModelAndView("user/products/category", map);
	}
}
