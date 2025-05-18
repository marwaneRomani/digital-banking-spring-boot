package com.marwane.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner dataLoader(com.marwane.server.service.auth.AuthenticationService authenticationService) {
//        return args -> {
//
//            // Add an agent
//            com.marwane.server.dtos.auth.register.RegisterUserDto agentDto = new com.marwane.server.dtos.auth.register.RegisterUserDto();
//            agentDto.setEmail("agent@gmail.com");
//            agentDto.setPassword("agent123");
//            agentDto.setFullName("Marwane Agent");
//            agentDto.setTelephone("0700000000");
//
//            authenticationService.signupAgent(agentDto);
//
//
//            // Add a client
//            com.marwane.server.dtos.auth.register.RegisterUserDto clientDto = new com.marwane.server.dtos.auth.register.RegisterUserDto();
//            clientDto.setEmail("client@gmail.com");
//            clientDto.setPassword("client123");
//            clientDto.setFullName("Marwane Client");
//            clientDto.setTelephone("0600000000");
//            authenticationService.signup(clientDto);
//
//        };
//    }


}
