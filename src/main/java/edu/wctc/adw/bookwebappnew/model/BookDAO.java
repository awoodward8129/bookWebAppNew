/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.adw.bookwebappnew.model;

import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex
 */
public class BookDAO implements DAOStrategy {
   
    private DbStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public BookDAO(DbStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    BookDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Book> getAllBooks() throws Exception{
    db.openConnection(this.driverClass, this.url, this.userName, this.password);
    List<Book> records = new ArrayList<>();
    List<Map<String,Object>> rawData = db.findAllRecords("book");
    for(Map rawRec: rawData){
    Book book = new Book();
    
    Object obj = rawRec.get("book_id");
    Integer i = Integer.parseInt(obj.toString());
    book.setBookId(i);
    
    obj = rawRec.get("title");
    String t = obj.toString();
    book.setTitle(t);
    
    obj = rawRec.get("author");
    String a = obj.toString();
    book.setAuthor(a);
    
    obj = rawRec.get("page_count");
    Integer p = Integer.parseInt(obj.toString());
    book.setPageCount(p);
    
    obj = rawRec.get("publish_date");
    Date d= (Date) obj;
    book.setPublishDate(d);
    
    records.add(book);
    }
    
    db.closeConnection();
    return records;
    }
 

    @Override
    public void deleteRecordById(String tableName, String column, Object value) throws Exception {
            db.openConnection(this.driverClass, this.url, this.userName, this.password);
            db.deleteRecordById(tableName, column, value);
            db.closeConnection();
    }
    
       @Override
    public void insertRecord(String tableName, List columns, List value) throws Exception {
            db.openConnection(this.driverClass, this.url, this.userName, this.password);
            db.insertRecord(tableName, columns, value);
            db.closeConnection();
    }
    
    @Override
    public void updateRecord(String tableName, List colDescriptors, List colValues, String whereField, Object whereValue) throws Exception {
           db.openConnection(this.driverClass, this.url, this.userName, this.password);
            db.updateRecord(tableName, colDescriptors, colValues, whereField,whereValue);
            db.closeConnection();
    }
    
    public static void main(String[] args) throws Exception {
        
        BookDAO dao = new BookDAO(new MySqlDbStrategy(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book_db", "root", "admin");
        
   
//       dao.deleteByBookId("book", "book_id", "3");
//        dao.insertRecord("book", "title,author,page_count, publish_date", "'JAVA is the BEST','Alejando Inaratu', 200, '2005-02-15'");
//        dao.updateRecord("book ", "title='Update Works' ","book_id=1");
           List<Book> books = dao.getAllBooks();
       for(Book b :books){
           System.out.println(b);
       }
       
       
         List bookValues = new ArrayList();
          List colDescription = new ArrayList();
         
           System.out.println("-----------");
//          db.deleteRecordById("book", "book_id", "1");
           Book book = new Book();
         book.setTitle("New SICK TITLE!");
           book.setAuthor("Kick ASS AUTHOR!");
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
        
//          dao.insertRecord("book", colDescription, bookValues);
          dao.updateRecord("book ", colDescription, bookValues, "book_id", 28);
      
       
          List<Book> books2 = dao.getAllBooks();
       for(Book b :books2){
           System.out.println(b);
       }
    }

    

 
}
