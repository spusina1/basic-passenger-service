package com.softraysolutions.basicpassengerservice.util;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.responses.Response;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorHandlingHelper {

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
}
