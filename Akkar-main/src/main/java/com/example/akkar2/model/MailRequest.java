package com.example.akkar2.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {

    private String name;
    private String to;
    private String subject;
    private String body;
    private String buttonTitle;
    private String buttonHref;
}
