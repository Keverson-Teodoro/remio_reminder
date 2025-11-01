package projetos.kev.remio.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {

    public void setCookie (HttpServletResponse response, String key, String value) throws UnsupportedEncodingException {

//        DecodedJWT decodedJWT = JWT.decode(token);
        Cookie cookie = new Cookie(key, value);
//        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(14400);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key){


        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> key.equals(cookie.getName()))
                        .findAny()
                ).map(e -> e.getValue()).orElse(null);

    }
}
