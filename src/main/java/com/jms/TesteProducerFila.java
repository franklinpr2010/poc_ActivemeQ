package com.jms;



import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteProducerFila {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
	    Connection createConnection = factory.createConnection();
	    createConnection.start();
	    Session session = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    //Caso quiser armazenar numa fila
	    //Destination fila = (Destination)context.lookup("financeiro");
		  //Caso quiser armazenar num tópico
	    Destination fila = (Destination)context.lookup("financeiro");
	    MessageProducer producer = session.createProducer(fila);
	    Message message = session.createTextMessage("Teste");
		producer.send(message);
		 for (int i = 0; i < 1000; i++) { producer.send(message); }
		//Message receive = consumer.receive();
		//System.out.println("Recebendo message: " + receive);
	    //new Scanner(System.in).nextLine();
	    session.close();
	    createConnection.close();
	    context.close();
	}
}
