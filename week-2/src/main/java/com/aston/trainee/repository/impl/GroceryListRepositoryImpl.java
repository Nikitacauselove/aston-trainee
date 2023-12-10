package com.aston.trainee.repository.impl;

import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroceryListRepositoryImpl implements com.aston.trainee.repository.GroceryListRepository {
    private final static String INSERT_SQL = "insert into list (author_id) values (?)";
    private final static String SELECT_SQL = "select * from list";
    private final static String DELETE_SQL = "delete from list where id = ?";

    private final AuthorRepository authorRepositoryImpl;
    private final GroceryItemRepository groceryItemRepository;
    private final ConnectionManager connectionManager;

    public GroceryListRepositoryImpl() {
        this.connectionManager = new ConnectionManager();
        this.authorRepositoryImpl = new AuthorRepositoryImpl();
        this.groceryItemRepository = new GroceryItemRepositoryImpl();
    }

    public GroceryListRepositoryImpl(ConnectionManager connectionManager, AuthorRepository authorRepository, GroceryItemRepository groceryItemRepository) {
        this.connectionManager = connectionManager;
        this.authorRepositoryImpl = authorRepository;
        this.groceryItemRepository = groceryItemRepository;
    }

    public GroceryList create(GroceryList groceryList) {
        try (Connection connection = connectionManager.getConnection()) {
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
            System.out.println("При добавлении нового списка покупок возникла ошибка");
        }
        return groceryList;
    }

    public List<GroceryList> read() {
        List<GroceryList> groceryLists = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                GroceryList groceryList = new GroceryList();
                Author author = authorRepositoryImpl.readById(resultSet.getLong("author_id"));
                List<GroceryItem> items = groceryItemRepository.readByListId(resultSet.getLong("id"));

                groceryList.setId(resultSet.getLong("id"));
                groceryList.setAuthor(author);
                groceryList.setItems(items);
                groceryLists.add(groceryList);
            }
        } catch (SQLException exception) {
            System.out.println("При поиске списков покупок возникла ошибка");
        }
        return groceryLists;
    }

    public GroceryList update(GroceryList groceryList) {
        try (Connection connection = connectionManager.getConnection()) {
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
            System.out.println("При изменении информации о списке покупок возникла ошибка");
        }
        return groceryList;
    }

    public void delete(Long id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("При удалении списка покупок возникла ошибка");
        }
    }
}
