package com.raysi.onetoonewithspringboot.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String errorCode;
    private String errorMessage;
    public ResourceNotFoundException(String errorCode, String errorMessage){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
