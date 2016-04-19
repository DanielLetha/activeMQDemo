package com.daniel.study;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Brief :  ${用途}
 * @Author: liangfei/liangfei@simpletour.com
 * @Date :  2016/4/19 20:37
 * @Since ： ${VERSION}
 * @Remark: ${Remark}
 */
public class JmsReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.4.49:61616"
        );
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("myQueue");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        while(true){
           TextMessage textMessage =  (TextMessage)messageConsumer.receive(100000);
            if(null != textMessage){
                System.out.println("收到信息"+textMessage.getText());
            }else
                break;
        }
        session.close();
        connection.close();

    }
}
