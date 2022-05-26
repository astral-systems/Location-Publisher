package location.publisher.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTHelperProcessor implements JWTHelper {

    private static final String SECRET = "vishal";

    @Override
    public String createJwtToken(Map<String, String> claims) {
        var builder = JWT.create();
        var headerMap = new HashMap<String, Object>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
        builder.withHeader(headerMap);
        String[] pos = {"72.2", "26.1"};
        builder.withArrayClaim("pos", pos);
        builder.withClaim("access_key", SECRET);
        builder.withIssuedAt(Date.from(Instant.now()));
        var token = builder.sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    @Override
    public boolean verifyJWTToken(String token) {
        var decodedToken = JWT.decode(token);
        var key = decodedToken.getClaim("access_key").asString();
        var algo = Algorithm.HMAC256(key);
        algo.verify(decodedToken);
        return true;
    }

}
