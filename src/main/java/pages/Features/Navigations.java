package pages.Features;

import com.base.Common;
import com.base.Core;
import org.openqa.selenium.By;

public class Navigations extends Core {

    public static By txt_WelcomText = By.xpath("(//*[@class=\"button tiny_button button_pale regular_text\"])[1]");

    public static void method_ClickonTabs() {
        //Common.method_VerifyElement(button_Add_Users, ""); // Verifies the presence of the Add User button.
        Common.method_hardPause(3); // Adds a short pause before interacting with the button.
        Common.method_VirticalScrollingToAnElement_New(txt_WelcomText);
        Common.method_ButtonClick(txt_WelcomText, "Main page"); // Clicks the Add User button.

    }

}
