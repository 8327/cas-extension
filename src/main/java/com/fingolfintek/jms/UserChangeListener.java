package com.fingolfintek.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

@Component("userChangeListener")
public class UserChangeListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        logger.info(message.toString());
    }
}
