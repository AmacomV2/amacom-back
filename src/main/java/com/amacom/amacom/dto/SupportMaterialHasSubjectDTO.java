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
public class SupportMaterialHasSubjectDTO implements Serializable {

    private static final long serialVersionUID = -4671326862230894836L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID idSupportMaterial;

    @NotNull(message = "Field cannot be null")
    private UUID subjectId;

    private String materialName;

    private String materialDescription;

    private String subjectName;

    private String subjectDescription;


}
