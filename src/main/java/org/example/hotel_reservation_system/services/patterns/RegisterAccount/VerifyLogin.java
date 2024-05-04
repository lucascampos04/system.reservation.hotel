package org.example.hotel_reservation_system.services.patterns.RegisterAccount;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class VerifyLogin implements IVerifyLogin {

    public String verifyLogin(String login) {
        if (isEmail(login)) {
            return "EMAIL";
        } else {
            return "USERNAME";
        }
    }

    private boolean isEmail(String text) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(text).matches();
    }
}
