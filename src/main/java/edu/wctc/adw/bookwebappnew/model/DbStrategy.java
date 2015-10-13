/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.adw.bookwebappnew.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Alex
 */
public interface DbStrategy {

   void closeConnection() throws SQLException;
   List<Map<String, Object>> findAllRecords(String tableName) throws SQLException;
   void openConnection(String driverClass, String url, String userName, String password) throws Exception;
   void openConnection(DataSource ds) throws Exception;
   void deleteRecordById(String tableName, String column, Object value) throws Exception;
   void insertRecord(String tableName, List columns, List value)throws Exception;
   void updateRecord(String tableName, List colDescriptors, List colValues, String whereField, Object whereValue)throws Exception;
    
}
