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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorRepositoryTest {
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

    private AuthorRepository authorRepository;

    @BeforeEach
    void beforeEach() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);

        authorRepository = new AuthorRepository(connectionManager);
    }

    @Test
    void create() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(preparedStatement.getGeneratedKeys().next()).thenReturn(true);
        when(preparedStatement.getGeneratedKeys().getLong(anyString())).thenReturn(1L);

        assertEquals(Expected.AUTHOR, authorRepository.create(Expected.AUTHOR));
    }

    @Test
    void read() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(anyString())).thenReturn(1L);
        when(resultSet.getString(anyString())).thenReturn("Callan");

        assertEquals(List.of(Expected.AUTHOR), authorRepository.read());
    }

    @Test
    void update() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        authorRepository.update(Expected.AUTHOR);

        verify(preparedStatement).setString(1, "Callan");
        verify(preparedStatement).setLong(2, 1L);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void delete() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        authorRepository.delete(1L);

        verify(preparedStatement).setLong(1, 1L);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void readById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(anyString())).thenReturn(1L);
        when(resultSet.getString(anyString())).thenReturn("Callan");

        assertEquals(Expected.AUTHOR, authorRepository.readById(1L));
    }
}
