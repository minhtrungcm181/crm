package cybersoft.java18.crm.api;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.services.RoleServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

// api/role
@WebServlet(name = "role",urlPatterns = {
        UrlUtils.URL_ROLE,
        UrlUtils.URL_ROLE_ADD,
        UrlUtils.URL_ROLE_UPDATE
})
public class RoleController extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("kiemtra do get");

//        RoleModel roleModel = new RoleModel();
//        roleModel.setId(1);
//        roleModel.setName("Nguyễn Văn Test");
//        roleModel.setDescription("Đây là mô tả về JSON");

        List<RoleModel> listRoles =  RoleServices.getInstance().getAllRole();

        String json = gson.toJson(listRoles);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();

    }

//    @Override
//    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Headers", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String description = req.getParameter("description");
        System.out.println("kiemtra do post " + role + " " + description);
        Integer result = RoleServices.getInstance().saveRole(role,description);
        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Xoá thành công
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Cập nhật thành công !");
        }else{
            //Xoá thất bại
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Cập nhật thất bại !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Integer result = RoleServices.getInstance().deleteRoleById(id);
        System.out.println("kiemtra do detele " + result);

        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Xoá thành công
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Xoá thành công !");
        }else{
            //Xoá thất bại
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Xoá thất bại !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader br = new BufferedReader(req.getReader());

        System.out.println("doput br" + br);
        StringBuilder builder = new StringBuilder();
        System.out.println("doput bui" + builder);
        String line;
        while((line = br.readLine()) != null){
            System.out.println("doput line" + builder);
            builder.append(line);
        }
        String data = builder.toString();
        System.out.println("doput" + data);
        String jSonString = paramJson(data);
        RoleModel roleModel = gson.fromJson(jSonString, RoleModel.class);

        System.out.println("kiemtra do put" + roleModel.getId());
        Integer result = RoleServices.getInstance().updateRoleById(roleModel);
        System.out.println("kiemtra do put" + roleModel.getId());
        ResponseData responseData = new ResponseData();

        if(result == 1){
            //Xoá thành công
            responseData.setStatusCode(200);
            responseData.setSuccess(true);
            responseData.setMesssage("Cập nhật thành công !");
        }else{
            //Xoá thất bại
            responseData.setStatusCode(200);
            responseData.setSuccess(false);
            responseData.setMesssage("Cập nhật thất bại !");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(responseData));
        printWriter.flush();
    }
    public static String paramJson(String paramIn) {
        paramIn = paramIn.replaceAll("=", "\":\"");
        paramIn = paramIn.replaceAll("&", "\",\"");
        return "{\"" + paramIn + "\"}";
    }

}
