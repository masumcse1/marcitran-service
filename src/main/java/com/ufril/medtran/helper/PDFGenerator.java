package com.ufril.medtran.helper;

import com.lowagie.text.DocumentException;
import org.apache.velocity.app.VelocityEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class PDFGenerator {

    @Autowired
    private VelocityEngine velocityEngine;

    public void getPcrReport(OutputStream outputStream, Map dataModel) throws DocumentException, IOException {
        String htmlString = getPcrReportAsHtmlString(dataModel);

        Document xhtml = Jsoup.parse(htmlString, "UTF-8");
        xhtml.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);

        ITextRenderer renderer = new ITextRenderer();
        SharedContext sharedContext = renderer.getSharedContext();
        sharedContext.setPrint(true);
        sharedContext.setInteractive(false);
        renderer.setDocumentFromString(xhtml.html());
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

//    todo move this to appropriate file
    private String getPcrReportAsHtmlString(Map dataModel) {
        String templateLocation = "templates/mail/sendPcrReport.vm";
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, "UTF-8", dataModel);
    }
}
