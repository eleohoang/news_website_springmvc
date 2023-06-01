package spring_mvc.controller.admin;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import spring_mvc.dto.NewDTO;
import spring_mvc.service.INewsService;

@Controller(value = "thumbnailControllerOfAdmin")
public class ThumbnailController {
	@Autowired
	private INewsService newService;
	
    @RequestMapping(value="/quan-tri/bai-viet/chinh-sua/thumbnail", method=RequestMethod.POST)  
    public ModelAndView saveimage( @RequestParam CommonsMultipartFile file,  
    		HttpSession session, @RequestParam(value = "id", required = true) Long id,
    		@RequestParam(value="categoryCode", required = true) String categoryCode, HttpServletRequest request
    		) throws Exception{  

    	String thumbnail = getThumbnail(file, request);
    	
    	NewDTO newdto = new NewDTO();
    	newdto.setId(id);
    	newdto.setThumbnail(thumbnail);
    	newdto.setCategoryCode(categoryCode);
    	NewDTO oldto = newService.save(newdto);
    	System.out.println(thumbnail);
    	ModelAndView mav = new ModelAndView("/admin/new/thumbnail");   
    	mav.addObject("model", oldto);
    	mav.addObject("message", "update thumbnail success");
    	mav.addObject("thumbnail", thumbnail);
    	return mav;
    }  
    
    private String getThumbnail(MultipartFile file, HttpServletRequest request) {
    	String nameFile = String.valueOf(new Date().getTime()+".jpg");
		System.out.println(nameFile);
		if(!"".equals(nameFile)){
			String dirFile = request.getServletContext().getRealPath("my_images");
			System.out.println(dirFile);
			File fileDir = new File(dirFile);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			try {
				file.transferTo(new File(fileDir+File.separator+nameFile));
				System.out.println("Upload file thành công!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Upload file thất bại!");
			}
		}
		return nameFile;
    }
}

