package org.example.multimodule.configurations;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


public class TestListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if  (event instanceof ApplicationContextInitializedEvent) {

        }

        System.out.println("Event ::: " + event);
    }
}
