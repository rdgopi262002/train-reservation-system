package com.gopi.trainservice.exception.handler;


import com.gopi.trainservice.exception.custom.DuplicateResourceException;
import com.gopi.trainservice.exception.custom.DuplicateRouteException;
import com.gopi.trainservice.exception.custom.ResourceNotFoundException;
import com.gopi.trainservice.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateStationCode(
            DuplicateResourceException exception){

        ApiResponse<Void> response=new ApiResponse<>(
                false,exception.getMessage(),null,null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> nonExistStationId(
            ResourceNotFoundException exception){
        ApiResponse<Void> response=new ApiResponse<>(false,exception.getMessage(),null,null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateRouteException.class)
    public ResponseEntity<String> handleDuplicate(
            DuplicateRouteException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

}
