package org.example;
import net.sf.jasperreports.engine.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ExportarSubInforme {

    public static void main(String[] args) {
        try {
            // Paso 1: Compilar los subinformes
            JasperCompileManager.compileReportToFile("C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\CentroMedico.jrxml");
            JasperCompileManager.compileReportToFile("C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\Especialidad.jrxml");
            JasperCompileManager.compileReportToFile("C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\Urgencias.jrxml");

            // Paso 2: Compilar el informe principal
            JasperCompileManager.compileReportToFile("C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\Centro.jrxml");

            // Paso 3: Conectar a la base de datos (ajusta las credenciales según tu configuración)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/centro_medico", "root", "");

            // Paso 4: Crear parámetros (si es necesario)
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", 1); // Ajusta el valor del parámetro según tus necesidades

            // Paso 5: Llenar el informe principal
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\Centro.jasper", parametros, connection);

            // Paso 6: Exportar el informe a PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\Centro.pdf");

            System.out.println("Informe generado exitosamente.");
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }
}