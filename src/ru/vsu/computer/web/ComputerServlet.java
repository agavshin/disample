package ru.vsu.computer.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.vsu.computer.Computer;
import ru.vsu.computer.di.factory.BeanFactory;

@WebServlet(name = "computer", urlPatterns = {"/computer"})
public class ComputerServlet extends HttpServlet {

    private Computer computer;

    @Override
    public void init() throws ServletException {
        super.init();
        this.computer = BeanFactory.getInstance().getBean(Computer.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("info", computer.start().split("\n"));

        getServletContext()
            .getRequestDispatcher("/computer.jsp")
            .forward(req, resp);
    }
}
