package com.marwane.server.service;


import com.marwane.server.dtos.register.RegisterUserDto;
import com.marwane.server.models.users.Agent;
import com.marwane.server.models.users.Client;
import com.marwane.server.models.users.User;
import com.marwane.server.repositories.AgentRepository;
import com.marwane.server.repositories.ClientRepository;
import com.marwane.server.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AuthenticationService implements UserDetailsService {
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final AgentRepository agentRepository;

    public AuthenticationService(UserRepository userRepository, ClientRepository clientRepository, AgentRepository agentRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository  = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.agentRepository = agentRepository;
    }


    public User signup(RegisterUserDto dto) {
        Client client = new Client();

        client.setFullName(dto.getFullName());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setPassword(passwordEncoder.encode(dto.getPassword()));
        client.setDateCreation(LocalDateTime.now());
        client.setComptes(new ArrayList<>());

        return clientRepository.save(client);
    }

    public User signupAgent(RegisterUserDto dto) {
        Agent agent = new Agent();

        agent.setFullName(dto.getFullName());
        agent.setEmail(dto.getEmail());
        agent.setTelephone(dto.getTelephone());
        agent.setPassword(passwordEncoder.encode(dto.getPassword()));
        agent.setDateCreation(LocalDateTime.now());
        agent.setManagedAccounts(new ArrayList<>());

        return agentRepository.save(agent
        );
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsServiceImpl(user);
    }
}
