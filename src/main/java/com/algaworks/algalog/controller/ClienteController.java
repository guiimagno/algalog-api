package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("guilherme");
        cliente1.setEmail("gui@email");
        cliente1.setTelefone("97237643");

        Cliente cliente2 = new Cliente();
        cliente2.setId(1L);
        cliente2.setNome("maria");
        cliente2.setEmail("maria@email");
        cliente2.setTelefone("97237643");

        return Arrays.asList(cliente1, cliente2);
    }
}
