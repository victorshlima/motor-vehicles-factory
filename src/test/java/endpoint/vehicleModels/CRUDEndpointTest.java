package endpoint.vehicleModels;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.motorcompany.MotorVehicleFactoryApplication;
import com.motorcompany.domain.VehicleModel;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.Random;

import static com.motorcompany.enums.vehicle.Whells.TWO;
import static org.hamcrest.Matchers.*;
import static org.mockito.AdditionalMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = MotorVehicleFactoryApplication.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CRUDEndpointTest
{


@Autowired
 WebApplicationContext context;

private  MockMvc mvc;

  VehicleModel vehicleModel;

    {
        vehicleModel = new VehicleModel();
    }

    @Test
    void init() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();

    }





    @Nested
    @DisplayName("CRUD test - Create VehicleModel ")
    class WhenNew {
        @Test
        void getReturnEmpty() throws Exception {
            mvc.perform(get("http://localhost:9000/v1/vehicleModels/" + vehicleModel.getModelCode())
                    .accept(MediaType.APPLICATION_JSON))
                    //.andExpect(status().isNotFound())
                    .andExpect(content().string(""));
        }


        @Nested
        @DisplayName("CRUD test - Create VehicleModel ")
        class Posting {

            @Test
            void PostShouldCreateVehicleModel() throws Exception {
                vehicleModel = mockVehicleModel200("PostShouldCreateVehicleModel");
                byte[] byteArrayVehicleModel = toJson(vehicleModel);

                 mvc.perform(post("http://localhost:9000/v1/vehicleModels")
                        .content(byteArrayVehicleModel)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn();
            }

            @Nested
            @DisplayName("after posting an element")
            class AfterPushing {

                @SneakyThrows
                @Test
                void deleteS()  {
                    mvc.perform(delete("http://localhost:9000/v1/vehicleModels/" + vehicleModel.getModelCode())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();
                }

                @Nested
                @DisplayName("Confirm Deletion")
                class AfterDelete {
                   // @Test(expected = NotExistDaoException.class)
                    @Test
                    public void AlredyDeletedShoudReturnError() throws Exception {
                        mvc.perform(delete("http://localhost:9000/v1/vehicleModels/" + vehicleModel.getModelCode())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound())
                                .andReturn();
                    }

                }

            }

            @Test
            public void endTests() {

            }
        }
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

//    private long getResourceIdFromUrl(String locationUrl) {
//        String[] parts = locationUrl.split("/");
//        return Long.valueOf(parts[parts.length - 1]);
//    }

    @SneakyThrows
    private byte[] toJson(Object r) {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

}

