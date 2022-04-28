package com.tienda.nordeste.controllers;

import com.tienda.nordeste.services.RopaService;
import com.tienda.nordeste.services.TallaRopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TallaRopaController {
    @Autowired
    private TallaRopaService tallaRopaService;

    @Autowired
    private RopaService ropaService;

    //Buscar por idropa y talla la cantidad


    //Añadir talla y cantidad a tallaropa


}
