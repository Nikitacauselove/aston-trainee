package com.aston.trainee.repository;

import com.aston.trainee.util.ConnectionManager;
import com.aston.trainee.util.Expected;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroceryListRepositoryTest {
    @Mock
    private ConnectionManager connectionManager;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GroceryItemRepository groceryItemRepository;

    private GroceryListRepository groceryListRepository;

    @BeforeEach
    void beforeEach() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);

        groceryListRepository = new GroceryListRepository(connectionManager, authorRepository, groceryItemRepository);
    }

    @Test
    void create() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(preparedStatement.getGeneratedKeys().next()).thenReturn(true);
        when(preparedStatement.getGeneratedKeys().getLong(anyString())).thenReturn(1L);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        assertEquals(Expected.GROCERY_LIST, groceryListRepository.create(Expected.GROCERY_LIST));
    }

    @Test
    void read() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(authorRepository.readById(anyLong())).thenReturn(Expected.AUTHOR);
        when(groceryItemRepository.readByListId(anyLong())).thenReturn(List.of(Expected.GROCERY_ITEM));
        when(resultSet.getLong(anyString())).thenReturn(1L);

        assertEquals(List.of(Expected.GROCERY_LIST), groceryListRepository.read());
    }

    @Test
    void update() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        groceryListRepository.update(Expected.GROCERY_LIST);

        verify(preparedStatement).setLong(2, 1L);
        verify(preparedStatement, times(2)).executeUpdate();
    }

    @Test
    void delete() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        groceryListRepository.delete(1L);

        verify(preparedStatement).setLong(1, 1L);
        verify(preparedStatement).executeUpdate();
    }
}
