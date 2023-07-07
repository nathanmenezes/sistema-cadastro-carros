package br.com.omotor.carro.carroapi.service;


import br.com.omotor.carro.carroapi.model.CarroModel;
import br.com.omotor.carro.carroapi.model.dto.CarroDto;
import br.com.omotor.carro.carroapi.model.dto.CarroDtoAlteracao;
import br.com.omotor.carro.carroapi.model.dto.MensagemRetorno;
import br.com.omotor.carro.carroapi.repository.CarroRepository;
import br.com.omotor.carro.carroapi.repository.CarroSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    CarroRepository repository;


    public ResponseEntity<MensagemRetorno> salvarCarro(CarroDto carroDto) {
        carroDto.setModelo(carroDto.getModelo().trim());
        carroDto.setMarca(carroDto.getMarca().trim());
        carroDto.setAno(carroDto.getAno().trim());
        if (repository.existsByMarca(carroDto.getMarca())
                && repository.existsByModelo(carroDto.getModelo())
                && repository.existsByAno(carroDto.getAno())) {
            return ResponseEntity.status(404).body(new MensagemRetorno("Carro já cadastrado no sistema!", carroDto));
        }
        CarroModel carro = new CarroModel();

        BeanUtils.copyProperties(carroDto, carro);
        repository.save(carro);


        return ResponseEntity.status(201).body(new MensagemRetorno("Carro cadastrado com sucesso!", carroDto));
    }

    public ResponseEntity<Page<CarroDto>> listarCarros(Pageable paginacao) {
        return ResponseEntity.status(200).body(repository.findAll(paginacao).map(CarroDto::new));
    }

    public ResponseEntity<MensagemRetorno> deletarCarro(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body(new MensagemRetorno("Carro não encontrado no sistema!", null));
        }

        repository.deleteById(id);

        return ResponseEntity.status(200).body(new MensagemRetorno("Carro deletado com sucesso!", null));
    }

    public ResponseEntity<MensagemRetorno> alterarCarro(CarroDtoAlteracao carroDtoAlteracao) {
        if (!repository.existsById(carroDtoAlteracao.getId())) {
            return ResponseEntity.status(404).body(new MensagemRetorno("Carro não encontrado no sistema!", null));
        }

        CarroModel carro = repository.findById(carroDtoAlteracao.getId()).get();

        carro.alterarCarro(carroDtoAlteracao);
        repository.save(carro);

        CarroDto carroDto = new CarroDto();
        BeanUtils.copyProperties(carro, carroDto);

        return ResponseEntity.status(200).body(new MensagemRetorno("Carro alterado com sucesso!", carroDto));
    }

    public ResponseEntity<MensagemRetorno> listarCarroId(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body(new MensagemRetorno("Carro não encontrado no sistema!", null));
        }
        CarroDto carroDto = new CarroDto();
        BeanUtils.copyProperties(repository.findById(id).get(), carroDto);
        return ResponseEntity.status(200).body(new MensagemRetorno("Carro encontrado com sucesso!", carroDto));
    }

    //public ResponseEntity<Page<CarroDto>> listarCarrosFiltrados(Pageable paginacao, String marca, String modelo, String ano) {
    //Page<CarroDto> carros = repository.findAll(Specification.where(CarroSpecification.marca(marca))
    //            .and(CarroSpecification.modelo(modelo)).and(CarroSpecification.ano(ano)), paginacao).map(CarroDto::new);
    //    return ResponseEntity.status(200).body(carros);
    //}

    public ResponseEntity<Page<CarroDto>> listarCarrosFiltrados(Pageable paginacao, String marca, String modelo, String ano) {
        Specification<CarroModel> specification = Specification.where(null);

        if (marca != null && !marca.isEmpty()) {
            specification = specification.and(CarroSpecification.marca(marca));
        }

        if (modelo != null && !modelo.isEmpty()) {
            specification = specification.and(CarroSpecification.modelo(modelo));
        }

        if (ano != null && !ano.isEmpty()) {
            specification = specification.and(CarroSpecification.ano(ano));
        }

        Page<CarroDto> carros = repository.findAll(specification, paginacao).map(CarroDto::new);
        return ResponseEntity.status(200).body(carros);
    }
}
