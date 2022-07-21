package jp.co.mindshift.ayakashi.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 20/07/2022<br/>
 */
@Component
@Slf4j
public class Filter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange swe, GatewayFilterChain chain) {
        var request = swe.getRequest();
        log.info("Url: {}", request.getURI().getPath());
        String ipAddress = request.getHeaders().getFirst("X-FORWARDED-FOR");
        if (!StringUtils.hasLength(ipAddress)) {
            var ips = request.getRemoteAddress();
            ipAddress = ips == null ? "" : ips.getHostString();
        }
        log.info("ipAddress: {}", ipAddress);

        var headers = request.getHeaders().entrySet();
        log.info("Headers: {}", headers);

        var params = request.getQueryParams();
        log.info("Params: {}", params);
        return chain.filter(swe)
                .then(Mono.fromRunnable(() -> printBody(swe.getRequest())));
    }
    private void printBody(ServerHttpRequest serverHttpRequest) {
        var method = serverHttpRequest.getMethod();
        serverHttpRequest.getBody().subscribe(dataBuffer -> {
            log.info("AAAAAAAAAAAAAAAAAAAAAAAa");
            if (method != null && HttpMethod.POST.name().equalsIgnoreCase(method.name())) {
                ByteArrayOutputStream baocs = new ByteArrayOutputStream();
                try {
                    Channels.newChannel(baocs).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                    String body = baocs.toString(StandardCharsets.UTF_8);
                    log.info("Request: payload={}", body);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        baocs.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
