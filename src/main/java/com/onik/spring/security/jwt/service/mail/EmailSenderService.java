package com.onik.spring.security.jwt.service.mail;
import com.onik.spring.security.jwt.dtos.request.SignupEmailRequest;

public interface EmailSenderService {
    void sendCreatePasswordEmail(SignupEmailRequest signupEmailRequest, String oneTimePassword);
}
