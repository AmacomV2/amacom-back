package com.amacom.amacom.dto;

import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO implements Serializable {

    private static final long serialVersionUID = -7256480951049205013L;

    private UUID id;

    private String nombreAndApellido;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idTipoDocumento;
    private String nombreTipoDocumento;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idGenero;

    private String nombreGenero;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idEstadoCivil;
    private String nombreEstadoCivil;

    @NotNull(message = "Campo no puede ser nulo")
    private String documento;

    @NotNull(message = "Campo no puede ser nulo")
    private String nombre;

    @NotNull(message = "Campo no puede ser nulo")
    private String apellido;


    @NotNull(message = "Campo no puede ser nulo")
    private String direccion;

    private String ocupacion;

    @NotNull(message = "Campo no puede ser nulo")
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaNacimiento;

    @NotNull(message = "Campo no puede ser nulo")
    private Boolean consentimiento;

    @NotNull(message = "Campo no puede ser nulo")
    private Boolean politicaPrivacidad;

    @NotNull(message = "Campo no puede ser nulo")
    private Boolean evaluacionCompletada;

    private String linkFoto;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;

}
