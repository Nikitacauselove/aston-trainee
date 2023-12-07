package com.aston.trainee.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class JsonHttpMessageHelper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T read(HttpServletRequest req, Class<T> valueType) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining());

        return objectMapper.readValue(requestBody, valueType);
    }

    public void write(HttpServletResponse resp, Object value) throws IOException {
        PrintWriter printWriter = resp.getWriter();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        printWriter.print(objectMapper.writeValueAsString(value));
    }
}
