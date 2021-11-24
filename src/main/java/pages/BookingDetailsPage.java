package pages;

import common.BasePage;
import common.CommonConstants;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingDetailsPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(BookingDetailsPage.class);


    public String getDetails(String field) {
        return getElementText(By.xpath("//dt[contains(.,'" + field + ")]/following-sibling::dd[1]"));
    }

    public void verifyPickupLocation(String expectedPickup) {
        String actual = getDetails("From");
        logger.info("Expected :- " + expectedPickup + " Actual:- " + actual);
        if (actual.contains(expectedPickup))
            report(CommonConstants.PASS, "Booking details verified for pickup");
        else
            report(CommonConstants.FAIL, "Booking details verification failed for pickup");
    }

    public void verifyDropLocation(String expectedDrop) {
        String actual = getDetails("To");
        logger.info("Expected :- " + expectedDrop + " Actual:- " + actual);
        if (actual.contains(expectedDrop))
            report(CommonConstants.PASS, "Booking details verified for Drop location");
        else
            report(CommonConstants.FAIL, "Booking details verification failed for Drop location");
    }

    public void verifyPassangers(String passanger) {
        String actual = getDetails("Passengers");
        logger.info("Expected :- " + passanger + " Actual:- " + actual);
        if (actual.contains(passanger))
            report(CommonConstants.PASS, "Booking details verified for passanger");
        else
            report(CommonConstants.FAIL, "Booking details verification failed for passanger");
    }

}
