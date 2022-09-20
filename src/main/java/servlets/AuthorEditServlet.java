package servlets;

import books.manager.AuthorManager;
import books.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/author/edit")
public class AuthorEditServlet extends HttpServlet {

    private final AuthorManager AUTHOR_MANAGER = new AuthorManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Author author = AUTHOR_MANAGER.getById(authorId);
        req.setAttribute("authors", author);
        req.getRequestDispatcher("/WEB-INF/authorEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

            Author author = Author.builder()
                    .id(authorId)
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .build();

            AUTHOR_MANAGER.edit(author);
            resp.sendRedirect("/author");

    }
}
