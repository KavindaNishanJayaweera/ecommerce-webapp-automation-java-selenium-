//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common extends Core {
    public Common() {
    }

    public static void method_Login(String userName, String password) {
        try {
            test.log("Browser loaded successfully");
        } catch (Exception e) {
            String exception = e.toString();
            test.log(exception);
        }

    }

    public static void method_verifyPageTitle(String ptitle) {
        try {
            String pageTile = driver.getTitle();
            method_AssertEquals_String(pageTile, ptitle);
        } catch (Exception e) {
            String exception = e.toString();
            test.log(exception);
        }

    }

    public static void method_hardPause(int sec) {
        int seconds = 1000 * sec;

        try {
            Thread.sleep((long)seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static int method_GetElementCount(By element) {
        int elementSize = driver.findElements(element).size();
        Assert.assertTrue(elementSize > 0);
        return elementSize;
    }

    public static void method_SwitchToFrame(By element) {
        try {
            WebElement iFrame = driver.findElement(element);
            driver.switchTo().frame(iFrame);
            test.log("Switched to " + element + " successfully");
        } catch (Exception var2) {
            test.log("Switched to " + element + " successfully");
        }

    }

    public static void method_SwitchToParent() {
        try {
            driver.switchTo().defaultContent();
            test.log("Switched parent page successfully");
        } catch (Exception var1) {
        }

    }

    public static void method_VerifyElement(By element, String pageName) {
        String webElement = element.toString();
        method_WaitUntilElementClickable(element, pageName, webElement, globalWaitTime);
        int count = 0;
        test.log("###2  Verify Xpath of page : " + pageName + " , xpath is" + element);

        try {
            count = driver.findElements(element).size();
            if (count > 0) {
                Assert.assertTrue(pageName + ":" + element, count > 0);
            } else {
                test.log("###3 Verify Xpath of page : " + pageName + " , xpath is " + element + " not verified successfully");
                Assert.assertTrue(pageName + ":" + element, count > 0);
            }
        } catch (Exception var5) {
            test.log("ERROR ####4   Web element " + element + " of " + pageName + "is not available ");
            Assert.assertTrue(pageName + ":" + element, count > 0);
            test.log("ERROR ####5   Web element " + element + " of " + pageName + "is not available ");
        }

    }

    public static void method_ButtonClick(By element, By waitElement, String pageName, String elementName) {
        try {
            method_VerifyElement(element, (String)null);
            method_WaitUntilElementClickable(element, pageName, elementName, globalWaitTime);
            driver.findElement(element).click();
            test.log("Web element " + elementName + " of page " + pageName + " clicked successfully ");
            method_VerifyElement(waitElement, (String)null);
            method_WaitUntilElementLoad(waitElement);
        } catch (Exception var5) {
            test.log("Web element " + elementName + " of page " + pageName + " not clicked successfully ");
            Assert.assertTrue(pageName + ":---------------" + element, false);
        }

    }

    public static void method_ButtonClick(By element, String pageName) {
        try {
            method_VerifyElement(element, pageName);
            driver.findElement(element).click();
            test.log("Web element " + element + " of " + pageName + " clicked successfully ");
        } catch (Exception var3) {
            test.log("Web element " + element + "of " + pageName + " not clicked successfully");
            Assert.assertTrue("###1 Fail Web element " + element + "of " + pageName + " not clicked successfully", false);
        }

    }

    public static void method_ButtonClick(By element, String pageName, String elementName) {
        try {
            method_VerifyElement(element, (String)null);
            driver.findElement(element).click();
            test.log("Web element " + elementName + " of page " + pageName + " clicked successfully ");
        } catch (Exception var4) {
            test.log("Web element " + elementName + " of page " + pageName + " not clicked successfully ");
        }

    }

    public static void method_SendKeys(By element, String pageName, String elementName, String value) {
        try {
            method_VerifyElement(element, elementName);
            driver.findElement(element).click();
            test.log("Clicked on field before insert value.Field is ---" + elementName);
            driver.findElement(element).clear();
            test.log("cleared the  field before insert value.Field is ---" + elementName);
            driver.findElement(element).sendKeys(new CharSequence[]{value});
            test.log("The value " + value + " updated to element - " + elementName + " successfully");
        } catch (Exception var5) {
            test.log("The value " + value + " not updated to element - " + elementName + " successfully");
            Assert.assertTrue("Fail ----The value " + value + " not updated to element - " + elementName + " successfully", false);
        }

    }

    public static void method_SendKeys_edit(By element, String pageName, String elementName, String value) {
        try {
            method_VerifyElement(element, elementName);
            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)", new Object[0]);
            driver.findElement(element).click();
            driver.findElement(element).clear();
            driver.findElement(element).sendKeys(new CharSequence[]{value});
            test.log("The value " + value + " updated to element - " + elementName + " successfully");
        } catch (Exception var6) {
            boolean pass = false;
            test.log("The value " + value + " not updated to element - " + elementName + " successfully");
            Assert.assertTrue(pass);
        }

    }

    public static void method_SelectFromDropdown(By element, String pageName, String elementName, String value) {
        try {
            method_VerifyElement(element, elementName);
            Select dropdown = new Select(driver.findElement(element));
            method_hardPause(1);
            dropdown.selectByVisibleText(value);
            String option = dropdown.getFirstSelectedOption().getText();
            Assert.assertEquals(value, option);
            method_hardPause(1);
            test.log("Value of dropdown " + value + " of page " + pageName + " successfully selected");
        } catch (Exception var7) {
            test.log("Value of dropdown " + value + " of page " + pageName + " not successfully selected");
            Select dropdown = new Select(driver.findElement(element));
            String option = dropdown.getFirstSelectedOption().getText();
            Assert.assertEquals(value, option);
        }

    }

    public static void method_ElementAssertFalse(By element) {
        int eCount = method_GetElementCount(element);
        Assert.assertFalse((String)null, eCount > 0);
    }

    public static void method_WaitUntilElementLoad(By element, String pageName, String elementName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            test.log("Sucessfully waited until element " + elementName + " is available");
        } catch (Exception var4) {
            method_ElementAssertFalse(element);
            test.log("waited until element " + elementName + " visible but it was not not found");
        }

    }

    public static void method_WaitUntilElementLoad(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception var2) {
            method_ElementAssertFalse(element);
            test.log("Web element " + element + "  is not available");
        }

    }

    public static void method_WaitUntilElement_Visible(By element, String elementName, int Seconds) {
        WebElement result = null;

        try {
            WebDriverWait wait = new WebDriverWait(driver, (long)Seconds);
            result = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            Assert.assertTrue(result.isDisplayed());
        } catch (Exception var5) {
            Assert.assertTrue(result.isDisplayed());
            test.log("Web element " + elementName + " is not available");
        }

    }

    public static void method_WaitUntilElement_Visible(By element, String pageName, String elementName, int Seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, (long)Seconds);
            WebElement result = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            Assert.assertTrue(result.isDisplayed());
        } catch (Exception var6) {
            test.log("Web element " + elementName + " of page " + pageName + " is not available");
        }

    }

    public static void method_WaitUntilElementClickable(By element, String pageName, String elementName, int Seconds) {
        WebElement result = null;

        try {
            test.log("Waiting to display element " + element);
            WebDriverWait wait = new WebDriverWait(driver, (long)Seconds);
            result = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            test.log("ERROR__ ####1" + element + " ### not displayed");
            Assert.assertNotNull(result);
            test.log(elementName + " not displayed" + e);
            test.log("Web element " + elementName + " of page " + pageName + " is not available");
        }

    }

    public static void method_WaitUntilElementDisappear(By element, String pageName, String elementName, int Seconds) {
        try {
            int count = Seconds;

            for(int i = 0; i < count; ++i) {
                WebElement we = (WebElement)element;
                WebDriverWait wait = new WebDriverWait(driver, 1L);
                wait.until(ExpectedConditions.invisibilityOf(we));
            }
        } catch (Exception var8) {
            method_ElementAssertFalse(element);
            test.log("Web element " + elementName + " of page " + pageName + " is not available");
        }

    }

    public static void method_VirticalScrollingToAnElement_New(By element) {
        int elementCount = 0;

        try {
            WebElement webElement = driver.findElement(element);
            elementCount = method_GetElementCount(element);
            Assert.assertTrue(elementCount > 0);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Center the element in viewport instead of top
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", webElement);

            test.log("Scroll to element " + element + " completed successfully");
            method_hardPause(2);
        } catch (Exception e) {
            Assert.assertTrue(elementCount > 0);
            test.log("Scroll to element " + element + " failed");
        }
    }

    public static void method_VirticalScrollingToAnElement(By element) {
        int elementCount = 0;

        try {
            WebElement webElement = driver.findElement(element);
            elementCount = method_GetElementCount(element);
            Assert.assertTrue(elementCount > 0);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView();", new Object[]{webElement});
            test.log("Scroll down  to  element " + element + " is completed successfully");
            method_hardPause(2);
        } catch (Exception var4) {
            Assert.assertTrue(elementCount > 0);
            test.log("Scroll down  to  element " + element + " is not completed successfully");
        }

    }

    public static String method_getText(By element) {
        String textVal = null;
        method_VerifyElement(element, (String)null);

        try {
            WebDriverWait wait = new WebDriverWait(driver, 60L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            textVal = driver.findElement(element).getText();
        } catch (Exception var3) {
            test.log("Web element " + element + " not clicked successfully");
        }

        return textVal;
    }

    public static void method_AssertEquals_String(String expectedValue, String actualValue) {
        try {
            test.log("Expected value " + expectedValue + " and actual value " + actualValue + " successfully compared ");
            Assert.assertEquals(expectedValue, actualValue);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void method_AssertNotEquals_String(String expectedValue, String actualValue) {
        try {
            test.log("Expected value " + expectedValue + " and actual value " + actualValue + " successfully compared ");
            Assert.assertNotEquals(expectedValue, actualValue);
        } catch (Exception e) {
            System.out.println(e);
        }

        test.log("Expected value " + expectedValue + " and actual value " + actualValue + " not successfully compared ");
    }

    public static void method_AssertEquals_Integer(int expectedValue, int actualValue) {
        try {
            test.log("Expected value " + expectedValue + " and actual value " + actualValue + " successfully compared ");
            Assert.assertEquals((long)expectedValue, (long)actualValue);
        } catch (Exception e) {
            System.out.println(e);
            test.log("Expected value " + expectedValue + " and actual value " + actualValue + " not successfully compared ");
        }

    }

    public static void method_AssertNotEquals_Integer(int expectedValue, int actualValue) {
        try {
            test.log("Expected value " + expectedValue + " and actual value " + actualValue + " successfully compared ");
            Assert.assertNotEquals((long)expectedValue, (long)actualValue);
        } catch (Exception e) {
            System.out.println(e);
        }

        test.log("Expected value " + expectedValue + " and actual value " + actualValue + " not successfully compared ");
    }

    public static void method_AssertNull_String(String expactedValue) {
        try {
            Assert.assertNull(expactedValue);
            test.log("The value is null");
        } catch (Exception e) {
            System.out.println(e);
        }

        test.log("The value is not null");
    }

    public static void method_AssertNull_Integer(int expactedValue) {
        try {
            Assert.assertNull(expactedValue);
        } catch (Exception e) {
            System.out.println(e);
        }

        test.log("The value is not null");
    }

    public static void method_AssertNotNull_String(String expactedValue) {
        try {
            Assert.assertNotNull(expactedValue);
            test.log("The value is not null");
        } catch (Exception e) {
            System.out.println(e);
        }

        test.log("The value is null");
    }

    public static void method_AssertNotNull_Integer(int expactedValue) {
        try {
            Assert.assertNotNull(expactedValue);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void method_AssertTrue_Boolean(Boolean booleanValue_ture) {
        try {
            test.log("The expected value is True but found" + booleanValue_ture);
            Assert.assertTrue(booleanValue_ture);
        } catch (Exception e) {
            System.out.println(e);
        }

        test.log("The value is not true");
    }

    public static void method_AssertFalse_Boolean(Boolean booleanValue_false) {
        try {
            test.log("The expected value is False but found" + booleanValue_false);
            Assert.assertFalse(booleanValue_false);
        } catch (Exception e) {
            System.out.println(e);
            test.log("The value is not false");
        }

    }

    public static String generateRandomNumber(int min, int max) {
        Random r = new Random();
        int result = r.nextInt(max - min) + min;
        String uniqueNumber = String.valueOf(result);
        return uniqueNumber;
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
}