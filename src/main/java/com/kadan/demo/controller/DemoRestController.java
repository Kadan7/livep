package com.kadan.demo.controller;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/")
public class DemoRestController {

    private final static Logger logger = LoggerFactory.getLogger(DemoRestController.class);

    @Autowired
    RestTemplate restTemplate;

//    @Value("${client}")
//    String client;

    @RequestMapping(value = "/liveProbe", method = RequestMethod.GET)
    public ResponseEntity liveProbe() {
        List<String> timeList = new ArrayList<String>();
        timeList.add("IM222OK1");
        timeList.add("IMOK");
        timeList.add("IM222OK");
        timeList.add("IM222OK");
        timeList.add("IM222OK4");
        timeList.add("IM222OK");
        timeList.add("IM222OK2");
        timeList.add("5IMOK");
        timeList.add("IMOK");
        timeList.add("IMOK");
        timeList.add("IMOK7");
        timeList.add("IMOK");
        Random r = new Random();
        int a = r.nextInt(12);
        String header = timeList.get(a);

        logger.info("this is the header >>>> " + header);
        if(StringUtils.containsIgnoreCase(header,"IMOK"))
            return ResponseEntity.status(HttpStatus.OK).body("check the header pleases ... ...");
        else
            return ResponseEntity.status(500).body("check the header pleases ... ...");
    }

//    @RequestMapping(value = "/circuit1", method = RequestMethod.GET)
//    @HystrixCommand(fallbackMethod = "defaultReturn")
//    public String getBookmarks() {
//        logger.info("visting circuit1 ...");
//        return restTemplate.getForObject(client,String.class);
//        //return ResponseEntity.status(HttpStatus.OK).body("you are visiting circuit service 1 ...");
//
//    }

    public String defaultReturn(){
        return "somethings' wrong in the downstream host ...";
    }


    @RequestMapping(value = "/timeoutTest", method = RequestMethod.GET)
    @HystrixCommand(commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "520")},fallbackMethod = "timeout")
    public String testTimeout() {

        //return restTemplate.getForObject(client,String.class);
        List<Long> timeList = new ArrayList<Long>();
        timeList.add(Long.valueOf(500));
        timeList.add(Long.valueOf(100));
        timeList.add(Long.valueOf(200));
        timeList.add(Long.valueOf(600));
        timeList.add(Long.valueOf(700));
        timeList.add(Long.valueOf(550));
        timeList.add(Long.valueOf(300));
        Random r = new Random();
        int a = r.nextInt(6);
        logger.info("watch out ...i will sleep for  ..." + timeList.get(a).longValue());
        try {
            Thread.sleep(timeList.get(a).longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("you are visiting circuit service 1 ...").toString();

    }

    public String timeout(){
        return " time out .... ...";
    }




}
