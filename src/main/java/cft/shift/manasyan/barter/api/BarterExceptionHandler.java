package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.dtos.ErrorTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BarterExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorTO> constructNotFoundEntity(NotFoundException nfe){
        return ResponseEntity.status(404).body(new ErrorTO(nfe.getLocalizedMessage()));
    }
}