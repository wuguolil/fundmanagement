package com.example.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }


    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }

    public Manager getManager(int id) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if(optionalManager.isEmpty()){
            throw new ManagerNotFoundException(id);
        }
        else {
            return optionalManager.get();
        }
    }

    public void addManager(Manager manager) {
        managerRepository.save(manager);

    }

    public void deleteManager(int id) {
        if(managerRepository.existsById(id)){
            managerRepository.deleteById(id);
        }
        else{
            throw new ManagerNotFoundException(id);
        }
    }

    public void updateManager(int id, Manager manager) {
        Optional<Manager> managerOptional = managerRepository.findById(id);

        if(managerOptional.isEmpty()){
            throw new ManagerNotFoundException(id);
        }

        Manager updateManager = managerOptional.get();

        if(updateManager.getEmployeeId() != manager.getEmployeeId()){
            throw new IllegalStateException("Manager Id in path and in request body is different.");
        }

        if(manager.getFirstName() != null && !Objects.equals(updateManager.getFirstName(), manager.getFirstName()) && manager.getFirstName().length() > 0){
            updateManager.setFirstName(manager.getFirstName());
        }

        if(manager.getLastName() != null && !Objects.equals(updateManager.getLastName(), manager.getLastName()) && manager.getLastName().length() > 0){
            updateManager.setLastName(manager.getLastName());
        }
        managerRepository.save(updateManager);
    }
}
