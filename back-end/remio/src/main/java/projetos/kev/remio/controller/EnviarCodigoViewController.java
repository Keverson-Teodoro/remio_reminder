package projetos.kev.remio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projetos.kev.remio.DTO.CodeVerifyDTO;
import projetos.kev.remio.service.VerifyCodeService;

@Controller
@RequiredArgsConstructor
public class EnviarCodigoViewController {

    private final VerifyCodeService verifyCodeService;

    @GetMapping("/verificarCodigo")
    public String sendCode(){
        return "enviar_codigo_page";
    }

    @RequestMapping(value = "/verificarCodigo", method = RequestMethod.POST)
    public String verifyCode (CodeVerifyDTO code){

        ResponseEntity<String> response = verifyCodeService.verifyCode(code);
        if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) return "redirect:/login-view";
        String uriWithoutCode = "redirect:/newPassword-view/code";
        return uriWithoutCode.replace("code", code.code().toString());

    }
}