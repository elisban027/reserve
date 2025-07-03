package com.example.demo; // Puedes cambiar este paquete si tu carpeta principal es diferente

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Encoders;
import javax.crypto.SecretKey;

public class GeneradorClaveJWT {
    public static void main(String[] args) {
        // Genera una clave segura de 512 bits para el algoritmo HS512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Codifica la clave binaria en una cadena Base64
        String base64EncodedKey = Encoders.BASE64.encode(key.getEncoded());

        System.out.println("-----------------------------------------------------------------");
        System.out.println("¡NUEVA CLAVE JWT GENERADA! (Cópiala y pégala en application.properties):");
        System.out.println(base64EncodedKey);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Esta clave debería tener al menos 86 caracteres para HS512.");
        System.out.println("¡NO compartas esta clave y bórrala de aquí una vez la hayas usado!");
    }
}