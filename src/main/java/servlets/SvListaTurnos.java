package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controlador;
import logica.Turno;

@WebServlet(name = "SvListaTurnos", urlPatterns = {"/SvListaTurnos"})
public class SvListaTurnos extends HttpServlet {

    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Turno> listaTurno = new ArrayList<Turno>();

        listaTurno = control.getTurnos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Turno turno : listaTurno) {
            String fechaFormateada = sdf.format(turno.getFecha_turno());
            turno.setFechaFormateada(fechaFormateada); // Método setter que necesitas agregar a la clase Turno
        }

        HttpSession misession = request.getSession();
        misession.setAttribute("listaTurno", listaTurno);

        response.sendRedirect("verTurnos.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
