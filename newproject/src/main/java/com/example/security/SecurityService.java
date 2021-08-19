package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SecurityService {

    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }


    public List<Security> getSecurities() {
        return securityRepository.findAll();
    }

    public Security getSecurity(int id) {
        Optional<Security> securityOptional = securityRepository.findById(id);
        if(securityOptional.isEmpty()){
            throw new SecurityNotFoundException(id);
        }
        else{
            return securityOptional.get();
        }
    }

    public void addNewSecurity(Security security) {
        securityRepository.save(security);
    }

    public void deleteSecurity(int id) {
        if(securityRepository.existsById(id)){
            securityRepository.deleteById(id);
        }
        else{
            throw new SecurityNotFoundException(id);
        }
    }

    public void updateSecurity(int id, Security security) {

        Optional<Security> securityOptional = securityRepository.findById(id);
        if(securityOptional.isEmpty()){
            throw new SecurityNotFoundException(id);
        }
        Security updateSecurity = securityOptional.get();
        if(security.getSecurityId() != updateSecurity.getSecurityId()){
            throw new IllegalStateException("Security Id in path and in request body is different.");
        }

        if(security.getSymbol() != null && !Objects.equals(updateSecurity.getSymbol(), security.getSymbol()) && security.getSymbol().length() > 0){
            updateSecurity.setSymbol(security.getSymbol());
        }

        securityRepository.save(updateSecurity);

    }
}
