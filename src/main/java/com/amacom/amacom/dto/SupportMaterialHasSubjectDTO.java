package com.amacom.amacom.dto;

import com.amacom.amacom.model.Subject;
import com.amacom.amacom.model.SupportMaterial;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SupportMaterialHasSubjectDTO implements Serializable {

    private static final long serialVersionUID = -4671326862230894836L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idSupportMaterial;


    @NotNull(message = "Campo no puede ser nulo")
    private UUID idSubject;

}
