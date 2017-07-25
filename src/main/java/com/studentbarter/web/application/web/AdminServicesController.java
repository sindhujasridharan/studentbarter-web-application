package com.studentbarter.web.application.web;

import java.text.SimpleDateFormat;
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
import com.studentbarter.web.application.bean.UserRoleBean;
import com.studentbarter.web.application.bean.WebsiteFeedbackBean;
import com.studentbarter.web.application.service.AccountService;
import com.studentbarter.web.application.service.AdminService;
import com.studentbarter.web.application.service.ItemCategoryService;
import com.studentbarter.web.application.service.UserService;

@Controller
public class AdminServicesController {

	@Autowired
	private ItemCategoryService itemcservice;

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = { "/adminfunctions" }, method = RequestMethod.GET)
	public ModelAndView contactUs(Model model, Authentication authentication) {
		ModelAndView mv = new ModelAndView("adminfunctions");
		return mv;
	}

	@RequestMapping(value = { "/additemcategory" }, method = RequestMethod.GET)
	public ModelAndView addItemCategory(Model model, Authentication authentication, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("additemcategory");
		boolean isAdmin = true;
		Set<ItemCategory> itemCategories = itemcservice.findItemCategories();
		Iterator<ItemCategory> itr = itemCategories.iterator();
		List<String> itemCategoryNames = new ArrayList<String>();
		while (itr.hasNext()) {
			itemCategoryNames.add(itr.next().getItemcname());
		}

		mv.addObject("itemcategories", itemCategories);
		mv.addObject("isAdmin", isAdmin);
		request.getSession().removeAttribute("itemcategoriesforadmin");
		request.getSession().setAttribute("itemcategoriesforadmin", itemCategoryNames);

		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/insertnewitemcategory" }, method = RequestMethod.POST)
	public String insertItemcategory(@ModelAttribute("itemcnameForm") ItemCategory itemcnameForm,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		List<String> existingItemCategoryNames = (List<String>) (request.getSession()
				.getAttribute("itemcategoriesforadmin"));

		if (bindingResult.hasErrors()) {
			return "additemcategory";
		}

		boolean nameExists = false;
		for (String name : existingItemCategoryNames) {
			if (name.equalsIgnoreCase(itemcnameForm.getItemcname().trim())) {
				nameExists = true;
				break;
			}
		}

		request.getSession().removeAttribute("CategoryExists");
		if (nameExists) {
			request.getSession().setAttribute("CategoryExists", "yes");
		} else {
			itemcservice.save(itemcnameForm);
		}
		return "redirect:/additemcategory";
	}

	@RequestMapping(value = { "/userrolemodify" }, method = RequestMethod.GET)
	public ModelAndView userRoleModify(Model model, Authentication authentication) {
		ModelAndView mv = new ModelAndView("userrolemodify");

		List<User> users = userService.findUsers();
		UserRoleBean userRoleBean;
		List<UserRoleBean> list = new ArrayList<UserRoleBean>();
		for (User user : users) {
			userRoleBean = new UserRoleBean();
			userRoleBean.setUserId(user.getUserId());
			userRoleBean.setUserName(user.getFirstName() + user.getLastName());
			Set<Role> roles = user.getRoles();
			Iterator<Role> rolesItr = roles.iterator();
			List<String> existingRoles = new ArrayList<String>();
			List<String> newRolesToAdd = new ArrayList<String>();
			newRolesToAdd.add("ROLE_ADMIN");
			newRolesToAdd.add("ROLE_MODERATOR");
			newRolesToAdd.add("ROLE_USER");
			while (rolesItr.hasNext()) {
				existingRoles.add(rolesItr.next().getName());
			}

			userRoleBean.setExistingRoles(existingRoles);
			userRoleBean.setNewRolesToAdd(newRolesToAdd);
			list.add(userRoleBean);
		}

		return mv.addObject("userrolebeans", list);
	}

	@RequestMapping(value = { "/modifyuserrolesubmit" }, method = RequestMethod.GET)
	public ModelAndView userRoleModifySubmit(Model model, Authentication authentication, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("userrolemodify");
		System.out.println("on submit here");
		List<User> users = userService.findUsers();
		UserRoleBean userRoleBean;
		List<UserRoleBean> list = new ArrayList<UserRoleBean>();
		for (User user : users) {
			userRoleBean = new UserRoleBean();
			userRoleBean.setUserId(user.getUserId());
			userRoleBean.setUserName(user.getFirstName() + user.getLastName());
			Set<Role> roles = user.getRoles();
			/*if(user.getUserId() == 3) {
				roles.add(roleRepository.findOne(RoleRepository.DEFAULT_USER_ROLE));
				userRepository.updateRole(roles, user.getUserId());
			}*/
			Iterator<Role> rolesItr = roles.iterator();
			List<String> existingRoles = new ArrayList<String>();
			List<String> newRolesToAdd = new ArrayList<String>();
			newRolesToAdd.add("ROLE_ADMIN");
			newRolesToAdd.add("ROLE_MODERATOR");
			newRolesToAdd.add("ROLE_USER");
			while (rolesItr.hasNext()) {
				existingRoles.add(rolesItr.next().getName());
				if (!existingRoles.contains("ROLE_ADMIN") && user.getUserId() == 3) {
					existingRoles.add("ROLE_ADMIN");
				}
			}

			userRoleBean.setExistingRoles(existingRoles);
			userRoleBean.setNewRolesToAdd(newRolesToAdd);
			list.add(userRoleBean);
		}

		return mv.addObject("userrolebeans", list);
	}

	@RequestMapping(value = { "/viewuserfeedback" }, method = RequestMethod.GET)
	public ModelAndView viewUsersFeedback(Model model, Authentication authentication) {
		ModelAndView mv = new ModelAndView("userfeedbacks");
		List<WebsiteFeedback> feedbacks = adminService.findAllFeedbacks();

		List<WebsiteFeedbackBean> feedbackBeans = new ArrayList<WebsiteFeedbackBean>();
		WebsiteFeedbackBean websiteFeedbackBean;
		for (WebsiteFeedback feedback : feedbacks) {
			Account account = accountService.findByUsername(authentication.getName());
			User user = userService.findByAccount(account);
			websiteFeedbackBean = new WebsiteFeedbackBean(user.getFirstName().concat(" ").concat(user.getLastName()),
					authentication.getName(), feedback.getDescription(),
					new SimpleDateFormat("yyyy-MM-dd").format(feedback.getAddeddate()));
			feedbackBeans.add(websiteFeedbackBean);
		}

		mv.addObject("websitefeedbacks", feedbackBeans);
		return mv;
	}

}
