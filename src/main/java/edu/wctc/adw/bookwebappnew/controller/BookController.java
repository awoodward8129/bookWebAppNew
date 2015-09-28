package edu.wctc.adw.bookwebappnew.controller;


import edu.wctc.adw.bookwebappnew.model.Book;
import edu.wctc.adw.bookwebappnew.model.BookDAO;
import edu.wctc.adw.bookwebappnew.model.BookService;
import edu.wctc.adw.bookwebappnew.model.DbStrategy;
import edu.wctc.adw.bookwebappnew.model.MySqlDbStrategy;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * The main controller for author-related activities
 *
 * @author jlombardo
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
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

        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);

        /*
         For now we are hard-coding the strategy objects into this
         controller. In the future we'll auto inject them from a config
         file. Also, the DAO opens/closes a connection on each method call,
         which is not very efficient. In the future we'll learn how to use
         a connection pool to improve this.
         */
        DbStrategy db = new MySqlDbStrategy();
        BookDAO authDao
                = new BookDAO(db, "com.mysql.jdbc.Driver",
                        "jdbc:mysql://localhost:3306/book_db", "root", "admin");
        BookService bookService = new BookService(authDao);

        try {
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
            if (action.equals(LIST_ACTION)) {
                List<Book> books = null;
                books = bookService.getAllBooks();
                request.setAttribute("books", books);
                destination = LIST_PAGE;

            } else if (action.equals(ADD_BUTTON)) {
               destination = ADD_PAGE;}
            else if(action.equals(EDIT_DELETE_BUTTON)){
                String bookId = request.getParameter("bookId");
                request.setAttribute("bookId", bookId);
                String title =  request.getParameter("title");
                request.setAttribute("title", title);
                 String author =  request.getParameter("author");
                request.setAttribute("author", author);
               String pageCount =  request.getParameter("pageCount");
                request.setAttribute("pageCount", pageCount);
                 String pubDate =  request.getParameter("pubDate");
                request.setAttribute("pubDate", pubDate);
                
               destination = EDIT_DELETE_PAGE;
               }
            else if(action.equals(ADD_ACTION)){
               List columns= new ArrayList();
               columns.add("title");
               columns.add("author");
               columns.add("page_count");
               columns.add("publish_date");
                List values= new ArrayList();
               String title =  request.getParameter("title");
               String author =  request.getParameter("author");
               String pageCount =  request.getParameter("pageCount");
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
               Date date = new Date();
               date = sdf.parse(request.getParameter("pubDate"));
               values.add(title);
               values.add(author);
               values.add(pageCount);
               values.add(date);
              bookService.insertRecord("book", columns, values);
           
            List<Book> books = null;
                books = bookService.getAllBooks();
                request.setAttribute("books", books);
           destination = LIST_PAGE;
            } else if (action.equals("UPDATE_ACTION")) {
                // coming soon
            } else if (action.equals(DELETE_ACTION)) {
              String submitType =request.getParameter("submit");
              if(submitType.equals("delete")){
                  
                  
             String bookId = request.getParameter("bookId");       
             bookService.deleteByBookId("book", "book_id", bookId);
             
             
              }else if (submitType.equals("update")){
                  
                  
                  
                  
                    List columns= new ArrayList();
               columns.add("book_id");     
               columns.add("title");
               columns.add("author");
               columns.add("page_count");
               columns.add("publish_date");
                List values= new ArrayList();
               String bookId = request.getParameter("bookId");
               String title =  request.getParameter("title");
               String author =  request.getParameter("author");
               String pageCount =  request.getParameter("pageCount");
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
               Date date = new Date();
               date = sdf.parse(request.getParameter("pubDate"));
               values.add(bookId);
               values.add(title);
               values.add(author);
               values.add(pageCount);
               values.add(date);
               
              bookService.updateRecord("book", columns, values, "author", author);
              }
              
                 List<Book> books = null;
                books = bookService.getAllBooks();
                request.setAttribute("books", books);
           destination = LIST_PAGE;
                
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

}
