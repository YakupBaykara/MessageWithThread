package com.tkdk.message.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tkdk.message.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

	@Query(value = "SELECT m FROM message m WHERE m.completed = false",
		   nativeQuery = true)
	List<Message> getMessagesNotCompleted();
}
