package ai_tech.service.mail;

import ai_tech.config.jwt.JwtTokenProvider;
import ai_tech.service.mail.dto.RegContentDTO;
import ai_tech.service.user.dto.BaseUserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final String SUPPORT_MAIL = "for_testing_mail@internet.ru";
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String CREATE_PASSWORD_TEMPLATE = "create_password";
    private static final String RESET_PASSWORD_TEMPLATE = "reset_password";

    @Override
    public void sendCreatePasswordEmail(BaseUserDTO createUserDTO, String oneTimePassword) {
        String token = jwtTokenProvider.generateOneTimeToken(createUserDTO.getUsername(), oneTimePassword);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String to = createUserDTO.getEmail();
        collectMimeMessage(mimeMessage, to, token, CREATE_PASSWORD_TEMPLATE);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendResetPasswordEmail(String email, String username, String password) {
        String token = jwtTokenProvider.generateResetPasswordToken(username, password);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        collectMimeMessage(mimeMessage, email, token, RESET_PASSWORD_TEMPLATE);
        mailSender.send(mimeMessage);
    }

    private void collectMimeMessage(MimeMessage mimeMessage, String to, String token, String template) {
        LOGGER.info("Start collecting MimeMessage. to: {}, token: {}", to, token);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, String.valueOf(StandardCharsets.UTF_8));
            RegContentDTO resetPasswordMailContentDTO = new RegContentDTO();
            resetPasswordMailContentDTO.setToken(token);
            String content = mailContentBuilder
                    .build(resetPasswordMailContentDTO, template);
            String subject = "ИЗМЕНИТь ПАРОЛь";
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
