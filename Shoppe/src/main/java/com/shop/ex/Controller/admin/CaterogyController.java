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

import com.shop.ex.domain.Category;
import com.shop.ex.model.CategoryDTO;
import com.shop.ex.services.CategoryServices;

@Controller
@RequestMapping("admin/categories")
public class CaterogyController {
    @Autowired
	CategoryServices categoryServices;
	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDTO());
		return "admin/categories/addOrEdit";
	}
	
	@RequestMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model,@PathVariable("categoryId") long categoryId) {
		Optional<Category> optional = categoryServices.findById(categoryId);
		CategoryDTO dto =new CategoryDTO();
		if (optional.isPresent()) {
			Category entity = optional.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(false);
			model.addAttribute("category", dto);
			return new ModelAndView("admin/categories/addOrEdit",model);
		}
		System.out.println("khong tim dc");
		model.addAttribute("mess", "category is not existed");
		return new ModelAndView("forward:/admin/categories/addOrEdit",model);
	}
	@RequestMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap modelMap,@PathVariable("categoryId") long categoryId) {
		categoryServices.deleteById(categoryId);
		return new ModelAndView("forward:/admin/categories",modelMap);
	}
	
	@PostMapping("saveOrupdate")
	public ModelAndView saveOrupdate(ModelMap model,@Valid @ModelAttribute("category") CategoryDTO dto,BindingResult result) {	
		System.out.println("bắt đầu");
		if (result.hasErrors()) {
			
			return new ModelAndView("admin/categories/addOrEdit",model);
		}
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		categoryServices.save(entity);
		System.out.println("thành công");
		return new ModelAndView("redirect:/admin/categories", model);
	}
	
	@GetMapping("")
	public String search(ModelMap model , @RequestParam(name = "name",required = false) String name,@RequestParam("page") Optional<Integer> page,@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize,Sort.by("name"));
		Page<Category> resultPage = null;
	
		if (StringUtils.hasText(name)) {
			resultPage =categoryServices.findByNameContaining(name,pageable);
			model.addAttribute("name", name);
		}else {
			resultPage =categoryServices.findAll(pageable);
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
		model.addAttribute("categoryPage", resultPage);
		return "admin/categories/list";
	}
	
}
