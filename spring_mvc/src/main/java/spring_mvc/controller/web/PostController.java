package spring_mvc.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.constant.SystemConstant;
import spring_mvc.dto.CommentDTO;
import spring_mvc.dto.MyUser;
import spring_mvc.dto.NewDTO;
import spring_mvc.dto.UserDTO;
import spring_mvc.entity.UserEntity;
import spring_mvc.repository.UserRepository;
import spring_mvc.service.ICommentService;
import spring_mvc.service.INewsService;
import spring_mvc.service.IUserService;
import spring_mvc.util.MessageUtil;
import spring_mvc.util.SecurityUtils;

@Controller(value = "postControllerOfWeb")
public class PostController {
	@Autowired
	private INewsService newService;
	
	@Autowired
	private ICommentService commentService;

	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/chi-tiet-bai-viet", method = RequestMethod.GET)
	public ModelAndView showpost(@RequestParam(value = "postId", required = true) Long postId, HttpServletRequest request) {
		NewDTO model = newService.findPost(postId);
		CommentDTO commentmodel = new CommentDTO();
		commentmodel.setListResult(commentService.getComments(postId));
		
		ModelAndView mav = new ModelAndView("web/post");
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		mav.addObject("commentmodel", commentmodel);
		
		//get information for current use
		UserDTO user = new UserDTO();
		if (SecurityUtils.getAuthorities().contains("ADMIN") ||
			SecurityUtils.getAuthorities().contains("USER")) {
			String userName = SecurityUtils.getPrincipal().getUsername();
			user = userService.findUser(userName);
		} else {
			user.setId(0L);
		}
		
		mav.addObject("user", user);
		return mav;
	}
}
