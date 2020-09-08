package definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static support.TestContext.getDriver;

public class Usps_support {

    public static String xpathClick (String myUrl, String filter, String option) {
        String xpath = "";
        if (myUrl.equals("https://store.usps.com/store") && option.equals("checkbox")) {
            switch (filter) {
                case "Priority Mail" -> xpath = "//input[@id='checkbox-type-Mail Service-Priority Mail-1']/../label";
                case "Stamps" -> xpath = "//input[contains(@id,'checkbox-type-Category-Stamps')]/../label";
                case "Vertical" -> xpath = "//input[contains(@id,'checkbox-type-Stamp Shape-Vertical')]/../label";
                case "Blue" -> xpath = "//div[@class='result-grid' and @style='background-color:#033366;']";

            }

        }
       else if (myUrl.equals("https://store.usps.com/store") && option.equals("resultset")) {

            xpath = "//div[@class='col-6 col-md-4 results-per-page ']";

        }

        else if (myUrl.equals("https://store.usps.com/store") && option.equals("set")) {

            xpath = "//div[contains(@class,'d-none d-lg-block breadcrumb-cartridge')]//span";

        }

        else if (myUrl.equals("https://tools.usps.com/rcas.htm") && option.equals("service")) {

            xpath = "//select[@id='passportappointmentType']";

        }



        return xpath;   //xpathClick(myUrl,filter,option)
    }

    public static List<WebElement> webElementList (String xpath) {
        List<WebElement> webElements = new ArrayList<>();
        webElements = getDriver().findElements(By.xpath(xpath));

        return webElements;
    }

}
