package com.db.studentbarter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.db.studentbarter.model.Post;

public interface ItemRepository extends JpaRepository<Post, Long> {
	
    @Query("select p from Post p where p.addedby NOT IN (?1) and p.statusid = ?2 and p.rowstate = ?3")
    List<Post> findOtherUserPosts(Long loggedInUserId, Long availableStatusId, String rowState);
    
    @Query("select p from Post p where p.addedby = ?1 and p.statusid = ?2 and p.rowstate = ?3")
    List<Post> findLoggedInUserPosts(Long loggedInUserId, Long availableStatusId, String rowState);
    
    @Query("select p from Post p where p.enduserid = ?1 and p.statusid = ?2 and p.rowstate = ?3")    
    List<Post> findBoughtItemsTransactionHistory(Long loggedInUserId, Long soldStatusId, String rowState);
    
    @Query("select p from Post p where p.addedby = ?1 and p.statusid = ?2 and p.rowstate = ?3")
    List<Post> findSoldItemsTransactionHistory(Long loggedInUserId, Long soldStatusId, String rowState);
    
    @Transactional
    @Modifying
    @Query("update Post p set p.title = ?1, p.description = ?2, p.itemworth = ?3, p.itemcid = ?4, p.rowstate = ?5 where p.itemid = ?6")
    void updateUserPost(String title, String description, Double itemworth, Long itemcid, String rowstate, Long itemid);

}
