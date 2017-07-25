package com.studentbarter.web.application.web;

import java.util.Date;
import java.util.Properties;

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

import com.studentbarter.web.application.model.*;
import com.studentbarter.web.application.service.AccountService;
import com.studentbarter.web.application.service.AdminService;
import com.studentbarter.web.application.service.UserService;

@Controller
public class ContactUsController {
	
	@Autowired
	public AdminService adminService;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/contactus" }, method = RequestMethod.GET)
	public ModelAndView contactUs(Model model, Authentication authentication) {
		ModelAndView mv = new ModelAndView("contactus");
		return mv;
	}

	@RequestMapping(value = { "/email" }, method = RequestMethod.POST)
	public ModelAndView emailSubmit(Model model, HttpServletRequest request, Authentication authentication) throws MessagingException {
		ModelAndView mv = new ModelAndView("contactus");

		String to =  request.getParameter("email");
		String from = "sbarterfall2016@gmail.com";
		String subject = "Student Barter Street - Welcome to our email list";
		String body = "Dear " + request.getParameter("fullname") + ",\n\n" + "Thanks for joining our email list.\n" + request.getParameter("message") 
		+ "\n" + "We will respond to your message very soon." +"\n\n\n"
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
		
		Account account = accountService.findByUsername(authentication.getName());
		User user = userService.findByAccount(account);
		
		WebsiteFeedback websiteFeedback = new WebsiteFeedback();
		websiteFeedback.setDescription(request.getParameter("message"));
		websiteFeedback.setAddedby(user.getUserId());
		websiteFeedback.setAddeddate(new Date());
		adminService.insertWebsiteFeedback(websiteFeedback);

		return mv.addObject("emailsuccess", "Thank You for the feedback. Please check your inbox for acknowledgment");
	}
}
