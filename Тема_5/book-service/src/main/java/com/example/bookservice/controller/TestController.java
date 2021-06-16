package com.example.bookservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOG = Logger.getLogger(TestController.class.getName());

   @Autowired
   private DiscoveryClient discoveryClient;

   @Autowired
   private Environment env;

   @ResponseBody
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home() {
       String home = "User-Service running at port: " + env.getProperty("local.server.port");
       LOG.log(Level.INFO, home);
       return "<a href='showAllServices'>Show All Services</a>";
   }

   @ResponseBody
   @RequestMapping(value = "/showAllServices", method = RequestMethod.GET)
   public String showAllServiceIds() {
       List<String> serviceIds = this.discoveryClient.getServices();

       if (serviceIds == null || serviceIds.isEmpty()) {
           return "No services found!";
       }

       StringBuilder result = new StringBuilder("Service Ids:");

       for (String serviceId : serviceIds) {
           result.append("<br><a href='showService?serviceId=").append(serviceId).append("'>").append(serviceId).append("</a>");
       }
       return result.toString();
   }

   @ResponseBody
   @RequestMapping(value = "/showService", method = RequestMethod.GET)
   public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {
       List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

       if (instances == null || instances.isEmpty()) {
           return "No instances for service: " + serviceId;
       }

       StringBuilder result = new StringBuilder("Instances for Service: " + serviceId + "<br>");

       for (ServiceInstance serviceInstance : instances) {
           result.append("<br>Instance: ").append(serviceInstance.getUri());
           result.append("<br>" + "Hostname: ").append(serviceInstance.getHost());
           result.append("<br>" + "Port: ").append(serviceInstance.getPort()).append("<br>");
       }
       return result.toString();
   }
}
