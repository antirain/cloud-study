package com.hyf.cloud.config;

import com.hyf.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManagerBean) // 使用密码模式必须配置
                .tokenStore(tokenStore)//配置存储令牌策略
                .accessTokenConverter(jwtAccessTokenConverter)//使用jwt
                .reuseRefreshTokens(false)//refresh_tokens是否重复使用
                .userDetailsService(userService)//刷新令牌授权是否包含对用户信息的检查
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);//支持get和post请求
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 读取数据库的配置使用oss
        clients.withClientDetails(clientDetails());
    }
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        /**
//         *授权码模式
//         *http://localhost:8080/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://www.baidu.com&scope=all
//         *http://localhost:8080/oauth/authorize?response_type=code&client_id=client
//         *
//         * password模式
//         * http://localhost:8080/oauth/token?
//         username=fox&password=123456&grant_type=password&client_id=client&client_secret=123123&scope=all
//         *
//         * 客户端模式
//         * http://localhost:8080/oauth/token?grant_type=client_credentials&scope=all&client_id=client&client_s
//         ecret=123123
//         */
//        clients.inMemory()
//                //配置client_id
//                .withClient("client")
//                //配置client‐secret
//                .secret(passwordEncoder.encode("123123"))
//                //配置访问token的有效期
//                .accessTokenValiditySeconds(48000)
//                //配置刷新token的有效期
//                .refreshTokenValiditySeconds(864000)
//                //配置redirect_uri，用于授权成功后跳转
//                .redirectUris("http://www.baidu.com")
//                //配置申请的权限范围
//                .scopes("all")
//                //配置grant_type，表示授权类型
//                /**
//                 * 配置grant_type，表示授权类型
//                 * authorization_code: 授权码
//                 * password： 密码
//                 * client_credentials: 客户端
//                 * refresh_token: 更新令牌
//                 */
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "refresh_token");
//    }
}
