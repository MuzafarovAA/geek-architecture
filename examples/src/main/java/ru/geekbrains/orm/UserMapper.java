package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection conn;

    private final PreparedStatement selectUser;
    private final PreparedStatement updateUser;
    private final PreparedStatement insertUser;
    private final PreparedStatement deleteUser;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection conn) {
        this.conn = conn;
        try {
            this.selectUser = conn.prepareStatement("select id, username, password from users where id = ?");
            this.updateUser = conn.prepareStatement("update users set username = ?, password = ? where id = ?");
            this.insertUser = conn.prepareStatement("insert into users (username, password) values (?, ?)");
            this.deleteUser = conn.prepareStatement("delete from users where id = ?");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(Long userId) {
        User user = identityMap.get(userId);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, userId);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                identityMap.put(user.getId(), user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public Optional<User> update(User user) {
        Long userId = user.getId();
        if (userId == null) {
            throw new IllegalArgumentException("User ID required");
        }
        if (identityMap.get(userId) == null) {
            throw new IllegalStateException("No such user exists with id " + userId);
        }
        try {
            updateUser.setString(1, user.getLogin());
            updateUser.setString(2, user.getPassword());
            updateUser.setLong(3, userId);
            ResultSet rs = updateUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                identityMap.put(userId, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Optional<User> insert(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User can not be with id");
        }
        try {
            insertUser.setString(1, user.getLogin());
            insertUser.setString(2, user.getPassword());
            ResultSet rs = insertUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                identityMap.put(user.getId(), user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void delete(User user) {
        Long userId = user.getId();
        if (identityMap.get(userId) == null) {
            throw new IllegalStateException("No such user exists with id " + userId);
        }
        try {
            deleteUser.setLong(1, userId);
            deleteUser.executeQuery();
            identityMap.remove(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

