package Servlets;

import Accaunts.AccauntService;
import Accaunts.UserProfile;
import Tamplate.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Владислав on 23.01.2017.
 */
public class SignInServlet extends HttpServlet {
    UserProfile userProfile;
    AccauntService accauntService;
    SignUpServlet signUpServlet = new SignUpServlet(accauntService);
//    String email = userProfile.getEmail();


    Map<String,Object> data = new HashMap<String, Object>();

    public SignInServlet(AccauntService accauntService) {
        this.accauntService = accauntService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String login = req.getParameter("login");
       String pass = req.getParameter("login");
        if (login.isEmpty() || pass.isEmpty()){
            resp.getWriter().println(PageGenerator.instance().getPage("NullFields.html", data));
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("NULL");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        else{
            String session =  signUpServlet.getSession();
            userProfile = accauntService.getProfileBySessionId(session);


            resp.getWriter().println(PageGenerator.instance().getPage("SignIn.html", data));
            resp.getWriter().println("\n client \n" + userProfile.toString());
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);


        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
