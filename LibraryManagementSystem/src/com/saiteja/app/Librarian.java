package com.saiteja.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Librarian {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		try
		{
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			Library library = new Library();
			Scanner scanner = new Scanner(System.in);
			System.out.print("please enter your name : ");
			System.out.println("Hai "+scanner.nextLine()+", Welcome to the library");
			System.out.println();
			while(true) {
				System.out.println("press 1 to add book    || 2 to search book");
				System.out.println("press 3 to update book || 4 to delete book");
				System.out.println("              press 0 to exit             ");
				int num = scanner.nextInt();
				if(num==1) {
					for(int i = 1 ; i>0 ; i--) {
						System.out.println("you have "+i+" chances");
						preparedStatement = connection.prepareStatement(library.addBook());
						System.out.print("Enter the title of book : ");
						preparedStatement.setString(1, scanner.next());
						System.out.print("Enter the name of author : ");
						preparedStatement.setString(2, scanner.next());
						System.out.print("Enter the no of pages : ");
						preparedStatement.setInt(3, scanner.nextInt());
						System.out.print("Enter the edition of book : ");
						preparedStatement.setInt(4, scanner.nextInt());
						System.out.print("enter the price of book : ");
						preparedStatement.setDouble(5, scanner.nextDouble());
						preparedStatement.executeUpdate();
						System.out.println();
					}
				}
				else if(num==2)
				{
					for(int i =2 ; i>0;i--) {
						System.out.println("you have "+i+" chances");
						System.out.println();
						System.out.println("press 1 to search book by title");
						System.out.println("press 2 to search book by author");
						System.out.println("press 0 to go back");
						int n = scanner.nextInt();
						if(n==1)
						{
							preparedStatement = connection.prepareStatement(library.searchBookTitle());
							System.out.print("enter the title of book : ");
							preparedStatement.setString(1, scanner.next());
							
						}
						else if(n==2)
						{
							preparedStatement  = connection.prepareStatement(library.searchBookAuthor());
							System.out.print("enter the name of the author : ");
							preparedStatement.setString(1, scanner.next());
						}
						else if(n==0)
						{
							break;
						}
						else
						{
							System.err.println("Invalid option selected!!!");
							break;
						}
						resultSet = preparedStatement.executeQuery();
						boolean status = resultSet.next();
						String title = resultSet.getString("title");
						String author = resultSet.getString("author");
						int edition  = resultSet.getInt("edition");
						int pages = resultSet.getInt("pages");
						double price = resultSet.getDouble("price");
						System.out.println(title+" edition "+edition+" written by "+author+" with "+pages+" pages costs "+price);
						System.out.println();
					}
				}	
				else if(num==3)
				{
					for(int i = 3; i>0;i--) {
						System.out.println("you have "+i+" chances");
						System.out.println();
						System.out.print("enter 1 to update title||2 to update author||3 to update edition||4 to update pages"
								+ "||5 to update price of the book||0 to go back");
						System.out.println();
						int column = scanner.nextInt();
						preparedStatement = connection.prepareStatement(library.updateBook(column));
						if(column==1) {
							System.out.print("update the 'title'column with new value : ");
							preparedStatement.setString(1, scanner.next());
						}else if(column==2) {
							System.out.print("update the 'author' column with new value : ");
							preparedStatement.setString(1, scanner.next());
						}else if(column==3) {
							System.out.print("update the 'edition' column with new value : ");
							preparedStatement.setInt(1, scanner.nextInt());
						}else if(column==4) {
							System.out.print("update the 'pages' column with new value : ");
							preparedStatement.setInt(1, scanner.nextInt());
						}else if(column==5) {
							System.out.print("update the 'price' column with new value : ");
							preparedStatement.setDouble(1, scanner.nextDouble());
						}else if(column==0) {
							break;
						}else {
							System.err.println("Invalid choice");
							System.out.println();
						}
						System.out.print("enter the title of book : ");
						preparedStatement.setString(2, scanner.next());
						preparedStatement.executeUpdate();
						System.out.println();
					}
				}
				else if(num==4)
				{
					for(int i = 4;i>0;i--) {
						System.out.println("you have "+i+" chances");
						System.out.println();
						System.out.println("enter 1 to delete using title of book");
						System.out.println("enter 2 to delete using author of book");
						System.out.println("enter 0 to go back");
						int n = scanner.nextInt();
						if(n==1)
						{
							preparedStatement = connection.prepareStatement(library.deleteBookTitle());
							System.out.print("enter the title of book : ");
							preparedStatement.setString(1, scanner.next());
							preparedStatement.executeUpdate();
							System.out.println();
						}
						else if(n==2)
						{
							preparedStatement = connection.prepareStatement(library.deleteBookAuthor());
							System.out.print("enter the author name : ");
							preparedStatement.setString(1, scanner.next());
							preparedStatement.executeUpdate();
							System.out.println();
						}
						else if(n==0)
						{
							break;
						}
						else
						{
							System.err.println("Invalid input!!");
							System.out.println();
						}
					}
				}
				else if(num==0)
				{
					System.out.println("Thank you. Visit us again..");
					break;
				}
				else
				{
					System.err.println("Invalid choice...");
					System.out.println();
				}
			}
			connection.close();
			scanner.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
