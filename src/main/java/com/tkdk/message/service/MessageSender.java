package com.tkdk.message.service;

public class MessageSender implements Runnable {

	private String message;
	private String name;

	public MessageSender(String message, String name) {
		this.name = name;
		this.message = message;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() +": " +this.name + " " + this.message + " gönderildi");
		
		processmessage();
//		System.out.println(Thread.currentThread().getName() + " (End)");
	}

	private void processmessage() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

}
