package endpoint.vehicleModels;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorcompany.MotorVehicleFactoryApplication;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.messaging.listener.FactoryConsumer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.Random;

import static com.motorcompany.enums.vehicle.Whells.TWO;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


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

    @Test
    @Order(3)
    public void initTests() {

    }

    @Test
    @Order(2)
    public void shouldReturnOnlineStatusAnd200() throws Exception {
        init();
        mvc.perform(get("http://localhost:9000/v1/status")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("Online")));
    }

    @Test
    @Order(1)
    public void PostShouldCreateVehicleModel() throws Exception {
        vehicleModel = mockVehicleModel200("PostShouldCreateVehicleModel");
        byte[] byteArrayVehicleModel = toJson(vehicleModel);

        MvcResult result = mvc.perform(post("http://localhost:9000/v1/vehicleModels")
                .content(byteArrayVehicleModel)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @Order(4)
    public void deleteS() throws Exception {
        MvcResult result = mvc.perform(delete("http://localhost:9000/v1/vehicleModels/" + vehicleModel.getModelCode())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(5)
    public void endTests() {

    }


    public void init(){
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }



    private VehicleModel mockVehicleModel200(String prefix) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setCommercialName("prefix");
        vehicleModel.setEngineDisplacement((short) 200);
        vehicleModel.setModelCode(new Random().nextInt(9));
        vehicleModel.setModelYear(new Date(System.currentTimeMillis()));
        vehicleModel.setNumberPassengers((short) 4);
        vehicleModel.setNumberWheels(TWO);
        vehicleModel.setSteppe(true);
        return vehicleModel;
    }

    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

}

