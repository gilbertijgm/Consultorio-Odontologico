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
import logica.Horario;
import logica.Odontologo;

@WebServlet(name = "SvOdonto", urlPatterns = {"/SvOdonto"})
public class SvOdonto extends HttpServlet {

    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Odontologo> listaOdontos = new ArrayList<Odontologo>();

        listaOdontos = control.getOdontos();

        HttpSession misession = request.getSession();
        misession.setAttribute("listaOdontos", listaOdontos);

        // Verificar si hay un parámetro "success" en la URL
        String success = request.getParameter("success");
        if (success != null && success.equals("true")) {
            // Construir la página con SweetAlert de éxito
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js\"></script>");
            out.println("</head><body>");
            out.println("<script type=\"text/javascript\">");
            out.println("document.addEventListener('DOMContentLoaded', function() {");
            out.println("Swal.fire({");
            out.println("  position: 'top-center',");
            out.println("  icon: 'success',");
            out.println("  title: 'Actualización realizada correctamente',");
            out.println("  showConfirmButton: false,");
            out.println("  timer: 1000");
            out.println("}).then(function() {");
            out.println("  window.location.href = 'verOdonto.jsp';"); // Redirigir después de mostrar SweetAlert
            out.println("});");
            out.println("});");
            out.println("</script>");
            out.println("</body></html>");
        } else {
            // Verificar si hay un parámetro "error" en la URL
            String error = request.getParameter("error");
            if (error != null && error.equals("true")) {
                // Construir la página con SweetAlert de error
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
                out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js\"></script>");
                out.println("</head><body>");
                out.println("<script type=\"text/javascript\">");
                out.println("document.addEventListener('DOMContentLoaded', function() {");
                out.println("Swal.fire({");
                out.println("  icon: 'error',");
                out.println("  title: 'Error',");
                out.println("  text: 'No se pudo actualizar',");
                out.println("  confirmButtonText: 'OK'");
                out.println("}).then(function() {");
                out.println("  window.location.href = 'verOdonto.jsp';"); // Redirigir después de mostrar SweetAlert
                out.println("});");
                out.println("});");
                out.println("</script>");
                out.println("</body></html>");
            } else {
                // Si no hay parámetros "success=true" ni "error=true", redirigir directamente a tablaUsuarios.jsp
                response.sendRedirect("verOdonto.jsp");
            }
        }
//        response.sendRedirect("verOdonto.jsp?success=true");
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

        // Validar que todos los campos no estén vacíos
        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || fecha_nac.isEmpty()
                || telefono.isEmpty() || direccion.isEmpty() || especialidad.isEmpty()
                || horarioInicio.isEmpty() || horarioFin.isEmpty()) {
            // Si algún campo está vacío, imprimir mensaje y redirigir a crearOdonto.jsp
            System.out.println("Debes llenar todos los campos");
            response.sendRedirect("crearOdonto.jsp");
            return; // Terminar el método doPost aquí si hay campos vacíos
        }
        // Si todos los campos están llenos, proceder con la creación del odontólogo
        control.crearOdontos(dni, nombre, apellido, fechaNac, telefono, direccion, especialidad, horarioInicio, horarioFin);

        response.sendRedirect("crearOdonto.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
