package endpoint.vehicleModels;

import com.motorcompany.MotorVehicleFactoryApplication;
import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.messaging.listener.FactoryConsumer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MotorVehicleFactoryApplication.class)
@ActiveProfiles("test")
public class SpringBootEssentialsApplicationTests {

@InjectMocks
FactoryConsumer factoryConsumer;

@Autowired
WebApplicationContext context;

private MockMvc mvc;


    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void GetshouldHaveEmptyDB() throws Exception {
        VehicleModel vehicleModel = new VehicleModel();

        mvc.perform(get("http://localhost:9000/v1/vehicleModels")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(empty())));
        // is(not(empty()))) for validate only if has content
    }

    @Test
    public void PostShouldReturn() throws Exception {
        VehicleModel vehicleModel = new VehicleModel();
        MvcResult result = mvc.perform(post("http://localhost:9000/v1/vehicleModels")
                .content(vehicleModel)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is(empty())));
        // is(not(empty()))) for validate only if has content
    }


}
