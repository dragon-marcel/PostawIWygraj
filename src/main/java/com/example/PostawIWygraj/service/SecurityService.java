package com.example.PostawIWygraj.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
    String getNameLoogedUser();
    Long getIdLoogerUser();
}
