package com.example.akkar2;

import com.example.akkar2.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Akkar2Application {
    public static void main(String[] args) {
        SpringApplication.run(Akkar2Application.class, args);
    }


}
