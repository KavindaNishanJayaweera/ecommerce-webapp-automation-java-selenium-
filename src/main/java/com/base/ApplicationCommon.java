package com.base;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * The ApplicationCommon class provides a set of utility methods to interact with web elements on a page.
 * These methods are specifically designed to automate the process of entering data into text fields,
 * selecting values from dropdown menus, and interacting with date pickers in a web application.
 * The methods allow for flexibility, such as entering data with or without modification, selecting random dates
 * within a certain range, and handling data tables for batch data entry.
 * <p>
 * The primary purpose of this class is to streamline form automation by reducing repetitive code and
 * improving maintainability in test scripts.
 * Author: Kavinda Jayaweera
 */
public class ApplicationCommon extends Core {

    /**
     * Enters data into a text field using the 'formcontrolname' attribute, appending a random string to the provided value.
     *
     * @param elementID The form control name of the text field.
     * @param dataValue The base value to input into the text field.
     */
    public static void method_EnterDataToTextField(String elementID, String dataValue) {
        By filedName = By.xpath("//input[@formcontrolname='" + elementID + "']");
        String randomStr = method_GenerateRandomString(); // Generate a random string to append to the value.
        String propertyName = dataValue + "_" + randomStr; // Combine the base value and random string.
        Common.method_SendKeys(filedName, "", elementID, propertyName); // Input the generated value into the field.
    }


    /**
     * Enters data into a text field based on the given controller type and element identifier.
     * The method dynamically selects the locator strategy (`formcontrolname`, `id`, or `name`),
     * appends a random string to the input value to ensure uniqueness, and then sends the value
     * to the identified field.
     *
     * @param elementID      The identifier of the field (formControlName, id, or name).
     * @param dataValue      The base value to be entered in the field.
     * @param controllerType The type of locator to use ("formController", "id", or "name").
     */
    public static void method_EnterDataToTextField(String elementID, String dataValue, String controllerType, boolean isRandomGenerateRequired) {
        By fieldElement = null; // Locator for the input field.

        // Determine the locator strategy based on the controller type.
        switch (controllerType) {
            case "formController":
                // Locate element using formcontrolname attribute.
                fieldElement = By.xpath("//input[@formcontrolname='" + elementID + "']");
                break;
            case "id":
                // Locate element using id attribute.
                fieldElement = By.xpath("//*[@id='" + elementID + "']");
                break;
            case "name":
                // Locate element using name attribute.
                fieldElement = By.xpath("//*[@name='" + elementID + "']");
                break;
            case "placeholder":
                // Locate element using name attribute.
                fieldElement = By.xpath("//*[@placeholder='" + elementID + "']");
                break;
            default:
                // If an invalid controllerType is provided, exit the method.
                return;
        }

        if (isRandomGenerateRequired) {
            // Generate a random string to ensure unique input values.
            String randomStr = method_GenerateRandomString();
            // Append the random string to the provided dataValue.
            String propertyName = dataValue + "_" + randomStr;

            // Send the generated input value to the identified field.
            Common.method_SendKeys(fieldElement, "", elementID, propertyName);
        } else {
            Common.method_SendKeys(fieldElement, "", elementID, dataValue); // Directly input the value into the field.
        }
    }


    /**
     * Enters data into a text field using the 'formcontrolname' attribute without modifying the value.
     *
     * @param elementID The form control name of the text field.
     * @param dataValue The value to input into the text field.
     */
    public static void method_EnterDataToTextFieldUsingFormControlWithoutChanging(String elementID, String dataValue) {
        By filedName = By.xpath("//input[@formcontrolname='" + elementID + "']");
        Common.method_SendKeys(filedName, "", elementID, dataValue); // Directly input the value into the field.
    }

    /**
     * Selects a value from a dropdown using the 'formcontrolname' attribute.
     *
     * @param dropDownId The form control name of the dropdown.
     * @param value      The value to select from the dropdown.
     */
    public static void method_SelectValueFromDropDown(String dropDownId, String value) {
        By dropDown = By.xpath("//mat-select[@formcontrolname='" + dropDownId + "']"); // Locate the dropdown element.
        By dropDownValue = By.xpath("//mat-option//span[contains(text(), '" + value + "')]"); // Locate the dropdown option.
        Common.method_ButtonClick(dropDown, ""); // Open the dropdown.
        Common.method_ButtonClick(dropDownValue, ""); // Select the desired value.
        Common.method_hardPause(1); // Pause to allow for the selection action to complete.
    }

