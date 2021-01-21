package com.jurepinjuh.demo.Handlers;

import com.jurepinjuh.demo.Controllers.HttpModels.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    public ValidationExceptionHandler(MessageSource messageSource){
        this.messageSource=messageSource;
    }
    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> errors=ex.getBindingResult().getFieldErrors();
        List<String> messages=new ArrayList<String>();
        for (FieldError error:
             errors) {
            try{
                messages.add(messageSource.getMessage(error.getDefaultMessage(),null,LocaleContextHolder.getLocale()));
            }
            catch (NoSuchMessageException exception){
                messages.add(error.getField()+":"+error.getDefaultMessage());
            }
        }
        ValidationError error=new ValidationError(new Date(),messages);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
