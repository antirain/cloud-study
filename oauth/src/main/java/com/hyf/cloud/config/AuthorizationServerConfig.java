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
        endpoints.authenticationManager(authenticationManagerBean) // ??????????????????????????????
                .tokenStore(tokenStore)//????????????????????????
                .accessTokenConverter(jwtAccessTokenConverter)//??????jwt
                .reuseRefreshTokens(false)//refresh_tokens??????????????????
                .userDetailsService(userService)//??????????????????????????????????????????????????????
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);//??????get???post??????
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //??????????????????
        security.allowFormAuthenticationForClients()
//                .checkTokenAccess("isAuthenticated()")
        ;
    }

//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // ??????????????????????????????oss
//        clients.withClientDetails(clientDetails());
//    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         *???????????????
         *http://localhost:8080/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://www.baidu.com&scope=all
         *http://localhost:8080/oauth/authorize?response_type=code&client_id=client
         *
         * password??????
         * http://localhost:8080/oauth/token?
         username=fox&password=123456&grant_type=password&client_id=client&client_secret=123123&scope=all
         *
         * ???????????????
         * http://localhost:8080/oauth/token?grant_type=client_credentials&scope=all&client_id=client&client_s
         ecret=123123
         */
        clients.inMemory()
                //??????client_id
                .withClient("client")
                //??????client???secret
                .secret(passwordEncoder.encode("123123"))
                //????????????token????????????
                .accessTokenValiditySeconds(48000)
                //????????????token????????????
                .refreshTokenValiditySeconds(864000)
                //??????redirect_uri??????????????????????????????
                .redirectUris("http://www.baidu.com")
                //???????????????????????????
                .scopes("all")
                //??????grant_type?????????????????????
                /**
                 * ??????grant_type?????????????????????
                 * authorization_code: ?????????
                 * password??? ??????
                 * client_credentials: ?????????
                 * refresh_token: ????????????
                 */
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "refresh_token");
    }
}