    /**
     * This method simulates selecting a random date from a calendar control in a UI.
     * The steps include opening the calendar, selecting a random year within a calculated range,
     * randomly selecting a month, and selecting a random day (from 1 to 28).
     *
     * @param calendarName The name of the calendar component (not used in this implementation, can be used for logging or debugging).
     * @param date         The specific date string that would be used (not utilized in this implementation, but can be extended for future enhancements).
     */
    public static void method_SelectDateFromCalendar(String calendarName, String date) {
        // Step 1: Open the calendar by clicking on the calendar icon
        By calendarIcon = By.xpath("//mat-datepicker-toggle//button[@aria-label='Open calendar']");
        Common.method_ButtonClick(calendarIcon, "");

        // Step 2: Click the button to choose month and year from the calendar UI
        By monthAndYearButton = By.xpath("//button[@aria-label='Choose month and year']");
        Common.method_ButtonClick(monthAndYearButton, "");

        // Step 3: Navigate back in time by clicking on the "Previous 24 years" button twice
        By previous24yearsButton = By.xpath("//button[@aria-label='Previous 24 years']");
        Common.method_ButtonClick(previous24yearsButton, "");
        Common.method_hardPause(1); // Pause to allow UI to update
        Common.method_ButtonClick(previous24yearsButton, "");

        // Step 4: Get the current year and calculate a base year for selecting a random year
        int currentYear = Year.now().getValue();
        int baseYear = currentYear - 48; // Set a base year as 48 years ago

        // Step 5: Define a random range (5 years before to 5 years after) from the base year
        int minYear = baseYear - 5;
        int maxYear = baseYear + 5;

        // Step 6: Select a random target year within the calculated range
        int targetYear = ThreadLocalRandom.current().nextInt(minYear, maxYear + 1);

        // Step 7: Construct an XPath to dynamically select the year button from the calendar UI
        String xpathYear = "//button[@aria-label='" + targetYear + "']";
        By selectedYear = By.xpath(xpathYear);
        Common.method_ButtonClick(selectedYear, "");

        // Step 8: Create a list of month abbreviations to randomly select a month
        List<String> monthAbbreviations = Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC");

        // Step 9: Randomly select a month abbreviation from the list
        Random random = new Random();
        String randomMonth = monthAbbreviations.get(random.nextInt(monthAbbreviations.size()));

        // Step 10: Dynamically construct an XPath to select the randomly chosen month from the calendar
        String randomMonthXpath = "//button[span[normalize-space()='" + randomMonth + "']]";
        By randomMonthButton = By.xpath(randomMonthXpath);
        Common.method_ButtonClick(randomMonthButton, "");

        // Step 11: Generate a random day (1-28) to ensure it is a valid date for all months
        int randomDayOfMonth = random.nextInt(28) + 1;

        // Step 12: Dynamically construct an XPath to select the randomly chosen day from the calendar
        String xpathDay = "//button[span[contains(normalize-space(), '" + randomDayOfMonth + "')]]";
        By selectedDay = By.xpath(xpathDay);
        Common.method_ButtonClick(selectedDay, "");

        // Step 13: Pause briefly to allow the UI to update and reflect the selected date
        Common.method_hardPause(1);
    }


