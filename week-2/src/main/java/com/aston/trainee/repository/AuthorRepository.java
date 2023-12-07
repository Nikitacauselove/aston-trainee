package com.aston.trainee.repository;

import com.aston.trainee.entity.Author;
import com.aston.trainee.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements BaseRepository<Author> {
    private final static String INSERT_SQL = "insert into author (name) values (?)";
    private final static String SELECT_SQL = "select * from author";
    private final static String UPDATE_SQL = "update author set name = ? where id = ?";
    private final static String DELETE_SQL = "delete from author where id = ?";

    private final ConnectionManager connectionManager = new ConnectionManager();

    public Author create(Author author) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, author.getName());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException exception) {
            System.out.println("При добавлении нового автора возникла ошибка");
        }
        return author;
    }

    public List<Author> read() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                Author author = new Author();

                author.setId(resultSet.getLong("id"));
                author.setName(resultSet.getString("name"));
                authors.add(author);
            }
        } catch (SQLException exception) {
            System.out.println("При поиске авторов возникла ошибка");
        }
        return authors;
    }

    public Author update(Author updatedAuthor) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);

            statement.setString(1, updatedAuthor.getName());
            statement.setLong(2, updatedAuthor.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("При изменении информации об авторе возникла ошибка");
        }
        return updatedAuthor;
    }

    public void delete(Long id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("При удалении автора возникла ошибка");
        }
    }

    public Author readById(Long id) {
        Author author = new Author();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from author where id = ?");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                author.setId(resultSet.getLong("id"));
                author.setName(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            System.out.println("При получении подробной информации об авторе возникла ошибка");
        }
        return author;
    }
}
