package ai_tech.utils.validators.password;

import ai_tech.exception.PasswordMismatchException;
import ai_tech.exception.PermissionDeniedException;
import ai_tech.service.user.account.dto.ChangePasswordDTO;
import ai_tech.service.user.dto.CreatePasswordUserDTO;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import static ai_tech.utils.constant.SecurityConstants.TEMP_PASSWORD_LENGTH;

public class PasswordUtils {

    public static String generate() {
        return RandomStringUtils.randomAlphanumeric(TEMP_PASSWORD_LENGTH);
    }

    public static String getDisfiguredPassword(String password) {
        if (password.length() < 10) throw new PermissionDeniedException();
        byte[] passwordArr = password.substring(0, 10).getBytes(StandardCharsets.UTF_8);
        passwordArr = Base64.getEncoder().encode(passwordArr);
        Arrays.sort(passwordArr);
        return Base64.getEncoder().encodeToString(passwordArr);
    }

    public static void comparePasswords(CreatePasswordUserDTO createPasswordUserDTO) {
        if (createPasswordUserDTO.getPassword().equals(createPasswordUserDTO.getRepeatedPassword()))
            return;
        throw new PasswordMismatchException("Password mismatch");
    }

    public static void comparePasswords(ChangePasswordDTO changePasswordDTO) {
        if (changePasswordDTO.getNewPassword1().equals(changePasswordDTO.getNewPassword2())) {
            return;
        }
        throw new PasswordMismatchException("Password mismatch");
    }
}
