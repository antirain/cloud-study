//package com.hyf.cloud.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//
//@Configuration
//@EnableResourceServer  // 开启资源服务器功能
//@EnableWebSecurity  // 开启web访问安全
//public class ResourceServerConfiger extends ResourceServerConfigurerAdapter {
//
//    private String sign_key = "lagou123"; // jwt签名密钥
//
//    /**
//     * 该方法用于定义资源服务器向远程认证服务器发起请求，进行token校验等事宜
//     *
//     * @param resources
//     * @throws Exception
//     */
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//
//        // 设置当前资源服务的资源id
//        resources.resourceId("autodeliver");
//        // 定义token服务对象（token校验就应该靠token服务对象）
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        // 校验端点/接口设置
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:7787/oauth/check_token");
//        // 携带客户端id和客户端安全码
//        remoteTokenServices.setClientId("client_lagou");
//        remoteTokenServices.setClientSecret("abcxyz");
//
//        // 别忘了这一步
//        resources.tokenServices(remoteTokenServices);
//    }
//
//
//    /**
//     * 场景：一个服务中可能有很多资源（API接口）
//     * 某一些API接口，需要先认证，才能访问
//     * 某一些API接口，压根就不需要认证，本来就是对外开放的接口
//     * 我们就需要对不同特点的接口区分对待（在当前configure方法中完成），设置是否需要经过认证
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // 设置session的创建策略（根据需要创建即可）
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/autodeliver/**").authenticated() // autodeliver为前缀的请求需要认证
//                .antMatchers("/demo/**").authenticated()  // demo为前缀的请求需要认证
//                .anyRequest().permitAll();  //  其他请求不认证
//    }
//
//}
