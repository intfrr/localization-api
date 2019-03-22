package com.maruf.i18n.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maruf.i18n.security.auth.ajax.LoginRequest;
import com.maruf.i18n.security.auth.jwt.extractor.JwtHeaderTokenExtractor;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class AbstractIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    private DataSource dataSource;

    public JacksonJsonParser jsonParser;

    public MockMvc mvc;

    public String token;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();

        jsonParser = new JacksonJsonParser();
        //updateDb("/src/main/resources/liquibase/changelog/changelog-test-data.sql");
    }

    public String obtainAccessToken(String username, String password) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest(username, password);

        ResultActions result
                = this.mvc.perform(post("/api/auth/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }

    public String getResponseAsString(String url) throws Exception{
        return mvc.perform(get(url)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    public String getObjectAsString(Object o)throws Exception{
        return objectMapper.writeValueAsString(o);
    }

    public String getBearer(String token){
        return JwtHeaderTokenExtractor.HEADER_PREFIX + token;
    }

/*
    // Dn not delete this code
    private void updateDb(String changeLog){
        try {
            String filePath = System.getProperty("user.dir") + changeLog;
            Connection c = dataSource.getConnection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            Liquibase liquibase = new Liquibase(new File(filePath).getAbsolutePath(), new FileSystemResourceAccessor(),  database);
            liquibase.update("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}