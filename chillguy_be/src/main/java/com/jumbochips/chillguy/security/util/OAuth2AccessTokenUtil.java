package com.jumbochips.chillguy.security.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AccessTokenUtil {

    public String getGoogleAccessToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if("googleAccessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        throw new RuntimeException("Google OAuth2 Access Token이 없습니다. 다시 로그인해주세요.");
    }
}
