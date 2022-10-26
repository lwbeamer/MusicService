package com.example.server.service;

import com.example.server.entity.Country;
import com.example.server.entity.Organisation;
import com.example.server.repository.CountryRepository;
import com.example.server.repository.OrganisationRepository;
import com.example.server.service.serviceInterface.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void createOrganisation(String description, String name, String countryName) {
        Optional<Country> country = countryRepository.findByName(countryName);
        Organisation organisation = new Organisation(name, description);
        organisation.setCountryId(country.get());
        organisationRepository.save(organisation);
    }
}