    /**
     * Iterates over a table of data and inputs values into fields based on their type.
     *
     * @param dataTable A Cucumber DataTable containing the field details and input values.
     */
    public static void method_EnterTableData(io.cucumber.datatable.DataTable dataTable) {
        String value = null;
        List<List<String>> fieldLits = dataTable.asLists(); // Convert the DataTable to a list of lists.
        int listLength = dataTable.asLists().size(); // Get the number of rows in the DataTable.

        // Iterate through each row of the DataTable (starting from the second row).
        for (int i = 1; i < listLength; i++) {
            String fieldString = fieldLits.get(i).toString().replace('[', ' '); // Clean up the data.
            String fList[] = fieldString.replace(']', ' ').split(","); // Split the row into individual elements.

            String webElement = fList[1].trim(); // Extract the element identifier.
            String inputType = fList[2].trim(); // Extract the type of input (Text, Dropdown, etc.).
            value = fList[3].trim(); // Extract the value to be input.
            String controllerType = fList.length >= 5 ? fList[4].trim() : null; // Extract the value to be input.

            // Handle input based on its type.
            if (inputType.contentEquals("Text")) {
                switch (webElement) {
                    case "userName":
                    case "roleID":
                    case "companyName":
                    case "address":
                    case "description":
                    case "companyId":
                    case "name":
                    case "year":
                    case "roleName":
                    case "jobTitle":
                    case "openPositions":
                    case "position":
                    case "title":
                    case "symbol":
                    case "ratingRangeName":
                    case "ratingGroup":
                    case "locationDetails":
                    case "notificationName":
                    case "kpiName":
                    case "subject":
                        //My
                    case "g2599-name":
                        if (controllerType != null) {
                            method_EnterDataToTextField(webElement, value, controllerType, true); // Input with modification.
                        } else {
                            // To be Removed After all the controller types added in all feature files
                            method_EnterDataToTextField(webElement, value); // Input with modification.
                        }
                        break;
                    case "code":
                        // Special handling for "Segment Code" without underscore
                        method_EnterDataToTextFieldWithoutUnderscore(webElement, value);
                        break;
                    case "mobileNo":
                    case "fullName":
                    case "phone":
                    case "colour":
                    case "minimumPasswordCharacterLength":
                    case "percentageEnd":
                        if (controllerType != null) {
                            method_EnterDataToTextField(webElement, value, controllerType, false); // Input with modification.
                        } else {
                            // To be Removed After all the controller types added in all feature files
                            method_EnterDataToTextFieldUsingFormControlWithoutChanging(webElement, value); // Input with modification.
                        }
                        break;

                    case "email":
                    case "emailAddress":
                        method_EnterEmailToTextFieldUsingFormControlWithChanging(webElement, value); // Input with modification.
                        break;
                    //My Start
                    case "g2599-email":
                        method_EnterEmailToTextFieldUsingFormControlWithChanging_class(webElement, value);
                        break;
                    case "g2599-website":
                        method_EnterURLToTextFieldUsingFormControlWithChanging_NotRandom(webElement, value);
                        break;
                    //My End
                    case "new-name":
                        method_EnterNameToTextMatLableText(webElement, value);
                        break;
                    case "startDate":
                        method_EnterDateToStartDate(webElement, value);
                        break;
                    case "endDate":
                        method_EnterDateToendDate(webElement, value);
                        break;
                    case "contactNo":
                        method_EnterDateToendDate(webElement, value);
                        break;

                    case "interviewFormType":
                        method_EnterDateToendDate(webElement, value);
                        break;
                    case "marks":
                        method_EnterDateToendDate(webElement, value);
                        break;
                    case "question":
                        method_EnterDateToendDate(webElement, value);
                        break;


                    default:// Do nothing for unhandled cases.
                }
            } else if (inputType.contentEquals("Dropdown")) {
                method_SelectValueFromDropDown(webElement, value); // Select value from a dropdown.
            } else if (inputType.contentEquals("Calendar")) {
                method_SelectDateFromCalendar(webElement, value);
            } else if (inputType.contentEquals("TextArea")) {
                method_EnterTextArea(webElement, value, controllerType); // Select value from a dropdown.
            } else if (inputType.contentEquals("Checkbox")) {
                method_SelectCheckBox(webElement, value);
            } else if (inputType.contentEquals("Dropdowncategory")){
                method_SelectValueFromDropDown_category(webElement,value);
            } else if (inputType.contentEquals("Radio")) {
                method_SelectValueFromRadio(webElement, value);
            }

            // Future cases for additional input types can be added here.
        }

        Common.method_hardPause(4); // Pause after completing all actions to allow for stabilization.
    }

