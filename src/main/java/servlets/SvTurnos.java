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
import logica.Odontologo;
import logica.Pasciente;
import logica.Turno;

@WebServlet(name = "SvTurnos", urlPatterns = {"/SvTurnos"})
public class SvTurnos extends HttpServlet {

    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Odontologo> listaOdontos = control.getOdontos();
            List<Pasciente> listaPacientes = control.getPacientes();

            HttpSession misession = request.getSession();
            misession.setAttribute("listaOdontos", listaOdontos);
            misession.setAttribute("listaPacientes", listaPacientes);

            response.sendRedirect("crearTurno.jsp");
        } catch (Exception e) {
            // Manejo de excepciones, puedes registrar el error o redirigir a una página de error
            e.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_paci = request.getParameter("id_paci");
        String id_odonto = request.getParameter("id_odonto");
        String afeccion = request.getParameter("afeccion");
        String hora_turno = request.getParameter("hora_turno");
        String fecha_turno = request.getParameter("fecha_turno");

        // Definir el formato de la fecha esperado (yyyy-MM-dd)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaTurno = null;

        try {
            // Parsear la cadena a un objeto Date
            fechaTurno = formatter.parse(fecha_turno);
        } catch (ParseException e) {
            // Manejar la excepción en caso de error
            e.printStackTrace();
        }
        
        
        control.crearTurno(id_paci,id_odonto,afeccion,hora_turno,fechaTurno);
        
        response.sendRedirect("crearTurno.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
