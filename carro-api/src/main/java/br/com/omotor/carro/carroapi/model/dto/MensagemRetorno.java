package br.com.omotor.carro.carroapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRetorno {

    private String mensagem;

    private Object corpo;
}
