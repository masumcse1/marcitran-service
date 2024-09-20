package com.ufril.medtran.event.listener;

import com.ufril.medtran.event.OnLoginSuccessEvent;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author moin
 */
@Component
public class LoginSuccessListener implements ApplicationListener<OnLoginSuccessEvent> {

    private final Logger logger = Logger.getLogger(this.getClass());

//    @Autowired
//    private PushNotification pushNotification;

    @Override
    public void onApplicationEvent(OnLoginSuccessEvent event) {
        logger.debug("Inside LoginSuccessListener. Before registerWithSNSForWeb Call");
//        pushNotification.registerWithSNSForWeb(event.getUser().getUsername());
    }
}
