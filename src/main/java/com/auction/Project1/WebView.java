package com.auction.Project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebView {
    @Autowired
    private com.auction.Project1.WebviewService webviewService;
    @GetMapping("/")
    public String loadLogin(){
        return "redirect:/Login";
    }
    @GetMapping("/Login")
    public String login(){
        return webviewService.loadLoginPage();
    }
}
