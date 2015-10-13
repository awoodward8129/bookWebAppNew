package edu.wctc.adw.bookwebappnew.controller;


import edu.wctc.adw.bookwebappnew.model.Book;
import edu.wctc.adw.bookwebappnew.model.BookDAO;
import edu.wctc.adw.bookwebappnew.model.BookService;
import edu.wctc.adw.bookwebappnew.model.DAOStrategy;
import edu.wctc.adw.bookwebappnew.model.DbStrategy;
import edu.wctc.adw.bookwebappnew.model.MySqlDbStrategy;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class BookController extends HttpServlet {

    // NO MAGIC NUMBERS!

    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/listAuthors.jsp";
    private static final String ADD_PAGE = "/addBook.jsp";
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
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private String dbStrategyClassName;
    private String daoClassName;
    private DbStrategy db;
    private BookDAO bookDao;
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
        
        
          HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();
//        
//        String action = request.getParameter("action");
//        String destination = request.getParameter("dest");
        String fontColor = request.getParameter("fontColor");
        if(action.equals("session")){
        if(action.equals("end")) {
            session.invalidate();
            fontColor = "black";
            ctx.setAttribute("fontColor", fontColor);
            
//            if(destination.equalsIgnoreCase("home")) {
//                response.sendRedirect("index.jsp");
//            } else {
//                response.sendRedirect("testsession.jsp");
//            }
        } else {
            String color = request.getParameter("color");
            // Session scope is per user
            session.setAttribute("color", color);
          
            // in JSP the ServletContext is referred to as 'application'
            // and as applicatio-wide scope
            if(fontColor != null && fontColor.length() > 0) {
                ctx.setAttribute("fontColor", fontColor);
            }
            
//            destination = "page2.jsp";
        }
        }
        

        if(action.equals(ACTION_REDIRECT)){
         PrintWriter out = response.getWriter();
        try {
            // Just for fun we'll stick an attribute in the request object
            request.setAttribute("data", 5);
            // ... but it won't get forwarded, it will just die here because
            // the code on line #47 doesn't forward the request object
            
//            out.println("It looks like I'm writing to the response but I'm not because I haven't done a flush yet.");
            
            // If you uncomment line #45 the redirect will fail. That's
            // because you CANNOT redirect after writing to the response.
            // Remember, the Container and its Servlets don't return web page files, they
            // return data in the form of code. In this example, the data is
            // the text in the println statement above, plus the code in page2.jsp
            
//            out.flush();
            
            
             response.sendRedirect("redirect.jsp");
             return;
        
        } catch(IllegalStateException ise) {
            out.println("<br><br>Error: IllegalStateException. You tried to redirect after writing to the response.");
        } finally {            
            out.close();
        }
        }
        /*
         For now we are hard-coding the strategy objects into this
         controller. In the future we'll auto inject them from a config
         file. Also, the DAO opens/closes a connection on each method call,
         which is not very efficient. In the future we'll learn how to use
         a connection pool to improve this.
         */
//        DbStrategy db = new MySqlDbStrategy();
//        BookDAO authDao
//                = new BookDAO(db, "com.mysql.jdbc.Driver",
//                        "jdbc:mysql://localhost:3306/book_db", "root", "admin");
        //getparametervalues
        //get parameter
        //not empty author
        //servlet lifecycle
        //server starts -> app server starts -> app starts -> servlet constructor called-> init(httpSErvice) -> init

        try {
            
            BookService bookService = injectDependenciesAndGetAuthorService();
            /*
             Here's what the connection pool version looks like.
             */
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource)ctx.lookup("jdbc/book");
//            AuthorDaoStrategy authDao = new ConnPoolAuthorDao(ds, new MySqlDbStrategy());
//            AuthorService authService = new AuthorService(authDao);

            /*
             Determine what action to take based on a passed in QueryString
             Parameter
             */
            if (action.equals(LIST_ACTION) || action.equals("session&end&list") || action.equals("session")  ) {
                
                  if(action.equals("end")) {
            session.invalidate();
            fontColor = "black";
            ctx.setAttribute("fontColor", fontColor);
            
//            if(destination.equalsIgnoreCase("home")) {
//                response.sendRedirect("index.jsp");
//            } else {
//                response.sendRedirect("testsession.jsp");
//            }
        } else {
            String color = request.getParameter("color");
            // Session scope is per user
            session.setAttribute("color", color);
          
            // in JSP the ServletContext is referred to as 'application'
            // and as applicatio-wide scope
            if(fontColor != null && fontColor.length() > 0) {
                ctx.setAttribute("fontColor", fontColor);
            }
            
//            destination = "page2.jsp";
        }
                
                
                 getListOfBooksWithListPageDestination(request, bookService);
                
                //response.sendRedirect("/about.jsp");

            } else   if(action.equals("session")){
        if(action.equals("end")) {
            session.invalidate();
            fontColor = "black";
            ctx.setAttribute("fontColor", fontColor);
            
//            if(destination.equalsIgnoreCase("home")) {
//                response.sendRedirect("index.jsp");
//            } else {
//                response.sendRedirect("testsession.jsp");
             getListOfBooksWithListPageDestination(request, bookService);
//            }
        } else {
            String color = request.getParameter("color");
            // Session scope is per user
            session.setAttribute("color", color);
          
            // in JSP the ServletContext is referred to as 'application'
            // and as applicatio-wide scope
            if(fontColor != null && fontColor.length() > 0) {
                ctx.setAttribute("fontColor", fontColor);
            }
            
//            destination = "page2.jsp";
        }
        }else
                
                if (action.equals(ADD_BUTTON)) {
               destination = ADD_PAGE;}
          
            else if(action.equals(EDIT_DELETE_BUTTON)){
                 List values = getParameters( request);
                 
                String bookId = (String)values.get(0);
                request.setAttribute("bookId", bookId);
                String title =  (String)values.get(1);
                request.setAttribute("title", title);
                 String author =  (String)values.get(2);
                request.setAttribute("author", author);
               String pageCount =  (String)values.get(3);
                request.setAttribute("pageCount", pageCount);
                 String pubDate =  (String)values.get(4);
                request.setAttribute("pubDate", pubDate);
                
               destination = EDIT_DELETE_PAGE;
               }
            else if(action.equals(ADD_ACTION)){
               
                      List values = getParameters( request);
                      values.remove(0);

              bookService.insertRecord("book", values);
          
           
           getListOfBooksWithListPageDestination(request, bookService);
           
            } else if (action.equals(DELETE_ACTION)) {
              String submitType =request.getParameter("submit");
              if(submitType.equals("delete")){
                  
             String bookId = request.getParameter("bookId");       
             bookService.deleteByBookId("book", "book_id", bookId);
             
             
              }else if (submitType.equals("update")){

                   bookService.updateRecord("book", getParameters( request), "book_id", (String) getParameters( request).get(0));
              }
              
             getListOfBooksWithListPageDestination(request, bookService);
                
            } else {
                // no param identified in request, must be an error
                request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                destination = LIST_PAGE;
            }
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }

        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
    
    private List getParameters(HttpServletRequest request){
            List values= new ArrayList();
               String bookId = request.getParameter("bookId");
               String title =  request.getParameter("title");
               String author =  request.getParameter("author");
               String pageCount =  request.getParameter("pageCount");

               
               String date = request.getParameter("pubDate");
               values.add(bookId);
               values.add(title);
               values.add(author);
               values.add(pageCount);
               values.add(date);
    return values;
    }
    
    private void getListOfBooksWithListPageDestination(HttpServletRequest request, BookService bs) throws Exception{
    
          List<Book> books = null;
                books = bs.getAllBooks();
                request.setAttribute("books", books);
                destination = LIST_PAGE;
    }
      private BookService injectDependenciesAndGetAuthorService() throws Exception {
        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DBStrategy based on the class name retrieved
        // from web.xml
        Class dbClass = Class.forName(dbStrategyClassName);
        // Note that DBStrategy classes have no constructor params
        DbStrategy db = (DbStrategy) dbClass.newInstance();

            // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DAO based on the class name retrieved above.
        // This one is trickier because the available DAO classes have
        // different constructor params
        DAOStrategy bookDao = null;
        Class daoClass = Class.forName(daoClassName);
         Constructor constructor =null;
        try{
     constructor = daoClass.getConstructor(new Class[]{
            DbStrategy.class, String.class, String.class, String.class, String.class
        });
        }catch(NoSuchMethodException nsme){
        
        }
            // This will be null if using connectin pool dao because the
        // constructor has a different number and type of arguments
      if (constructor != null) {
            Object[] constructorArgs = new Object[]{
                db, driverClass, url, userName, password
            };
            bookDao = (DAOStrategy) constructor
                    .newInstance(constructorArgs);
      }else{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/book_db");
            constructor = daoClass.getConstructor(new Class[]{
                DataSource.class, DbStrategy.class
            });
            Object[] constructorArgs = new Object[]{
                ds, db
            };

            bookDao = (DAOStrategy) constructor
                    .newInstance(constructorArgs);
      }
            
             return new BookService(bookDao);
      }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

      @Override
    public void init() throws ServletException {
        // Get init params from web.xml
        //I'll use getServletContext().get
        driverClass = getServletContext().getInitParameter("driverClass");
        url = getServletContext().getInitParameter("url");
        userName = getServletContext().getInitParameter("userName");
        password = getServletContext().getInitParameter("password");
        dbStrategyClassName = this.getServletContext().getInitParameter("dbStrategy");
        daoClassName = this.getServletContext().getInitParameter("bookDao");

        // You can't do the Java Reflection stuff here because exceptions
        // are thrown that can't be handled by this stock init() method
        // because the method signature can't be modified -- remember, you 
        // are overriding the method.
    }
}
