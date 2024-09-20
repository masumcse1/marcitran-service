package com.ufril.medtran.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author moin
 *
 */
@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html", "/v2/api-docs**").permitAll()
                .antMatchers(
                        HttpMethod.POST,
                        "/v1/createUser",
                        "/v1/users/login",
                        "/v1/users/confirm-signup",
                        "/v1/users/resend-signup-token",
                        "/v1/users/reset-password",
                        "/v1/users/change-password",
                        "/v1/users/social",
                        "/v1/common/email-subscriptions",
                        "/v1/common/contactus",
                        "/v1/newMessage/**",
                        "/v1/newMessage",
                        "/v1/sns/notifications/webs",
						"/v1/createCompany",

                        "/v1/employee/**",

                        "/v1/tag/**",
                        "/v1/dispatch/**",
                        "/v1/shift/**",
                        "/v1/facility/**",
                        "/v1/serviceLevel/**",
                        "/v1/vehicle/**",
                        "/v1/createPatient",
                        "/v1/editPatient",
                        "/v1/payrollLog/**",
                        "/v1/timeClock/**",
                        "/v1/fuelPurchaseLog/**",

                        "/v1/incident/**",
                        "/v1/vehicleMaintenanceLog/**",
                        "/v1/vehicle/**",
                        "/v1/zone/**",
                        "/v1/lead/**",
                        "/v1/event/**",
                        "/v1/journeyLog/**",
                        "/v1/payment/**",
                        "/v1/claim/**",
                        "/v1/checklist/**"
                ).permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/v1/common/privacy-policy",
                        "/v1/newMessage**",
                        "gs-guide-websocket/info",
                        "/v1/chat/**",

                        "/v1/employee/**",

                        "/v1/tag/**",
                        "/v1/dispatch/**",
                        "/v1/shift/**",
                        "/v1/facility/**",
                        "/v1/serviceLevel/**",
                        "/v1/getAllRoles",
                        "/v1/vehicle/**",
                        "/v1/getAllPatients/**",
                        "/v1/patient/**",
                        "/v1/payrollLog/**",
                        "/v1/timeClock/**",
                        "/v1/fuelPurchaseLog/**",
                        "/v1/incident/**",
                        "/v1/vehicleMaintenanceLog/**",
                        "/v1/vehicle/**",
                        "/v1/zone/**",
                        "/v1/lead/**",
                        "/v1/event/**",
                        "/v1/report/**",
                        "/v1/common/**",
                        "/v1/journeyLog/**",
                        "/v1/claim/**",
                        "/v1/checklist/**",
                        "/v1/dropdown/**"
                ).permitAll()
                .antMatchers(
                        HttpMethod.POST,
                        "web/payment"
                ).permitAll()
                .antMatchers("/v1/provider/admin/**").hasAnyRole("admin")
                .antMatchers("/v1/**").authenticated().and()
                .httpBasic()

                .realmName("Marcitran API").and()
                .csrf().disable();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
