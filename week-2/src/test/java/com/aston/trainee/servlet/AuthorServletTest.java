package com.aston.trainee.servlet;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.service.impl.AuthorServiceImpl;
import com.aston.trainee.util.Expected;
import com.aston.trainee.util.JsonHttpMessageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServletTest {
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private AuthorServiceImpl authorServiceImpl;
    @Mock
    private JsonHttpMessageHelper messageHelper;

    private AuthorServlet authorServlet;

    @BeforeEach
    void beforeEach() {
        this.authorServlet = new AuthorServlet(authorServiceImpl, messageHelper);
    }

    @Test
    void doPost() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(Expected.AUTHOR_DTO);
        when(authorServiceImpl.create(any(AuthorDto.class))).thenReturn(Expected.AUTHOR_DTO);

        authorServlet.doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_CREATED);
        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doGet() throws IOException {
        when(authorServiceImpl.read()).thenReturn(List.of(Expected.AUTHOR_DTO));

        authorServlet.doGet(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doPut() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(Expected.AUTHOR_DTO);
        when(req.getPathInfo()).thenReturn("/1");
        when(authorServiceImpl.update(anyLong(), any(AuthorDto.class))).thenReturn(Expected.AUTHOR_DTO);

        authorServlet.doPut(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doDelete() {
        when(req.getPathInfo()).thenReturn("/1");

        authorServlet.doDelete(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_NO_CONTENT);
        verify(authorServiceImpl).delete(anyLong());
    }
}
