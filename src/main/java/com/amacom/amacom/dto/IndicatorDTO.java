package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndicatorDTO implements Serializable {

    private static final long serialVersionUID = 1898795851125566654L;

    private UUID id;

    private String name;

}
