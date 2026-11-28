package ru.mdkhoteev.repository;


import ru.mdkhoteev.entity.HotelEntity;
import ru.mdkhoteev.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHotelRepository implements HotelRepository {

    private final ConnectionFactory connectionFactory;

    public JdbcHotelRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public int save(HotelEntity entity) {
        String sql = "INSERT INTO hotel (name, stars) VALUES (?, ?) RETURNING id";
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getStars());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка сохранения отеля: " + entity, e);
        }
    }

    @Override
    public HotelEntity findById(int id) {
        String sql = "SELECT id, name, stars FROM hotel WHERE id = ?";
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка поиска отеля по id " + id, e);
        }
    }

    @Override
    public HotelEntity findByName(String name) {
        String sql = "SELECT id, name, stars FROM hotel WHERE name = ?";
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка поиска отеля по имени " + name, e);
        }
    }

    @Override
    public List<HotelEntity> findAll() {
        String sql = "SELECT id, name, stars FROM hotel ORDER BY id";
        List<HotelEntity> hotels = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                hotels.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка получения списка отелей", e);
        }
        return hotels;
    }

    @Override
    public boolean update(HotelEntity entity) {
        String sql = "UPDATE hotel SET name = ?, stars = ? WHERE id = ?";
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getStars());
            ps.setInt(3, entity.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка обновления отеля с id " + entity.getId(), e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM hotel WHERE id = ?";
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка удаления отеля с id " + id, e);
        }
    }

    private static HotelEntity map(ResultSet rs) throws SQLException {
        return new HotelEntity(rs.getInt("id"), rs.getString("name"), rs.getInt("stars"));
    }
}