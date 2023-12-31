package com.aston.trainee.repository.impl;

import com.aston.trainee.entity.Author;
import com.aston.trainee.repository.AuthorRepository;
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
public class AuthorRepositoryImpl implements AuthorRepository {
    private final static String INSERT_SQL = "insert into author (name) values (?)";
    private final static String SELECT_BY_ID_SQL = "select * from author where id = ?";
    private final static String SELECT_SQL = "select * from author";
    private final static String UPDATE_SQL = "update author set name = ? where id = ?";
    private final static String DELETE_SQL = "delete from author where id = ?";

    private final ConnectionPool connectionPool;

    public AuthorRepositoryImpl() {
        this.connectionPool = new ConnectionPool();
    }

    public AuthorRepositoryImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Author create(Author author) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, author.getName());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException exception) {
            log.error("При добавлении нового автора возникла ошибка");
        }
        return readById(author.getId());
    }

    public Author readById(Long id) {
        Author author = new Author();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                author.setId(resultSet.getLong("id"));
                author.setName(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            log.error("При получении подробной информации об авторе возникла ошибка");
        }
        return author;
    }

    public List<Author> read() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                Author author = new Author();

                author.setId(resultSet.getLong("id"));
                author.setName(resultSet.getString("name"));
                authors.add(author);
            }
        } catch (SQLException exception) {
            log.error("При поиске авторов возникла ошибка");
        }
        return authors;
    }

    public Author update(Author updatedAuthor) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);

            statement.setString(1, updatedAuthor.getName());
            statement.setLong(2, updatedAuthor.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("При изменении информации об авторе возникла ошибка");
        }
        return readById(updatedAuthor.getId());
    }

    public void delete(Long id) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("При удалении автора возникла ошибка");
        }
    }
}
