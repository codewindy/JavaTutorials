## 公共资源
* 线程池
* redis 
* 通用util工具类
```
https://github.com/techial1042/Blog/issues/59
可以使用 org.springframework.core.io.ClassPathResource 并配合 FileCopyUtils，demo 如下：

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


    public void run() throws IOException {
        byte[] bytes = read("data/user.txt");
        List<String> list = Arrays.stream(new String(bytes).split("\n"))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    private static byte[] read(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return FileCopyUtils.copyToByteArray(resource.getInputStream());
    }

https://github.com/NekoChips/SpringDemo
Spring Boot Mybatis 优雅解决敏感信息加解密问题(https://ld246.com/article/1587991687126)
Spring Boot 使用 Kafka 生产和消费消息(https://www.chenyangjie.com.cn/articles/2020/04/30/1588254024454.html)
```