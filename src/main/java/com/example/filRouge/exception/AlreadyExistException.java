package com.example.filRouge.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends RuntimeException{
    public String getError() {
        return "This Record Already Exist";
    }
    public HttpStatus getCode(){
        return HttpStatus.FOUND;
    }
}
