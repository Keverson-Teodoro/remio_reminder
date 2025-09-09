package projetos.kev.remio.configuration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import projetos.kev.remio.repository.UserRepository;
import projetos.kev.remio.service.TokenService;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);
        if (token != null) {
            var login = tokenService.validationToken(token);
            if (login != null && !login.isBlank()) {
                UserDetails user = userRepository.findByUsername(login)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));

                var authentication = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);


    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        var token = this.recoverToken(request);
//        if (token != null) {
//            var login = tokenService.validationToken(token);
//            UserDetails user = userRepository.findByUsername(login)
//                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
//
//            var authentication = new UsernamePasswordAuthenticationToken(
//                        user, null, user.getAuthorities()
//                );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        }
//        filterChain.doFilter(request, response);
//
//
//    }




    private String recoverToken (HttpServletRequest request){
        var headerAuth = request.getHeader("Authorization");
        if(headerAuth == null){
            return null;
        }
        return headerAuth.replace("Bearer", "").trim();
    }
}
