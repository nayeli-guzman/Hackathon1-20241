package dbp.hackathon.funcion.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dbp.hackathon.ticket.domain.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private LocalDateTime fecha;

    private Integer duracion;

    private Double precio;

    private Integer stock;

    @JsonBackReference
    @OneToMany(mappedBy = "funcion")
    private List<Ticket> tickets;

}
