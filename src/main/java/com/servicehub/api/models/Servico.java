package com.servicehub.api.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Servico() {}

    public Servico(Long id, String nome, String descricao, BigDecimal preco, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = (ativo != null) ? ativo : true;
    }

    public static ServicoBuilder builder() {
        return new ServicoBuilder();
    }

    public static class ServicoBuilder {
        private Long id;
        private String nome;
        private String descricao;
        private BigDecimal preco;
        private Boolean ativo = true;

        public ServicoBuilder id(Long id) { this.id = id; return this; }
        public ServicoBuilder nome(String nome) { this.nome = nome; return this; }
        public ServicoBuilder descricao(String descricao) { this.descricao = descricao; return this; }
        public ServicoBuilder preco(BigDecimal preco) { this.preco = preco; return this; }
        public ServicoBuilder ativo(Boolean ativo) { this.ativo = ativo; return this; }

        public Servico build() {
            return new Servico(id, nome, descricao, preco, ativo);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
