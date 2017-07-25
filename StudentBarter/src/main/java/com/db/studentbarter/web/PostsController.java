package com.db.studentbarter.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.studentbarter.model.Account;
import com.db.studentbarter.model.ItemCategory;
import com.db.studentbarter.model.Post;
import com.db.studentbarter.model.Role;
import com.db.studentbarter.model.User;
import com.db.studentbarter.service.AccountService;
import com.db.studentbarter.service.ItemCategoryService;
import com.db.studentbarter.service.ItemService;
import com.db.studentbarter.service.UserService;
import com.db.studentbarter.uibean.ItemCategoryBean;
import com.db.studentbarter.uibean.PostsBean;

import antlr.debug.MessageEvent;

@Controller
public class PostsController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemCategoryService itemCategoryService;

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = { "/", "/posts" }, method = RequestMethod.GET)
	public ModelAndView posts(Model model, Authentication authentication, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("posts");
		if (request.getParameter("emailquerysuccess") != null
				&& !(request.getParameter("emailquerysuccess")).isEmpty()) {
			mv.addObject("emailquerysuccesspost", "Query emailed to the user");			
		}
		Account account = accountService.findByUsername(authentication.getName());
		User user = userService.findByAccount(account);

		boolean isAdmin = false;
		for (Role role : user.getRoles()) {
			if (role.getName().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}
		}

		List<Post> dbPosts = itemService.findOtherUserPosts(user.getUserId(), new Long(1000));
		List<PostsBean> postsBeanList = new ArrayList<PostsBean>();
		PostsBean post = null;
		for (Post dbPost : dbPosts) {
			User addedByUser = userService.findByUserId(dbPost.getAddedby());
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());

			post = new PostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(), dbPost.getDescription(),
					dbPost.getItemworth(), dbPost.getItemcid(), itemCategory.getItemcname(), null, null,
					dbPost.getSolddate(), dbPost.getAddeddate(), dbPost.getAddedby(), addedByUser.getFirstName(),
					dbPost.getRowstate());
			postsBeanList.add(post);
		}
		Set<ItemCategory> itemCategoryDB = itemCategoryService.findItemCategories();
		Iterator<ItemCategory> itr = itemCategoryDB.iterator();
		List<ItemCategoryBean> itemCategoryBeanList = new ArrayList<ItemCategoryBean>();
		while (itr.hasNext()) {
			ItemCategory itemCat = itr.next();
			itemCategoryBeanList.add(new ItemCategoryBean(itemCat.getItemcid(), itemCat.getItemcname(), false));
		}

		
		mv.addObject("itemcategories", itemCategoryBeanList);
		mv.addObject("posts", postsBeanList);
		mv.addObject("isAdmin", isAdmin);

		return mv;
	}

	@RequestMapping(value = { "/fliterposts" }, method = RequestMethod.GET)
	public ModelAndView clearPosts(HttpServletRequest request, Model model, Authentication authentication) {
		System.out.println("filter is: " + request.getParameter("filter"));
		System.out.println("auth:" + authentication.getName());
		Account account = accountService.findByUsername(authentication.getName());
		User user = userService.findByAccount(account);

		boolean isAdmin = false;
		for (Role role : user.getRoles()) {
			if (role.getName().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}
		}

		List<Post> dbPosts = itemService.findOtherUserPosts(user.getUserId(), new Long(1000));
		List<Long> selectedItemCids = new ArrayList<Long>();
		if (request.getParameter("filter").equals("Y")) {
			String[] selectedItems = request.getParameterValues("itemcategories");
			if (selectedItems != null && selectedItems.length != 0) {
				List<String> selectedItemsList = Arrays.asList(selectedItems);
				for (String selectedItemCatId : selectedItemsList) {
					selectedItemCids.add(Long.parseLong(selectedItemCatId));
				}
			}
		}

		List<PostsBean> postsBeanList = new ArrayList<PostsBean>();
		PostsBean post = null;
		for (Post dbPost : dbPosts) {
			if (request.getParameter("filter").equals("Y") && selectedItemCids.contains(dbPost.getItemcid())) {
				User addedByUser = userService.findByUserId(dbPost.getAddedby());
				ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());

				post = new PostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(),
						dbPost.getDescription(), dbPost.getItemworth(), dbPost.getItemcid(),
						itemCategory.getItemcname(), null, null, dbPost.getSolddate(), dbPost.getAddeddate(),
						dbPost.getAddedby(), addedByUser.getFirstName(), dbPost.getRowstate());
				postsBeanList.add(post);
			} else if (request.getParameter("filter").equals("N")
					|| (request.getParameter("filter").equals("Y") && selectedItemCids.isEmpty())) {
				User addedByUser = userService.findByUserId(dbPost.getAddedby());
				ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());

				post = new PostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(),
						dbPost.getDescription(), dbPost.getItemworth(), dbPost.getItemcid(),
						itemCategory.getItemcname(), null, null, dbPost.getSolddate(), dbPost.getAddeddate(),
						dbPost.getAddedby(), addedByUser.getFirstName(), dbPost.getRowstate());
				postsBeanList.add(post);
			}
		}

		Set<ItemCategory> itemCategoryDB = itemCategoryService.findItemCategories();
		Iterator<ItemCategory> itr = itemCategoryDB.iterator();
		List<ItemCategoryBean> itemCategoryBeanList = new ArrayList<ItemCategoryBean>();
		while (itr.hasNext()) {
			ItemCategory itemCat = itr.next();
			if (selectedItemCids.contains(itemCat.getItemcid())) {
				itemCategoryBeanList.add(new ItemCategoryBean(itemCat.getItemcid(), itemCat.getItemcname(), true));
			} else {
				itemCategoryBeanList.add(new ItemCategoryBean(itemCat.getItemcid(), itemCat.getItemcname(), false));
			}
		}

		ModelAndView mv = new ModelAndView("posts");
		mv.addObject("itemcategories", itemCategoryBeanList);
		mv.addObject("posts", postsBeanList);
		mv.addObject("isAdmin", isAdmin);

		return mv;
	}

	@RequestMapping(value = { "/emailuserpost" }, method = RequestMethod.GET)
	public ModelAndView emailUserAboutPost(HttpServletRequest request, Model model, Authentication authentication) {
		ModelAndView mv = new ModelAndView("emailuseraboutpost");
		Long userIdOfPostOwner = Long.parseLong(request.getParameter("email"));
		User owner = userService.findByUserId(userIdOfPostOwner);

		Post dbPost = itemService.findPostByItemId(Long.parseLong(request.getParameter("itemid")));
		ItemCategory itemCategory = itemCategoryService.findItemCategoryById(dbPost.getItemcid());

		PostsBean postBean = new PostsBean(dbPost.getItemid(), dbPost.getTitle(), dbPost.getStatusid(),
				dbPost.getDescription(), dbPost.getItemworth(), dbPost.getItemcid(), itemCategory.getItemcname(), null,
				null, dbPost.getSolddate(), dbPost.getAddeddate(), dbPost.getAddedby(), owner.getFirstName(),
				dbPost.getRowstate());

		request.getSession().setAttribute("fromemailid", authentication.getName());
		request.getSession().setAttribute("toemailid", owner.getAccount().getUsername());
		request.getSession().setAttribute("toname", postBean.getAddedbyName());
		request.getSession().setAttribute("postitle", postBean.getTitle());

		mv.addObject("postdetails", postBean);

		return mv;

	}

	@RequestMapping(value = { "/emailqueries" }, method = RequestMethod.POST)
	public String emailQuery(HttpServletRequest request, Model model, Authentication authentication)
			throws MessagingException {
		
		String question = request.getParameter("message");

		String to = (String) request.getSession().getAttribute("toemailid");
		String toname = (String) request.getSession().getAttribute("toname");
		String fromname = (String) request.getSession().getAttribute("fromemailid");
		String title = (String) request.getSession().getAttribute("postitle");
		String from = "sbarterfall2016@gmail.com";
		String subject = "Student Barter Street - Welcome to our email list";
		String body = "Dear " + toname + ",\n\n" + fromname + " has some questions on your post titled '" + title
				+ "'.\n Below is the message: \n\n" + question + "\n"
				+ "Kindly look into these and email your reply to " + fromname + "\n\n\n"
				+ "Regards,\nTeam: Student Barter Street";
		boolean isBodyHTML = false;

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", "smtp.gmail.com");
		props.put("mail.smtps.port", 465);
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtps.quitwait", "false");
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);

		// 2 - create a message
		Message message = new MimeMessage(session);
		message.setSubject(subject);
		if (isBodyHTML) {
			message.setContent(body, "text/html");
		} else {
			message.setText(body);
		}

		// 3 - address the message
		Address fromAddress = new InternetAddress(from);
		Address toAddress = new InternetAddress(to);
		message.setFrom(fromAddress);
		message.setRecipient(Message.RecipientType.TO, toAddress);

		// 4 - send the message
		Transport transport = session.getTransport();
		transport.connect("sbarterfall2016@gmail.com", "sbs@1234");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

		request.getSession().removeAttribute("toemailid");
		request.getSession().removeAttribute("toname");
		request.getSession().removeAttribute("fromemailid");
		request.getSession().removeAttribute("postitle");

		//request.setAttribute("emailquerysuccess", "Query emailed to the user");

		return "redirect:/posts?emailquerysuccess=1";

	}

}
