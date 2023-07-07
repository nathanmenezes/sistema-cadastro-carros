package br.com.omotor.carro.carroapi.model;

import br.com.omotor.carro.carroapi.model.dto.CarroDtoAlteracao;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "carro_tb")
@Data
public class CarroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String ano;

    public void alterarCarro(CarroDtoAlteracao carroDtoAlteracao){
        if (carroDtoAlteracao.getAno() != null) {
            this.ano = carroDtoAlteracao.getAno();
        }
        if (carroDtoAlteracao.getMarca() != null) {
            this.marca = carroDtoAlteracao.getMarca();
        }
        if (carroDtoAlteracao.getModelo() != null) {
            this.modelo = carroDtoAlteracao.getModelo();
        }
    }
}
