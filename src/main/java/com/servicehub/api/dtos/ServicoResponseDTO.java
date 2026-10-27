package com.servicehub.api.dtos;

import com.servicehub.api.models.Servico;
import java.math.BigDecimal;

public record ServicoResponseDTO(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    Boolean ativo
) {
    public static ServicoResponseDTO fromEntity(Servico servico) {
        return new ServicoResponseDTO(
            servico.getId(),
            servico.getNome(),
            servico.getDescricao(),
            servico.getPreco(),
            servico.getAtivo()
        );
    }
}
