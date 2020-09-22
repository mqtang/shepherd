package guru.bootstrap.shepherd.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;

/**
 * 处理redis-session失效
 *
 * @author tangcheng
 */
@Component
public class RedisSessionStateListener implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(RedisSessionStateListener.class);

    private ApplicationContext applicationContext;

    @Bean
    public SessionCreatedListener sessionCreatedListener() {
        return new SessionCreatedListener();
    }

    @Bean
    public SessionDestroyedListener sessionDestroyedListener() {
        return new SessionDestroyedListener();
    }

    private final class SessionCreatedListener implements ApplicationListener<SessionCreatedEvent> {
        @Override
        public void onApplicationEvent(SessionCreatedEvent event) {
            String sessionId = event.getSessionId();
            if (logger.isInfoEnabled()) {
                logger.info("session {} is created", sessionId);
            }
        }
    }

    private final class SessionDestroyedListener implements ApplicationListener<SessionDestroyedEvent> {
        @Override
        public void onApplicationEvent(SessionDestroyedEvent event) {
            String sessionId = event.getSessionId();
            if (event instanceof SessionExpiredEvent) {
                if (logger.isInfoEnabled()) {
                    logger.info("session {} is expired", sessionId);
                }
            }
            if (event instanceof SessionDeletedEvent) {
                if (logger.isInfoEnabled()) {
                    logger.info("session {} is deleted", sessionId);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
// 2020/9/22 16:53
