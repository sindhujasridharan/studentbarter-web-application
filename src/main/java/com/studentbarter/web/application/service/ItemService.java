package com.studentbarter.web.application.service;

import java.util.List;

import com.studentbarter.web.application.model.*;

public interface ItemService {
		
    public List<Post> findOtherUserPosts(Long loggedInUserId, Long availableStatusId);
    
    public List<Post> findLoggedInUserPosts(Long loggedInUserId, Long availableStatusId);
        
    public List<Post> findBoughtItemsTransactionHistory(Long loggedInUserId, Long soldStatusId);
    
    public List<Post> findSoldItemsTransactionHistory(Long loggedInUserId, Long soldStatusId);
        
    public void insertNewPost(Post post);
        
    public Post findPostByItemId(Long itemid);
    
    public void updateUserPost(Post modifiedPost);
    

}
