package com.studentbarter.web.application.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.bean.MyPostsBean;
import com.studentbarter.web.application.service.AccountService;
import com.studentbarter.web.application.service.ItemCategoryService;
import com.studentbarter.web.application.service.ItemService;
import com.studentbarter.web.application.service.SecurityService;
import com.studentbarter.web.application.service.UserService;

@Controller
public class modifyrolecontroller {
	
	@Autowired
    private SecurityService securityService;
	
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ItemCategoryService itemCategoryService;
    
    @RequestMapping(value = {"/myposts1"}, method = RequestMethod.GET)
    public ModelAndView myPosts(Model model, Authentication authentication) {    	    	
    	ModelAndView mv = new ModelAndView("myposts");
    	
    	String username = securityService.findLoggedInUsername();
    	User user = userService.findByAccount(accountService.findByUsername(username));
    	
    	List<Post> dbPosts = itemService.findLoggedInUserPosts(user.getUserId(), new Long(1000));
		List<MyPostsBean> myPostsBeanList = new ArrayList<MyPostsBean>();
		MyPostsBean mypost = null;
		for(Post dbPost : dbPosts) {			
			User addedByUser = userService.findByUserId(dbPost.getAddedby());			
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());
									
			mypost = new MyPostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(), dbPost.getDescription(),
					dbPost.getItemworth(), dbPost.getItemcid(),
					itemCategory.getItemcname(), null, null,
					dbPost.getSolddate(), dbPost.getAddeddate(), dbPost.getAddedby(),
					addedByUser.getFirstName(), dbPost.getRowstate());
			myPostsBeanList.add(mypost);			
		}

    	
    	mv.addObject("loggedinusername", username);
    	mv.addObject("myposts", myPostsBeanList);
    	
    	return mv;
    }
}
