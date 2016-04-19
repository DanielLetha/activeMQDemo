package com.daniel.study;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Brief :  ${用途}
 * @Author: liangfei/liangfei@simpletour.com
 * @Date :  2016/4/19 20:27
 * @Since ： ${VERSION}
 * @Remark: ${Remark}
 */
public class JmsSender {


    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.4.49:61616"
        );
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session =  connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("myQueue");

        MessageProducer messageProducer =  session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        sendMsg(session,messageProducer);
        session.commit();
        connection.close();

    }
    public static void sendMsg(Session session,MessageProducer messageProducer) throws JMSException{
        TextMessage textMessage = session.createTextMessage("Hello,World");
        messageProducer.send(textMessage);
        System.out.print("");
    }

}
