package com.jms;



import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteConsumidorFila {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
	    Connection createConnection = factory.createConnection();
	    createConnection.start();
	    
	    
	    Session session = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination file = (Destination)context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(file);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				
				
				
				System.out.println(message);
				
			}
		});
	    
		//Message receive = consumer.receive();
		
		//System.out.println("Recebendo message: " + receive);
	    
	    new Scanner(System.in).nextLine();
	    session.close();
	    createConnection.close();
	    context.close();
	}
}
