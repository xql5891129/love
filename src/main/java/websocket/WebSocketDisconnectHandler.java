package websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import pojo.User;
import service.UserService;

import java.util.Date;
import java.util.Map;

/**
 * @author XQL
 * @version 1.0.0
 * @ClassName WebSocketDisconnectHandler.java
 * @Description TODO
 * @createTime 2022年05月14日 17:13:00
 */
public class WebSocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService;

    private final static String SUBSCRIBE_LOGOUT_URI = "/topic/logout";

    /**
     * 当sessionDisconnectEvent发布时，此方法将被调用，从事件中的message取出websocket sessionAttributes
     * 从中取出离开的User，通知其他用户
     * @param sessionDisconnectEvent
     */
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        //Map<String, User> activeSessions = participantRepository.getActiveSessions();
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        User disconnectSession = (User) sessionAttributes.get("user");
        messagingTemplate.convertAndSend(SUBSCRIBE_LOGOUT_URI, disconnectSession);
    }
}
