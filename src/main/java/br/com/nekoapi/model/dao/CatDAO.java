/*
 * @author Marina Yumi Kanadani - RM 558404 - 1TDSPX
 */

package br.com.nekoapi.model.dao;

import br.com.nekoapi.model.vo.CatVO;
import br.com.nekoapi.connection.CatConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatDAO {

    private final CatConnection catConnection = new CatConnection();

    public boolean create(CatVO cat) {
        String sql = "INSERT INTO cats (id, name, description, temperament, origin, lifespan, image_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = catConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cat.getId());
            stmt.setString(2, cat.getName());
            stmt.setString(3, cat.getDescription());
            stmt.setString(4, cat.getTemperament());
            stmt.setString(5, cat.getOrigin());
            stmt.setString(6, cat.getLifeSpan());
            stmt.setString(7, cat.getImageUrl());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CatVO> findAll() {
        List<CatVO> cats = new ArrayList<>();
        String sql = "SELECT * FROM cats";
        try (Connection conn = catConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
            	CatVO cat = new CatVO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("temperament"),
                        rs.getString("origin"),
                        rs.getString("lifespan"),
                        rs.getString("image_url")
                );
                cats.add(cat);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cats;
    }

    public CatVO findById(String id) {
        String sql = "SELECT * FROM cats WHERE id = ?";
        try (Connection conn = catConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CatVO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("temperament"),
                        rs.getString("origin"),
                        rs.getString("lifespan"),
                        rs.getString("image_url")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(CatVO cat) {
        String sql = "UPDATE cats SET name = ?, description = ?, temperament = ?, origin = ?, lifespan = ?, image_url = ? WHERE id = ?";
        try (Connection conn = catConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cat.getName());
            stmt.setString(2, cat.getDescription());
            stmt.setString(3, cat.getTemperament());
            stmt.setString(4, cat.getOrigin());
            stmt.setString(5, cat.getLifeSpan());
            stmt.setString(6, cat.getImageUrl());
            stmt.setString(7, cat.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM cats WHERE id = ?";
        try (Connection conn = catConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

