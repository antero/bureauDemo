package com.bw.translatorCRUD.exception;

import com.bw.translatorCRUD.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Server error", getDetailsFromException(ex));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TranslatorNotFoundException.class)
    public final ResponseEntity<Object> handleTranslatorNotFoundException(TranslatorNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Translator not found", getDetailsFromException(ex));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public final ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Email already exists", getDetailsFromException(ex));
        return new ResponseEntity(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TranslationSkillNotFoundException.class)
    public final ResponseEntity<Object> handleTranslationSkillNotFoundException(TranslationSkillNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Translation skill not found", getDetailsFromException(ex));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = ex.getBindingResult().getAllErrors().stream()
                                                                   .map(ObjectError::getDefaultMessage)
                                                                   .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    private List<String> getDetailsFromException(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        return details;
    }
}
