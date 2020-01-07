package endpoint.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorcompany.MotorVehicleFactoryApplication;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.messaging.listener.FactoryConsumer;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.Random;

import static com.motorcompany.enums.vehicle.Whells.TWO;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MotorVehicleFactoryApplication.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatusEndpointTest
{

@InjectMocks
FactoryConsumer factoryConsumer;

@Autowired
 WebApplicationContext context;

private  MockMvc mvc;
    VehicleModel vehicleModel;

    {
        vehicleModel = new VehicleModel();
    }


    @BeforeAll
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnOnlineStatusAnd200() throws Exception {
    mvc.perform(get("http://localhost:9000/v1/status")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("Online")));
    }


}

