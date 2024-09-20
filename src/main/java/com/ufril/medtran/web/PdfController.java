package com.ufril.medtran.web;

import com.ufril.medtran.helper.PDFGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "web/pdf-export")
public class PdfController {

    private static Logger logger = Logger.getLogger(PdfController.class);

    @Autowired
    private PDFGenerator pdfGenerator;

    @RequestMapping(value = "/download-pdf", method = RequestMethod.GET)
    public void generatePcrReport(HttpServletResponse servletResponse, @RequestParam Map params) throws IOException {

        servletResponse.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        servletResponse.setHeader(headerKey, headerValue);

//        todo add model here, will be empty now
        pdfGenerator.getPcrReport(servletResponse.getOutputStream(), new HashMap());
    }
}
