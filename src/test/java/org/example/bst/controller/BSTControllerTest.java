package org.example.bst.controller;

import org.example.bst.model.TreeEntry;
import org.example.bst.service.BSTService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BSTController.class)
class BSTControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BSTService service;

    @Test
    void processNumbersReturnsJson() throws Exception {
        when(service.processNumbers("10,5,15", false))
                .thenReturn(new TreeEntry("10,5,15", "{\"value\":10}"));

        mvc.perform(post("/process-numbers")
                        .param("numbers", "10,5,15")
                        .param("balanced", "false"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }
}
