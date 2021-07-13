package com.sda.weddingApp.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Wrapper for API messaging.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage<T> {


    @ApiModelProperty(value = "Response body of given type")
    private T body;

    @ApiModelProperty(value = "Response message with diagnostic information")
    private String message;
}
