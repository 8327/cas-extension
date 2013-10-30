package com.fingolfintek.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fingolfintek.dao.UserAuthenticationInfoRepository;
import com.fingolfintek.model.UserAuthenticationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Map;

@Component("userChangeListener")
public class UserChangeListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private @Autowired UserAuthenticationInfoRepository repository;

    @Override
    public void onMessage(Message message) {
        Assert.isInstanceOf(TextMessage.class, message, "Only text messages are allowed");

        try {
            TextMessage textMessage = (TextMessage) message;
            String messageText = textMessage.getText();
            logger.info("Received message {} with correlation Id {}", messageText, textMessage.getJMSCorrelationID());

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> updateInfo = mapper.readValue(messageText, Map.class);

            String username = updateInfo.get("username");
            UserAuthenticationInfo newInfo = new UserAuthenticationInfo(username, updateInfo.get("password"));

            UserAuthenticationInfo info = repository.findByUsername(username);
            if (info != null) {
                BeanUtils.copyProperties(newInfo, info, new String[]{"id"});
            } else {
                info = newInfo;
            }

            repository.save(info);
        } catch (Exception e) {
            logger.error("Encountered unexpected exception", e);
            throw new RuntimeException(e);
        }
    }
}
