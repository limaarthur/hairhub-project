package com.ignite.hairhub.service;

import com.ignite.hairhub.dto.EstablishmentDTO;
import com.ignite.hairhub.entity.Address;
import com.ignite.hairhub.entity.Establishment;
import com.ignite.hairhub.repository.AddressRepository;
import com.ignite.hairhub.repository.EstablishmentRepository;
import com.ignite.hairhub.service.exceptions.DatabaseException;
import com.ignite.hairhub.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    public List<EstablishmentDTO> findAll() {
        List<Establishment> list = repository.findAll();
        return list.stream().map(x -> new EstablishmentDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public EstablishmentDTO findById(UUID id) {
        Establishment establishment = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found")); // Tratamento da exceção personalizada
        return new EstablishmentDTO(establishment);
    }

    @Transactional
    public EstablishmentDTO insert(EstablishmentDTO establishmentDTO) {
        Establishment entity = new Establishment();

        copyDTOToEntity(establishmentDTO, entity); // Chamada ao método auxiliar evita repetição

        UUID addressId = establishmentDTO.getAddress().getId();
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found: " + addressId));
        entity.setAddress(address);

        entity = repository.save(entity);
        return new EstablishmentDTO(entity);
    }

    @Transactional
    public EstablishmentDTO update(UUID id, EstablishmentDTO establishmentDTO) {
        try { // Lança a exceção se o id não existir
            Establishment entity = repository.getReferenceById(id);
            copyDTOToEntity(establishmentDTO, entity); // Chamada ao método auxiliar evita repetição
            entity = repository.save(entity);
            return new EstablishmentDTO(entity);
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

    private void copyDTOToEntity(EstablishmentDTO establishmentDTO, Establishment entity) { // Método auxiliar
        entity.setName(establishmentDTO.getName());
        entity.setDescription(establishmentDTO.getDescription());
        entity.setCnpj(establishmentDTO.getCnpj());
        entity.setImgUrl(establishmentDTO.getImgUrl());
    }
}