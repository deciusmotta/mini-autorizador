package com.mini.autorizador.autorizador.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMEssage> errors = new ArrayList<>();

    public List<FieldMEssage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMEssage(fieldName,message));
    }
}
