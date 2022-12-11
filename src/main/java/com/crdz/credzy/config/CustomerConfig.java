//package com.crdz.credzy.config;
//
//import com.crdz.credzy.entities.Customer;
//import com.crdz.credzy.repositories.CustomerRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//
//@Configuration
//public class CustomerConfig {
//
//
//
//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
//        return args -> {
//            Customer customer = new Customer(
//                1,
//                  "Bhavesh",
//                    "9999999999",
//                    "100",
//                    LocalDate.parse("31-12-2022"),
//                    "asdasdas",
//                    "male",
//                    LocalDate.parse("31-12-2022")
//            );
//            customerRepository.save(customer);
//        };
//    }
//}
