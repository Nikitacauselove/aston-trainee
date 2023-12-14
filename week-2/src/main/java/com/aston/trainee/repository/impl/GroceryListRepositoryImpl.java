package com.aston.trainee.repository.impl;

import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.repository.GroceryListRepository;
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
public class GroceryListRepositoryImpl implements GroceryListRepository {
    private final static String INSERT_SQL = "insert into list (author_id) values (?)";
    private final static String SELECT_BY_ID_SQL = "select * from list where id = ?";
    private final static String SELECT_SQL = "select * from list";
    private final static String DELETE_SQL = "delete from list where id = ?";

    private final AuthorRepository authorRepositoryImpl;
    private final GroceryItemRepository groceryItemRepository;
    private final ConnectionPool connectionPool;

    public GroceryListRepositoryImpl() {
        this.connectionPool = new ConnectionPool();
        this.authorRepositoryImpl = new AuthorRepositoryImpl();
        this.groceryItemRepository = new GroceryItemRepositoryImpl();
    }

    public GroceryListRepositoryImpl(ConnectionPool connectionPool, AuthorRepository authorRepository, GroceryItemRepository groceryItemRepository) {
        this.connectionPool = connectionPool;
        this.authorRepositoryImpl = authorRepository;
        this.groceryItemRepository = groceryItemRepository;
    }

    public GroceryList create(GroceryList groceryList) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, groceryList.getAuthor().getId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                groceryList.setId(generatedKeys.getLong("id"));
                PreparedStatement relationshipStatement = connection.prepareStatement("insert into list_item (list_id, item_id) values (?, ?)");

                for (GroceryItem groceryItem : groceryList.getItems()) {
                    relationshipStatement.setLong(1, groceryList.getId());
                    relationshipStatement.setLong(2, groceryItem.getId());
                    relationshipStatement.executeUpdate();
                }
            }
        } catch (SQLException exception) {
            log.error("При добавлении нового списка покупок возникла ошибка");
        }
        return readById(groceryList.getId());
    }

    public GroceryList readById(Long id) {
        GroceryList groceryList = new GroceryList();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Author author = authorRepositoryImpl.readById(resultSet.getLong("author_id"));
                List<GroceryItem> items = groceryItemRepository.readByListId(resultSet.getLong("id"));

                groceryList.setId(resultSet.getLong("id"));
                groceryList.setAuthor(author);
                groceryList.setItems(items);
            }
        } catch (SQLException exception) {
            log.error("При получении подробной информации о списке покупок возникла ошибка");
        }
        return groceryList;
    }

    public List<GroceryList> read() {
        List<GroceryList> groceryLists = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                groceryLists.add(readById(resultSet.getLong("id")));
            }
        } catch (SQLException exception) {
            log.error("При поиске списков покупок возникла ошибка");
        }
        return groceryLists;
    }

    public GroceryList update(GroceryList groceryList) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement deleteRelationshipStatement = connection.prepareStatement("delete from list_item where list_id = ?");
            PreparedStatement relationshipStatement = connection.prepareStatement("insert into list_item (list_id, item_id) values (?, ?)");

            deleteRelationshipStatement.setLong(1, groceryList.getId());
            deleteRelationshipStatement.executeUpdate();

            for (GroceryItem groceryItem : groceryList.getItems()) {
                relationshipStatement.setLong(1, groceryList.getId());
                relationshipStatement.setLong(2, groceryItem.getId());
                relationshipStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            log.error("При изменении информации о списке покупок возникла ошибка");
        }
        return readById(groceryList.getId());
    }

    public void delete(Long id) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("При удалении списка покупок возникла ошибка");
        }
    }
}
