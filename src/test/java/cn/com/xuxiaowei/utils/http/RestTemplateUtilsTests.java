package cn.com.xuxiaowei.utils.http;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * {@link RestTemplateUtils} 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RestTemplateUtilsTests {

    private final String URL = "http://127.0.0.1:8080/map";

    @Test
    public void postForEntityStream() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> map = new HashMap<>();
        map.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityStreamJson = RestTemplateUtils.postForEntityStream(restTemplate, URL, map,
                MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForEntityStreamJson);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityStreamXml = RestTemplateUtils.postForEntityStream(restTemplate, URL, map,
                MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForEntityStreamXml);
    }

    @Test
    public void postForObjectStream() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> map = new HashMap<>();
        map.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        Map postForObjectStreamJson = RestTemplateUtils.postForObjectStream(restTemplate, URL, map,
                MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForObjectStreamJson);

        @SuppressWarnings("rawtypes")
        Map postForObjectStreamXml = RestTemplateUtils.postForObjectStream(restTemplate, URL, map,
                MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForObjectStreamXml);
    }

    @Test
    public void postForEntityParam() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> map = new HashMap<>();
        map.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityParamJson = RestTemplateUtils.postForEntityParam(restTemplate, URL, map,
                MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForEntityParamJson);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityParamXml = RestTemplateUtils.postForEntityParam(restTemplate, URL, map,
                MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForEntityParamXml);
    }

    @Test
    public void postForObjectParam() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> map = new HashMap<>();
        map.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        Map postForObjectParamJson = RestTemplateUtils.postForObjectParam(restTemplate, URL, map,
                MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForObjectParamJson);

        @SuppressWarnings("rawtypes")
        Map postForObjectParamXml = RestTemplateUtils.postForObjectParam(restTemplate, URL, map,
                MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForObjectParamXml);
    }

    @Test
    public void postForEntityStreamParam() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> map = new HashMap<>();
        map.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityStreamParamJson = RestTemplateUtils.postForEntityStreamParam(restTemplate, URL, map,
                MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForEntityStreamParamJson);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityStreamParamXml = RestTemplateUtils.postForEntityStreamParam(restTemplate, URL, map,
                MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForEntityStreamParamXml);
    }

    @Test
    public void postForObjectStreamParam() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> map = new HashMap<>();
        map.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        Map postForObjectStreamParamJson = RestTemplateUtils.postForObjectStreamParam(restTemplate, URL, map,
                MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForObjectStreamParamJson);

        @SuppressWarnings("rawtypes")
        Map postForObjectStreamParamXml = RestTemplateUtils.postForObjectStreamParam(restTemplate, URL, map,
                MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForObjectStreamParamXml);
    }

    @Test
    public void postForEntityStreamOrParam() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> streamMap = new HashMap<>();
        streamMap.put("parma", UUID.randomUUID().toString());
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityStreamParamJson = RestTemplateUtils.postForEntityStreamOrParam(restTemplate,
                URL, streamMap, paramMap, MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForEntityStreamParamJson);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> postForEntityStreamParamXml = RestTemplateUtils.postForEntityStreamOrParam(restTemplate,
                URL, streamMap, paramMap, MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForEntityStreamParamXml);
    }

    @Test
    public void postForObjectStreamOrParam() {
        RestTemplate restTemplate = RestTemplateUtils.charset(StandardCharsets.UTF_8);
        Map<String, String> streamMap = new HashMap<>();
        streamMap.put("parma", UUID.randomUUID().toString());
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("parma", UUID.randomUUID().toString());

        @SuppressWarnings("rawtypes")
        Map postForObjectStreamOrParamJson = RestTemplateUtils.postForObjectStreamOrParam(restTemplate,
                URL, streamMap, paramMap, MediaType.APPLICATION_JSON, Map.class);
        System.out.println(postForObjectStreamOrParamJson);

        @SuppressWarnings("rawtypes")
        Map postForObjectStreamOrParamXml = RestTemplateUtils.postForObjectStreamOrParam(restTemplate,
                URL, streamMap, paramMap, MediaType.APPLICATION_XML, Map.class);
        System.out.println(postForObjectStreamOrParamXml);
    }

}
