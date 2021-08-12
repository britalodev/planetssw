package br.com.italo.planetssw.advice;

import br.com.italo.planetssw.exception.PlanetException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {PlanetException.class})
    public ErrorBean exception(PlanetException planetException){
        return ErrorBean.builder().error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message(planetException.getMessage()).build();
    }

}