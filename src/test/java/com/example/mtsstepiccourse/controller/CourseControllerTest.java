package com.example.mtsstepiccourse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void updateCourseCorrectRu() throws Exception {
        mockMvc.perform(put("/course/2")
                        .content("{\"author\":\"Путин В.В.\",\"title\":\"Русское название\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateCourseIncorrectRuLang() throws Exception {
        mockMvc.perform(put("/course/2")
                        .content("{\"author\":\"Путин В.В.\",\"title\":\"English Correct Title\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Title should write in russian title case mode")));
    }

    @Test
    void createCourseCorrectAnyInEng() throws Exception {
        mockMvc.perform(post("/course")
                        .content("{\"author\":\"Путин В.В.\",\"title\":\"English Correct Title\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createCourseCorrectAnyInRu() throws Exception {
        mockMvc.perform(post("/course")
                        .content("{\"author\":\"Путин В.В.\",\"title\":\"Русский корректный заголовок\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createCourseIncorrectAny() throws Exception {
        mockMvc.perform(post("/course")
                        .content("{\"author\":\"Путин В.В.\",\"title\":\"Лишние  пробелы\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Title should write in title case mode")));
    }

    @Test
    void createCourseIncorrectAnyDigits() throws Exception {
        mockMvc.perform(post("/course")
                        .content("{\"author\":\"Путин В.В.\",\"title\":\"Запрещенные 1 символы\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Title should write in title case mode")));
    }
}