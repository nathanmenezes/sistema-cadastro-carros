package br.com.omotor.carro.carroapi.resource;


import br.com.omotor.carro.carroapi.model.CarroModel;
import br.com.omotor.carro.carroapi.model.dto.CarroDto;
import br.com.omotor.carro.carroapi.model.dto.CarroDtoAlteracao;
import br.com.omotor.carro.carroapi.model.dto.MensagemRetorno;
import br.com.omotor.carro.carroapi.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class CarroResource {

    @Autowired
    CarroService service;

    @PostMapping
    public ResponseEntity<MensagemRetorno> salvarCarro(@RequestBody @Valid CarroDto carroDto){
        return service.salvarCarro(carroDto);
    }

    @GetMapping
    public ResponseEntity<Page<CarroDto>> listarCarros(@PageableDefault(size = 10) Pageable paginacao){
        return service.listarCarros(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensagemRetorno> listarCarroId(@PathVariable Long id){
        return service.listarCarroId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> deletarCarro(@PathVariable Long id){
        return service.deletarCarro(id);
    }

    @PutMapping
    public ResponseEntity<MensagemRetorno> alterarCarro(@RequestBody CarroDtoAlteracao carroDtoAlteracao){
        return service.alterarCarro(carroDtoAlteracao);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<CarroDto>> listarCarrosFiltrados(@PageableDefault(size = 10) Pageable paginacao,
                                                                @RequestParam(required = false) String marca,
                                                                @RequestParam(required = false) String modelo,
                                                                @RequestParam(required = false) String ano){
        return service.listarCarrosFiltrados(paginacao, marca, modelo, ano);
    }
}
