package ai_tech.service.mail;

import ai_tech.service.user.dto.BaseUserDTO;

public interface EmailSenderService {
    void sendCreatePasswordEmail(BaseUserDTO createUserDTO, String token);

    void sendResetPasswordEmail(String email, String username, String password);
}
