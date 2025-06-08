package com.ignite.hairhub.controller;

import com.ignite.hairhub.dto.AddressDTO;
import com.ignite.hairhub.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController // Essa anotation vai configurar quando a aplicação for executada, vai responder pela web
@RequestMapping(value = "/address") // Configura a rota para o endpoint address
public class AddressController {

    @Autowired // Anotation para injetar automaticamente a dependência
    private AddressService service; // Injeta um componente do AddressService

    @GetMapping // Método Get
    public ResponseEntity<List<AddressDTO>> findAll() { // Pageable faz a busca pagina dos registros
        List<AddressDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}") // Método Get para o id
    public AddressDTO findById(@PathVariable UUID id) {
        AddressDTO addressDTO = service.findById(id);
        return addressDTO;
    }

    @PostMapping // Método post para inserir um novo endereço
    public ResponseEntity<AddressDTO> insert(@RequestBody AddressDTO addressDTO) { // RequestBody vai receber os dados da Request do postman
        addressDTO = service.insert(addressDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}") // Constrói a URI do novo recurso (/addresses/{id}) para ser usada no cabeçalho Location
                .buildAndExpand(addressDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(addressDTO);
    }

    @PutMapping(value = "/{id}") // Método put para atualizar um endereço
    public ResponseEntity<AddressDTO> update(@PathVariable UUID id, @RequestBody AddressDTO addressDTO) { // PathVariable terá a variável na url e RequestBody vai receber os dados no corpo da requisição
        addressDTO = service.update(id, addressDTO); // Atualiza o endereço
        return ResponseEntity.ok(addressDTO);
    }
}
