package servlets;

import books.manager.BookManager;
import books.model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/book")
public class BookServlet extends HttpServlet {

    private final BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> all = bookManager.getAllBooks();
        request.setAttribute("book", all);
        request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
    }

}
