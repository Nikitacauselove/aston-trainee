package com.aston.trainee.servlet;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.service.AuthorService;
import com.aston.trainee.util.JsonHttpMessageHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authors/*")
public class AuthorServlet extends HttpServlet {
    private final AuthorService authorService;
    private final JsonHttpMessageHelper messageHelper;

    public AuthorServlet() {
        this.authorService = new AuthorService();
        messageHelper = new JsonHttpMessageHelper();
    }

    public AuthorServlet(AuthorService authorService, JsonHttpMessageHelper messageHelper) {
        this.authorService = authorService;
        this.messageHelper = messageHelper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AuthorDto authorDto = messageHelper.read(req, AuthorDto.class);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        messageHelper.write(resp, authorService.create(authorDto));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        messageHelper.write(resp, authorService.read());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AuthorDto authorDto = messageHelper.read(req, AuthorDto.class);
        Long authorId = Long.parseLong(req.getPathInfo().substring(1));

        messageHelper.write(resp, authorService.update(authorId, authorDto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long authorId = Long.parseLong(req.getPathInfo().substring(1));

        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        authorService.delete(authorId);
    }
}
