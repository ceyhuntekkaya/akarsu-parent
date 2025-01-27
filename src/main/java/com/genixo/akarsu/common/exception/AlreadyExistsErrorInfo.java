package com.genixo.akarsu.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlreadyExistsErrorInfo extends BaseErrorInfo{

    public AlreadyExistsErrorInfo(String name){
        super(name);
    }
}
