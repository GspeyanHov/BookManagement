package servlets;

import books.manager.UserManager;
import books.model.User;
import books.model.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
@WebServlet(urlPatterns = "/users/add")
public class UserAddServlet extends HttpServlet {

    private static final String IMG_PATH = "C:\\Users\\SmartS\\Java2022ultimate\\BookManagementWeb\\userImages\\";
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           req.getRequestDispatcher("/WEB-INF/userAdd.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        if (userManager.getUserByEmail(email) != null) {
                req.setAttribute("msg","User already exists");
                req.getRequestDispatcher("/WEB-INF/userAdd.jsp").forward(req,resp);
        }else{
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            UserRole userRole = UserRole.valueOf(req.getParameter("user_role"));

            Part profilePicPart = req.getPart("profile_pic");
            String fileName = null;
            if(profilePicPart != null){
                long nanoTime = System.nanoTime();
                fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
                profilePicPart.write(IMG_PATH + fileName);
                User user = User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .userRole(userRole)
                        .profilePic(fileName)
                        .build();

                userManager.addUser(user);
                resp.sendRedirect("/login");
            }
        }
    }
}
