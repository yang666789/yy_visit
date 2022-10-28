package whut.yy.gateway.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import whut.yy.gateway.config.properties.IgnoreWhiteProperties;
import whut.yy.utils.utils.JwtUtils;
import whut.yy.utils.utils.R;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String url = request.getURI().getPath();
        //1.跳过不需要验证的路径
        //放行白名单以及静态资源
        if (ignoreWhite.getWhites().contains(url) || url.contains("static")) {
            return chain.filter(exchange);
        }
        //2.获取token
        String token = request.getHeaders().getFirst("X-Token");
        //3.判断是否为空
        if (token == null || "".equals(token)) {
            return unauthorizedResponse(exchange, "令牌不能为空");
        }
        try {
            JwtUtils.parseToken(token);
            //4.放行
            return chain.filter(exchange);
        } catch (ExpiredJwtException eje) {
            if (eje.getMessage().contains("Allowed clock skew")) {
                return unauthorizedResponse(exchange, "令牌已过期");
            } else {
                return unauthorizedResponse(exchange, "令牌验证失败");
            }
        } catch (Exception e) {
            return unauthorizedResponse(exchange, "令牌验证失败");
        }

    }

    //设置过滤器优先级
    @Override
    public int getOrder() {
        return -1;
    }

    //令牌认证错误响应
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        var response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        R<?> result = R.error(HttpStatus.UNAUTHORIZED.value(), msg);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
