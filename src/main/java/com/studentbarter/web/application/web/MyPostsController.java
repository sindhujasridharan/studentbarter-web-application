package com.studentbarter.web.application.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.studentbarter.web.application.model.*;
import com.mysql.jdbc.StringUtils;
import com.studentbarter.web.application.bean.ItemCategoryBean;
import com.studentbarter.web.application.bean.MyPostsBean;
import com.studentbarter.web.application.service.AccountService;
import com.studentbarter.web.application.service.ItemCategoryService;
import com.studentbarter.web.application.service.ItemService;
import com.studentbarter.web.application.service.SecurityService;
import com.studentbarter.web.application.service.UserService;

@Controller
public class MyPostsController {

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

	@RequestMapping(value = { "/myposts" }, method = RequestMethod.GET)
	public ModelAndView myPosts(Model model, Authentication authentication) {
		ModelAndView mv = new ModelAndView("myposts");

		String username = securityService.findLoggedInUsername();
		User user = userService.findByAccount(accountService.findByUsername(username));

		List<Post> dbPosts = itemService.findLoggedInUserPosts(user.getUserId(), new Long(1000));
		List<MyPostsBean> myPostsBeanList = new ArrayList<MyPostsBean>();
		MyPostsBean mypost = null;
		for (Post dbPost : dbPosts) {
			User addedByUser = userService.findByUserId(dbPost.getAddedby());
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());

			mypost = new MyPostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(),
					dbPost.getDescription(), dbPost.getItemworth(), dbPost.getItemcid(), itemCategory.getItemcname(),
					null, null, dbPost.getSolddate(), dbPost.getAddeddate(), dbPost.getAddedby(),
					addedByUser.getFirstName(), dbPost.getRowstate());
			myPostsBeanList.add(mypost);
		}

		mv.addObject("loggedinusername", username);
		mv.addObject("myposts", myPostsBeanList);

		return mv;
	}

	@RequestMapping(value = { "/modifypost" }, method = RequestMethod.GET)
	public String modifyMyPost(Model model, Authentication authentication, HttpServletRequest request) {
		
		String username = securityService.findLoggedInUsername();
		
		Post dbPost = itemService.findPostByItemId(Long.parseLong(request.getParameter("itemid")));

		User addedByUser = userService.findByUserId(dbPost.getAddedby());
		ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());

		MyPostsBean mypost = new MyPostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(), dbPost.getDescription(),
				dbPost.getItemworth(), dbPost.getItemcid(), itemCategory.getItemcname(), null, null,
				dbPost.getSolddate(), dbPost.getAddeddate(), dbPost.getAddedby(), addedByUser.getFirstName(),
				dbPost.getRowstate());
		
		Set<ItemCategory> itemCategoryDB = itemCategoryService.findItemCategories();
		Iterator<ItemCategory> itr = itemCategoryDB.iterator();
		List<ItemCategoryBean> itemCategoryBeanList = new ArrayList<ItemCategoryBean>();
		while (itr.hasNext()) {
			ItemCategory itemCat = itr.next();
			itemCategoryBeanList.add(new ItemCategoryBean(itemCat.getItemcid(), itemCat.getItemcname(), false));			
		}
		
		model.addAttribute("itemcategoriesmodifypost", itemCategoryBeanList);		
		model.addAttribute("loggedinusername", username);		
		model.addAttribute("modifyPostForm", new Post());
		model.addAttribute("modifyPostFormValues", mypost);
		request.getSession().removeAttribute("mypostbeforeedit");
		request.getSession().setAttribute("mypostbeforeedit", mypost);

		return "modifypost";
	}
	
	@RequestMapping(value = { "/modifypost" }, method = RequestMethod.POST)
	public String editMyPost(@ModelAttribute("modifyPostForm") Post modifyPostForm,
    		BindingResult bindingResult, Model model, HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
            return "modifypost";
        }
		
		String username = securityService.findLoggedInUsername();
        User user = userService.findByAccount(accountService.findByUsername(username));
         
        if(StringUtils.isNullOrEmpty(request.getParameter("remove"))) {
        	modifyPostForm.setItemcid(Long.parseLong(request.getParameter("itemcategoriesmodifypost")));
            MyPostsBean postBeforeEdit = (MyPostsBean)(request.getSession().getAttribute("mypostbeforeedit"));
            modifyPostForm.setAddedby(postBeforeEdit.getAddedby());
            modifyPostForm.setAddeddate(postBeforeEdit.getAddeddate());
            modifyPostForm.setItemid(postBeforeEdit.getItemid());
            modifyPostForm.setRowstate("a");
            modifyPostForm.setStatusid(new Long(1000));        
            itemService.updateUserPost(modifyPostForm);
        } else {
        	modifyPostForm.setItemcid(Long.parseLong(request.getParameter("itemcategoriesmodifypost")));
            MyPostsBean postBeforeEdit = (MyPostsBean)(request.getSession().getAttribute("mypostbeforeedit"));
            modifyPostForm.setAddedby(postBeforeEdit.getAddedby());
            modifyPostForm.setAddeddate(postBeforeEdit.getAddeddate());
            modifyPostForm.setItemid(postBeforeEdit.getItemid());
            modifyPostForm.setRowstate("d");
            modifyPostForm.setStatusid(new Long(1000));        
            itemService.updateUserPost(modifyPostForm);
        }
        
		
		List<Post> dbPosts = itemService.findLoggedInUserPosts(user.getUserId(), new Long(1000));
		List<MyPostsBean> myPostsBeanList = new ArrayList<MyPostsBean>();
		MyPostsBean mypost = null;
		for (Post dbPost : dbPosts) {
			User addedByUser = userService.findByUserId(dbPost.getAddedby());
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());

			mypost = new MyPostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(),
					dbPost.getDescription(), dbPost.getItemworth(), dbPost.getItemcid(), itemCategory.getItemcname(),
					null, null, dbPost.getSolddate(), dbPost.getAddeddate(), dbPost.getAddedby(),
					addedByUser.getFirstName(), dbPost.getRowstate());
			myPostsBeanList.add(mypost);
		}

		model.addAttribute("loggedinusername", username);
		model.addAttribute("myposts", myPostsBeanList);		

		return "redirect:/myposts";
	}

}
