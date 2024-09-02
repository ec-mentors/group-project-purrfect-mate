package purrfectmate.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDTO {

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
