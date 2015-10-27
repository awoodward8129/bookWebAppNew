package edu.wctc.adw.bookwebappnew.controller;

import edu.wctc.adw.bookwebappnew.entity.Author;
import edu.wctc.adw.bookwebappnew.entity.Book;
import edu.wctc.adw.bookwebappnew.service.AbstractFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     private static final String EDIT_DELETE_PAGE = "/editDeleteBook.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_BUTTON = "addButton";
    private static final String EDIT_DELETE_BUTTON = "editDeleteButton";
      private static final String ADD_ACTION = "add";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
    private static final String ACTION_REDIRECT = "redirect";
    
    
        // Get init params from web.xml
    @Inject
   private AbstractFacade<Author> authorService;

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
              authorService.create(author);
             
           
           getListOfAuthorsWithListPageDestination(request, authorService);
           
            }
       

//          
//            else if(action.equals(EDIT_DELETE_BUTTON)){
//                 List values = getParameters( request);
//                 
//                String bookId = (String)values.get(0);
//                request.setAttribute("bookId", bookId);
//                String title =  (String)values.get(1);
//                request.setAttribute("title", title);
//               String pageCount =  (String)values.get(2);
//                request.setAttribute("pageCount", pageCount);
//                 String pubDate =  (String)values.get(3);
//                request.setAttribute("pubDate", pubDate);
//                
//                
////                 book = new Book(0);
////               book.setBookId(new Integer((String)values.get(0)));
////              book.setTitle((String)values.get(1));
////              book.setAuthor((String)values.get(2));
////               book.setPageCount(new Integer((String)values.get(3)));
////                     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
////            Date date = new Date();
////            date = sdf.parse((String)values.get(4));
////              book.setPublishDate(date);
//                
//               destination = EDIT_DELETE_PAGE;
//               }
//            else if(action.equals(ADD_ACTION)){
//                
//                    List values = getParameters( request);
//                 
//                    values.remove(0);
//               
//                String title =  (String)values.get(0);
//                request.setAttribute("title", title);
//        
//               String pageCount =  (String)values.get(1);
//                request.setAttribute("pageCount", pageCount);
//                 String pubDate =  (String)values.get(2);
//                request.setAttribute("pubDate", pubDate);
//                
//                 book = new Book(0);
//              book.setTitle((String)values.get(0));
//         
//               book.setPageCount(new Integer((String)values.get(1)));
//                     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date date = new Date();
//            date = sdf.parse((String)values.get(2));
//              book.setPublishDate(date);
//              bookService.create(book);
//             
//           
//           getListOfBooksWithListPageDestination(request, bookService);
//           
//            } else if (action.equals(DELETE_ACTION)) {
//                
//                 List values = getParameters( request);
//                 
//                String bookId = (String)values.get(0);
//                request.setAttribute("bookId", bookId);
//                String title =  (String)values.get(1);
//                request.setAttribute("title", title);
//                 
//               String pageCount =  (String)values.get(2);
//                request.setAttribute("pageCount", pageCount);
//                 String pubDate =  (String)values.get(3);
//                request.setAttribute("pubDate", pubDate);
//                
//                  book = new Book(0);
//                  book.setBookId(new Integer((String) values.get(0)));
//              book.setTitle((String)values.get(1));
//            
//               book.setPageCount(new Integer((String)values.get(2)));
//                     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date date = new Date();
//            date = sdf.parse((String)values.get(3));
//              book.setPublishDate(date);
//                
//                
//                
//              String submitType =request.getParameter("submit");
//                    if(submitType.equals("delete")){
//                        
//                  
////                   book = bookService.find(new Integer(bookId));
//                   bookService.remove(book);
//                    }else if (submitType.equals("update")){
//                        
//                        
//                    bookService.edit(book);
////                     getListOfBooksWithListPageDestination(request, bookService);
//                    }
//                   getListOfBooksWithListPageDestination(request, bookService);
//                  } else {
//                      // no param identified in request, must be an error
//                      request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
//                      destination = LIST_PAGE;
//            }
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }

        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
    
//    private List getParameters(HttpServletRequest request){
//            List values= new ArrayList();
//               String bookId = request.getParameter("bookId");
//               String title =  request.getParameter("title");
//               String pageCount =  request.getParameter("pageCount");
//               String date = request.getParameter("pubDate");
//               values.add(bookId);
//               values.add(title);
//               values.add(pageCount);
//               values.add(date);
//    return values;
//    }
    
    private void getListOfAuthorsWithListPageDestination(HttpServletRequest request, AbstractFacade<Author> as) throws Exception{
    
          List<Author> authors = null;
                authors = as.findAll();
                request.setAttribute("authors", authors);
                destination = LIST_PAGE;
    }
//      private BookService injectDependenciesAndGetAuthorService() throws Exception {
//        // Use Liskov Substitution Principle and Java Reflection to
//        // instantiate the chosen DBStrategy based on the class name retrieved
//        // from web.xml
//        Class dbClass = Class.forName(dbStrategyClassName);
//        // Note that DBStrategy classes have no constructor params
//        DbStrategy db = (DbStrategy) dbClass.newInstance();
//
//            // Use Liskov Substitution Principle and Java Reflection to
//        // instantiate the chosen DAO based on the class name retrieved above.
//        // This one is trickier because the available DAO classes have
//        // different constructor params
//        DAOStrategy bookDao = null;
//        Class daoClass = Class.forName(daoClassName);
//         Constructor constructor =null;
//        try{
//     constructor = daoClass.getConstructor(new Class[]{
//            DbStrategy.class, String.class, String.class, String.class, String.class
//        });
//        }catch(NoSuchMethodException nsme){
//        
//        }
//            // This will be null if using connectin pool dao because the
//        // constructor has a different number and type of arguments
//      if (constructor != null) {
//            Object[] constructorArgs = new Object[]{
//                db, driverClass, url, userName, password
//            };
//            bookDao = (DAOStrategy) constructor
//                    .newInstance(constructorArgs);
//      }else{
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("jdbc/book_db");
//            constructor = daoClass.getConstructor(new Class[]{
//                DataSource.class, DbStrategy.class
//            });
//            Object[] constructorArgs = new Object[]{
//                ds, db
//            };
//
//            bookDao = (DAOStrategy) constructor
//                    .newInstance(constructorArgs);
//      }
//            
//             return new BookService(bookDao);
//      }
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
