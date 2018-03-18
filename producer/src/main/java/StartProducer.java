import entity.DVD;
import producer.QueueProducer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/IndexServlet")
public class StartProducer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("/index.html").include(request, response);

        String title="";
        int year=0;
        double price=0.0;

        if(!(request.getParameter("year").equals("")))
            year=Integer.valueOf(request.getParameter("year"));
        if(!(request.getParameter("title").equals("")))
            title=request.getParameter("title");
        if(!(request.getParameter("price").equals("")))
            price=Double.valueOf(request.getParameter("price"));

        if((!title.equals("")) && year!=0 && price!=0.0){

            DVD dvd=new DVD(title,year,price);

            QueueProducer producer = new QueueProducer("queue");

            HashMap message = new HashMap();
            message.put("dvd", dvd.toString());
            producer.sendMessage(message);
        }
        else
            out.println("Fill all the fields!");
    }
}