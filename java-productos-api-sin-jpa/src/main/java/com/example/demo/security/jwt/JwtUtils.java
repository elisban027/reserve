package com.example.demo.security.jwt;

import com.example.demo.security.services.UserDetailsImpl; // ASEGÚRATE de que esta importación sea CORRECTA para tu UserDetailsImpl

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException; // Esta es la importación CORRECTA
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders; // Necesario para decodificar la clave Base64
import io.jsonwebtoken.security.Keys; // Necesario para generar y manejar SecretKey
import io.jsonwebtoken.security.SignatureException; // Necesario para manejar el error de firma JWT

import jakarta.annotation.PostConstruct; // Para Spring Boot 3.x y Java 17+. Si usas versiones anteriores, sería javax.annotation.PostConstruct
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey; // Tipo para la clave secreta
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    // Inyecta la clave secreta del archivo application.properties
    @Value("${app.jwtSecret}")
    private String jwtSecretString;

    // Inyecta el tiempo de expiración del token del archivo application.properties
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Esta variable contendrá la clave segura para firmar/verificar JWTs
    private SecretKey secretKey;

    // Este método se ejecuta automáticamente después de que las propiedades (@Value) sean inyectadas
    @PostConstruct
    public void init() {
        try {
            // Decodifica la clave Base64 del String a un array de bytes
            byte[] keyBytes = Decoders.BASE64.decode(jwtSecretString);
            // Crea una SecretKey segura a partir de los bytes para el algoritmo HMAC SHA
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);

            // Opcional: una verificación extra del tamaño de la clave para asegurarte de que es HS512
            // Aunque si la generaste con Keys.secretKeyFor(SignatureAlgorithm.HS512), ya será correcta.
            if (this.secretKey.getEncoded().length * 8 < 512) {
                logger.warn("¡Advertencia! La clave JWT decodificada es demasiado corta para HS512 (menos de 512 bits).");
                logger.warn("Asegúrate de que 'app.jwtSecret' en application.properties sea una clave Base64 de al menos 64 caracteres.");
                // Podrías lanzar una excepción aquí si quieres que la aplicación falle si la clave es débil
                // throw new IllegalArgumentException("La clave JWT proporcionada es demasiado débil para HS512.");
            }

        } catch (IllegalArgumentException e) {
            // Esto ocurre si la cadena en app.jwtSecret no es una Base64 válida o está vacía
            logger.error("Error al decodificar la clave JWT de 'app.jwtSecret' en application.properties. " +
                    "Asegúrate de que sea una cadena Base64 válida y que no esté vacía. " +
                    "Ejecuta el programa GeneradorClaveJWT.java para obtener una.", e);
            // Si no podemos inicializar la clave, la aplicación no funcionará correctamente.
            // Es mejor lanzar una excepción para que el problema sea evidente de inmediato.
            throw new RuntimeException("Fallo al inicializar la clave JWT. Revisa 'app.jwtSecret'.", e);
        }
    }

    /**
     * Genera un token JWT para el usuario autenticado.
     * @param authentication El objeto Authentication que contiene los detalles del usuario.
     * @return El token JWT generado.
     */
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())) // El "sujeto" del token, generalmente el nombre de usuario
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Fecha de expiración
                .signWith(secretKey, SignatureAlgorithm.HS512) // Firma el token con nuestra clave segura y HS512
                .compact(); // Construye el token y lo convierte en una cadena compacta
    }

    /**
     * Extrae el nombre de usuario del token JWT.
     * @param token El token JWT.
     * @return El nombre de usuario.
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // Establece la clave para verificar la firma
                .build()
                .parseClaimsJws(token) // Parsea el token (y verifica la firma)
                .getBody() // Obtiene el "cuerpo" (payload) del token
                .getSubject(); // Obtiene el sujeto (nombre de usuario)
    }

    /**
     * Valida un token JWT.
     * @param authToken El token JWT a validar.
     * @return true si el token es válido, false en caso contrario.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken);
            return true; // Si no hay excepción, el token es válido
        } catch (MalformedJwtException e) {
            logger.error("Token JWT inválido (MalformedJwtException): {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT ha expirado (ExpiredJwtException): {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT no soportado (UnsupportedJwtException): {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("La cadena del token JWT está vacía (IllegalArgumentException): {}", e.getMessage());
        } catch (SignatureException e) { // Esta excepción se lanza si la firma no coincide
            logger.error("Firma JWT inválida (SignatureException): {}", e.getMessage());
        }

        return false; // Si alguna excepción ocurre, el token no es válido
    }
}