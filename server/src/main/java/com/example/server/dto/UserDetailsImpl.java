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

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

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
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String name, String surname, String username,String password,Subscription subId,OffsetDateTime subStart, Country countryId, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.surname =surname;
        this.username = username;
        this.password = password;
        this.subId =subId;
        this.subStart =subStart;
        this.countryId = countryId;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Uzer user) {
        UserDetailsImpl c = new UserDetailsImpl();
        c.id = user.getId();
        c.name = user.getName();
        c.surname = user.getSurname();
        c.username= user.getLogin();
        c.password = user.getPassword();
        c.subId = user.getSubId();
        c.subStart = user.getSubStart();
        c.countryId = user.getCountryId();
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
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
