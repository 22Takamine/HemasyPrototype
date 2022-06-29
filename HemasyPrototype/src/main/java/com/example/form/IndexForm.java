package com.example.form;


import javax.validation.constraints.NotEmpty;

public class IndexForm {
    
	@NotEmpty
	private String mail;
    
    @NotEmpty
    private String password;

    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}