package com.db.studentbarter.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.studentbarter.model.ItemCategory;
import com.db.studentbarter.model.ItemRating;
import com.db.studentbarter.model.Post;
import com.db.studentbarter.model.User;
import com.db.studentbarter.service.AccountService;
import com.db.studentbarter.service.ItemCategoryService;
import com.db.studentbarter.service.ItemService;
import com.db.studentbarter.service.RatingService;
import com.db.studentbarter.service.SecurityService;
import com.db.studentbarter.service.UserService;
import com.db.studentbarter.uibean.BoughtItemsBean;
import com.db.studentbarter.uibean.SoldItemsBean;

@Controller
public class TransactionHistoryController {

	@Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ItemCategoryService itemCategoryService;
    
    @Autowired
    private RatingService ratingService;
     
    @RequestMapping(value = {"/transactionhistory"}, method = RequestMethod.GET)
    public ModelAndView transactionHistory(Model model, Authentication authentication) {    	    	
    	ModelAndView mv = new ModelAndView("transactionhistory"); 
    	
    	String username = securityService.findLoggedInUsername();
    	User user = userService.findByAccount(accountService.findByUsername(username));
    	
    	List<Post> soldPosts = itemService.findSoldItemsTransactionHistory
    			(user.getUserId(), new Long(2000));
		List<SoldItemsBean> soldItemsBeanList = new ArrayList<SoldItemsBean>();
		SoldItemsBean soldItem = null;
		for(Post soldDbPost : soldPosts) {			
			User addedByUser = userService.findByUserId(soldDbPost.getAddedby());
			User endUser = userService.findByUserId(soldDbPost.getEnduserid());
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(soldDbPost.getItemcid());
			
			String itemRatingGivenValue = "";
			ItemRating itemRating = ratingService.findItemRatingByItemId(soldDbPost.getItemid());
			if(itemRating == null || itemRating.getRating() == null) {
				itemRatingGivenValue = "Pending..";
			} else {
				itemRatingGivenValue = Double.valueOf(itemRating.getRating()).toString();
			}
									
			soldItem = new SoldItemsBean(soldDbPost.getItemid(), soldDbPost.getTitle(), soldDbPost.getStatusid(), soldDbPost.getDescription(),
					soldDbPost.getItemworth(), soldDbPost.getItemcid(),
					itemCategory.getItemcname(), soldDbPost.getEnduserid(), endUser.getFirstName(),
					soldDbPost.getSolddate(), soldDbPost.getAddeddate(), soldDbPost.getAddedby(),
					addedByUser.getFirstName(), soldDbPost.getRowstate(), itemRatingGivenValue);
			soldItemsBeanList.add(soldItem);			
		}
		mv.addObject("solditems", soldItemsBeanList);
    	
		List<Post> boughtPosts = itemService.findBoughtItemsTransactionHistory
    			(user.getUserId(), new Long(2000));
		List<BoughtItemsBean> boughtItemsBeanList = new ArrayList<BoughtItemsBean>();
		BoughtItemsBean boughtItem = null;
		for(Post boughtDbPost : boughtPosts) {			
			User addedByUser = userService.findByUserId(boughtDbPost.getAddedby());
			User endUser = userService.findByUserId(boughtDbPost.getEnduserid());
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(boughtDbPost.getItemcid());
			
			String itemRatingGivenValue = "";
			ItemRating itemRating = ratingService.findItemRatingByItemId(boughtDbPost.getItemid());
			if(itemRating == null || itemRating.getRating() == null) {
				itemRatingGivenValue = "";
			} else {
				itemRatingGivenValue = Double.valueOf(itemRating.getRating()).toString();
			}
									
			boughtItem = new BoughtItemsBean(boughtDbPost.getItemid(), boughtDbPost.getTitle(), boughtDbPost.getStatusid(), boughtDbPost.getDescription(),
					boughtDbPost.getItemworth(), boughtDbPost.getItemcid(),
					itemCategory.getItemcname(), boughtDbPost.getEnduserid(), endUser.getFirstName(),
					boughtDbPost.getSolddate(), boughtDbPost.getAddeddate(), boughtDbPost.getAddedby(),
					addedByUser.getFirstName(), boughtDbPost.getRowstate(), itemRatingGivenValue);
			boughtItemsBeanList.add(boughtItem);			
		}    	    	
    	mv.addObject("boughtitems", boughtItemsBeanList);
    			
    	mv.addObject("loggedinusername", username);
    	return mv;
    }
    
