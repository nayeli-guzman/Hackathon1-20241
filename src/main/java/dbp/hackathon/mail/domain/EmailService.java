package dbp.hackathon.mail.domain;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public static final String MAIL_TO_STUDENTS =
                    """
                    <!DOCTYPE html>
                    <html>
                    <head>
                      <title>¡Gracias por tu compra!</title>
                    </head>
                    <body>
                      <h1>¡Gracias por tu compra!</h1>
                      <p>¡Hola {{nombre}}! Te informamos que tu compra ha sido exitosa. A continuación, te presentamos los detalles de tu compra:</p>
                      <ul>
                        <li>Nombre de la película: {{nombrePelicula}}</li>
                        <li>Fecha de la función: {{fechaFuncion}}</li>
                        <li>Cantidad de entradas: {{cantidadEntradas}}</li>
                        <li>Precio total: {{precioTotal}}</li>
                        <li>Código QR: <img src="{{qr}}"></li>
                      </ul>
                      <p>¡No olvides llevar tu código QR impreso o en tu dispositivo móvil para poder ingresar a la función! ¡Te esperamos!</p>
                    </body>
                    </html>
                    """;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendHTMLEmail(String to, String subject, String html) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);
        helper.setText(html, true);
        helper.setTo(to);
        mailSender.send(mimeMessage);
    }
}