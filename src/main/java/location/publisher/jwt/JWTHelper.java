package location.publisher.jwt;

import com.auth0.jwt.algorithms.Algorithm;

import java.util.Map;

public interface JWTHelper {

    String createJwtToken(Map<String, String> claims);

    boolean verifyJWTToken(String token);

}
