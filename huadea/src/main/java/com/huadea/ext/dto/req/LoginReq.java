package com.huadea.ext.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginReq  implements Serializable {
    @NotEmpty
    @JsonProperty("policeNumber")
    public String policeNumber;

    @NotEmpty
    @JsonProperty("passwd")
    public String passwd;
}
