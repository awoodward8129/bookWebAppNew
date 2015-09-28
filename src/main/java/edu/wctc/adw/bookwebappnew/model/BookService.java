/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.adw.bookwebappnew.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alex
 */
public class BookService {
    
    DAOStrategy dao;

    public BookService(DAOStrategy dao) {
        this.dao = dao;
    }
    
    public List<Book> getAllBooks() throws Exception{
    return dao.getAllBooks();
    }
    
    
    public void deleteByBookId(String tableName, String columnName, String value) throws Exception{
    dao.deleteRecordById( tableName, columnName, value);
    }
    
     public void insertRecord(String tableName, List columns, List values) throws Exception{
    dao.insertRecord(tableName, columns, values);
    }
    
     public void updateRecord(String tableName, List colDescriptors, List colValues, String whereField, Object whereValue) throws Exception{
     dao.updateRecord(tableName,colDescriptors, colValues, whereField, whereValue);
     }
    public static void main(String[] args) throws Exception { 
        BookService service = new BookService(new BookDAO(new MySqlDbStrategy(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book_db", "root", "admin"));
//       service.deleteByBookId("book", "book_id", "3");
//       service.insertRecord("book", "title,author,page_count, publish_date", "'JAVA is the BEST','Alejando Inaratu', 200, '2005-02-15'");
//       service.updateRecord("book ", "title='Update Works' ","book_id=1");
        for(Book b: service.getAllBooks()){
            System.out.println(b);
        }
        
        
         List<Book> books = service.getAllBooks();
       for(Book b :books){
           System.out.println(b);
       }
       
       
         List bookValues = new ArrayList();
         List colDescription = new ArrayList();
         
           System.out.println("-----------");
//          db.deleteRecordById("book", "book_id", "1");
           Book book = new Book();
         book.setTitle("New UPDATE!");
           book.setAuthor("UPDATE AUTHOR!");
            book.setPageCount(400);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            date = sdf.parse("21/12/2012");
            book.setPublishDate(date);
           colDescription.add("title");
            colDescription.add("author");
            colDescription.add("page_count");
            colDescription.add("publish_date");
        bookValues.add(book.getTitle());
        bookValues.add(book.getAuthor());
         bookValues.add(book.getPageCount());
        bookValues.add(book.getPublishDate());
        
          service.insertRecord("book", colDescription, bookValues);
//          service.updateRecord("book ", colDescription, bookValues, "book_id", 28);
      
       
          List<Book> books2 = service.getAllBooks();
       for(Book b :books2){
           System.out.println(b);
         }
    }
}
