package com.tkdk.message.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Component
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
/*	private static boolean stopRequested;

	private static synchronized void requestStop() {
		stopRequested = true;
	}

	private static synchronized boolean stopRequested() {
		return stopRequested;
	} */

	@Id
    @SequenceGenerator(
            name = "message_id_sequence",
            sequenceName = "message_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_id_sequence")
    private Integer messageId;
    
    private String content;
    
    @ManyToMany
    @JoinTable(name = "message_customer", 
    		   joinColumns = @JoinColumn(name = "messageId"),
    		   inverseJoinColumns =@JoinColumn(name = "customerId"))
    private List<Customer> customers = new ArrayList<>();
    
    private int lastSentId = 1;
    private volatile boolean stopped;
    private boolean completed;
       
    public void addCustomer(Customer customer) {
    	this.customers.add(customer);
    }
}


