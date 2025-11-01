package projetos.kev.remio.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageViewController {

    @GetMapping("/")
    public String homePageController(HttpServletRequest request, HttpServletResponse response){
        return "home_page";
    }
}
