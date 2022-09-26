package cybersoft.java18.crm.api;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.ProjectModel;
import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.services.ProjectServices;
import cybersoft.java18.crm.services.RoleServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "role",urlPatterns = {
        UrlUtils.URL_PROJECT,
        UrlUtils.URL_PROJECT_ADD,
        UrlUtils.URL_PROJECT_UPDATE
})
public class ProjectController extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectModel> listProjects = ProjectServices.getInstance().getAllProJect();

        String json = gson.toJson(listProjects);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String
        Integer result = ProjectServices.getInstance().saveProJect();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
