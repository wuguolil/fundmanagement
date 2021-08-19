package com.example.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/manager")
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<Manager> getManagers(){
        return managerService.getManagers();
    }

    @GetMapping(path = "{managerId}")
    public Manager getManager(@PathVariable("managerId") int id){
        return managerService.getManager(id);
    }

    @PostMapping
    public void addManager(@RequestBody Manager manager){
        managerService.addManager(manager);
    }

    @DeleteMapping(path = "{managerId}")
    public void deleteManager(@PathVariable("managerId") int id){
        managerService.deleteManager(id);
    }

    @PutMapping(path = "{managerId}")
    public void updateManager(@PathVariable("managerId") int id, @RequestBody Manager manager){
        managerService.updateManager(id, manager);
    }
}
