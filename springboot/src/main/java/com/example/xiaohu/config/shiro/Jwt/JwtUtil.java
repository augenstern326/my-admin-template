package com.example.xiaohu.config.shiro.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class JwtUtil {

    private static final long EXPIRE_TIME = 30*60*1000;

    public static boolean verify(String token,String username,String password){
        try{
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username",username).build();

            DecodedJWT jwt = verifier.verify(token);

            return true;
        }catch(Exception e){
            return false;
        }
    }
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String sign(String username,String password){
        try{
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);

            Algorithm algorithm = Algorithm.HMAC256(password);

            return JWT.create().withClaim("username",username).withExpiresAt(date).sign(algorithm);

        } catch(Exception e){
            return null;
        }
    }
}
