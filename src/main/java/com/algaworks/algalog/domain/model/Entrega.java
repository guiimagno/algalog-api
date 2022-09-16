package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded // usa quando quer abstrair os dados para uma outra classe, mapeando para mesma tabela Entrega
    private Destinatario destinatario;

    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrenciaList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;
    private OffsetDateTime dataPedido;
    private OffsetDateTime datafinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrenciaList().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if (naoPodeSerFinalizada()){
            throw new NegocioException("Entrega não pode ser finalizada.");
        }

        setStatusEntrega(StatusEntrega.FINALIZADA);
        setDatafinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada(){
        return StatusEntrega.PENDENTE.equals(getStatusEntrega());
    }

    public boolean naoPodeSerFinalizada(){
        return !podeSerFinalizada();
    }
}
