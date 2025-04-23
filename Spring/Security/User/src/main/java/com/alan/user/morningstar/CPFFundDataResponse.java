package com.alan.user.morningstar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CPFFundDataResponse {
    private String secId;
    private String name;
    @JsonProperty("QualifiedCPFschemeaccount")
    private Integer qualifiedCPFSchemeAccount;
}
