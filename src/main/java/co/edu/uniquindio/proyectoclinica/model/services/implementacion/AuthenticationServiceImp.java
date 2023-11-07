package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.LoginDto;
import co.edu.uniquindio.proyectoclinica.model.dto.TokenDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.entities.Paciente;
import co.edu.uniquindio.proyectoclinica.model.entities.Usuario;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoUsuario;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AuthenticationService;
import co.edu.uniquindio.proyectoclinica.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.proyectoclinica.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDto login(LoginDto loginDto) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional <Usuario> usuarioOptional = Optional.ofNullable(usuarioRepositorio.findByEmail(loginDto.email()));

        if(usuarioOptional.isEmpty()){
            throw new Exception("No existe el correo ingresado");
        }

        Usuario usuario = usuarioOptional.get();

        if(usuario.getEstado() == EstadoUsuario.INACTIVO){

        }

        if( !passwordEncoder.matches(loginDto.contrasena(), usuario.getContrasena()) ){
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDto(createToken(usuario));
    }

    private String createToken(Usuario usuario){

        String rol;
        String nombre;
        if( usuario instanceof Paciente){
            rol = "paciente";
            nombre = ((Paciente) usuario).getNombre();
        }else if( usuario instanceof Medico){
            rol = "medico";
            nombre = ((Medico) usuario).getNombre();
        }else{
            rol = "admin";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", usuario.getCedula());

        return jwtUtils.generarToken(usuario.getEmail(), map);
    }


}
