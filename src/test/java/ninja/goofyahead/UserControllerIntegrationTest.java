package ninja.goofyahead;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getUserPublic() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/external_user.json");
        String jsonInput = IOUtils.toString(fis, "UTF-8");

        MvcResult result = mockMvc.perform(get("/user/3"))
                .andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(jsonInput, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void storeUserPublic() throws Exception {
        FileInputStream fisInternalUser = new FileInputStream("src/test/resources/internal_user.json");
        String internalUser = IOUtils.toString(fisInternalUser, "UTF-8");

        FileInputStream fisExternalUser = new FileInputStream("src/test/resources/internal_ignore_id_user.json");
        String externalUser = IOUtils.toString(fisExternalUser, "UTF-8");

        MvcResult result = mockMvc.perform(
                post("/user")
                        .content(internalUser)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(externalUser, result.getResponse().getContentAsString(), true);
    }
}
