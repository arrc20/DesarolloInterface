package org.example;

import com.lowagie.text.pdf.PdfReader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.util.HashMap;

public class ExportadorPDF {

    public static void main(String[] args) {
        // Ruta al archivo JRXML
        String jrxmlFilePath = "C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\datos.jrxml";

        // Ruta de salida para el archivo PDF
        String pdfOutputFilePath = "C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\datos.pdf";

        try {
            // Compilar el informe JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilePath);

            // Crear un origen de datos (puedes pasar datos si es necesario)
            JRDataSource dataSource = new JREmptyDataSource();

            // Llenar el informe con datos y obtener el objeto JasperPrint
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            // Exportar el informe a PDF
            exportarAPdf(jasperPrint, pdfOutputFilePath);


        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private static void exportarAPdf(JasperPrint jasperPrint, String pdfOutputFilePath) throws JRException {
        // Crear un exportador PDF
        JRPdfExporter pdfExporter = new JRPdfExporter();

        // Establecer el informe a exportar
        pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

        // Establecer la ruta de salida para el archivo PDF
        pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pdfOutputFilePath);

        // Opcional: establecer otras configuraciones del exportador, como el tamaño de la página, etc.

        // Exportar el informe a PDF
        pdfExporter.exportReport();
    }

    private static int obtenerNumeroDePaginas(String pdfFilePath) {
        try {
            // Crear un lector de PDF
            PdfReader pdfReader = new PdfReader(pdfFilePath);

            // Obtener el número total de páginas
            int numeroDePaginas = pdfReader.getNumberOfPages();

            // Cerrar el lector de PDF
            pdfReader.close();

            return numeroDePaginas;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}