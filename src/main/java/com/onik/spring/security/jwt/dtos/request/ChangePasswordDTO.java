package ai_tech.service.user.account.dto;

import ai_tech.utils.validators.password.ValidatePassword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

    @ValidatePassword
    private String oldPassword;

    @ValidatePassword
    private String newPassword1;

    @ValidatePassword
    private String newPassword2;
}
