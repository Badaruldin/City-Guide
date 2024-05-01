package com.badaruldin.check_application.SignUp;

public class DatabaseUserRegisterHelperClass {

    String fullname,username,email,dateofBirth,ccphoneno,gender,password;

    public DatabaseUserRegisterHelperClass(String fullname, String username, String email, String dateofBirth, String ccphoneno, String gender, String password) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.dateofBirth = dateofBirth;
        this.ccphoneno = ccphoneno;
        this.gender = gender;
        this.password = password;
    }

    public DatabaseUserRegisterHelperClass() {

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getCcphoneno() {
        return ccphoneno;
    }

    public void setCcphoneno(String ccphoneno) {
        this.ccphoneno = ccphoneno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