    private static void method_EnterTextArea(String elementID, String value, String controllerType) {
        By fieldElement = null; // Locator for the input field.

        // Determine the locator strategy based on the controller type.
        switch (controllerType) {
            case "formController":
                // Locate element using formcontrolname attribute.
                fieldElement = By.xpath("//textarea[@formcontrolname='" + elementID + "']");
                break;
            case "id":
                // Locate element using id attribute.
                fieldElement = By.xpath("//*[@id='" + elementID + "']");
                break;
            case "name":
                // Locate element using name attribute.
                fieldElement = By.xpath("//*[@name='" + elementID + "']");
                break;
            default:
                // If an invalid controllerType is provided, exit the method.
                return;
        }


        Common.method_SendKeys(fieldElement, "", elementID, value); // Directly input the value into the field.

    }

    /**
     * This method enters a dynamically modified email address into a text field identified by its formcontrolname attribute.
     * The email address is modified by appending a randomly generated string before the '@' symbol to ensure uniqueness.
     *
     * @param elementID The formcontrolname attribute of the input field to locate it.
     * @param dataValue The base email address to modify and input into the field.
     * @throws IllegalArgumentException if the provided email format is invalid.
     */
    // New method to handle "Segment Code" without underscore
    public static void method_EnterDataToTextFieldWithoutUnderscore(String elementID, String dataValue) {
        By fieldName = By.xpath("//input[@formcontrolname='" + elementID + "']");
        String uniqueStr = generateUniqueStringWithoutUnderscore(); // Generate a unique string without underscore
        String uniqueValue = dataValue + uniqueStr; // Combine the base value and unique string
        Common.method_SendKeys(fieldName, "", elementID, uniqueValue); // Input the combined value into the field
    }

    public static String generateUniqueStringWithoutUnderscore() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder uniqueStr = new StringBuilder();
        int length = 8; // Length of the unique string

