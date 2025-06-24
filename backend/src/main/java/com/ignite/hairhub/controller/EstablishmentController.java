package com.ignite.hairhub.controller;

import com.ignite.hairhub.dto.EstablishmentDTO;
import com.ignite.hairhub.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/establishment")
public class EstablishmentController {

    @Autowired
    private EstablishmentService service;

    @GetMapping
    public ResponseEntity<List<EstablishmentDTO>> findAll() {
        List<EstablishmentDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public EstablishmentDTO findById(@PathVariable UUID id) {
        EstablishmentDTO establishmentDTO = service.findById(id);
        return establishmentDTO;
    }

    @PostMapping
    public ResponseEntity<EstablishmentDTO> insert(@RequestBody EstablishmentDTO establishmentDTO) {
        establishmentDTO = service.insert(establishmentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(establishmentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(establishmentDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EstablishmentDTO> update(@PathVariable UUID id, @RequestBody EstablishmentDTO establishmentDTO) {
        establishmentDTO = service.update(id, establishmentDTO);
        return ResponseEntity.ok(establishmentDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
