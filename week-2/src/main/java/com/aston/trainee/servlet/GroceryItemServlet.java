package com.aston.trainee.servlet;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.service.GroceryItemService;
import com.aston.trainee.util.JsonHttpMessageHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items/*")
public class GroceryItemServlet extends HttpServlet {
    private final GroceryItemService groceryItemService = new GroceryItemService();
    private final JsonHttpMessageHelper messageHelper = new JsonHttpMessageHelper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GroceryItemDto groceryItemDto = messageHelper.read(req, GroceryItemDto.class);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        messageHelper.write(resp, groceryItemService.create(groceryItemDto));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        messageHelper.write(resp, groceryItemService.read());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GroceryItemDto groceryItemDto = messageHelper.read(req, GroceryItemDto.class);
        Long itemId = Long.parseLong(req.getPathInfo().substring(1));

        messageHelper.write(resp, groceryItemService.update(itemId, groceryItemDto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long itemId = Long.parseLong(req.getPathInfo().substring(1));

        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        groceryItemService.delete(itemId);
    }
}
