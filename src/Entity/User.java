package Entity;

public class User {
    private int UID;
    private String token;
    private String username;
    private String password;
    private String studentAccount;
    private String studentPassword;

    public User(String username, String password,String studentAccount,String studentPassword){
        this.username = username;
        this.password = password;
        this.studentAccount=studentAccount;
        this.studentPassword=studentPassword;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUID(){
        return UID;
    }

    public void logout(){
        this.token = null;
    }
}
