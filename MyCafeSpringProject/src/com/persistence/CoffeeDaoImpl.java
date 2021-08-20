package com.persistence;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bean.Addon;
import com.bean.Coffee;
import com.helper.MySqlConnection;



public class CoffeeDaoImpl implements CoffeeDao {


	@Override
	public int getCoffeeById(int coffeeId) throws ClassNotFoundException, SQLException, IOException, IOException {
		
		Connection connection = MySqlConnection.getConnection();
		int coffee = 0;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT coffee_price FROM coffee_size where coffee_id=?");
		preparedStatement.setInt(1, coffeeId);
		
		
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			coffee=(resultSet.getInt("coffee_price"));
			}
		return coffee ;
	}
	
	@Override
	public String getCoffeeNameById(int coffeeId) throws ClassNotFoundException, SQLException, IOException, IOException {
		
		Connection connection = MySqlConnection.getConnection();
		String coffee = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT coffee_name FROM coffee where coffee_id=?");
		preparedStatement.setInt(1, coffeeId);
		
		
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			coffee=(resultSet.getString("coffee_name"));
			}
		return coffee ;
	}
	
	@Override
	public String getCoffeeSizeById(int coffeeId) throws ClassNotFoundException, SQLException, IOException, IOException {
		
		Connection connection = MySqlConnection.getConnection();
		String coffee = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT coffee_size FROM coffee_size where coffee_id=?");
		preparedStatement.setInt(1, coffeeId);
		
		
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			coffee=(resultSet.getString("coffee_size"));
			}
		return coffee ;
	}

	@Override
	public List<Integer> getcoffeePriceList(List<Integer> coffeelist) throws ClassNotFoundException, SQLException, IOException, IOException {
		
		Connection connection = MySqlConnection.getConnection();
		List<Integer> coffeePriceList = new ArrayList<>();
		for(int c: coffeelist) {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT price FROM coffee where coffee_id=?");
		preparedStatement.setInt(1, c);
		
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			coffeePriceList.add(resultSet.getInt("addon_price"));
			}
		}
		return coffeePriceList ;
	}

	
	@Override
	public Collection<Coffee> getAllCoffeeDetails() throws ClassNotFoundException, SQLException, IOException {
		
		Connection connection = MySqlConnection.getConnection();
		Collection<Coffee> coffees = new ArrayList<Coffee>();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM coffee");
		
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Coffee coffee = new Coffee();
			coffee.setCoffeeId(resultSet.getInt("coffee_id"));
			coffee.setCoffeeName(resultSet.getString("coffee_name"));
			coffees.add(coffee);
		}
		return coffees;
		
	}
	
	@Override
	public boolean ifCoffeeIdValid(int coffeeChoice)throws ClassNotFoundException, SQLException, IOException, IOException {
		Connection connection = MySqlConnection.getConnection();;
		PreparedStatement preparedStatement = connection.prepareStatement("select count(coffee_id) from coffee where coffee_id = ?");
		
		preparedStatement.setInt(1, coffeeChoice);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			int count = resultSet.getInt(1);
			if(count != 0)
			return true;
		}
					
		return false;

	}
	@Override
	public Collection<Coffee> getCoffeeSize() throws ClassNotFoundException, SQLException, IOException {
		
		Connection connection = MySqlConnection.getConnection();
		Collection<Coffee> coffeeSize = new ArrayList<Coffee>();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM coffee_size");
		
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Coffee coffee = new Coffee();
			coffee.setCoffeeId(resultSet.getInt("coffee_id"));
			coffee.setSize(resultSet.getString("coffee_size"));
			coffee.setPrice(resultSet.getInt("coffee_price"));
			coffeeSize.add(coffee);
		}
		return coffeeSize;
		
	}
	
	@Override
	public int ifCoffeeSizeValid()throws ClassNotFoundException, SQLException, IOException, IOException {
		Connection connection = MySqlConnection.getConnection();;
		PreparedStatement preparedStatement = connection.prepareStatement("select count(distinct coffee_size) from coffee_size");
		
		ResultSet resultSet = preparedStatement.executeQuery();
		int coffeeCount= 0;
		if (resultSet.next()) {
			coffeeCount = resultSet.getInt(1);
			}
		
		return coffeeCount;

	}

	@Override
	public Collection<Coffee> getCoffeeSizeName()throws ClassNotFoundException, SQLException, IOException, IOException {
		Connection connection = MySqlConnection.getConnection();
		Collection<Coffee> coffeeSize = new ArrayList<Coffee>();
		PreparedStatement preparedStatement = connection.prepareStatement("select distinct coffee_size from coffee_size");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Coffee coffee = new Coffee();
			coffee.setSize(resultSet.getString("coffee_size"));
			coffeeSize.add(coffee);
		}
		return coffeeSize;
		
	}

		
		
}