    @RequestMapping(value = {"/boughtitemsrate"}, method = RequestMethod.GET)
    public ModelAndView transactionHistorySubmit(HttpServletRequest request, 
    		Model model, Authentication authentication) {    	    	
    	ModelAndView mv = new ModelAndView("transactionhistory");
    	
    	String item = request.getParameter("rated");
    	String ratingGiven = item.split("-")[0];
    	String ratedItemId = item.split("-")[1];
    	
    	ItemRating itemRating = new ItemRating();
    	itemRating.setItemid(Long.parseLong(ratedItemId));
    	itemRating.setRating(Double.parseDouble(ratingGiven));
    	itemRating.setAddeddate(new Date());
    	
    	ratingService.insertRatedItem(itemRating);
    	
    	String username = securityService.findLoggedInUsername();
    	User user = userService.findByAccount(accountService.findByUsername(username));
    	
    	List<Post> soldPosts = itemService.findSoldItemsTransactionHistory
    			(user.getUserId(), new Long(2000));
		List<SoldItemsBean> soldItemsBeanList = new ArrayList<SoldItemsBean>();
		SoldItemsBean soldItem = null;
		for(Post soldDbPost : soldPosts) {			
			User addedByUser = userService.findByUserId(soldDbPost.getAddedby());
			User endUser = userService.findByUserId(soldDbPost.getEnduserid());
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(soldDbPost.getItemcid());
			
			String itemRatingGivenValue = "";
			ItemRating soldItemRatingFromDB = ratingService.findItemRatingByItemId(soldDbPost.getItemid());
			if(soldItemRatingFromDB == null || soldItemRatingFromDB.getRating() == null) {
				itemRatingGivenValue = "Pending..";
			} else {
				itemRatingGivenValue = Double.valueOf(soldItemRatingFromDB.getRating()).toString();
			}
									
			soldItem = new SoldItemsBean(soldDbPost.getItemid(), soldDbPost.getTitle(), soldDbPost.getStatusid(), soldDbPost.getDescription(),
					soldDbPost.getItemworth(), soldDbPost.getItemcid(),
					itemCategory.getItemcname(), soldDbPost.getEnduserid(), endUser.getFirstName(),
					soldDbPost.getSolddate(), soldDbPost.getAddeddate(), soldDbPost.getAddedby(),
					addedByUser.getFirstName(), soldDbPost.getRowstate(), itemRatingGivenValue);
			soldItemsBeanList.add(soldItem);			
		}
		mv.addObject("solditems", soldItemsBeanList);
    	
		List<Post> boughtPosts = itemService.findBoughtItemsTransactionHistory
    			(user.getUserId(), new Long(2000));
		List<BoughtItemsBean> boughtItemsBeanList = new ArrayList<BoughtItemsBean>();
		BoughtItemsBean boughtItem = null;
		for(Post boughtDbPost : boughtPosts) {			
			User addedByUser = userService.findByUserId(boughtDbPost.getAddedby());
			User endUser = userService.findByUserId(boughtDbPost.getEnduserid());
			ItemCategory itemCategory = itemCategoryService.findItemCategoryById(boughtDbPost.getItemcid());
			
			String itemRatingGivenValue = "";
			ItemRating itemRatingFromDb = ratingService.findItemRatingByItemId(boughtDbPost.getItemid());
			if(itemRatingFromDb == null || itemRatingFromDb.getRating() == null) {
				itemRatingGivenValue = "";
			} else {
				itemRatingGivenValue = Double.valueOf(itemRatingFromDb.getRating()).toString();
			}
									
			boughtItem = new BoughtItemsBean(boughtDbPost.getItemid(), boughtDbPost.getTitle(), boughtDbPost.getStatusid(), boughtDbPost.getDescription(),
					boughtDbPost.getItemworth(), boughtDbPost.getItemcid(),
					itemCategory.getItemcname(), boughtDbPost.getEnduserid(), endUser.getFirstName(),
					boughtDbPost.getSolddate(), boughtDbPost.getAddeddate(), boughtDbPost.getAddedby(),
					addedByUser.getFirstName(), boughtDbPost.getRowstate(), itemRatingGivenValue);
			boughtItemsBeanList.add(boughtItem);			
		}    	    	
    	mv.addObject("boughtitems", boughtItemsBeanList);
    			
    	mv.addObject("loggedinusername", username);
    	return mv;
    }
	
}
