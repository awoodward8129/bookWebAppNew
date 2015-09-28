/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.adw.bookwebappnew.model;

import java.util.List;

/**
 *
 * @author Alex
 */
public interface DAOStrategy {

    List<Book> getAllBooks() throws Exception;
 void deleteRecordById(String tableName, String column, Object value) throws Exception;
   void insertRecord(String tableName, List columns, List value)throws Exception;
   void updateRecord(String tableName, List colDescriptors, List colValues, String whereField, Object whereValue)throws Exception;
    
}
