package es.equiperocket.sci.service;

import es.equiperocket.sci.model.Coordenador;
import es.equiperocket.sci.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private CoordenadorRepository coordenadorRepository;


    public boolean autenticar(String email, String senha) {
        Optional<Coordenador> coordOpt = coordenadorRepository.findByEmail(email);
        if (coordOpt.isPresent()) {
            Coordenador coord = coordOpt.get();
            return coord.getSenha().equals(senha);
        }
        return false;
    }

    public Coordenador buscarCoordenadorPorEmail(String email) {
        return coordenadorRepository.findByEmail(email).orElse(null);
    }
}
