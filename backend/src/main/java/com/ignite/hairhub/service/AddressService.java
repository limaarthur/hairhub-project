package com.ignite.hairhub.service;

import com.ignite.hairhub.dto.AddressDTO;
import com.ignite.hairhub.entity.Address;
import com.ignite.hairhub.repository.AddressRepository;
import com.ignite.hairhub.service.exceptions.DatabaseException;
import com.ignite.hairhub.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        Address address = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found")); // Tratamento da exceção personalizada
        return new AddressDTO(address);
    }

    @Transactional
    public AddressDTO insert(AddressDTO addressDTO) {
        Address entity = new Address();
        copyDTOToEntity(addressDTO, entity); // Chamada ao método auxiliar evita repetição
        entity = repository.save(entity);
        return new AddressDTO(entity);
    }

    @Transactional
    public AddressDTO update(UUID id, AddressDTO addressDTO) {
        try { // Lança a exceção se o id não existir
            Address entity = repository.getReferenceById(id);
            copyDTOToEntity(addressDTO, entity); // Chamada ao método auxiliar evita repetição
            entity = repository.save(entity);
            return new AddressDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDTOToEntity(AddressDTO addressDTO, Address entity) { // Método auxiliar
        entity.setStreet(addressDTO.getStreet());
        entity.setCity(addressDTO.getCity());
        entity.setUf(addressDTO.getUf());
        entity.setZipCode(addressDTO.getZipCode());
    }
}