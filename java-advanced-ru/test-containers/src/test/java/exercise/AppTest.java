package exercise;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.util.IterableUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    // Аннотация отмечает контейнер, который будет автоматически запущен
    @Container
    // Создаём контейнер с СУБД PostgreSQL
    // В конструктор передаём имя образа, который будет скачан с Dockerhub
    // Если не указать версию, будет скачана последняя версия образа
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            // Создаём базу данных с указанным именем
            .withDatabaseName("dbname")
            // Указываем имя пользователя и пароль
            .withUsername("sa")
            .withPassword("sa")
            // Скрипт, который будет выполнен при запуске контейнера и наполнит базу тестовыми данными
            .withInitScript("init.sql");

    // Так как мы не можем знать заранее, какой URL будет у базы данных в контейнере
    // Нам потребуется установить это свойство динамически
    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        // Устанавливаем URL базы данных
        registry.add("spring.datasource.url", database::getJdbcUrl);
        // Имя пользователя и пароль для подключения
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
        // Эти значения приложение будет использовать при подключении к базе данных
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }

    @Test
    void testGetAllPerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/people").contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);


    }

    @Test
    void updatePerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/people").contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);



        ObjectMapper mapper = new ObjectMapper();
        var people = mapper.readValue(response.getContentAsString(), new TypeReference<Iterable<Person>>() {
        });

        Person person = (Person) people.iterator().next();
        long id = person.getId();

        Person newPerson = new Person();
        newPerson.setFirstName("Boris");
        newPerson.setLastName("Demin");

        MockHttpServletResponse updateResponse = mockMvc
                .perform(patch("/people/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newPerson)))
                .andReturn()
                .getResponse();

        assertThat(updateResponse.getStatus()).isEqualTo(200);

        MockHttpServletResponse getTesponse = mockMvc
                .perform(
                        get("/people/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(getTesponse.getStatus()).isEqualTo(200);
        assertThat(getTesponse.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(getTesponse.getContentAsString()).contains("Boris");
    }

    @Test
    void deletePerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/people").contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);



        ObjectMapper mapper = new ObjectMapper();
        var people = mapper.readValue(response.getContentAsString(), new TypeReference<Iterable<Person>>() {
        });

        Person person = (Person) people.iterator().next();
        long id = person.getId();


        MockHttpServletResponse updateResponse = mockMvc
                .perform(delete("/people/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(updateResponse.getStatus()).isEqualTo(200);

        MockHttpServletResponse actualResponse = mockMvc
                .perform(get("/people/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        System.out.println(person.getFirstName());
        assertThat(actualResponse.getStatus()).isEqualTo(200);
        assertThat(actualResponse.getContentAsString()).doesNotContain(person.getFirstName(),
                person.getLastName());
    }
}
