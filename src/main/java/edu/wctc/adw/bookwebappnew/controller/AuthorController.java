package edu.wctc.adw.bookwebappnew.controller;

import edu.wctc.adw.bookwebappnew.entity.Author;
import edu.wctc.adw.bookwebappnew.entity.Book;

import edu.wctc.adw.bookwebappnew.service.AuthorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * The main controller for author-related activities
 *
 * @author jlombardo
 * 
 * String className = getServletContext(). getInitParameter("dbStrategy");
 * Class dbClass = Class.forName(ClassName);
 * DBStrategy db (DBStrategy) dbClass.newInstance();
 */
//@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class AuthorController extends HttpServlet {

    // NO MAGIC NUMBERS!

    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/listAuthor.jsp";
    private static final String ADD_PAGE = "/addAuthor.jsp";
     private static final String EDIT_DELETE_PAGE = "/editDeleteAuthor.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_BUTTON = "addButton";
    private static final String EDIT_DELETE_BUTTON = "editDeleteButton";
      private static final String ADD_ACTION = "add";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
    private static final String ACTION_REDIRECT = "redirect";
    
    


    private String destination;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter(ACTION_PARAM);
         Author author = null;
          // Get init params from web.xml
      ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        AuthorService authorService = (AuthorService) ctx.getBean("authorService");
 
   
        try {
  
            if (action.equals(LIST_ACTION)  ) {
                
                
      getListOfAuthorsWithListPageDestination(request, authorService);
              

            } else
                
                if (action.equals(ADD_BUTTON)) {
               destination = ADD_PAGE;
            } else if(action.equals(ADD_ACTION)){
                
                 author = new Author(0);
              author.setName(request.getParameter("name"));
         
               
                     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            date = sdf.parse(request.getParameter("dateAdded"));
            
              author.setDateAdded(date);
              authorService.edit(author);
             
           
           getListOfAuthorsWithListPageDestination(request, authorService);
           
            }
            else if(action.equals(EDIT_DELETE_BUTTON)){
              String authorId =  request.getParameter("authorId");
             
               author = authorService.findByIdAndFetchBooksEagerly(authorId);
                request.setAttribute("author", author);
              
               if(author == null){
               author = authorService.findById(authorId);
//               Book book = new Book(0);
//               book.setAuthorId(author);
//               book.setTitle("none");
//               book.setPageCount(400);
//               book.setPublishDate(new Date());
//               book.setAuthorId(author);
//               Set<Book> bookSet = new HashSet<>();
//               bookSet.add(book);
//               
//               author.setBookSet(bookSet);
                request.setAttribute("author", author);
               
               }
               destination = EDIT_DELETE_PAGE;
             
               }

            else if (action.equals(DELETE_ACTION)) {
             String authorId =  request.getParameter("authorId");
             author = authorService.findByIdAndFetchBooksEagerly(authorId);
                        if(author == null){
               author = authorService.findById(authorId);
                        }
               
              String submitType =request.getParameter("submit");
                    if(submitType.equals("delete")){
                        
                   authorService.remove(author);
                      getListOfAuthorsWithListPageDestination(request, authorService);
                    }else if (submitType.equals("update")){
                        
                        
                     String authorName = request.getParameter("name");
                      String dateAdded = request.getParameter("dateAdded");
                  
                      Date date = new Date(dateAdded);
                   author.setName(authorName);
                   author.setDateAdded(date);
               
                        
                    authorService.edit(author);
                     getListOfAuthorsWithListPageDestination(request, authorService);
                    }

            }
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }

        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
    

    private void getListOfAuthorsWithListPageDestination(HttpServletRequest request, AuthorService as) throws Exception{
    
          List<Author> authors = as.findAll();
                request.setAttribute("authors", authors);
                destination = LIST_PAGE;
    }

//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
//
//      @Override
//    public void init() throws ServletException {
//        // Get init params from web.xml
//        //I'll use getServletContext().get
//   
//
//        // You can't do the Java Reflection stuff here because exceptions
//        // are thrown that can't be handled by this stock init() method
//        // because the method signature can't be modified -- remember, you 
//        // are overriding the method.
//    }
}
