package com.sda.weddingApp.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "Basic Person information.",
        description = "This model contains information about student.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    @ApiModelProperty(value = "First name.", example = "JAN")
    private String name;

    @ApiModelProperty(value = "Last name.", example = "KOWALSKI")
    private String surname;

    @ApiModelProperty(value = "email.", example = "JAN.KOWALSKI@GMAIL.COM")
    private String email;

}

