package com.hotel.Excepction;

public class OYOException extends RuntimeException {

    OYOException(){

    }

    public OYOException(String str){
        super(str);
    }
}
