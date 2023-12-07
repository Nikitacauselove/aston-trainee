package com.aston.trainee.servlet;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.service.GroceryListService;
import com.aston.trainee.util.JsonHttpMessageHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lists/*")
public class GroceryListServlet extends HttpServlet {
    private final GroceryListService groceryListService = new GroceryListService();
    private final JsonHttpMessageHelper messageHelper = new JsonHttpMessageHelper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GroceryListDto groceryListDto = messageHelper.read(req, GroceryListDto.class);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        messageHelper.write(resp, groceryListService.create(groceryListDto));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        messageHelper.write(resp, groceryListService.read());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GroceryListDto groceryListDto = messageHelper.read(req, GroceryListDto.class);
        Long listId = Long.parseLong(req.getPathInfo().substring(1));

        messageHelper.write(resp, groceryListService.update(listId, groceryListDto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long listId = Long.parseLong(req.getPathInfo().substring(1));

        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        groceryListService.delete(listId);
    }
}
