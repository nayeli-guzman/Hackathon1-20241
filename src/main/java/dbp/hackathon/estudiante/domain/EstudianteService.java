package dbp.hackathon.estudiante.domain;

import dbp.hackathon.estudiante.infrastructure.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante findById(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        estudianteRepository.deleteById(id);
    }

    public Iterable<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public Estudiante replaceEstudiante(Estudiante newEstudiante, Long id) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setName(newEstudiante.getName());
                    estudiante.setEmail(newEstudiante.getEmail());
                    estudiante.setStudentCode(newEstudiante.getStudentCode());
                    return estudianteRepository.save(estudiante);
                })
                .orElseGet(() -> {
                    return estudianteRepository.save(newEstudiante);
                });
    }


    public Estudiante updateParcialEstudiante(Estudiante newEstudiante, Long id) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    if (newEstudiante.getName() != null) {
                        estudiante.setName(newEstudiante.getName());
                    }
                    if (newEstudiante.getEmail() != null) {
                        estudiante.setEmail(newEstudiante.getEmail());
                    }
                    if (newEstudiante.getStudentCode() != null) {
                        estudiante.setStudentCode(newEstudiante.getStudentCode());
                    }
                    return estudianteRepository.save(estudiante);
        })
        .orElseGet(() -> {
            newEstudiante.setId(id);
            return estudianteRepository.save(newEstudiante);
        });
    }
}
