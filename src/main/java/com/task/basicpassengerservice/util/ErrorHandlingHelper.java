package com.task.basicpassengerservice.util;

import com.task.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.task.basicpassengerservice.responses.Response;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorHandlingHelper {

    //komentar
    public static Response handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder message = new StringBuilder();
        List<String> messages = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        for (int i = 0; i < messages.size(); i++)
            if (i < messages.size() - 1) message.append(messages.get(i)).append("; ");
            else message.append(messages.get(i));
        return new Response(message.toString(),400);
    }

    public static Response handleEntityNotFoundException(ResourceNotFoundException exception) {
        return new Response(exception.getMessage(),404);
    }

    public static Response handleIllegalArgumentException(SQLException exception) {
        return new Response(exception.getMessage(),409);
    }

    public static Response handleParseException(ParseException exception) {
        return new Response(exception.getMessage(),415);
    }

    public static Response handleNumberFormatException(NumberFormatException exception) {
        return new Response("Invalid date and time format.",500);
    }
}
