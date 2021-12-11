package com.shop.ex.Controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.ex.domain.CartItem;
import com.shop.ex.domain.Customer;
import com.shop.ex.domain.Product;
import com.shop.ex.services.CartItemServices;
import com.shop.ex.services.ProductServices;
import com.shop.ex.services.StorageServices;

@Controller
@RequestMapping("cart")
public class ShopingCartController {
    
	@Autowired
	HttpSession session;
	@Autowired
	StorageServices storageServices;
	@Autowired
	ProductServices productServices;
	@Autowired
	CartItemServices cartItemServices;
	@GetMapping("add/{productId}")
	public String add(@PathVariable("productId") Long productId,ModelMap modelMap) {
		Customer customer =(Customer) session.getAttribute("user");
		Optional<Product> optional = productServices.findById(productId);
		Map<Long, CartItem> map =new HashMap<>();
		List<CartItem> list = cartItemServices.findByCustomer(customer);
		for (int i = 0; i < list.size(); i++) {
				map.put(list.get(i).getProduct().getProductId(), list.get(i));
		}
		CartItem  cartItem = new CartItem();
		cartItem.setCustomer(customer);
		cartItem.setProduct(optional.get());
		cartItem.setQuantity(1);
		cartItemServices.add(cartItem, map, productId);
		return "redirect:/cart/list";
	}
	//get image
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageServices.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFilename()+"\"").body(file);
	    
	}
	@PostMapping("update")
	public String update(@RequestParam("id") Integer id,@RequestParam("quantity") Integer quantity) {
		cartItemServices.update(id, quantity);
		if (quantity<=0) {
			cartItemServices.deleteById(id);
		}
		return "redirect:/cart/list";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id,ModelMap map) {
		cartItemServices.deleteById(id);
		return "redirect:/cart/list";
	}
    
	@GetMapping("list")
	public ModelAndView list(ModelMap map) {
		Customer customer =(Customer) session.getAttribute("user");
		List<CartItem> list = cartItemServices.findByCustomer(customer);
		map.addAttribute("list", list);
		return new ModelAndView("user/customers/shoppingcart");
	}

}
