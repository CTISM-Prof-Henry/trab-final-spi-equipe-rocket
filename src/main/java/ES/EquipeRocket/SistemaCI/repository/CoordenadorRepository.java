package ES.EquipeRocket.SistemaCI.repository;

import ES.EquipeRocket.SistemaCI.model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
    Optional<Coordenador> findByEmail(String email);
}
