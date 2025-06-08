package com.ignite.hairhub.service;

import com.ignite.hairhub.dto.AddressDTO;
import com.ignite.hairhub.entity.Address;
import com.ignite.hairhub.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired // Dependencia do repositoy
    private AddressRepository repository;

    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        List<Address> list = repository.findAll();
        return list.stream().map(x -> new AddressDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public AddressDTO findById(UUID id) {
        Address address = repository.findById(id).get();
        return new AddressDTO(address);
    }
}
