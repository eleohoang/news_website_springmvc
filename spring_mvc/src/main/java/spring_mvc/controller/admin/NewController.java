package spring_mvc.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.dto.NewDTO;
import spring_mvc.service.ICategoryService;
import spring_mvc.service.INewsService;
import spring_mvc.util.MessageUtil;

@Controller(value = "newControllerAdmin")
public class NewController {
	@Autowired
	private INewsService newService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, 
						 		 @RequestParam("limit") int limit, HttpServletRequest request){
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		Pageable pageable = new PageRequest(page - 1, limit);
		
		ModelAndView mav = new ModelAndView("admin/new/list");
		model.setListResult(newService.findAll(pageable));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if (id != null) {
			model = newService.findById(id);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/thumbnail", method = RequestMethod.GET)
	public ModelAndView addThumbnail(@RequestParam(value="id", required = true) Long id, 
			@RequestParam(value="categoryCode", required = true) String categoryCode) {
		ModelAndView mav = new ModelAndView("admin/new/thumbnail");
		mav.addObject("id", id);
		mav.addObject("categoryCode", categoryCode);
		return mav;
	}
	
}

