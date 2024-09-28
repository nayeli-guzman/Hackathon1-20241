package dbp.hackathon.mail.domain;

import dbp.hackathon.ticket.domain.Ticket;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailService {



    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine engine;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendHTMLEmail(Ticket ticket) throws MessagingException {

        Context context = new Context();
        context.setVariable("nombrePelicula", ticket.getFuncion().getNombre());
        context.setVariable("fechaFuncion", ticket.getFuncion().getFecha().toString());
        context.setVariable("cantidadEntradas", ticket.getCantidad());
        context.setVariable("precioTotal", ticket.getFuncion().getPrecio());
        context.setVariable("qr", ticket.getQr());

        String process =
                engine.process("file/template/emailTemplate.html", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Ticket comprado: " + ticket.getEstudiante().getName());
        helper.setText(process, true);
        helper.setTo(ticket.getEstudiante().getEmail());
        mailSender.send(mimeMessage);
    }
}