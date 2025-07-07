package PAI.VOs;

public class Email {
    private final String _email;

    public Email(String email) throws IllegalArgumentException {
        if (isEmailInvalid(email))
            throw new IllegalArgumentException("This email is not valid.");
        else
            _email = email;

    }

    private boolean isEmailInvalid (String email) {

        if  (email ==  null || email.isBlank() || !email.matches("^[a-zA-Z0-9][a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-][a-zA-z0-9]+\\.[a-zA-Z]{2,}+(\\.[a-zA-Z]{2,})?$"))
            return true;
        else
            return false;
    }

    public String getEmail() {
        return _email;
    }
}
