package com.tkdk.message.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tkdk.message.entity.Customer;
import com.tkdk.message.entity.Message;
import com.tkdk.message.repo.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;
	private final CustomerService customerService;

	// Mesajı kaydetme
	public void saveMessage(Message message) {
		messageRepository.save(message);
	}

	// Mesajı gönderme
	public void sendMessage(Message message) {
		
		new Thread(new Runnable() {
						
			@Override
			public void run() {

				int nextId = message.getLastSentId();
				
				while (!(message.isStopped()) && !(nextId == 301)) {
					Optional<Customer> customer = customerService.getNextcustomer(nextId);
					if(customer.isPresent()) {
						Customer customerToSend = customer.get();
						System.out.println(Thread.currentThread().getName() + ": " + customerToSend.getName() + " "
								+ message.getContent() + " gönderildi");						
						message.setLastSentId(nextId);
						message.addCustomer(customerToSend);
						nextId++;
	
					} else {
						nextId++;
					}
				}
				message.setCompleted(true);
				messageRepository.save(message);
			}
		}).start();
	}

	// Tamamlanmamış mesajları durdurma
	public void stopRequest() {
		List<Message> messages = messageRepository.getMessagesNotCompleted();
		for(Message message : messages) {
			message.setStopped(true);
			messageRepository.save(message);
		}
	}

	// Tamamlanmamış ve durdurulmuş mesajları devam ettirme
	public void resumeRequest() {
		List<Message> messages = messageRepository.getMessagesNotCompleted();
		for(Message message : messages) {
			message.setStopped(false);
			messageRepository.save(message);
			sendMessage(message);
		}
	}

}

/*
 * long customerNumber = customerRepository.count();
 * System.out.println("Customer number : " + customerNumber);
 * 
 * ExecutorService executor = Executors.newFixedThreadPool(10);
 * 
 * for (int i = 1; i <= customerNumber; i++) { if (!message.isStopped() &&
 * !message.isCompleted()) {
 * 
 * @SuppressWarnings("deprecation") Customer customer =
 * customerRepository.getById(i); MessageSender sender = new
 * MessageSender(message.getContent(), customer.getName());
 * executor.execute(sender); message.addCustomer(customer); } else { break; } }
 * 
 * message.setCompleted(true); messageRepository.save(message);
 * 
 * executor.shutdown(); while (!executor.isTerminated()) { }
 * 
 * System.out.println("Finished all threads");
 * 
 * System.out.println(message.isCompleted());
 * System.out.println(message.isStopped()); +
 */
