package com.servicehub.api.services;

import com.servicehub.api.dtos.ServicoRequestDTO;
import com.servicehub.api.dtos.ServicoResponseDTO;
import com.servicehub.api.models.Servico;
import com.servicehub.api.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Transactional
    public ServicoResponseDTO criar(ServicoRequestDTO dto) {
        Servico servico = Servico.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .preco(dto.preco())
                .ativo(dto.ativo())
                .build();
        return ServicoResponseDTO.fromEntity(repository.save(servico));
    }

    @Transactional(readOnly = true)
    public List<ServicoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ServicoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ServicoResponseDTO buscarPorId(Long id) {
        Servico servico = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Serviço com ID " + id + " não encontrado."));
        return ServicoResponseDTO.fromEntity(servico);
    }

    @Transactional
    public ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto) {
        Servico servico = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Serviço com ID " + id + " não encontrado."));

        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setPreco(dto.preco());
        servico.setAtivo(dto.ativo());

        return ServicoResponseDTO.fromEntity(repository.save(servico));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Serviço com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
}