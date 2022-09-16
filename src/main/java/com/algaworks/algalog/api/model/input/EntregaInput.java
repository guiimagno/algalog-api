package com.algaworks.algalog.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {

    @Valid
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatarioInput destinatarioInput;

    @NotNull
    private BigDecimal taxa;

}