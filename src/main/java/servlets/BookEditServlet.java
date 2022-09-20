package servlets;

import books.manager.AuthorManager;
import books.manager.BookManager;
import books.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/book/edit")
public class BookEditServlet extends HttpServlet {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final BookManager bookManager = new BookManager();
    private final AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        Book book = bookManager.getById(bookId);
        req.setAttribute("books",book);
        req.setAttribute("authors",authorManager.getAll());
        req.getRequestDispatcher("/WEB-INF/bookEdit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        double price = Double.parseDouble(req.getParameter("price"));
        int count = Integer.parseInt(req.getParameter("count"));
        String genre = req.getParameter("genre");
        String addedDateStr = req.getParameter("addedDate");
        String description = req.getParameter("description");

        try {
           Book book = Book.builder()
                    .id(bookId)
                    .title(title)
                    .author(authorManager.getById(authorId))
                    .price(price)
                    .count(count)
                    .genre(genre)
                    .addedDate(sdf.parse(addedDateStr))
                    .description(description)
                    .build();

            bookManager.edit(book);
            resp.sendRedirect("/book");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
