package model;

public class User {
    private Integer uId;
    private String username;
    private String password;
    private String email;
    private String address;

    public Integer getUID() {
        return uId;
    }

    public void setUID(Integer id) {
        this.uId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + uId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public User(Integer id, String username, String password, String email, String address) {
        this.uId = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}
