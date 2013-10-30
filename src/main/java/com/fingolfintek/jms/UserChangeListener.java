package com.fingolfintek.jms;

import com.fasterxml.jackson.core.JsonFactory;
import com.fingolfintek.dao.UserAuthenticationInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component("userChangeListener")
public class UserChangeListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private @Autowired UserAuthenticationInfoRepository repository;

    @Override
    public void onMessage(Message message) {
        Assert.isInstanceOf(TextMessage.class, message, "Only text messages are allowed");

        try {
            TextMessage textMessage = (TextMessage) message;
            logger.info(textMessage.getText());

        } catch (JMSException e) {
            logger.error("Encountered unexpected JMS exception");
            throw new RuntimeException(e);
        }


    }
}
