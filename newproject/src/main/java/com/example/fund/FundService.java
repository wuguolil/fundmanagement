package com.example.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FundService {
    private final FundRepository fundRepository;

    @Autowired
    public FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public List<Fund> getFunds() {
        return fundRepository.findAll();
    }

    public Fund getFund(int id) {
        Optional<Fund> fund = fundRepository.findById(id);
        if(fund.isEmpty()){
            throw new FundNotFoundException(id);
        }
        else{
            return fund.get();
        }
    }

    public void addNewFund(Fund fund) {
        fundRepository.save(fund);
    }

    public void deleteFund(int id) {
        if(fundRepository.existsById(id)){
            fundRepository.deleteById(id);
        }
        else{
            throw new FundNotFoundException(id);
        }
    }

    public void updateFund(int id, Fund fund) {
        Optional<Fund> fundOptional = fundRepository.findById(id);
        if(fundOptional.isEmpty()){
            throw new FundNotFoundException(id);
        }
        Fund updateFund = fundOptional.get();

        if(fund.getFundId() != updateFund.getFundId()){
            throw new IllegalStateException("Fund Id in path and in request body is different.");
        }

        if(fund.getName() != null && !Objects.equals(updateFund.getName(), fund.getName()) && fund.getName().length() > 0){
            updateFund.setName(fund.getName());
        }

        if(fund.getManager() != null && !Objects.equals(updateFund.getManager(), fund.getManager())){
            updateFund.setManager(fund.getManager());
        }

        fundRepository.save(updateFund);
    }
}
