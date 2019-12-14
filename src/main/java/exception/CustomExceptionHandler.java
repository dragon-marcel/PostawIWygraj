package exception;

import java.util.Date;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.PostawIWygraj.model.ErrorApi;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  
{
    
@ResponseBody
@ExceptionHandler(value = ValidationException.class)
public ResponseEntity<?> handleException(ValidationException exception) {
    System.out.print("Bladddddd");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
}
}
