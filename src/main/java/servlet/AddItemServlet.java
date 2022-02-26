package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = "/addItem")
public class AddItemServlet {
    private final String UPLOAD_DIR = "C:\\Users\\Admin\\IdeaProjects\\myItems.am\\src\\test\\upload";
    private final ItemManager itemManager = new ItemManager();
    private final UserManager userManager = new UserManager();
    private final CategoryManager categoryManager = new CategoryManager();


    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        Category category = categoryManager.getCategoryById(Integer.parseInt(req.getParameter("category_id")));
        double price = Double.parseDouble(req.getParameter("price"));


        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Item item = Item.builder()
                .title(title)
                .category_id(category)
                .price(price)
                .user_id(user)
                .build();

        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                item.setPic_url(fileName);
            }
        }
        itemManager.addItem(item);
        resp.sendRedirect("/");
    }


    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }
}
