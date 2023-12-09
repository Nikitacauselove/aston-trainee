package com.aston.trainee.servlet;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.service.GroceryItemService;
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
    private GroceryItemService groceryItemService;
    @Mock
    private JsonHttpMessageHelper messageHelper;

    private GroceryItemServlet groceryItemServlet;
    private final GroceryItemDto groceryItemDto = new GroceryItemDto("Apples");

    @BeforeEach
    void beforeEach() {
        groceryItemServlet = new GroceryItemServlet(groceryItemService, messageHelper);
    }

    @Test
    void doPost() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(groceryItemDto);
        when(groceryItemService.create(any(GroceryItemDto.class))).thenReturn(groceryItemDto);

        groceryItemServlet.doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_CREATED);
        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doGet() throws IOException {
        when(groceryItemService.read()).thenReturn(List.of(groceryItemDto));

        groceryItemServlet.doGet(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doPut() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(groceryItemDto);
        when(req.getPathInfo()).thenReturn("/1");
        when(groceryItemService.update(anyLong(), any(GroceryItemDto.class))).thenReturn(groceryItemDto);

        groceryItemServlet.doPut(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doDelete() {
        when(req.getPathInfo()).thenReturn("/1");

        groceryItemServlet.doDelete(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_NO_CONTENT);
        verify(groceryItemService).delete(anyLong());
    }
}
