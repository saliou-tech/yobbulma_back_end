package com.Yobulma.Yobulma.Configuration;

import com.Yobulma.Yobulma.Service.CustomUserDetailService;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey="jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0" ;
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h
@Autowired
    private CustomUserDetailService customUserDetailService;
@PostConstruct
    protected void init(){
/*
    secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
*/
}

public String createToken(String username){
    Claims claims= Jwts.claims().setSubject(username);
    //claims.put("roles", set);
    Date now=new Date();
    Date validity=new Date(now.getTime()+validityInMilliseconds*1000);
    return
            Jwts.builder().
                    signWith(SignatureAlgorithm.HS512,secretKey+"jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0")
                  //  .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .compact();


}

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }


    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey+"jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0").parseClaimsJws(token).getBody().getSubject();

    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey+"jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0").parseClaimsJws(token);
           /* if (claims.getBody(). getExpiration().before(new Date())) {
                return false;
            }

            */
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT token"+e);

        }

    }
}
