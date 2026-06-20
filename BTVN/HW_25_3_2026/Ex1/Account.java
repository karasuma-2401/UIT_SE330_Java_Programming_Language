package Ex1;
public class Account {
    private String email;
    private String password;

    public Account (String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String email() {return email;}
    public String password() {return password;}

}