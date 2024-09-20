package com.ufril.medtran.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ufril.medtran.event.OnPasswordResetEvent;
import com.ufril.medtran.event.OnSignupCompleteEvent;
import com.ufril.medtran.helper.PDFGenerator;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.domain.dispatch.PCRLog;
import com.ufril.medtran.persistence.repository.common.PreferenceRepository;
import com.ufril.medtran.persistence.repository.dispatch.PCRLogRepository;
import com.ufril.medtran.service.MailSenderService;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author moin
 *
 */
@Component
public class MailSenderServiceImpl implements MailSenderService {

    private static Logger LOGGER = Logger.getLogger(MailSenderServiceImpl.class);

    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private PreferenceRepository preferenceRepository;
    @Autowired
    private PDFGenerator pdfGenerator;
    @Autowired
    private PCRLogRepository pcrLogRepository;

   private String from;
   private String webUrl;

    final int BUFFER_SIZE = 32768;

    @Override
    public void sendMail(final OnSignupCompleteEvent event, final String token) {
      // webUrl = preferenceRepository.findByName("ip-address").getValue();
       from = preferenceRepository.findByName("mail.from").getValue();
        final String mailTo = event.getUser().getUsername() + " " +  " <" + event.getUser().getEmail() + ">";
        final String mailFrom = "Marcitran <"+ from + ">";
        final String subject = "Marcitran Email confirmation";
        final String templateLocation = "templates/mail/confirmSignup.vm";
        MimeMessagePreparator mimeMessagePreparator = getMimeMessagePreparator(event.getUser(), "default", token, mailTo, mailFrom, subject, templateLocation);
        taskExecutor.execute(new MimeMessagePreparatorRunnable(mimeMessagePreparator));
        LOGGER.debug("Successfully execute the mail send task");
    }

    @Override
    public void sendMail(final OnPasswordResetEvent event, final String token) {
    //   webUrl = preferenceRepository.findByName("ip-address").getValue();
        webUrl = "http://localhost:8080/";
      from = preferenceRepository.findByName("mail.from").getValue();
        LOGGER.debug("Inside sendMail for Password Reset");
       final String mailTo = event.getUser().getUsername() + " "  + " <" + event.getUser().getEmail() + ">";
        System.out.println("Find user name and email");
        final String mailFrom = "Ufril <"+ from + ">";
        final String subject = "Reset Password Request";
        final String templateLocation = "templates/mail/changePassword.vm";
        MimeMessagePreparator mimeMessagePreparator = getMimeMessagePreparator(event.getUser(), "default", token, mailTo, mailFrom, subject, templateLocation);
        taskExecutor.execute(new MimeMessagePreparatorRunnable(mimeMessagePreparator));
        LOGGER.debug("Successfully execute the mail send task");
    }

    @Override
    public void sendPcrReport(PCRLog pcrLog, String email) {
        from = preferenceRepository.findByName("mail.from").getValue();
        final String mailTo = "Receiver" + " " +  " <" + email + ">";
        final String mailFrom = "Marcitran <"+ from + ">";
        final String subject = "Marcitran Email confirmation";
        final String templateLocation = "templates/mail/sendPcrReport.vm";
        MimeMessagePreparator mimeMessagePreparator = getMimeMessagePreparatorForPcr(pcrLog, mailTo, mailFrom, subject, templateLocation);
        taskExecutor.execute(new MimeMessagePreparatorRunnable(mimeMessagePreparator));
        LOGGER.debug("Successfully execute the mail send task");
    }

    private String buildMailTo(User user) {
        return user.getUsername() + " <" + user.getEmail() + ">";
    }

