package co.edu.uniquindio.proyectoclinica.utils;

import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.security.SignatureException;

@Component
@RequiredArgsConstructor
public class FiltroToken implements Filter {

    private final JWTUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        String token = getToken(req);
        boolean error = true;

        try {
            if (requestURI.startsWith("/api/pacientes") || requestURI.startsWith("/api/medicos")
                    || requestURI.startsWith("/api/admin")) {

                if (token != null) {
                    Jws<Claims> jws = jwtUtils.parseJwt(token);

                    if ((requestURI.startsWith("/api/pacientes") && !jws.getBody().get("rol").equals("paciente")) ||
                            (requestURI.startsWith("/api/medicos") && !jws.getBody().get("rol").equals("medico")) ||
                            (requestURI.startsWith("/api/admins") && !jws.getBody().get("rol").equals("admin"))) {

                        crearRespuestaError("No tiene permisos para acceder a este recurso", HttpServletResponse.SC_FORBIDDEN, res);

                    } else {
                        error = false;
                    }
                } else {
                    crearRespuestaError("No hay un token", HttpServletResponse.SC_FORBIDDEN, res);
                }

            }else{
                error = false;
            }

        // | SignatureException e
        }catch (MalformedJwtException e){
            crearRespuestaError("El token es incorrecto", HttpServletResponse.SC_INTERNAL_SERVER_ERROR,res);
        }catch (ExpiredJwtException e){
            crearRespuestaError("El token está vencido", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        }catch (Exception e){
            crearRespuestaError(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR,res);
        }

        if (!error){
            chain.doFilter(request,response);
        }
    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }

    private void crearRespuestaError(String mensaje, int codigoError, HttpServletResponse response) throws IOException{
        MensajeDto<String> dto = new MensajeDto<>(true,mensaje);

        response.setContentType("application/json");
        response.setStatus(codigoError);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();

    }
}
