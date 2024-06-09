package com.auction.Project1;

import org.springframework.stereotype.Service;

@Service
public class WebviewService {
    public String loadLoginPage(){
        return "forward:/Login.html";
    }
    public String forwardHomepage(){
        return "forward:/Homepage.html";
    }
}