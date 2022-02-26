package com.onik.spring.security.jwt.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class _GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(_GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleUserNotFoundException(HttpServletRequest req, UserNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(CarNumberAlreadyTakenException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> CarNumberAlreadyTakenException(HttpServletRequest req, CarNumberAlreadyTakenException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePasswordMismatchException(HttpServletRequest req, PasswordMismatchException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleRoleNotFoundException(HttpServletRequest req, RoleNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(ApartmentNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleApartmentNotFoundException(HttpServletRequest req, ApartmentNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleCarNotFoundException(HttpServletRequest req, CarNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(OfficeNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleOfficeNotFoundException(HttpServletRequest req, OfficeNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleUsernameAlreadyTakenException(HttpServletRequest req, UsernameAlreadyTakenException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleEmailAlreadyTakenException(HttpServletRequest req, EmailAlreadyTakenException e) {
        logError(req, e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleRefreshTokenNotFoundException(HttpServletRequest req, RefreshTokenNotFoundException e) {
        logError(req, e);
        return buildResponse(HttpStatus.FORBIDDEN, e.getMessage(), req.getRequestURI());
    }

    private ResponseEntity<?> buildResponse(HttpStatus httpCode, String message, String requestURI) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", message);
        errors.put("URL", requestURI);
        return ResponseEntity.status(httpCode).body(errors);
    }

    private void logError(HttpServletRequest req, Exception e) {
        LOGGER.warn(e.getMessage());
        LOGGER.warn("RequestURI {}", req.getRequestURI());
        LOGGER.error(ExceptionUtils.getStackTrace(e));
    }
}
