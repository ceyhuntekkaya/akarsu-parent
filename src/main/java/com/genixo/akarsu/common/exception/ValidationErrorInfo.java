package com.genixo.akarsu.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationErrorInfo extends BaseErrorInfo{
    public ValidationErrorInfo(String errorVal){
        super(errorVal);
    }

}
