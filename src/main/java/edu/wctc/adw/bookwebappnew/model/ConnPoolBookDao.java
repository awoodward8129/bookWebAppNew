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
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Alex
 */
public class ConnPoolBookDao implements DAOStrategy {

      private DataSource ds;
    private DbStrategy db;
    
      public ConnPoolBookDao(DataSource ds, DbStrategy db) {
        this.ds = ds;
        this.db = db;
    }
    
    @Override
    public List<Book> getAllBooks() throws Exception {
        db.openConnection(ds);
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
        db.openConnection(ds);
        db.deleteRecordById(tableName, column, value);
        db.closeConnection();
    }

    @Override
    public void insertRecord(String tableName, List values) throws Exception {
       db.openConnection(ds);
       
        List columns= new ArrayList();
               columns.add("title");
               columns.add("author");
               columns.add("page_count");
               columns.add("publish_date");
               
//                List values= new ArrayList();
               String sweetDate = (String)values.get(3);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
               Date date = new Date();
               date = sdf.parse(sweetDate);
               values.remove(3);
               values.add(date);
           
            db.insertRecord(tableName, columns, values);
            db.closeConnection();
    }

    @Override
    public void updateRecord(String tableName, List values, String whereField, Object whereValue) throws Exception {
          db.openConnection(ds);
            List columns= new ArrayList();
               columns.add("book_id");     
               columns.add("title");
               columns.add("author");
               columns.add("page_count");
               columns.add("publish_date");
               String sweetDate = (String)values.get(4);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               Date date = new Date();
               date = sdf.parse(sweetDate);
               values.remove(4);
               values.add(date);
           
            db.updateRecord(tableName, columns, values, whereField,whereValue);
            db.closeConnection();
    }
    
}
