
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteSims")
public class DeleteSims extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public DeleteSims() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String Name = request.getParameter("Name");

      Connection connection = null;
      String insertSql = "DELETE FROM MyTableTechEx WHERE MYUSER= ?";

      try {
         DBConnectionSims.getDBConnection();
         connection = DBConnectionSims.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, Name);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Delete Data from DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>User Name</b>: " + Name + "\n" + //

            "</ul>\n");

      out.println("<a href=/techexercise/search_sims.html>Search Contacts</a> <br>");
      out.println("<a href=/techexercise/insert_sims.html>Enter Contact</a> <br>");
      out.println("<a href=/techexercise/delete_sims.html>Delete Contact</a> <br>");
      out.println("</body></html>");
   }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);}
  }
