//Copied line

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.base;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Core {

    public static String reportPath = null;
    public static boolean startTest = false;
    public static WebDriver driver;
    public static Scenario test = null;
    public static int globalWaitTime = 10;


    public static void setup(Scenario testSce) {
        test = testSce;
    }

    public static void openWebDriver() {
        try {
            String val = validatemethod();
            if (val.contentEquals("Success")) {
                switch (readProperty("browser")) {
                    case "firefox-incognito":
                        WebDriverManager.firefoxdriver().setup();
                        DesiredCapabilities ffcapsInccognito = new DesiredCapabilities();
                        ffcapsInccognito.setAcceptInsecureCerts(true);
                        FirefoxOptions ffInccognito = new FirefoxOptions();
                        ffInccognito.merge(ffcapsInccognito);
                        ffInccognito.addArguments(new String[]{"--start-maximized"});
                        ffInccognito.addArguments(new String[]{"-private"});
                        driver = new FirefoxDriver(ffInccognito);
                        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                        break;
                        //Adding Incogneto tab
                        //  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
                        //  ChromeOptions options = new ChromeOptions();
                        //  options.addArguments(new String[]{"--ignore-certificate-errors"});
                        //  options.addArguments("--incognito"); // ✅ Open in incognito mode
                        //  driver = new ChromeDriver(options);
                        //  driver.manage().window().maximize();
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments(new String[]{"--ignore-certificate-errors"});
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                        break;
                    case "chrome-incognito":
                        WebDriverManager.chromedriver().setup();
                        DesiredCapabilities chromecapsInccognito = new DesiredCapabilities();
                        chromecapsInccognito.setAcceptInsecureCerts(true);
                        ChromeOptions chromeOptionInccognito = new ChromeOptions();
                        chromeOptionInccognito.merge(chromecapsInccognito);
                        chromeOptionInccognito.addArguments(new String[]{"--start-maximized"});
                        chromeOptionInccognito.addArguments(new String[]{"--incognito"});
                        driver = new ChromeDriver(chromeOptionInccognito);
                        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void method_NavigateToBaseURL() {
        String baseURL = readProperty("baseURL");
        driver.get(baseURL);
    }

    public static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return "[" + dtf.format(now) + "]";
    }

    public static String readProperty(String propertyName) {
        String value = null;
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("./src/main/resources/config.properties");
            prop.load(input);
            value = prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return value;
    }

    public static String readProperty(String propertyFilePath, String propertyName) throws IOException {
        String value = null;
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(propertyFilePath);
            prop.load(input);
            value = prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return value;
    }

    public static void writeProperty(String propertyFilePath, String propertyName, String propoertyValue) {
        try {
            FileInputStream in = new FileInputStream(propertyFilePath);
            Properties props = new Properties();
            props.load(in);
            in.close();
            FileOutputStream out = new FileOutputStream(propertyFilePath);
            props.setProperty(propertyName, propoertyValue);
            props.store(out, (String)null);
            out.close();
        } catch (IOException var6) {
        }

    }

    public static void tearDown(Scenario scenario) {
        try {
            if (test.isFailed()) {
                byte[] screenshot = (byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                test.attach(screenshot, "image/png", "image");
            }

            SessionId session = ((RemoteWebDriver)driver).getSessionId();
            String sessionid = session.toString();
            sessionid.isEmpty();
        } catch (Exception var3) {
        }

    }

    //Inhere License file need to be encripted
    static String validatemethod() throws IOException, NoSuchAlgorithmException {
        String validate = null;
        String productName = readProperty("./src/main/resources/license", "ProductName");
        String productOwner = readProperty("./src/main/resources/license", "ProductOwner");
        String productKey = readProperty("./src/main/resources/license", "ProductKey");
        String version = readProperty("./src/main/resources/license", "Version");
        String developedBy = readProperty("./src/main/resources/license", "DevelopedBy");
        String email = readProperty("./src/main/resources/license", "Email");
        String baseurl = readProperty("./src/main/resources/config.properties", "baseURL");
        if (productName.contentEquals("Kavinda.bdd.test.automation") & productOwner.contentEquals("kavinda") & version.contentEquals("2025") & developedBy.contentEquals("Kavinda Jayaweera") & productKey.contentEquals("a8d7b243b192ba2a1738a5fa673c27b8") & email.contentEquals("kavindajayaweera115@gmail.com") & (baseurl.contains("globalsqa.com") | baseurl.contains("https://demoqa.com"))) {
            validate = "Success";
        } else {
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Invalid license !.\r\n\r\n   This is a property of Kavinda SL.\r\n\r\n   Developed by Kavinda Jayaweera, Email - kavindajayaweera115@gmail.com, Mobile: 0094764192437", "Invalid license", 2);
        }

        return validate;
    }
}
