package com.aston.trainee.repository.impl;

import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.util.ConnectionPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GroceryItemRepositoryImpl implements GroceryItemRepository {
    private final static String INSERT_SQL = "insert into item (name) values (?)";
    private final static String SELECT_BY_ID_SQL = "select * from item where id = ?";
    private final static String SELECT_SQL = "select * from item";
    private final static String UPDATE_SQL = "update item set name = ? where id = ?";
    private final static String DELETE_SQL = "delete from item where id = ?";

    private final ConnectionPool connectionPool;

    public GroceryItemRepositoryImpl() {
        this.connectionPool = new ConnectionPool();
    }

    public GroceryItemRepositoryImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public GroceryItem create(GroceryItem groceryItem) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, groceryItem.getName());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                groceryItem.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException exception) {
            log.error("При добавлении нового товара возникла ошибка");
        }
        return readById(groceryItem.getId());
    }

    public GroceryItem readById(Long id) {
        GroceryItem groceryItem = new GroceryItem();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                groceryItem.setId(resultSet.getLong("id"));
                groceryItem.setName(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            log.error("При получении подробной информации о товаре возникла ошибка");
        }
        return groceryItem;
    }

    public List<GroceryItem> read() {
        List<GroceryItem> groceryItems = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                GroceryItem groceryItem = new GroceryItem();

                groceryItem.setId(resultSet.getLong("id"));
                groceryItem.setName(resultSet.getString("name"));
                groceryItems.add(groceryItem);
            }
        } catch (SQLException exception) {
            log.error("При поиске товаров возникла ошибка");
        }
        return groceryItems;
    }

    public GroceryItem update(GroceryItem updatedGroceryItem) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);

            statement.setString(1, updatedGroceryItem.getName());
            statement.setLong(2, updatedGroceryItem.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("При изменении информации о товаре возникла ошибка");
        }
        return readById(updatedGroceryItem.getId());
    }

    public void delete(Long id) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("При удалении товара возникла ошибка");
        }
    }

    public GroceryItem readByName(String name) {
        GroceryItem groceryItem = new GroceryItem();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from item where name = ?");

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                groceryItem.setId(resultSet.getLong("id"));
                groceryItem.setName(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            log.error("При получении подробной информации о товаре по его названию возникла ошибка");
        }
        return groceryItem;
    }

    public List<GroceryItem> readByListId(Long listId) {
        List<GroceryItem> groceryItems = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
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
            log.error("При поиске товаров по идентификатору списка покупок возникла ошибка");
        }
        return groceryItems;
    }
}
