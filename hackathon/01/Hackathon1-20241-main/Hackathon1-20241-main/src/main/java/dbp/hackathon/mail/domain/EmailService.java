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
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendHTMLEmail(Ticket ticket) throws MessagingException {

        final Context ctx = new Context();
        ctx.setVariable("nombrePelicula", ticket.getFuncion().getNombre());
        ctx.setVariable("fechaFuncion", ticket.getFuncion().getFecha());
        ctx.setVariable("precioTotal", ticket.getFuncion().getPrecio());
        ctx.setVariable("qr", ticket.getQr());


        //ctx.setVariable("imageResourceName", imageResourceName);

        final String htmlContent = this.templateEngine.process("html/emailTemplate.html", ctx);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(ticket.getEstudiante().getName());
        helper.setText("Tu ticket ha sido comprado con exito", true);
        helper.setTo(ticket.getEstudiante().getEmail());
        mailSender.send(mimeMessage);
    }
}