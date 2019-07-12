package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.exceptions.WrongUserNameException;
import cft.shift.manasyan.barter.models.dtos.ErrorTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BarterExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorTO> constructNotFoundEntity(NotFoundException nfe){
        return ResponseEntity.status(404).body(new ErrorTO(nfe.getLocalizedMessage()));
    }

    @ExceptionHandler (WrongUserNameException.class)
    public ResponseEntity<ErrorTO> constructNotFoundEntity(WrongUserNameException wue){
        return ResponseEntity.status(404).body(new ErrorTO(wue.getLocalizedMessage()));
    }
}