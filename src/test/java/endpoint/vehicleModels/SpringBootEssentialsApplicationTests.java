//package endpoint.vehicleModels;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.motorcompany.MotorVehicleFactoryApplication;
//import com.motorcompany.dao.VehicleModelDao;
//import com.motorcompany.domain.VehicleModel;
//import com.motorcompany.messaging.listener.FactoryConsumer;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.sql.Date;
//import java.util.Random;
//
//import static com.motorcompany.enums.vehicle.Whells.TWO;
//import static org.hamcrest.Matchers.*;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest(classes = MotorVehicleFactoryApplication.class)
//@ActiveProfiles("test")
//public class SpringBootEssentialsApplicationTests {
//
//    @InjectMocks
//    FactoryConsumer factoryConsumer;
//
//    @Autowired
//    WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @BeforeAll
//    public void initTests() {
//        MockitoAnnotations.initMocks(this);
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//
//    @Test
//    @DisplayName("Verify if its empty")
//    @Order(1)
//    public void GetshouldHaveEmptyDB() throws Exception {
//        VehicleModel vehicleModel = new VehicleModel();
//        mvc.perform(get("http://localhost:9000/v1/vehicleModels")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", is(empty())));
//    }
//
//
//    @Test
//    @Order(2)
//    public void PostShouldCreateVehicleModel() throws Exception {
//        VehicleModel vehicleModel = new VehicleModel();
//
//        vehicleModel = mockVehicleModel200("PostShouldCreateVehicleModel");
//        byte[] byteArrayVehicleModel = toJson(vehicleModel);
//
//        MvcResult result = mvc.perform(post("http://localhost:9000/v1/vehicleModels")
//                .content(byteArrayVehicleModel)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//    }
//
//    @Test
//    @Order(3)
//    public void PostShoulCreateAndAtualizeVehicleModel() throws Exception {
//        VehicleModel vehicleModel = mockVehicleModel200("PostShouldHaveEmptyDB");
//        byte[] byteArrayVehicleModel = toJson(vehicleModel);
//        // validar a mudança no valor
//        mvc.perform(post("http://localhost:9000/v1/vehicleModels")
//                .content(byteArrayVehicleModel)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.steppe", equalTo(true)));
//        vehicleModel.setSteppe(false);
//        byteArrayVehicleModel = toJson(vehicleModel);
//
//        MvcResult result = mvc.perform(put("http://localhost:9000/v1/vehicleModels/" + vehicleModel.getModelCode())
//                .content(byteArrayVehicleModel)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.modelCode", equalTo(vehicleModel.getModelCode())))
//                .andExpect(jsonPath("$.steppe", equalTo(false)))
//                .andReturn();
//        // long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());
//    }
//
//    private VehicleModel mockVehicleModel200(String prefix) {
//        VehicleModel vehicleModel = new VehicleModel();
//        vehicleModel.setCommercialName("prefix");
//        vehicleModel.setEngineDisplacement((short) 200);
//        vehicleModel.setModelCode(new Random().nextInt(9));
//        vehicleModel.setModelYear(new Date(System.currentTimeMillis()));
//        vehicleModel.setNumberPassengers((short) 4);
//        vehicleModel.setNumberWheels(TWO);
//        vehicleModel.setSteppe(true);
//        return vehicleModel;
//    }
//
//    private long getResourceIdFromUrl(String locationUrl) {
//        String[] parts = locationUrl.split("/");
//        return Long.valueOf(parts[parts.length - 1]);
//    }
//
//    private byte[] toJson(Object r) throws Exception {
//        ObjectMapper map = new ObjectMapper();
//        return map.writeValueAsString(r).getBytes();
//    }
//
//}