package spring_mvc.controller.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.dto.CategoryDTO;
import spring_mvc.dto.NewDTO;
import spring_mvc.entity.CategoryEntity;
import spring_mvc.service.ICategoryService;
import spring_mvc.service.ICommentService;
import spring_mvc.service.INewsService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private INewsService newService;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		NewDTO newest = newService.findTheNewest();
		
		List<CategoryDTO> categoryDTOs = categoryService.findAllList();
		NewDTO models = new NewDTO();
		long[] ids = new long[categoryDTOs.size()];
		int i = 0;
		for (CategoryDTO categoryDTO : categoryDTOs) {
			if (categoryDTO.getId() != newest.getCategoryId()) {
				ids[i] = categoryDTO.getId();
				i++;
			}
		}
		models.setListResult(newService.findNewList(ids));
		
		ModelAndView mav = new ModelAndView("web/home");
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("newest", newest);
		mav.addObject("models", models);
		return mav;
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
	
} 
