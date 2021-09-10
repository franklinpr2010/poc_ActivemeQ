package com.jms;



import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteConsumidorTopico {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
	    Connection connection = factory.createConnection();
	    connection.setClientID("estoque");
	    connection.start();
	    
	    
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Topic topico = (Topic)context.lookup("loja");
		TopicSubscriber topic = session.createDurableSubscriber(topico, "assinatura");
		topic.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				
				
				
				System.out.println(message);
				
			}
		});
	    
		//Message receive = consumer.receive();
		
		//System.out.println("Recebendo message: " + receive);
	    
	    new Scanner(System.in).nextLine();
	    session.close();
	    connection.close();
	    context.close();
	}
}
