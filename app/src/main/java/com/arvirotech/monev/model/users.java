package com.arvirotech.monev.model;

public class users {

    private String id;
    private String nama;
    private String email;
    private String phone;
    private String password;
    private String roles;

    public users() {
    }

    public users(String nama, String email, String phone, String password, String roles, String id) {
        this.nama = nama;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roles = roles;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
