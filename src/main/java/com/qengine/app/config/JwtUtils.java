package com.qengine.app.config;

import com.qengine.app.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author sergey
 */
@Component
public class JwtUtils {

    private final String SECRET_KEY = "sfHs6fHWodsWAFwdw27eN36FZI8of2e84542h8Ea0ofnr0F8De5453q4Fe0g3pi";
    private final long EXPIRATION_TIME = 86400000;
    
    public String generateToken(User user){
        return Jwts.builder().
                setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
