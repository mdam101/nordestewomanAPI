package com.tienda.nordeste.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RedirectController {

    @GetMapping("/")
    @ApiOperation("Redirecciona a la página de Swagger")
    public void redirectToSwagger(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui/");
    }

}
