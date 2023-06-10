import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CiJunitTest {

    @Test
    public void testDockerConfig() throws InterruptedException, IOException {

        AlertflexControllerRest scanDockerConfig = new AlertflexControllerRest(
                "http://192.168.1.20:8080/alertflex-ctrl/rest/posture", //url
                1000,// delay
                "DockerConfig", // scan type
                "/root/downloads/altprobe-docker", //target
                "devhost", //host
                "node01", // node
                "5a50c6fe-ef05-49b8-9d21-14567b58d4e7" //project id
        );

        Assert.assertTrue(scanDockerConfig.getAlerts(
                3, // Threshold for critical alerts
                0, // Threshold for major alerts
                0, // Threshold for minor alerts
                0  // Threshold for info alerts
        ));
    }
}
