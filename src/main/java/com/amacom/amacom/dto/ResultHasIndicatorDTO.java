package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultHasIndicatorDTO implements Serializable {

    private static final long serialVersionUID = 1777672116375679585L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID idResult;

    @NotNull(message = "Field cannot be null")
    private UUID idIndicator;

}
