package servlet;

import manager.ItemManager;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userAds")
public class UserServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            List<Item> itemsByUser = itemManager.getUserAds(user.getId());

            req.setAttribute("items",itemsByUser);
            req.getRequestDispatcher("/home.jsp").forward(req,resp);
        }
    }
