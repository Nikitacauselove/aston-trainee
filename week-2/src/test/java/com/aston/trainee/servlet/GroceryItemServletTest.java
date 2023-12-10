package com.aston.trainee.servlet;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.service.impl.GroceryItemServiceImpl;
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
public class GroceryItemServletTest {
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private GroceryItemServiceImpl groceryItemServiceImpl;
    @Mock
    private JsonHttpMessageHelper messageHelper;

    private GroceryItemServlet groceryItemServlet;

    @BeforeEach
    void beforeEach() {
        groceryItemServlet = new GroceryItemServlet(groceryItemServiceImpl, messageHelper);
    }

    @Test
    void doPost() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(Expected.GROCERY_ITEM_DTO);
        when(groceryItemServiceImpl.create(any(GroceryItemDto.class))).thenReturn(Expected.GROCERY_ITEM_DTO);

        groceryItemServlet.doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_CREATED);
        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doGet() throws IOException {
        when(groceryItemServiceImpl.read()).thenReturn(List.of(Expected.GROCERY_ITEM_DTO));

        groceryItemServlet.doGet(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doPut() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(Expected.GROCERY_ITEM_DTO);
        when(req.getPathInfo()).thenReturn("/1");
        when(groceryItemServiceImpl.update(anyLong(), any(GroceryItemDto.class))).thenReturn(Expected.GROCERY_ITEM_DTO);

        groceryItemServlet.doPut(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doDelete() {
        when(req.getPathInfo()).thenReturn("/1");

        groceryItemServlet.doDelete(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_NO_CONTENT);
        verify(groceryItemServiceImpl).delete(anyLong());
    }
}
