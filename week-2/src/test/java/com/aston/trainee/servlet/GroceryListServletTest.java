package com.aston.trainee.servlet;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.service.GroceryListService;
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
public class GroceryListServletTest {
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private GroceryListService groceryListService;
    @Mock
    private JsonHttpMessageHelper messageHelper;

    private GroceryListServlet groceryListServlet;

    @BeforeEach
    void beforeEach() {
        groceryListServlet = new GroceryListServlet(groceryListService, messageHelper);
    }

    @Test
    void doPost() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(Expected.GROCERY_LIST_DTO);
        when(groceryListService.create(any(GroceryListDto.class))).thenReturn(Expected.GROCERY_LIST_DTO);

        groceryListServlet.doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_CREATED);
        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doGet() throws IOException {
        when(groceryListService.read()).thenReturn(List.of(Expected.GROCERY_LIST_DTO));

        groceryListServlet.doGet(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doPut() throws IOException {
        when(messageHelper.read(any(HttpServletRequest.class), any())).thenReturn(Expected.GROCERY_LIST_DTO);
        when(req.getPathInfo()).thenReturn("/1");
        when(groceryListService.update(anyLong(), any(GroceryListDto.class))).thenReturn(Expected.GROCERY_LIST_DTO);

        groceryListServlet.doPut(req, resp);

        verify(messageHelper).write(any(HttpServletResponse.class), any(Object.class));
    }

    @Test
    void doDelete() {
        when(req.getPathInfo()).thenReturn("/1");

        groceryListServlet.doDelete(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_NO_CONTENT);
        verify(groceryListService).delete(anyLong());
    }
}
