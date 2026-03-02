package pages;

import com.base.ApplicationCommon;
import com.base.Common;
import com.base.Core;
import org.openqa.selenium.By;

public class HomePage extends Core {

    public static By dropdownHover = By.xpath("(//*[contains(text(),\"Tester’s Hub\")])[1]");

    public static By samplePageTest = By.xpath("(//*[contains(text(),\"Sample Page Test\")])[1]");
    //Will use future
    public static void VerifyTitle(String pageTitle){
        Common.method_verifyPageTitle(pageTitle);
    }

    public static void hover_Testershubdropdown(){
        ApplicationCommon.method_HoverElement(dropdownHover,"Tester’s Hub Dropdown");
    }

    public static void clickSamplePageTest(){
        Common.method_ButtonClick(samplePageTest,"PageTest");
    }
}
