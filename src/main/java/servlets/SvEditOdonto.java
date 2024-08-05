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
import logica.Horario;
import logica.Odontologo;
 
@WebServlet(name = "SvEditOdonto", urlPatterns = {"/SvEditOdonto"})
public class SvEditOdonto extends HttpServlet {
    Controlador control = new Controlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Odontologo odonto = control.traerOdonto(id);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("odontoEditar", odonto);
        
        response.sendRedirect("editOdonto.jsp");
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
                // Recibir el valor del parámetro fecha_nac como cadena

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
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String especialidad = request.getParameter("especialidad");
        
        String horarioInicio = request.getParameter("horario_ini");
        String horarioFin = request.getParameter("horario_fin");
        
        Horario horario = new Horario();
        horario.setHorario_inicio(horarioInicio);
        horario.setHorario_fin(horarioFin);
        
        Odontologo odonto = (Odontologo)request.getSession().getAttribute("odontoEditar");
        odonto.setDni(dni);
        odonto.setNombre(nombre);
        odonto.setApellido(apellido);
        odonto.setFecha_nac(fechaNac);
        odonto.setTelefono(telefono);
        odonto.setDireccion(direccion);
        odonto.setEspecialidad(especialidad);
        odonto.setUnHorario(horario);
        
        control.editarOdonto(odonto);
        
        response.sendRedirect("SvOdonto?success=true");
        
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
