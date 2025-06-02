package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Theme;
import com.example.web.dao.model.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemeDao {
    private Connection con = DbConnect.getConnection();

    public ThemeDao(){}

    public List<Theme> getAllTheme() throws SQLException {
        List<Theme> themes = new ArrayList<Theme>();
        String sql = "select * from themes";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("themeName");
            Theme theme = new Theme(id, name);
            themes.add(theme);
        }
        return themes;
    }

    public boolean addTheme(String themeName) throws SQLException {
        String sql = "INSERT INTO themes (themeName) VALUES (?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, themeName);
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;

    }
    public boolean updateTheme(int id, String  themeName) throws SQLException {
        String updateQuery = "UPDATE themes SET themeName = ? WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(updateQuery);
        statement.setString(1,themeName);
        statement.setInt(2, id);

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;


    }
    public Theme getThemeById(int id) throws SQLException {
        String sql = "select * from themes where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Theme theme = new Theme();
            theme.setId(rs.getInt("id"));
            theme.setThemeName(rs.getString("themeName"));
            return theme;
        }
        return null;
    }
    public boolean deleteTheme(int i) throws SQLException {
        String query = "delete from themes where id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, i);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static void main(String[] args) throws SQLException {
        ThemeDao dao = new ThemeDao();
        System.out.println(dao.getThemeById(2));
    }
}
