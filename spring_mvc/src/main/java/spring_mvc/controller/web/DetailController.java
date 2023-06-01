package spring_mvc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.dto.NewDTO;
import spring_mvc.entity.CategoryEntity;
import spring_mvc.repository.CategoryRepository;
import spring_mvc.service.INewsService;

@Controller(value = "detailControllerOfWeb")
public class DetailController {
	
	@Autowired
	private INewsService newsService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value={"/thoi-su", "/the-gioi", "/the-thao", "/phap-luat","/giao-duc", "/giai-tri", "/suc-khoe"}, method = RequestMethod.GET)
	public ModelAndView homePage1(@RequestParam("categoryCode") String categoryCode, @RequestParam("categoryName") String categoryName) {
		CategoryEntity category = categoryRepository.findOneByCode(categoryCode);
		NewDTO newest = newsService.findLastDetail(category.getId());
		
		NewDTO models = new NewDTO();
		models.setListResult(newsService.findByCategoryCode(categoryCode, newest.getId()));
		
		ModelAndView mav = new ModelAndView("web/detail");
		mav.addObject("models", models);
		mav.addObject("categoryName", categoryName);
		mav.addObject("categoryCode", categoryCode);
		mav.addObject("newest", newest);
		return mav;
	}
} 
