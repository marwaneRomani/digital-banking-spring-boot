package com.marwane.server.models.users;

import com.marwane.server.models.Compte;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@DiscriminatorValue("AGENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent extends User {

    @OneToMany(mappedBy = "createdBy")
    private List<Client> clientsManaged;

    @OneToMany(mappedBy = "managedBy", cascade = CascadeType.ALL)
    private List<Compte> managedAccounts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
