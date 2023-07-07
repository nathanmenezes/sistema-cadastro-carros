package br.com.omotor.carro.carroapi.model.dto;

import br.com.omotor.carro.carroapi.model.CarroModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarroDto {

    private Long id;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    private String ano;

    public CarroDto(CarroModel carro){
        this.id = carro.getId();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.ano = carro.getAno();
    }
}
