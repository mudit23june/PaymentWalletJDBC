package com.capgemini.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
//import java.util.LinkedList;
//import java.util.ListIterator;

import com.capgemini.beans.Customer;
//import com.capgemini.exception.PhoneNoDoesNotExist;

public class WalletRepoImpl implements WalletRepo {
	
	@Override
	public boolean save(Customer customer) {
		
		String name = customer.getName();
		String mobile = customer.getMobileNumber();
		long amount = customer.getWallet();
		try{  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Capgemini1234");  

			
			PreparedStatement stmt=con.prepareStatement("insert into paywallet values(?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, mobile);
			stmt.setLong(3, amount);
			stmt.executeQuery();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return true;  
		
	}
	public Customer findOne(String mobilenumber)
	{
		Customer c = new Customer();
		try{  
		
			Class.forName("oracle.jdbc.driver.OracleDriver");  

		
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Capgemini1234");  

		
			PreparedStatement stmt=con.prepareStatement("select * from paywallet where mobile=?");
			stmt.setString(1, mobilenumber);
			ResultSet rs=stmt.executeQuery();  
			while(rs.next())
			{
			c.setName(rs.getString(1));
			c.setMobileNumber(rs.getString(2));
			c.setWallet(rs.getInt(3));
			}
			con.close();
			return c;
			}
		catch(Exception e)
		{
			System.out.println(e);
		}  
		return null;
	}
}
