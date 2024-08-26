package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.AuthControllers;
import com.example.demo.pojo.User;
import com.example.demo.repository.UserRepository;

@WebMvcTest(AuthControllers.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthControllers authenticationController;

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(userRepository.save(user)).thenReturn(user);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"password\"}"))
                .andExpect(status().isOk());
    }
}

