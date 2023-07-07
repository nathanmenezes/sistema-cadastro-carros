package br.com.omotor.carro.carroapi.repository;

import br.com.omotor.carro.carroapi.model.CarroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<CarroModel, Long>, JpaSpecificationExecutor<CarroModel> {
    boolean existsByMarca(String marca);

    boolean existsByModelo(String modelo);

    boolean existsByAno(String ano);
}
