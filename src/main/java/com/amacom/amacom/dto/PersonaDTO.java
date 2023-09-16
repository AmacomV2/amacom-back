package com.amacom.amacom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO implements Serializable {

    private static final long serialVersionUID = -7256480951049205013L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idTipoDocumento;

    @NotNull(message = "Campo no puede ser nulo")
    private String documento;

    @NotNull(message = "Campo no puede ser nulo")
    private String nombre;

    @NotNull(message = "Campo no puede ser nulo")
    private String apellido;

    @NotNull(message = "Campo no puede ser nulo")
    private String genero;

    @NotNull(message = "Campo no puede ser nulo")
    private String direccion;

    private String estadoCivil;

    private String ocupacion;

    @NotNull(message = "Campo no puede ser nulo")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotNull(message = "Campo no puede ser nulo")
    private Boolean consentimiento;

    @NotNull(message = "Campo no puede ser nulo")
    private Boolean politicaPrivacidad;

    @NotNull(message = "Campo no puede ser nulo")
    private Boolean evaluacionCompletada;

    private String linkFoto;

}
