package br.com.omotor.carro.carroapi.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarroDtoAlteracao {

    private Long id;

    private String marca;

    private String modelo;

    private String ano;
}
