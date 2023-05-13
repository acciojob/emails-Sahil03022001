package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(!oldPassword.equals(password) || newPassword.length() < 8){
            return;
        }

        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecialChar = false;
        for(char ch : newPassword.toCharArray()) {
            if(hasUpper && hasLower && hasDigit && hasSpecialChar) break;
            if(Character.isLetter(ch)) {
                if(Character.isUpperCase(ch)) hasUpper = true;
                else hasLower = true;
            } else if(Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }

        if(hasUpper && hasLower && hasDigit && hasSpecialChar) {
            password = newPassword;
        }
    }
}
