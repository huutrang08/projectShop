package com.shop.ex.Controller.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.mail.FetchProfile.Item;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.ex.domain.Category;
import com.shop.ex.domain.Product;
import com.shop.ex.model.CategoryDTO;
import com.shop.ex.model.ProductDTO;
import com.shop.ex.services.CategoryServices;
import com.shop.ex.services.ProductServices;
import com.shop.ex.services.StorageServices;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	@Autowired
	ProductServices productServices;
    @Autowired
    CategoryServices categoryServices;
    @Autowired
    StorageServices storageServices;
    
    @ModelAttribute("categories")
    public List<CategoryDTO> getCategoryDTOs(){
    	return categoryServices.findAll().stream().map(item->{
    		CategoryDTO dto = new CategoryDTO();
    		BeanUtils.copyProperties(item, dto);
    		return dto;
    	}).collect(Collectors.toList());
    }
    
	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("product", new ProductDTO());
		return "admin/products/addOrEdit";
	}
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		System.out.println("bắt đầu tìm ảnh");
		Resource file = storageServices.loadAsResource(filename);System.out.println("tìm xong");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFilename()+"\"").body(file);
	    
	}
	@RequestMapping("edit/{productId}")
	public ModelAndView edit(ModelMap model,@PathVariable("productId") long productId) {
		Optional<Product> optional = productServices.findById(productId);
		ProductDTO dto =new ProductDTO();
		if (optional.isPresent()) {
			Product entity = optional.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setCategotyId(entity.getCategory().getCategotyId());
			dto.setIsEdit(true);
			model.addAttribute("product", dto);
			System.out.println();
			return new ModelAndView("admin/products/addOrEdit",model);
		}
		System.out.println("khong tim dc");
		model.addAttribute("mess", "product is not existed");
		return new ModelAndView("forward:/admin/products/addOrEdit",model);
	}
	
	@RequestMapping("delete/{productId}")
	public ModelAndView delete(ModelMap modelMap,@PathVariable("productId") long productId) throws IOException {
		Optional<Product> optional = productServices.findById(productId);
		if (optional.isPresent()) {
			if (!StringUtils.isEmpty(optional.get().getImage())) {
				storageServices.delete(optional.get().getImage()); 
			}
			productServices.delete(optional.get());
		    modelMap.addAttribute("message","deleted");
		}else {
			modelMap.addAttribute("message", "deleted fault");
		}
	     
		return new ModelAndView("forward:/admin/products",modelMap);
	}
	
	@PostMapping("saveOrupdate")
	public ModelAndView saveOrupdate(ModelMap model,@Valid @ModelAttribute("product") ProductDTO dto,BindingResult result) {	
		System.out.println("bắt đầu");
		if (result.hasErrors()) {
			
			return new ModelAndView("admin/products/addOrEdit",model);
		}
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		Category category = new Category();
		category.setCategotyId(dto.getCategotyId());
		entity.setCategory(category);
		if (!dto.getImageFile().isEmpty()) {
		    UUID uuid = UUID.randomUUID();
		    String uu = uuid.toString();
			entity.setImage(storageServices.getStoredFileName(dto.getImageFile(), uu));
			storageServices.store(dto.getImageFile(), entity.getImage());
		}
		productServices.save(entity);
		model.addAttribute("category", entity);
		System.out.println("thành công");
		return new ModelAndView("redirect:/admin/products", model);
	}
	@GetMapping("")
	public String search(ModelMap model , @RequestParam(name = "name",required = false) String name,@RequestParam("page") Optional<Integer> page,@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize,Sort.by("name"));
		Page<Product> resultPage = null;
	
		if (StringUtils.hasText(name)) {
			resultPage =productServices.findByNameContaining(name,pageable);
			model.addAttribute("name", name);
		}else {
			resultPage =productServices.findAll(pageable);
		}
		int total = resultPage.getTotalPages();
		if (total>0) {
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage+2, total);
			if (total>5) {
				if (end == total) start = end - 5;
				else if (start==1) end =start + 5;
			}
		List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		model.addAttribute("pageNumber",pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		return "admin/products/list";
	}
}
