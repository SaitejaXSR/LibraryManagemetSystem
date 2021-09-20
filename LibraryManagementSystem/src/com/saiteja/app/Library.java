package com.saiteja.app;

public class Library {
	
	public String addBook()
	{
		return "insert into tejm20.librarybook values(?,?,?,?,?)";
	}
	public String searchBookTitle()
	{
		return "select * from tejm20.librarybook where title = ?";
	}
	public String searchBookAuthor()
	{
		return "select * from tejm20.librarybook where author = ?";
	}
	public String updateBook(int column)
	{
		if(column==1) {
			return "update tejm20.librarybook set title = ? where title = ? ";
		}else if(column==2) {
			return "update tejm20.librarybook set author = ? where title = ? ";
		}else if(column==3) {
			return "update tejm20.librarybook set edition = ? where title = ? ";
		}else if(column==4) {
			return "update tejm20.librarybook set pages = ? where title = ? ";
		}else if(column==5) {
			return "update tejm20.librarybook set price = ? where title = ? ";
		}else {
			return null;
		}
	}
	public String deleteBookTitle()
	{
		return "delete from tejm20.librarybook where title = ?";
	}
	public String deleteBookAuthor()
	{
		return "delete from tejm20.librarybook where author = ?";
	}

}
