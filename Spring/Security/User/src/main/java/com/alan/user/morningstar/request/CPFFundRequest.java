package com.alan.user.morningstar.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CPFFundRequest {
    private Integer page;
    private Integer pageSize;
    private String sortOrder;
    private String outputType;
    private Integer version;
    private String languageId;
    private String currencyId;
    private String universeIds;
    private String securityDataPoints;
    private String filters;

    public static CPFFundRequest initializeDefaultRequest(){
        return CPFFundRequest.builder()
                .page(1)
                .pageSize(50)
                .sortOrder("name+asc")
                .outputType("json")
                .version(1)
                .languageId("en-GB")
                .currencyId("BAS")
                .universeIds("FOALL$$ALL%7CETALL$$ALL")
                .securityDataPoints("secId,name,QualifiedCPFschemeaccount")
                .filters("QualifiedCPFschemeaccount:IN:2:4")
                .build();
    }

}
