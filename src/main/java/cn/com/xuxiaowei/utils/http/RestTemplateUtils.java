package cn.com.xuxiaowei.utils.http;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * {@link RestTemplate} 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RestTemplateUtils {

    /**
     * 获取指定编码类型的响应数据返回的 {@link RestTemplate}
     *
     * @param charset 编码，{@link StandardCharsets#UTF_8}
     * @return 返回 获取指定编码类型的响应数据
     * @see StandardCharsets
     * @see ByteArrayHttpMessageConverter {@link RestTemplate#getMessageConverters()} 默认值，索引：0
     * @see StringHttpMessageConverter {@link RestTemplate#getMessageConverters()} 默认值，索引：1，默认编码：{@link StringHttpMessageConverter#DEFAULT_CHARSET}
     * @see ResourceHttpMessageConverter {@link RestTemplate#getMessageConverters()} 默认值，索引：2
     * @see SourceHttpMessageConverter {@link RestTemplate#getMessageConverters()} 默认值，索引：3
     * @see AllEncompassingFormHttpMessageConverter 索引：4
     * @see Jaxb2RootElementHttpMessageConverter 索引：5
     */
    public static RestTemplate charset(Charset charset) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.set(1, new StringHttpMessageConverter(charset));
        return restTemplate;
    }

}
