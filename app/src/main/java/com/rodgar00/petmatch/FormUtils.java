package com.rodgar00.petmatch;


import com.google.android.material.textfield.TextInputLayout;
import org.mindrot.jbcrypt.BCrypt;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FormUtils {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

    public boolean isTILEmpty(TextInputLayout textInputLayout) {
        return String.valueOf(textInputLayout.getEditText().getText()).isEmpty();
    }

    public String getTILText(TextInputLayout textInputLayout) {
        return String.valueOf(textInputLayout.getEditText().getText());
    }

    public String generateHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }


    public boolean arePasswordsTheSame(TextInputLayout pass1, TextInputLayout pass2) {
        String p1 = getTILText(pass1);
        String p2 = getTILText(pass2);
        return !p1.isEmpty() && p1.equals(p2);
    }

    public boolean isEmailCorrect(TextInputLayout emailTIL) {
        String email = getTILText(emailTIL);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    public boolean checkUser(String inputUser, String savedUser) {
        return inputUser.equals(savedUser);
    }

}
