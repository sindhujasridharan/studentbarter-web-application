package com.studentbarter.web.application.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.studentbarter.web.application.bean.*;
import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.service.*;

@Controller
public class AddNewPostController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserService userService;
   
    @Autowired
    private ItemCategoryService itemCategoryService;
    
    @Autowired
    private ItemService itemService;
  
    @RequestMapping(value = "/addnewpost", method = RequestMethod.GET)
    public String addNewPostNavigate(Model model, HttpServletRequest request) {
    	
    	model.addAttribute("addNewPostForm", new Post());
    	
    	Set<ItemCategory> itemCategoryDB = itemCategoryService.findItemCategories();
		Iterator<ItemCategory> itr = itemCategoryDB.iterator();
		List<ItemCategoryBean> itemCategoryBeanList = new ArrayList<ItemCategoryBean>();
		while (itr.hasNext()) {
			ItemCategory itemCat = itr.next();	
			itemCategoryBeanList.add(new ItemCategoryBean(itemCat.getItemcid(), itemCat.getItemcname(), false));			
		}
		
		model.addAttribute("itemcategoriesnewpost", itemCategoryBeanList);
    	
        return "addnewpost";
    }
   
    @RequestMapping(value = "/addnewpost", method = RequestMethod.POST)
    public String addNewUserPost(@ModelAttribute("addNewPostForm") Post addNewPostForm,
    		BindingResult bindingResult, Model model, HttpServletRequest request) {
        //userValidator.validate(addNewPostForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addnewpost";
        }
        String username = securityService.findLoggedInUsername();
        User user = userService.findByAccount(accountService.findByUsername(username));
        
        addNewPostForm.setAddedby(user.getUserId());
        addNewPostForm.setAddeddate(new Date());
        addNewPostForm.setRowstate("a");
        addNewPostForm.setStatusid(new Long(1000));
        addNewPostForm.setItemcid(Long.parseLong(request.getParameter("itemcategoryfornewpost")));
        itemService.insertNewPost(addNewPostForm);
                
        return "redirect:/myposts";
    }
}
