package com.kadan.demo.controller;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/")
public class ClientController {

    private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String defaultReturn(){
        return " here comes the client response >>>> :::::: ";
    }


    }
