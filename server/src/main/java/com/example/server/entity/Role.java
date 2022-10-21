package com.example.server.entity;

import com.example.server.entity.entityhelper.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(ERole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public String getAuthority() {
        return getName().toString();
    }
}
