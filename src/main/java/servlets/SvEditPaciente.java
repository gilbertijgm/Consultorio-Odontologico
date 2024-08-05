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
import logica.Pasciente;
import logica.Responsable;

@WebServlet(name = "SvEditPaciente", urlPatterns = {"/SvEditPaciente"})
public class SvEditPaciente extends HttpServlet {

    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Pasciente paci = control.traerPaci(id);
        // Formatear la fecha de nacimiento
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacFormatted = sdf.format(paci.getFecha_nac());

        HttpSession misession = request.getSession();
        misession.setAttribute("fechaNac", fechaNacFormatted);
        misession.setAttribute("paciEditar", paci);

        if (paci.getUnResponsable() != null) {
            Responsable resp = paci.getUnResponsable();
            SimpleDateFormat sdfResp = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNacRespFormatted = sdfResp.format(resp.getFecha_nac());
            misession.setAttribute("fechaNacResp", fechaNacRespFormatted);
            misession.setAttribute("resp", resp);
        }
        response.sendRedirect("editPaciente.jsp");
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

        // Campos de responsable
        String tipoResp = request.getParameter("tipo_resp");
        String dniResp = request.getParameter("dni_resp");
        String nombreResp = request.getParameter("nombre_resp");
        String apellidoResp = request.getParameter("apellido_resp");
        String telefonoResp = request.getParameter("telefono_resp");
        String direccionResp = request.getParameter("direccion_resp");
        String fecha_nacResp = request.getParameter("fecha_nac_resp");

        Date fechaNacResp = null;

        if (fecha_nacResp != null && !fecha_nacResp.isEmpty()) {
            try {
                // Parsear la cadena a un objeto Date
                fechaNacResp = formatter.parse(fecha_nacResp);
            } catch (ParseException e) {
                // Manejar la excepción en caso de error
                e.printStackTrace();
            }
        }

        Pasciente paci = (Pasciente) request.getSession().getAttribute("paciEditar");
        paci.setDni(dniPac);
        paci.setNombre(nombrePac);
        paci.setApellido(apellidoPac);
        paci.setFecha_nac(fechaNacPac);
        paci.setTelefono(telefonoPac);
        paci.setDireccion(direccionPac);
        paci.setObra_social(obraSocPac);
        paci.setTipo_sangre(tipo_sangrePac);

        // Verificar si se debe actualizar o crear el responsable
        if (tipoResp != null && !tipoResp.isEmpty() || dniResp != null && !dniResp.isEmpty()
                || nombreResp != null && !nombreResp.isEmpty() || apellidoResp != null && !apellidoResp.isEmpty()
                || telefonoResp != null && !telefonoResp.isEmpty() || direccionResp != null && !direccionResp.isEmpty()
                || fechaNacResp != null) {

            Responsable resp = new Responsable();
            resp.setTipo_resp(tipoResp);
            resp.setDni(dniResp);
            resp.setNombre(nombreResp);
            resp.setApellido(apellidoResp);
            resp.setFecha_nac(fechaNacResp);
            resp.setTelefono(telefonoResp);
            resp.setDireccion(direccionResp);
            paci.setUnResponsable(resp);
        } else {
            paci.setUnResponsable(null); // No hay responsable
        }
        
       
        control.editarPaciente(paci);

        response.sendRedirect("SvPaciente");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