        for (int i = 0; i < length; i++) {
            uniqueStr.append(characters.charAt(random.nextInt(characters.length())));
        }
        return uniqueStr.toString();
    }

    private static void method_EnterEmailToTextFieldUsingFormControlWithChanging(String elementID, String dataValue) {
        // Locate the input field using the formcontrolname attribute
        By filedName = By.xpath("//input[@formcontrolname='" + elementID + "']");

        // Generate a random string to append to the email
        String randomStr = method_GenerateRandomString();

        // Split the email to insert the random string before '@'
        String[] emailParts = dataValue.split("@");
        if (emailParts.length != 2) {
            throw new IllegalArgumentException("Invalid email format: " + dataValue);
        }
        String modifiedEmail = emailParts[0] + "_" + randomStr + "@" + emailParts[1];

        // Send the modified email to the input field
        Common.method_SendKeys(filedName, "", elementID, modifiedEmail);
    }

    private static void method_EnterNameToTextMatLableText(String elementID, String dataValue){
        // Locate the input field using the formcontrolname attribute
        By filedName = By.xpath("//mat-label[text()="+elementID+"]");
        String randomStr = method_GenerateRandomString();
        String modifiedName = dataValue + " " + randomStr;
        Common.method_ButtonClick(filedName,"");
        Common.method_hardPause(1);
        Common.method_SendKeys(filedName, "", elementID, modifiedName);
        Common.method_hardPause(1);

    }

    public static void method_EnterDateToStartDate(String elementID, String dataValue){
        By startDate = By.xpath("//input[@formcontrolname='" + elementID + "']");
        Common.method_hardPause(1);
        Common.method_ButtonClick(startDate,"");
        Common.method_SendKeys(startDate, "", elementID, dataValue);
        Common.method_hardPause(1);
    }

    public static void method_EnterDateToendDate(String elementID, String dataValue){
        By startDate = By.xpath("//input[@formcontrolname='" + elementID + "']");
        Common.method_hardPause(1);
        Common.method_ButtonClick(startDate,"");
        Common.method_SendKeys(startDate, "", elementID, dataValue);
        Common.method_hardPause(1);
    }

    public static void method_SelectValueFromDropDown_category(String dropDownId, String value) {
        By dropDown = By.xpath("//mat-select[@formcontrolname='" + dropDownId + "']"); // Locate the dropdown element.
        By dropDownValue = By.xpath("//span[normalize-space()='"+ value +"']"); // Locate the dropdown option.
        Common.method_ButtonClick(dropDown, ""); // Open the dropdown.
        Common.method_ButtonClick(dropDownValue, ""); // Select the desired value.
        Common.method_hardPause(1); // Pause to allow for the selection action to complete.
        Common.method_ButtonClick(By.xpath("//div[@class='cdk-overlay-container']"),"");
    }

    public static void clickCheckboxByXPathWithIndex(String xpathTemplate, int value) {
        try {
            // Construct the full XPath with the index
            String fullXPath = String.format("(%s)[%d]", xpathTemplate, value);

            // Find the element using the constructed XPath
            WebElement checkbox = driver.findElement(By.xpath(fullXPath));

            // Interact with the checkbox
            if (!checkbox.isSelected()) {
                checkbox.click(); // Click the checkbox if it's not already selected
                System.out.println("Checkbox at index " + value + " clicked.");
            } else {
                System.out.println("Checkbox at index " + value + " is already selected.");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Checkbox at index " + value + " not found.");
        }
    }

    public static String method_GenerateRandomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(4);
        return generatedString;
    }

    public static void method_SelectCheckBox(String elementID, String value) {
        By checkBox = By.xpath("//input[@type='checkbox'][@name='" + elementID + "']");
        boolean isChecked = !Core.driver.findElements(checkBox.tagName("checked")).isEmpty();

        if (value.equals("Yes") && !isChecked) {
            Common.method_ButtonClick(checkBox, "Professions/Experience");
        } else if (value.equals("No") && isChecked) {
            Common.method_ButtonClick(checkBox, "Professions/Experience");
        }

    }

    // Below method selects radio buttons
    public static void method_SelectValueFromRadio(String elementID, String value) {

        List<WebElement> radio = driver.findElements(By.xpath("//input[@name='" + elementID + "']//parent::label"));
        for (int i = 0; i <= radio.size(); i++) {
            String txt = radio.get(i).getText();
            if (txt.trim().equals(value)) {
                radio.get(i).click();
                break;
            }
        }
    }

    //If dont have formcontroller

    private static void method_EnterEmailToTextFieldUsingFormControlWithChanging_class(String elementID, String dataValue) {
        // Locate the input field using the formcontrolname attribute
        By filedName = By.xpath("//*[@id='" + elementID + "']");

        // Generate a random string to append to the email
        String randomStr = method_GenerateRandomString();

        // Split the email to insert the random string before '@'
        String[] emailParts = dataValue.split("@");
        if (emailParts.length != 2) {
            throw new IllegalArgumentException("Invalid email format: " + dataValue);
        }
        String modifiedEmail = emailParts[0] + "_" + randomStr + "@" + emailParts[1];

        // Send the modified email to the input field
        Common.method_SendKeys(filedName, "", elementID, modifiedEmail);
    }

    public static void method_EnterURLToTextFieldUsingFormControlWithChanging_class(String elementID, String dataValue) {
        // Locate the input field using the id attribute
        By fieldName = By.xpath("//*[@id='" + elementID + "']");

        // Generate a random string to make the URL unique
        String randomStr = method_GenerateRandomString();

        // Modify the base URL by prepending the random string (e.g., test-www.Google.com)
        String modifiedURL = "test" + randomStr + "-" + dataValue;

        // Send the modified URL to the input field
        Common.method_SendKeys(fieldName, "", elementID, modifiedURL);
    }

    public static void method_EnterURLToTextFieldUsingFormControlWithChanging_NotRandom(String elementID, String dataValue) {
        // Locate the input field using the id attribute
        By fieldName = By.xpath("//*[@id='" + elementID + "']");

        // Send the exact URL value to the input field (no randomization)
        Common.method_SendKeys(fieldName, "", elementID, dataValue);
    }

    public static void method_HoverElement(By element, String elementName) {
        try {
            WebElement webElement = driver.findElement(element);
            Actions action = new Actions(driver);
            action.moveToElement(webElement).perform();
            test.log("Hovered over " + elementName + " successfully.");
        } catch (Exception e) {
            test.log("Failed to hover over " + elementName + ": " + e.getMessage());
            Assert.fail("Unable to hover over element: " + elementName);
        }
    }

}