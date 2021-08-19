package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/security")
public class SecurityController {
    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public List<Security> getSecurities(){
        return securityService.getSecurities();
    }

    @GetMapping(path = "{securityId}")
    public Security getSecurity(@PathVariable("securityId") int id){
        return securityService.getSecurity(id);
    }

    @PostMapping
    public void addSecurity(@RequestBody Security security){
        securityService.addNewSecurity(security);
    }

    @DeleteMapping(path = "{securityId}")
    public void deleteSecurity(@PathVariable("securityId") int id){
        securityService.deleteSecurity(id);
    }

    @PutMapping(path = "{securityId}")
    public void updateSecurity(@PathVariable("securityId") int id, @RequestBody Security security){
        securityService.updateSecurity(id,security);
    }
}
