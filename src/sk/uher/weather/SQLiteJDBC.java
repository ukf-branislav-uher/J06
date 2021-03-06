package sk.uher.weather;

import java.sql.*;

public class SQLiteJDBC {

    public SQLiteJDBC() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = getConnection();
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS city" +
                    " (id  INTEGER    NOT NULL, " +
                    " name  TEXT    NOT NULL)";
            stmt.executeUpdate(createTable);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("Table created successfully");
    }

    public Connection getConnection() {
        String url = "jdbc:sqlite:cities.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


//    public void insertCity(long id, String name, String state, String country, double lon, double lat) {
//        try {
//            PreparedStatement preparedStatement;
//            Connection connection = this.getConnection();
//            String sqlInsert = "INSERT INTO city (id, name, state, country, lon, lat)" +
//                    "VALUES (?,?,?,?,?,?)";
//            preparedStatement = connection.prepareStatement(sqlInsert);
//            preparedStatement.setLong(1, id);
//            preparedStatement.setString(2, name);
//            preparedStatement.setString(3, state);
//            preparedStatement.setString(4, country);
//            preparedStatement.setDouble(5, lon);
//            preparedStatement.setDouble(6, lat);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }

    public ResultSet getResultSet() throws SQLException {
        String query = "SELECT * FROM city";
        ResultSet resultSet = this.getConnection().createStatement().executeQuery(query);
        return resultSet;
    }

    public ResultSet getCity(String cityName) throws SQLException {
        String query = "SELECT * FROM city WHERE name = '" + cityName + "'";
        ResultSet resultSet = this.getConnection().createStatement().executeQuery(query);
        return resultSet;
    }

}
