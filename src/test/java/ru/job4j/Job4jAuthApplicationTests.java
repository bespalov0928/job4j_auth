package ru.job4j;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.Person;
import ru.job4j.service.PersonService;

@SpringBootTest(classes = Job4jAuthApplication.class)
@AutoConfigureMockMvc
class Job4jAuthApplicationTests {

    @MockBean
    private PersonService persons;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    public void findAll() throws Exception {
        this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void findById() throws Exception {
        this.mockMvc.perform(get("/person/?id=3"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    public void create() throws Exception {
        Person person = new Person("login", "password");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(person);

        this.mockMvc.perform(post("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        verify(persons).save(argument.capture());
        assertThat(argument.getValue().getLogin(), is("login"));
    }

    @Test
    @WithMockUser
    public void update() throws Exception{

        Person person = new Person(1, "ivan", "1234");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(person);

        this.mockMvc.perform(put("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        verify(persons).save(argument.capture());
        assertThat(argument.getValue().getPassword(), is("1234"));

    }

}
