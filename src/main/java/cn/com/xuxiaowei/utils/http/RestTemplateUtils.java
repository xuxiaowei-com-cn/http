package cn.com.xuxiaowei.utils.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RestTemplate} 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RestTemplateUtils {

    /**
     * 获取指定编码类型的响应数据返回的 {@link RestTemplate}
     * <p>
     * 注意：{@link RestTemplate#getMessageConverters()} 值可能根据环境而不同，如：
     * Spring Web 项目：只存在索引：0-5
     * Spring Boot 项目：不存在索引：5 {@link Jaxb2RootElementHttpMessageConverter}，
     * 增加了索引 {@link MappingJackson2XmlHttpMessageConverter}、{@link MappingJackson2HttpMessageConverter}
     * <p>
     * 为了处理 XML、JSON 在此增加了 {@link MappingJackson2XmlHttpMessageConverter}、{@link MappingJackson2HttpMessageConverter}
     *
     * @param charset 编码，{@link StandardCharsets#UTF_8}
     * @return 返回 获取指定编码类型的响应数据
     * @see StandardCharsets
     * @see ByteArrayHttpMessageConverter 默认值，索引：0，处理：application/octet-stream、*\/*
     * @see StringHttpMessageConverter 默认值，索引：1，处理：text/plain、*\/*，默认编码：{@link StringHttpMessageConverter#DEFAULT_CHARSET}
     * @see ResourceHttpMessageConverter 默认值，索引：2，处理：*\/*
     * @see SourceHttpMessageConverter 默认值，索引：3，处理：application/xml、text/xml、application/*+xml
     * @see AllEncompassingFormHttpMessageConverter 默认值，索引：4，处理：application/x-www-form-urlencoded、multipart/form-data、multipart/mixed
     * @see Jaxb2RootElementHttpMessageConverter 默认值，索引：5，处理：application/xml、text/xml、application/*+xml
     * @see MappingJackson2XmlHttpMessageConverter 手动添加，处理：application/xml;charset=UTF-8、text/xml;charset=UTF-8、application/*+xml;charset=UTF-8
     * @see MappingJackson2HttpMessageConverter 手动添加，处理：application/json、application/*+json
     */
    public static RestTemplate charset(Charset charset) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        boolean containsXml = false;
        boolean containsHttp = false;
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (messageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) messageConverter).setDefaultCharset(charset);
            } else if (messageConverter instanceof MappingJackson2XmlHttpMessageConverter) {
                containsXml = true;
            } else if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                containsHttp = true;
            }
        }
        if (!containsXml) {
            messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        }
        if (!containsHttp) {
            messageConverters.add(new MappingJackson2HttpMessageConverter());
        }
        return restTemplate;
    }

    /**
     * POST 发送请求流 获取响应
     *
     * @param restTemplate {@link RestTemplate}
     * @param url          URL
     * @param map          参数，如：{@link HashMap}、{@link LinkedMultiValueMap}
     * @param mediaType    请求流类型，如：{@link MediaType#APPLICATION_JSON}、{@link MediaType#APPLICATION_XML}
     * @param responseType 响应数据类型
     * @param <T>          响应数据泛型
     * @return 返回 请求结果
     */
    public static <T> ResponseEntity<T> postForEntityStream(RestTemplate restTemplate, String url, Map<?, ?> map,
                                                            @Nullable MediaType mediaType, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求格式
        httpHeaders.setContentType(mediaType);
        HttpEntity<Map<?, ?>> httpEntity = new HttpEntity<>(map, httpHeaders);
        return restTemplate.postForEntity(url, httpEntity, responseType);
    }

    /**
     * POST 发送请求流 获取响应
     *
     * @param restTemplate {@link RestTemplate}
     * @param url          URL
     * @param map          参数，如：{@link HashMap}、{@link LinkedMultiValueMap}
     * @param mediaType    请求流类型，如：{@link MediaType#APPLICATION_JSON}、{@link MediaType#APPLICATION_XML}
     * @param responseType 响应数据类型
     * @param <T>          响应数据泛型
     * @return 返回 请求结果
     */
    public static <T> T postForObjectStream(RestTemplate restTemplate, String url, Map<?, ?> map,
                                            @Nullable MediaType mediaType, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求格式
        httpHeaders.setContentType(mediaType);
        HttpEntity<Map<?, ?>> httpEntity = new HttpEntity<>(map, httpHeaders);
        return restTemplate.postForObject(url, httpEntity, responseType);
    }

    /**
     * POST 发送请求参数 获取响应
     *
     * @param restTemplate {@link RestTemplate}
     * @param url          URL
     * @param map          参数，如：{@link HashMap}、{@link LinkedMultiValueMap}
     * @param mediaType    请求流类型，如：{@link MediaType#APPLICATION_JSON}、{@link MediaType#APPLICATION_XML}
     * @param responseType 响应数据类
     * @param <T>          响应数据泛型
     * @return 返回 请求结果
     */
    public static <T> ResponseEntity<T> postForEntityParam(RestTemplate restTemplate, String url, Map<?, ?> map,
                                                           @Nullable MediaType mediaType, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求格式
        httpHeaders.setContentType(mediaType);
        HttpEntity<Map<?, ?>> httpEntity = new HttpEntity<>(httpHeaders);
        String parameterUrl = parameterUrl(url, map);
        return restTemplate.postForEntity(parameterUrl, httpEntity, responseType);
    }

    /**
     * POST 发送请求参数 获取响应
     *
     * @param restTemplate {@link RestTemplate}
     * @param url          URL
     * @param map          参数，如：{@link HashMap}、{@link LinkedMultiValueMap}
     * @param mediaType    请求流类型，如：{@link MediaType#APPLICATION_JSON}、{@link MediaType#APPLICATION_XML}
     * @param responseType 响应数据类
     * @param <T>          响应数据泛型
     * @return 返回 请求结果
     */
    public static <T> T postForObjectParam(RestTemplate restTemplate, String url, Map<?, ?> map,
                                           @Nullable MediaType mediaType, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求格式
        httpHeaders.setContentType(mediaType);
        HttpEntity<Map<?, ?>> httpEntity = new HttpEntity<>(httpHeaders);
        String parameterUrl = parameterUrl(url, map);
        return restTemplate.postForObject(parameterUrl, httpEntity, responseType);
    }

    /**
     * POST 发送请求流、参数 获取响应
     *
     * @param restTemplate {@link RestTemplate}
     * @param url          URL
     * @param map          参数，如：{@link HashMap}、{@link LinkedMultiValueMap}
     * @param mediaType    请求流类型，如：{@link MediaType#APPLICATION_JSON}、{@link MediaType#APPLICATION_XML}
     * @param responseType 响应数据类
     * @param <T>          响应数据泛型
     * @return 返回 请求结果
     */
    public static <T> ResponseEntity<T> postForEntityStreamParam(RestTemplate restTemplate, String url, Map<?, ?> map,
                                                                 @Nullable MediaType mediaType, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求格式
        httpHeaders.setContentType(mediaType);
        HttpEntity<Map<?, ?>> httpEntity = new HttpEntity<>(map, httpHeaders);
        String parameterUrl = parameterUrl(url, map);
        return restTemplate.postForEntity(parameterUrl, httpEntity, responseType);
    }

    /**
     * POST 发送请求流、参数 获取响应
     *
     * @param restTemplate {@link RestTemplate}
     * @param url          URL
     * @param map          参数，如：{@link HashMap}、{@link LinkedMultiValueMap}
     * @param mediaType    请求流类型，如：{@link MediaType#APPLICATION_JSON}、{@link MediaType#APPLICATION_XML}
     * @param responseType 响应数据类
     * @param <T>          响应数据泛型
     * @return 返回 请求结果
     */
    public static <T> T postForObjectStreamParam(RestTemplate restTemplate, String url, Map<?, ?> map,
                                                 @Nullable MediaType mediaType, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求格式
        httpHeaders.setContentType(mediaType);
        HttpEntity<Map<?, ?>> httpEntity = new HttpEntity<>(map, httpHeaders);
        String parameterUrl = parameterUrl(url, map);
        return restTemplate.postForObject(parameterUrl, httpEntity, responseType);
    }

    /**
     * 根据 URL与参数 获取符合发送请求的 URL
     *
     * @param url URL
     * @param map 参数
     * @return 返回 根据 URL与参数 获取符合发送请求的 URL 结果
     */
    public static String parameterUrl(String url, Map<?, ?> map) {
        // 以下为处理参数与URL
        StringBuilder parameterUrlBuilder = new StringBuilder(url);
        parameterUrlBuilder.append("?");
        for (Map.Entry<?, ?> entries : map.entrySet()) {
            Object key = entries.getKey();
            Object value = entries.getValue();
            parameterUrlBuilder.append(key).append("=").append(value).append("&");
        }
        return parameterUrlBuilder.toString();
    }

}
