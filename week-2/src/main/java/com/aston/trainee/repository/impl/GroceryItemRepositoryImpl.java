package com.aston.trainee.repository.impl;

import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroceryItemRepositoryImpl implements com.aston.trainee.repository.GroceryItemRepository {
    private final static String INSERT_SQL = "insert into item (name) values (?)";
    private final static String SELECT_SQL = "select * from item";
    private final static String UPDATE_SQL = "update item set name = ? where id = ?";
    private final static String DELETE_SQL = "delete from item where id = ?";

    private final ConnectionManager connectionManager;

    public GroceryItemRepositoryImpl() {
        this.connectionManager = new ConnectionManager();
    }

    public GroceryItemRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public GroceryItem create(GroceryItem groceryItem) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, groceryItem.getName());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                groceryItem.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException exception) {
            System.out.println("При добавлении нового товара возникла ошибка");
        }
        return groceryItem;
    }

    public List<GroceryItem> read() {
        List<GroceryItem> groceryItems = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                GroceryItem groceryItem = new GroceryItem();

                groceryItem.setId(resultSet.getLong("id"));
                groceryItem.setName(resultSet.getString("name"));
                groceryItems.add(groceryItem);
            }
        } catch (SQLException exception) {
            System.out.println("При поиске товаров возникла ошибка");
        }
        return groceryItems;
    }

    public GroceryItem update(GroceryItem updatedGroceryItem) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);

            statement.setString(1, updatedGroceryItem.getName());
            statement.setLong(2, updatedGroceryItem.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("При изменении информации о товаре возникла ошибка");
        }
        return updatedGroceryItem;
    }

    public void delete(Long id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("При удалении товара возникла ошибка");
        }
    }

    public GroceryItem readByName(String name) {
        GroceryItem groceryItem = new GroceryItem();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from item where name = ?");

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                groceryItem.setId(resultSet.getLong("id"));
                groceryItem.setName(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            System.out.println("При получении подробной информации о товаре по его названию возникла ошибка");
        }
        return groceryItem;
    }

    public List<GroceryItem> readByListId(Long listId) {
        List<GroceryItem> groceryItems = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select id, name from list_item left join item on list_item.item_id = item.id where list_item.list_id = ?");

            statement.setLong(1, listId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                GroceryItem groceryItem = new GroceryItem();

                groceryItem.setId(resultSet.getLong("id"));
                groceryItem.setName(resultSet.getString("name"));
                groceryItems.add(groceryItem);
            }
        } catch (SQLException exception) {
            System.out.println("При поиске товаров по идентификатору списка покупок возникла ошибка");
        }
        return groceryItems;
    }
}