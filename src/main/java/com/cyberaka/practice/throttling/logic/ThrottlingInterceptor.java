package com.cyberaka.practice.throttling.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Throttler interceptor to intercept any request that comes in and invoke the thorttler logic.
 */
@Component
public class ThrottlingInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Value("${throttling.start}")
    private int throttlingStart;

    @Value("${throttling.delay}")
    private int throttlingDelay;

    @Autowired
    private ThrottlerSchedule throttlerSchedule;

    private Throttler throttler;

    @PostConstruct
    private void bootup() {
        throttler = new Throttler(throttlingStart, throttlingDelay);
        throttlerSchedule.setThrottler(throttler);
    }

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Lets cause a delay if the delay is already identified at the level of the throttler.
        if (throttler.getDelay() > 0) {
            Thread.sleep(throttler.getDelay());
        }
        throttler.connectionReceived();
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {
        throttler.connectionFinished();
    }
}
