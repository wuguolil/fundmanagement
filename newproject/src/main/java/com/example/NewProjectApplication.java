package com.example;

import com.example.fund.Fund;
import com.example.fund.FundRepository;
import com.example.manager.Manager;
import com.example.manager.ManagerRepository;
import com.example.manager.ManagerService;
import com.example.position.Position;
import com.example.position.PositionRepository;
import com.example.security.Security;
import com.example.security.SecurityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class NewProjectApplication {

    @Bean
    CommandLineRunner commandLineRunner(ManagerRepository managerRepository, FundRepository fundRepository,
                                        PositionRepository positionRepository, SecurityRepository securityRepository){
        return args -> {
            List<Manager> managers = List.of(
                    new Manager(1, "Xingyao", "Wu", new ArrayList<>()),
                    new Manager(2, "Yan", "Gao", new ArrayList<>()),
                    new Manager(3, "Dehua", "Liu", new ArrayList<>()));

            List<Manager> savedManagers= managerRepository.saveAll(managers);

            List<Fund> funds = List.of(
                    new Fund(1,"Olympic Memorial Fund", savedManagers.get(0), new HashSet<>()),
                    new Fund(2,"UK Overseas Income Fund", savedManagers.get(0), new HashSet<>()),
                    new Fund(3,"North America Growth", savedManagers.get(1), new HashSet<>()),
                    new Fund(4, "Global Tech Fund", savedManagers.get(2), new HashSet<>()),
                    new Fund(5,"China Fund", savedManagers.get(0), new HashSet<>())
            );

            List<Fund> savedFunds = fundRepository.saveAll(funds);

            List<Security> securities = List.of(
                    new Security(1,"IBM"),
                    new Security(2,"APPL")
            );

            securityRepository.saveAll(securities);

            List<Position> positions = List.of(
                    new Position(1,securities.get(0), 100, LocalDate.of(2016,1,10),savedFunds.get(0)),
                    new Position(2,securities.get(0),250, LocalDate.of(2016, 9, 23), savedFunds.get(1)),
                    new Position(3, securities.get(0), 200, LocalDate.of(2016,8,14),savedFunds.get(2)),
                    new Position(4, securities.get(0), 125, LocalDate.of(2016,9,23),savedFunds.get(3)),
                    new Position(5, securities.get(1), 75, LocalDate.of(2017,1,27),savedFunds.get(4)),
                    new Position(6, securities.get(1), 150, LocalDate.of(2016,6,22),savedFunds.get(4)),
                    new Position(7, securities.get(1), 166, LocalDate.of(2016,12,22),savedFunds.get(4))

            );

            positionRepository.saveAll(positions);




        };


    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(NewProjectApplication.class, args);

        ManagerService managerService = applicationContext.getBean(ManagerService.class);
        List<Manager> managers = managerService.getManagers();
        for (Manager manager: managers) {
            System.out.println(manager.toString());
        }

    }
}
