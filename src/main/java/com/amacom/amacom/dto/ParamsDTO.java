package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParamsDTO implements Serializable {

    private static final long serialVersionUID = -154678713327571699L;

    private UUID id;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
