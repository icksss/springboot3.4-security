package com.kr.jikim.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.kr.jikim.filter.CustomGenerFilter;
import com.kr.jikim.filter.CustomOnceFilter;

@Configuration
@EnableWebSecurity(debug = true) //debug = true
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers("/images/**", "/js/**", "/css/**", "lib/**");
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        log.info("security config filterChain1");
        /**
         * 1. DisableEncodeUrlFilter : 첫번째로 위치, URL의 파라미터에 세션ID가 인코딩되어 로그로 유출되는 것을 방지
         *  : http.sessionManagement((manage) -> manage.disable())  비활성 방법 
         * 2. WebAsyncManagerIntegrationFilter : 비동기 작업에서 세션 관리를 지원하는 필터 
         *  : SecurityContextHolder 는 static 으로 1개 이고 내부적으로 LocalThread로 관리된다. 근데, 간혹 서버가 비동기 작업을 해야 되는 경우
         *  ex) 입/출력 처리와 실제 비지니스로직을 처리하는 2개의 Thred로 이루어 지는 경우 SecurityContextHolder 에서 SecurityContext 를 가져오기 어렵다.
         *  이때, WebAsyncManagerIntegrationFilter 를 처리하면 SecurityContext 정보를 잘 가져오게 돕는다.  
         *  Callable 을 사용할때만 적용된다.
         * 3.SecurityContextHolderFilter : 세션에 저장된 SecurityContext를 읽어와서 SecurityContextHolder에 저장하는 필터
         *  : 사용하지 않을 때는 http.securityContext((context) -> context.disable()); 설정
         *  : 커스텀 등록 방법 http.securityContext((context) -> context.securityContextRepository(new RequestAttributeSecurityContextRepository()));
         *  : SecurityContextPersistenceFilter 와 같은 역할이며 버전업이 되면서 SecurityContextPersistenceFilter 변경됨.
         *  : 변경된 내용은 세션값(유저 정보가 변경된경우 기존에는 업데이트를 했는데) 변경후에는 업데이트 하지 않음.
         * 4. HeaderWriterFilter : 응답 헤더에 보안 관련 정보를 추가하는 필터
         *  : 커스덤 http.headers((headers) -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                        .cacheControl(cache -> cache.disable())
                        .contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable())
         * 5. CorsFilter : 커스덤등록 방법 https://docs.spring.io/spring-security/reference/servlet/integrations/cors.html
         * 6. CsrfFilter : CSRF 공격을 방지하는 필터 https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-token-repository
        );
         * 
         * 
         */
        //http.sessionManagement();

        //filter 테스트
        /*
        http
            .authorizeHttpRequests((auth) -> auth.anyRequest().permitAll());

            //GenericFilter 테스트
            http
                .addFilterAfter(new CustomGenerFilter(), LogoutFilter.class);
            
            //OncePerRequestFilter 테스트
            http
                .addFilterAfter(new CustomOnceFilter(), LogoutFilter.class);
        */
        
        /*
        // filterChain1,2 이 모두 /** 에 메핑되어 먼저 동작하는 케이스 이후 filterChain1이 동작하여, filterChain2의 /admin 동작하지 않음
        // http
        //     .authorizeHttpRequests((auth) -> auth
        //             .requestMatchers("/user").permitAll());
        
        http.securityMatchers((auth) -> auth.requestMatchers("/user"));

        http
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/user").permitAll());
        
        // 위에 방식의 간소화 방법
        // http
        //     .securityMatcher("/user")
        //     .authorizeHttpRequests((auth) -> auth.requestMatchers("/user").permitAll());
         */
        return http.build(); 
    }

    /*
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        log.info("security config filterChain2!");
        
        //  http
        //     .authorizeHttpRequests((auth) -> auth
        //             .requestMatchers("/admin").permitAll());

        http.securityMatchers((auth) -> auth.requestMatchers("/admin"));

        http
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/admin").permitAll());

        // http filterChain2 의 간소화 방법
        //     .securityMatcher("/admin")
        //     .authorizeHttpRequests((auth) -> auth.requestMatchers("/admin").permitAll());
        return http.build();
    
    }
         */
}
