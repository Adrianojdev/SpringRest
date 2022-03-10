package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.cliente;
import com.algaworks.algalog.domain.repository.clienteRepository;
import com.algaworks.algalog.domain.service.catalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class clienteController {


    private clienteRepository clienteRepository;
    private catalogoClienteService catalogoClienteService;

    @GetMapping
    public List<cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}") //buscar cliente pelo ID
    public ResponseEntity<cliente> buscar(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> ResponseEntity.ok(cliente)) //outro modo = .map(ResponseEntity: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping  //Método para adicionar cliente ao banco
    @ResponseStatus(HttpStatus.CREATED)  //Método para retornar confirmação da criação do cliente
    public cliente adicionar(@Valid @RequestBody cliente cliente) {
        //return clienteRepository.save(cliente); substituido pelo service
        return catalogoClienteService.salvar(cliente);

    }

    @PutMapping("/{clienteId}") //Método para atualizar dados do cliente
    public ResponseEntity<cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody cliente cliente) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build(); //Não existe retorna 404
        }
        cliente.setId(clienteId); //se não tiver esse comando set.Id ele vai cadastrar novo cliente
        //cliente = clienteRepository.save(cliente); substituido pelo service
        cliente = catalogoClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}") //Método para deletar cliente
    public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build(); //Não existe retorna 404
        }

        //clienteRepository.deleteById(clienteId); substituido pelo service
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build(); //Existe retorna 204 = No Content
    }
}




























// return clienteRepository.findByNome("Joao da silva"); return do método do repository
// return clienteRepository.findByNomeContaining("mar"); return do método do repository



   /* @GetMapping("/clientes/{clienteId}") //buscar cliente pelo ID
    public cliente buscar(@PathVariable Long clienteId) {
        Optional<cliente> cliente = clienteRepository.findById(clienteId);

        return cliente.orElse(null);
        RETORNA O CLIENTE MAS QUANDO NÃO TEM RETORNA 200(ok) FALTA IMPLEMENTAÇÃO QUE VAI SER FEITA ACIMA
        */

//Optional<cliente> cliente = clienteRepository.findById(clienteId);

//if (cliente.isPresent()) { *Metodo certo, porém da para enxugar o código*
//     return ResponseEntity.ok(cliente.get());
//  }
//  return ResponseEntity.notFound().build();