package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

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

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date updatedAt;

    @NotNull(message = "Field cannot be null")
    private String mailHost;

    @NotNull(message = "Field cannot be null")
    private String termsAndConditions;

    @NotNull(message = "Field cannot be null")
    private Integer mailPort;

    @NotNull(message = "Field cannot be null")
    private String mailUsername;

    @NotNull(message = "Field cannot be null")
    private String mailPassword;

}
