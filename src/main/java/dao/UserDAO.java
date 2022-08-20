package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO {
	private static final String URL = PropertiesUtil.getProperties("URL");
	private static final String NAME = PropertiesUtil.getProperties("NAME");
	private static final String PASSWORD = PropertiesUtil.getProperties("PASSWORD");
	public static Connection connection;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void create(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, surname) VALUES (?,?)");
		statement.setString(1, user.getName());
		statement.setString(2, user.getSurname());
		statement.execute();
	}
	
	public static List<User> allUsers() throws SQLException{
		List<User> list = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setName(resultSet.getString("name"));
			user.setSurname(resultSet.getString("surname"));
			list.add(user);
		}
		
		return list;		
	}
	
	public static User getUser(int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		User user = new User();
		
		while(resultSet.next()) {
			user.setId(resultSet.getInt("id"));
			user.setName(resultSet.getString("name"));
			user.setSurname(resultSet.getString("surname"));
		}
		return user;
	}
	
	public static void updateUser(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?, surname=? WHERE id=?");
		statement.setString(1, user.getName());
		statement.setString(2, user.getSurname());
		statement.setInt(3, user.getId());
		statement.execute();
	}
	
	public static void deleteUser(int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
		statement.setInt(1, id);
		statement.execute();
	}
}



















