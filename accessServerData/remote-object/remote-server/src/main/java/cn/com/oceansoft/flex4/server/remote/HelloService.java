package cn.com.oceansoft.flex4.server.remote;

import org.springframework.stereotype.Service;

/**
 * Description: Hello Service just for testing
 */
@Service("helloService")
public class HelloService {

    public String hello(String name) {
        return "hello :" + name;
    }

}
