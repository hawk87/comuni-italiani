package it.comuni.comuniitaliani.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerManager {

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ComuneNotFoundException.class)
    String comuneNotFoundHandler(ComuneNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ProvinciaNotFoundException.class)
    String provinciaNotFoundHandler(ProvinciaNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(RegioneNotFoundException.class)
    String regioneNotFoundHandler(RegioneNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ZonaNotFoundException.class)
    String zonaNotFoundHandler(ZonaNotFoundException ex) {
        return ex.getMessage();
    }
}