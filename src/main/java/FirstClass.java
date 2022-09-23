import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;

public class FirstClass {
    public static void main(String[] args) throws IOException {

        //reads the excel file.
        FileInputStream file = new FileInputStream("LoginData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet worksheet = workbook.getSheetAt(0);

        String email = null;
        String password = null;

        //gets data from excel.
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = worksheet.getRow(i);
            email = row.getCell(0).getStringCellValue();
            password = row.getCell(1).getStringCellValue();


            System.setProperty("webdriver.chrome.driver", "D:\\Gülçin Özpamuk\\Yazılım\\Selenium-WebDriver\\chromedriver_win32\\chromedriver.exe");//adding chrome driver.
            WebDriver driver = new ChromeDriver();

            driver.manage().window().maximize();//maximize the page.

            driver.get("https://www.amazon.com.tr/");//opens amazon website.

            // checks if the driver is in home page.
            String homeUrl = driver.getCurrentUrl();
            if (homeUrl.equals("https://www.amazon.com.tr/")) {
                System.out.println("home page opened successfully");
            } else {
                System.out.println("failed");
            }

            driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]")).click();//opens login page.

            //checks if the driver is in login page.
            String loginUrl = driver.getCurrentUrl();
            if (loginUrl.equals("https://www.amazon.com.tr/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com.tr%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=trflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&")) {
                System.out.println("login page opened successfully");
            } else {
                System.out.println("failed");
            }


            driver.findElement(By.id("ap_email")).sendKeys(email);//enters given email.
            driver.findElement(By.id("continue")).click();//clicks "devam et" button
            //  driver.findElement(By.id("signInSubmit")).click();//clicks "giriş yap" button

            driver.findElement(By.id("ap_password")).sendKeys(password);//enters given password.
            driver.findElement(By.xpath("//*[@id=\"auth-signin-button\"]/span")).click();

            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("samsung");//writes "samsung" to the search bar.
            driver.findElement(By.id("nav-search-submit-button")).click();//clicks search button.

            driver.findElement(By.xpath("//*[@id=\"n/13709907031\"]/span/a/span")).click();//clicks "cep telefonları" button.

            driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div")).click();//clicks the first product.

            driver.findElement(By.id("add-to-cart-button")).click();//adds the product to the cart.

            driver.findElement(By.xpath("//*[@id=\"sw-gtc\"]/span/a")).click();//goes to cart

            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[3]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[1]/div/div/div[2]/div[1]/span[2]/span/input")).click();//deletes the product from the cart.

            driver.close();
        }
    }
}
