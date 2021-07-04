package taxi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.DriverService;

public class IndexController extends HttpServlet {
    private static final String DRIVER_ID = "driver_id";
    private Injector injector = Injector.getInstance("taxi");
    private DriverService driverService = (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute(DRIVER_ID);
        Driver driver = driverService.get(id);
        req.setAttribute("driver_name", driver.getName());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
