package templates.config;


import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.VehicleStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class BookMgrQueueListener {

    private final VehicleModelDao vehicleDao;

    @Autowired
    public BookMgrQueueListener(VehicleModelDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @JmsListener(containerFactory = "containerFactory",
            destination = "bookMgrQueueDestination",
            selector = "Operation = 'Create'")
    public void processCreateBookMessage(VehicleStructure vehicleStructure) throws JMSException {
        vehicleDao.save(vehicleStructure);
    }


}