package com.example.server.dto;

import com.example.server.entity.Country;
import com.example.server.entity.Subscription;
import com.example.server.entity.Uzer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String surname;
    private String username;
    @JsonIgnore
    private String password;
    private Subscription subId;
    private OffsetDateTime subStart;
    private Country countryId;
    private String profileImageLink;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Uzer user) {
        UserDetailsImpl c = new UserDetailsImpl();
        c.id = user.getId();
        c.name = user.getName();
        c.surname = user.getSurname();
        c.username = user.getLogin();
        c.password = user.getPassword();
        c.subId = user.getSubId();
        c.subStart = user.getSubStart();
        c.countryId = user.getCountryId();
        c.profileImageLink = user.getLink();
        c.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDetailsImpl other = (UserDetailsImpl) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
