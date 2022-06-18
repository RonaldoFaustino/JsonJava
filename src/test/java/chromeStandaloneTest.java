import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class chromeStandaloneTest {


    public static void main(String[] args)  {

        URL url = null;
        try {
            url = new URL("http//localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        //RemoteWebDriver
        RemoteWebDriver driver = new RemoteWebDriver(url,cap);

        driver.get("http://google.com");
        System.out.println(driver.getTitle());
    }
}
