package com.example.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/fund")
public class FundController {
    private final FundService fundService;

    @Autowired
    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping
    public List<Fund> getFunds(){
        return fundService.getFunds();
    }

    @GetMapping(path = "{fundId}")
    public Fund getFund(@PathVariable("fundId") int id){
        return fundService.getFund(id);
    }

    @PostMapping
    public void addFund(@RequestBody Fund fund){
        fundService.addNewFund(fund);
    }

    @DeleteMapping(path = "{fundId}")
    public void deleteFund(@PathVariable("fundId") int id){
        fundService.deleteFund(id);
    }

    @PutMapping(path = "{fundId}")
    public void updateFund(@PathVariable("fundId") int id, @RequestBody Fund fund) {
        fundService.updateFund(id, fund);

    }
}
