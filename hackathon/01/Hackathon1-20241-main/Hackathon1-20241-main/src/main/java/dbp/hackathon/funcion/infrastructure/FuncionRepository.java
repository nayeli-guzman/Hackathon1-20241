package dbp.hackathon.funcion.infrastructure;

import dbp.hackathon.funcion.domain.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {
}
