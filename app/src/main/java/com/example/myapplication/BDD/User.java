package com.example.myapplication.BDD;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;

    public User(){}

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID : "
        + Integer.toString(getId()) + "\n"
        + "Login : " + getLogin() + "\n"
        + "Password : " + getPassword() + "\n"
        + "Email : " + getEmail());
        return sb.toString();
    }
}
