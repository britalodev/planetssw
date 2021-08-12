package br.com.italo.planetssw.exception;

import java.util.Objects;

public class PlanetException extends RuntimeException {

    private static String DEFAULT_ERROR_MESSAGE = "Planets name can't be null";

    public PlanetException(String error){
        super(createMessageError(error));
    }

    private static String createMessageError(String error) {

        if(Objects.isNull(error) || "".equals(error)) {
            return DEFAULT_ERROR_MESSAGE;
        } else {
            return error;
        }
    }

}
