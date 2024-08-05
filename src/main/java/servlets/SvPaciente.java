package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controlador;
import logica.Pasciente;

@WebServlet(name = "SvPaciente", urlPatterns = {"/SvPaciente"})
public class SvPaciente extends HttpServlet {

    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<Pasciente> listaPacientes = new ArrayList<Pasciente>();

        listaPacientes = control.getPacientes();

        HttpSession misession = request.getSession();
        misession.setAttribute("listaPacientes", listaPacientes);
        
        response.sendRedirect("verPacientes.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dniPac = request.getParameter("dni");
        String nombrePac = request.getParameter("nombre");
        String apellidoPac = request.getParameter("apellido");
        String telefonoPac = request.getParameter("telefono");
        String direccionPac = request.getParameter("direccion");
        String obraSocialPac = request.getParameter("obraSocial");
        String tipo_sangrePac = request.getParameter("tipo_sangre");
        String fecha_nacPac = request.getParameter("fecha_nac");
        
        // Parsear el valor del select a booleano
        boolean obraSocPac = Boolean.parseBoolean(obraSocialPac);

        // Definir el formato de la fecha esperado (yyyy-MM-dd)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacPac = null;

        try {
            // Parsear la cadena a un objeto Date
            fechaNacPac = formatter.parse(fecha_nacPac);
        } catch (ParseException e) {
            // Manejar la excepción en caso de error
            e.printStackTrace();
        }
        
        if (dniPac == null || dniPac.isEmpty() ||
            nombrePac == null || nombrePac.isEmpty() ||
            apellidoPac == null || apellidoPac.isEmpty() ||
            telefonoPac == null || telefonoPac.isEmpty() ||
            direccionPac == null || direccionPac.isEmpty() ||
            obraSocialPac == null || obraSocialPac.isEmpty() ||
            tipo_sangrePac == null || tipo_sangrePac.isEmpty() ||
            fecha_nacPac == null) {

            request.setAttribute("error", "Debes llenar todos los campos del paciente");
            request.getRequestDispatcher("crearPaciente.jsp").forward(request, response);
            return;
        }

        // Calcular la edad del paciente
        int edad = calcularEdad(fechaNacPac, new Date());
        boolean camposResponsableValidos = true;
        if (edad < 18) {
            // Validar campos del responsable
            String tipoResp = request.getParameter("tipo_resp");
            String dniResp = request.getParameter("dni_resp");
            String nombreResp = request.getParameter("nombre_resp");
            String apellidoResp = request.getParameter("apellido_resp");
            String telefonoResp = request.getParameter("telefono_resp");
            String direccionResp = request.getParameter("direccion_resp");
            String fecha_nacResp = request.getParameter("fecha_nac_resp");

            Date fechaNacResp = null;

            try {
                // Parsear la cadena a un objeto Date
                fechaNacResp = formatter.parse(fecha_nacResp);
            } catch (ParseException e) {
                // Manejar la excepción en caso de error
                e.printStackTrace();
            }

            if (tipoResp == null || tipoResp.isEmpty()
                    || dniResp == null || dniResp.isEmpty()
                    || nombreResp == null || nombreResp.isEmpty()
                    || apellidoResp == null || apellidoResp.isEmpty()
                    || telefonoResp == null || telefonoResp.isEmpty()
                    || direccionResp == null || direccionResp.isEmpty()
                    || fecha_nacResp == null) {
                    camposResponsableValidos = false;
                request.setAttribute("error", "Debes llenar todos los campos del responsable");
                
            }

            if (camposResponsableValidos) {
                control.crearPacientes(dniPac, nombrePac, apellidoPac, fechaNacPac, telefonoPac, direccionPac, obraSocPac, tipo_sangrePac,
                        tipoResp, dniResp, nombreResp, apellidoResp, telefonoResp, direccionResp, fechaNacResp);
                request.setAttribute("success", "Paciente creado con éxito");
            }
        } else {
            control.crearPaciente(dniPac, nombrePac, apellidoPac, fechaNacPac, telefonoPac, direccionPac, obraSocPac, tipo_sangrePac);
            request.setAttribute("success", "Paciente creado con éxito");
        }

         request.getRequestDispatcher("crearPaciente.jsp").forward(request, response);
    }

    private int calcularEdad(Date fechaNac, Date fechaActual) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(fechaNac);
        Calendar today = Calendar.getInstance();
        today.setTime(fechaActual);

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)
                || (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
