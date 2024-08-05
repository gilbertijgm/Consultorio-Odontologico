package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controlador;
import logica.Secretario;

@WebServlet(name = "SvSecretario", urlPatterns = {"/SvSecretario"})
public class SvSecretario extends HttpServlet {

    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Secretario> listaSecre = new ArrayList<Secretario>();
        
        listaSecre = control.getSecretarios();
        
        HttpSession misession = request.getSession();
        misession.setAttribute("listaSecre", listaSecre);
                
        response.sendRedirect("verSecretarios.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String sector = request.getParameter("sector");
        String fecha_nac = request.getParameter("fecha_nac");
        // Definir el formato de la fecha esperado (yyyy-MM-dd)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Declarar la variable fechaNac como Date
        Date fechaNac = null;

        try {
            // Parsear la cadena a un objeto Date
            fechaNac = formatter.parse(fecha_nac);
        } catch (ParseException e) {
            // Manejar la excepción en caso de error
            e.printStackTrace();
        }
        
        control.crearSecretario(dni,nombre,apellido,telefono,direccion,sector,fechaNac);
        
        response.sendRedirect("crearSecretario.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