    private MimeMessagePreparator getMimeMessagePreparator(final User user, final String step, final String token, final String mailTo, final String mailFrom, final String subject, final String templateLocation) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom(mailFrom);
            message.setTo(mailTo);
            message.setSubject(subject);
            message.setSentDate(new Date());

            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("token", token);
            model.put("webUrl", webUrl);
            model.put("step", step);
            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, templateLocation, "UTF-8", model);
            message.setText(text, true);
        };
    }

    private MimeMessagePreparator getMimeMessagePreparatorForPcr(final PCRLog pcrLog, final String mailTo, final String mailFrom, final String subject, final String templateLocation) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom(mailFrom);
            message.setTo(mailTo);
            message.setSubject(subject);
            message.setSentDate(new Date());

            Map<String, Object> dataModel = new HashMap<>();

            dataModel.put("submittedTime", pcrLog.getSubmittedTime());

            if(pcrLog.getAssessment() != null && !pcrLog.getAssessment().isEmpty()) {
                JsonObject assessment = new JsonParser().parse(pcrLog.getAssessment()).getAsJsonObject();
                dataModel.put("assessment", assessment);
            }

            if(pcrLog.getEcgRecord() != null && !pcrLog.getEcgRecord().isEmpty()) {
                JsonObject ecgRecord = new JsonParser().parse(pcrLog.getEcgRecord()).getAsJsonObject();
                dataModel.put("ecgRecord", ecgRecord);
            }

            if(pcrLog.getIntervention() != null && !pcrLog.getIntervention().isEmpty()) {
                JsonObject intervention = new JsonParser().parse(pcrLog.getIntervention()).getAsJsonObject();
                dataModel.put("intervention", intervention);
            }

            if(pcrLog.getIcActivity() != null && !pcrLog.getIcActivity().isEmpty()) {
                JsonObject icActivity = new JsonParser().parse(pcrLog.getIcActivity()).getAsJsonObject();
                dataModel.put("icActivity", icActivity);
            }

            if(pcrLog.getLabTest() != null && !pcrLog.getLabTest().isEmpty()) {
                JsonObject labTest = new JsonParser().parse(pcrLog.getLabTest()).getAsJsonObject();
                dataModel.put("labTest", labTest);
            }

            if(pcrLog.getMedication() != null && !pcrLog.getMedication().isEmpty()) {
                JsonObject medication = new JsonParser().parse(pcrLog.getMedication()).getAsJsonObject();
                dataModel.put("medication", medication);
            }

            if(pcrLog.getMedication() != null && !pcrLog.getMedication().isEmpty()) {
                JsonObject medication = new JsonParser().parse(pcrLog.getMedication()).getAsJsonObject();
                dataModel.put("ecgRecord", medication);
            }

            if(pcrLog.getMiReport() != null && !pcrLog.getMiReport().isEmpty()) {
                JsonObject miReport = new JsonParser().parse(pcrLog.getMiReport()).getAsJsonObject();
                dataModel.put("miReport", miReport);
            }

            if(pcrLog.getProcedure() != null && !pcrLog.getProcedure().isEmpty()) {
                JsonObject procedure = new JsonParser().parse(pcrLog.getProcedure()).getAsJsonObject();
                dataModel.put("procedure", procedure);
            }

            if(pcrLog.getVital() != null && !pcrLog.getVital().isEmpty()) {
                JsonObject vital = new JsonParser().parse(pcrLog.getVital()).getAsJsonObject();
                dataModel.put("vital", vital);
            }

            if(pcrLog.getDetails() != null && !pcrLog.getDetails().isEmpty()) {
                JsonObject details = new JsonParser().parse(pcrLog.getDetails()).getAsJsonObject();
                dataModel.put("details", details);
            }

            String emailBodytext = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, templateLocation, "UTF-8", dataModel);

//            Email Body container
            Multipart multipart = new MimeMultipart();

//            text part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailBodytext, "text/html");
            multipart.addBodyPart(messageBodyPart);

//            get the pdf file as input stream
            PipedInputStream pdfStream = new PipedInputStream(BUFFER_SIZE);
            PipedOutputStream out = new PipedOutputStream(pdfStream);
            pdfGenerator.getPcrReport(out, dataModel);

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String fileName = "PCR_" + currentDateTime + ".pdf";

//            attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource dataSource = new ByteArrayDataSource(pdfStream, "application/pdf");
            DataHandler aAttachment = new DataHandler(dataSource);
            attachmentPart.setDataHandler(aAttachment);
            attachmentPart.setFileName(fileName);
            attachmentPart.setDisposition(MimeBodyPart.ATTACHMENT);
            multipart.addBodyPart(attachmentPart);

            mimeMessage.setContent(multipart);
        };
    }

    private class MimeMessagePreparatorRunnable implements Runnable {
        private MimeMessagePreparator mimeMessagePreparator;

        private MimeMessagePreparatorRunnable(MimeMessagePreparator mimeMessagePreparator) {
            this.mimeMessagePreparator = mimeMessagePreparator;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p/>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            javaMailSender.send(mimeMessagePreparator);
        }
    }
}
