package com.db.studentbarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import com.db.studentbarter.model.Post;
import com.db.studentbarter.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    JpaTransactionManager transactionManager;
    
    @Autowired
    LocalContainerEntityManagerFactoryBean entityManagerFactory;
    
    @Override
    public List<Post> findOtherUserPosts(Long loggedInUserId, Long availableStatusId) {
    	return itemRepository.findOtherUserPosts(loggedInUserId, availableStatusId, "a");
    }
    
    @Override
    public List<Post> findLoggedInUserPosts(Long loggedInUserId, Long availableStatusId) {
    	return itemRepository.findLoggedInUserPosts(loggedInUserId, availableStatusId, "a");
    }
    
    @Override
    public List<Post> findBoughtItemsTransactionHistory(Long loggedInUserId, Long soldStatusId) {
    	return itemRepository.findBoughtItemsTransactionHistory(loggedInUserId, soldStatusId, "a");
    }
    
    @Override
    public List<Post> findSoldItemsTransactionHistory(Long loggedInUserId, Long soldStatusId) {
    	return itemRepository.findSoldItemsTransactionHistory(loggedInUserId, soldStatusId, "a");
    } 
    
    @Override
    public void insertNewPost(Post post) {
    	itemRepository.save(post);
    }
    
    @Override
    public Post findPostByItemId(Long itemid) {
    	return itemRepository.findOne(itemid);
    }

	@Override
	public void updateUserPost(Post modifiedPost) {		
		itemRepository.updateUserPost(modifiedPost.getTitle(), modifiedPost.getDescription(),
				modifiedPost.getItemworth(), modifiedPost.getItemcid(), modifiedPost.getRowstate(), modifiedPost.getItemid());		
	}

}
