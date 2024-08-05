 
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controlador;
import logica.Secretario;
 
@WebServlet(name = "SvEditSecre", urlPatterns = {"/SvEditSecre"})
public class SvEditSecre extends HttpServlet {
    Controlador control = new Controlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Secretario secre = control.traerSecre(id);
        // Formatear la fecha de nacimiento
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacFormatted = sdf.format(secre.getFecha_nac());
        
        HttpSession misession = request.getSession();
        misession.setAttribute("fechaNac", fechaNacFormatted);
        misession.setAttribute("secreEditar", secre);
        
        response.sendRedirect("editSecretario.jsp");
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
        
        Secretario secre = (Secretario) request.getSession().getAttribute("secreEditar");
        secre.setDni(dni);
        secre.setNombre(nombre);
        secre.setApellido(apellido);
        secre.setTelefono(telefono);
        secre.setDireccion(direccion);
        secre.setSector(sector);
        secre.setFecha_nac(fechaNac);
        
        control.editSecretario(secre);
        
        response.sendRedirect("SvSecretario");
    }
 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
