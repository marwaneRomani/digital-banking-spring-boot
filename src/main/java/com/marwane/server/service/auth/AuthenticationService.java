package com.marwane.server.service.auth;

import com.marwane.server.dtos.auth.login.LoginUserDto;
import com.marwane.server.dtos.auth.register.RegisterUserDto;
import com.marwane.server.models.users.Agent;
import com.marwane.server.models.users.Client;
import com.marwane.server.models.users.User;
import com.marwane.server.repositories.AgentRepository;
import com.marwane.server.repositories.ClientRepository;
import com.marwane.server.repositories.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;
    private final AgentRepository agentRepository;

    public AuthenticationService (
            UserRepository userRepository,
            @Lazy
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            ClientRepository clientRepository, AgentRepository agentRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.agentRepository = agentRepository;
    }

    public User signup(RegisterUserDto input) {
        Client client = new Client();

        client.setFullName(input.getFullName());
        client.setEmail(input.getEmail());
        client.setTelephone(input.getTelephone());
        client.setPassword(passwordEncoder.encode(input.getPassword()));
        client.setDateCreation(LocalDateTime.now());
        client.setComptes(new ArrayList<>());

        client.setCreatedBy(null);

        return clientRepository.save(client);
    }

    public User signupAgent(RegisterUserDto input) {
        Agent agent = new Agent();

        agent.setFullName(input.getFullName());
        agent.setEmail(input.getEmail());
        agent.setTelephone(input.getTelephone());
        agent.setPassword(passwordEncoder.encode(input.getPassword()));
        agent.setDateCreation(LocalDateTime.now());
        agent.setManagedAccounts(new ArrayList<>());

        return agentRepository.save(agent
        );
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsImpl(user);
    }
}
