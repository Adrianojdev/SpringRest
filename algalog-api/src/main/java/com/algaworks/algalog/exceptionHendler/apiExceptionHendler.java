package com.algaworks.algalog.exceptionHendler;

import com.algaworks.algalog.domain.exception.negocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class apiExceptionHendler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
           HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<problema.campo> campos = new ArrayList<>(); //criar lista de problemas para retornar especificamente

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField(); //Tem que ser para Field error
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new problema.campo(nome, mensagem));

        }


        problema problema = new problema();
        problema.setStatus(status.value()); //retornar que tem problema com data e hora do erro
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo("Um ou mais campos inválidos, corriga e tente novamente");
        problema.setCampos(campos);

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(negocioException.class)
    public ResponseEntity<Object> handleNegocio(negocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        problema problema = new problema();
        problema.setStatus(status.value()); //retornar que tem problema com data e hora do erro
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex,problema, new HttpHeaders(), status, request);
    }

}
