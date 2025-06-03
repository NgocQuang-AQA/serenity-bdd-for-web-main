//package bb;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//public class DemoRunEdge {
//    WebDriver driver;
//
//    @BeforeEach
//    void setup() {
//        System.setProperty("webdriver.edge.driver", "C:\\Users\\ADMIN\\Desktop\\serenityBDD_Web_junit5\\serenity-bdd-for-web\\src\\test\\resources\\driver\\windows\\msedgedriver.exe");
//        driver = new EdgeDriver(); // hoặc bạn tự config driver tùy ý
//    }
//
//    @Test
//    void openGoogle() throws InterruptedException {
//        driver.get("https://www.google.com");
//        Thread.sleep(5000);
//    }
//
//    @AfterEach
//    void tearDown() {
////        if (driver != null) {
////            driver.quit();
////        }
//    }
//}
