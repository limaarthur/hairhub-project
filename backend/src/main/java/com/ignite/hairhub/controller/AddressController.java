package com.ignite.hairhub.controller;

import com.ignite.hairhub.dto.AddressDTO;
import com.ignite.hairhub.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
