package ru.vsu.computer.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.vsu.computer.di.factory.BeanFactory;
import ru.vsu.db.entity.Item;
import ru.vsu.db.persistence.ItemRepository;

@WebServlet(name = "items", urlPatterns = {"/items/*"})
public class ItemServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ItemServlet.class.getCanonicalName());

    private ItemRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = BeanFactory.getInstance().getBean(ItemRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String pathInfo = req.getPathInfo();
        logger.info("Request {" + req.getRequestURI() + "}, pathInfo {" + pathInfo + "}");

        var page = "";
        if (extractId(pathInfo) == null) {
            final List<Item> items = repository.list();
            req.setAttribute("items", items);
            page = "/items.jsp";
        } else {
            final Long id = (long) Integer.parseInt(pathInfo.split("/")[1]);
            final Item item = repository.find(id);
            req.setAttribute("item", item);
            page = "/item.jsp";
        }

        getServletContext()
            .getRequestDispatcher(page)
            .forward(req, resp);
    }

    private Long extractId(String path) {
        if (path != null) {
            try {
                return (long) Integer.parseInt(path.split("/")[1]);
            } catch (NumberFormatException e ) {
                return null;
            }
        }
        return null;
    }
}
