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

    private final Map<Integer, User> identityMap = new HashMap<>();

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

    public Optional<User> findById(int id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setInt(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(user.getId(), user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public void update(User user) {
        if (identityMap.get(user.getId()) == null) {
            throw new IllegalStateException("No such user exists with id " + user.getId());
        }
        try {
            updateUser.setString(1, user.getLogin());
            updateUser.setString(2, user.getPassword());
            updateUser.setInt(3, user.getId());
            ResultSet rs = updateUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(user.getId(), user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(User user) {
        if (identityMap.get(user.getId()) == null) {
            throw new IllegalStateException("No such user exists with id " + user.getId());
        }
        try {
            insertUser.setString(1, user.getLogin());
            insertUser.setString(2, user.getPassword());
            ResultSet rs = insertUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(user.getId(), user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) {
        if (identityMap.get(user.getId()) == null) {
            throw new IllegalStateException("No such user exists with id " + user.getId());
        }
        try {
            deleteUser.setInt(1, user.getId());
            deleteUser.executeQuery();
            identityMap.remove(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

