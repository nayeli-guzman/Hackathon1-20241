package dbp.hackathon.ticket.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QrCodeService {

    private static final String QR_API_URL = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";

    public String generateQRCode(String data) {
        RestTemplate restTemplate = new RestTemplate();
        return QR_API_URL + data;
    }
}
