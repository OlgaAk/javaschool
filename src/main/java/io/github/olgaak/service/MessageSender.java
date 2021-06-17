package io.github.olgaak.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.MessageCreator;

@Component
public class MessageSender {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    private static final Logger logger
            = LoggerFactory.getLogger(MessageSender.class);

    public void sendMessage(){
        MessageCreator messageCreator = session -> session.createTextMessage("update");
        jmsTemplate.send(messageCreator);
        logger.info("Jms message sent");
    }
}
