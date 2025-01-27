package com.example.BLOG_APP.payloads;

public class JwtAuthResponse {
    private String token;

    public JwtAuthResponse(String token)
    {
        this.token=token;
    }
    //Getters and Setters
    public void setToken(String token)
    {
        this.token=token;
    }
    public String getToken()
    {
        return this.token;
    }

}
