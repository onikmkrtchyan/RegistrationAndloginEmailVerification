package com.onik.spring.security.jwt.service.mail;

import com.onik.spring.security.jwt.dtos.request.SignupEmailRequest;
import com.onik.spring.security.jwt.security.jwt.JwtUtils;
import com.onik.spring.security.jwt.service.mail.dto.RegContentDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private static final Logger LOGGER = LogManager.getLogger(EmailSenderServiceImpl.class);

    @Value("${spring.mail.username}")
    private String SUPPORT_MAIL;
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    private final JwtUtils jwtUtils;

    private static final String CREATE_PASSWORD_TEMPLATE = "create_password";

    @Override
    public void sendCreatePasswordEmail(SignupEmailRequest signupEmailRequest, String oneTimePassword) {
        String token = jwtUtils.generateOneTimeToken(signupEmailRequest.getUsername(), oneTimePassword);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String to = signupEmailRequest.getEmail();
        collectMimeMessage(mimeMessage, to, token);
        mailSender.send(mimeMessage);
    }

    private void collectMimeMessage(MimeMessage mimeMessage, String to, String token) {
        LOGGER.info("Start collecting MimeMessage. to: {}, token: {}", to, token);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, String.valueOf(StandardCharsets.UTF_8));
            RegContentDTO resetPasswordMailContentDTO = new RegContentDTO();
            resetPasswordMailContentDTO.setToken(token);
            String content = mailContentBuilder
                    .build(resetPasswordMailContentDTO, EmailSenderServiceImpl.CREATE_PASSWORD_TEMPLATE);
            String subject = "CREATE PASSWORD";
            helper.setFrom(SUPPORT_MAIL);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException ex) {
            LOGGER.error("Can not send email", ex);
            throw new SecurityException("Can not send email");
        }
    }
}
