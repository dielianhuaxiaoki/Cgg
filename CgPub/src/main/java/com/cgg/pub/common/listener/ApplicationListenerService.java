package com.cgg.pub.common.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component("applicationListenerService")
public class ApplicationListenerService implements ApplicationListener<ApplicationEvent> {

	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		//如果容器关闭时，触发
		String eventName = event.getClass().getSimpleName();
        if(event instanceof ContextClosedEvent){
            ContextClosedEvent cce=(ContextClosedEvent) event;
            System.out.println("#####################-----------------------");
            System.out.println("容器关闭--"+eventName);
            System.out.println(cce);
            System.out.println("#####################-----------------------");
        }
        //容器刷新时候触发
        else if(event instanceof ContextRefreshedEvent){
            ContextRefreshedEvent cre=(ContextRefreshedEvent) event;
            System.out.println("#####################-----------------------");
            System.out.println("容器刷新--"+eventName);
            System.out.println(cre);
            System.out.println("#####################-----------------------");
        }
        //容器启动的时候触发
        else if(event instanceof ContextStartedEvent){
            ContextStartedEvent cse=(ContextStartedEvent) event;
            System.out.println("#####################-----------------------");
            System.out.println("容器启动--"+eventName);
            System.out.println(cse);
            System.out.println("#####################-----------------------");
        }
        //容器停止时候触发
        else if(event instanceof ContextStoppedEvent){
            ContextStoppedEvent cse=(ContextStoppedEvent) event;
            System.out.println("#####################-----------------------");
            System.out.println("容器停止--"+eventName);
            System.out.println(cse);
            System.out.println("#####################-----------------------");
        }
        else{
        	System.out.println("#####################-----------------------");
            System.out.println("有其它事件发生--"+eventName);
            if(event instanceof TestEmailEvent){
            	TestEmailEvent testEmailEvent = (TestEmailEvent) event;
            	testEmailEvent.print();
            }
            System.out.println("#####################-----------------------");
        }
	}

}
