package com.knu.noticesender.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * HTTP request/response logging filter
 * @see #logRequest(RequestWrapper) Request 데이터 로깅
 * @see #logResponse(ContentCachingResponseWrapper)  Response 데이터 로깅
 */
@Order(1)
@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // multipart form data 받을 때 오류 제어
        initRequestPartsIfMultipartFormData(request);

        MDC.put("traceId", UUID.randomUUID().toString().substring(30));
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response), filterChain);
        }
        MDC.clear();
    }

    /**
     * Request Wrapper Class 사용 시 Multipart Form Data 의 getParts() 가 호출되지 않는 문제 발생
     * 따라서 Multipart Form Data 가 전송될 경우 강제로 getParts() 를 호출해 데이터를 초기화
     */
    private static void initRequestPartsIfMultipartFormData(HttpServletRequest request) throws IOException, ServletException {
        if (getMediaType(request.getContentType()).equals(MediaType.MULTIPART_FORM_DATA)) {
            request.getParts();
        }
    }

    /**
     * ContentType 데이터를 바탕으로 MediaType 객체 반환
     * Default MediaType 은 application/json
     */
    private static MediaType getMediaType(String contentType) {
        return MediaType.valueOf(contentType == null ? MediaType.APPLICATION_JSON_VALUE : contentType);
    }

    protected void doFilterWrapped(RequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            logRequest(request);
            filterChain.doFilter(request, response);
        } finally {
            logResponse(response);
            response.copyBodyToResponse();
        }
    }

    private static void logRequest(RequestWrapper request) throws IOException {
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        log.info("Request : {} ip=[{}] uri=[{}] content-type=[{}]",
                request.getMethod(),
                request.getRemoteAddr(),
                queryString == null ? requestURI: requestURI + queryString,
                request.getContentType()
        );
        if (LoggingSupport.toLogPayload(requestURI)) {
            logPayload("Request", request.getContentType(), request.getInputStream());
        }
    }

    private static void logResponse(ContentCachingResponseWrapper response) throws IOException {
        log.info("Response '{}'", response.getStatus());
        logPayload("Response", response.getContentType(), response.getContentInputStream());
    }

    private static void logPayload(String prefix, String contentType, InputStream inputStream) throws IOException {
        boolean visible = isVisible(getMediaType(contentType));
        if (!visible) {
            log.info("{} Payload: Binary Content", prefix);
            return;
        }

        byte[] content = StreamUtils.copyToByteArray(inputStream);
        if (content.length > 0) {
            String contentString = new String(content).replace("\n", "").replaceAll(" ", "");
            log.info("{} Payload: {}", prefix, contentString);
        }
    }

    private static boolean isVisible(MediaType mediaType) {
        final List<MediaType> VISIBLE_TYPES = Arrays.asList(
                MediaType.valueOf("text/*"),
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.valueOf("application/*+json"),
                MediaType.valueOf("application/*+xml"),
                MediaType.MULTIPART_FORM_DATA
        );

        return VISIBLE_TYPES.stream()
                .anyMatch(visibleType -> visibleType.includes(mediaType));
    }

    private static class LoggingSupport {
        private static final Set<String> noPayloadUris;

        /**
         * payload 를 로깅하지 않을 uri 목록.
         */
        static {
            noPayloadUris = new HashSet<>();
            noPayloadUris.add("/notice");
        }

        public static boolean toLogPayload(String requestURI) {
            for (String uri : noPayloadUris) {
                if (requestURI.startsWith(uri)) { return false; }
            }
            return true;
        }
    }
}
