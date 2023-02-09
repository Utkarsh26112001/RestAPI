package com.markests.RestAPI.controller;

import com.markests.RestAPI.entity.EmployeeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class RestAPIController {


    private static RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/getEmployees")
    public static ResponseEntity<String> getEmployees() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/getall", HttpMethod.GET, entity, String.class);
        return result;
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody EmployeeData employeeData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<EmployeeData> entity = new HttpEntity<EmployeeData>(employeeData, headers);

        return restTemplate.exchange("http://localhost:8080/saveEmployee", HttpMethod.POST, entity, String.class).getBody();

    }

    @DeleteMapping("/deleteEmployee/{eid}")
    public Integer deleteEmployee(@PathVariable("eid") Integer eid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<EmployeeData> entity = new HttpEntity<EmployeeData>(headers);

        return restTemplate.exchange("http://localhost:8080/delete/{eid}", HttpMethod.DELETE, entity, Integer.class).getBody();

    }
}
