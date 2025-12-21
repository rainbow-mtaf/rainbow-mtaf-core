package starter.wikipedia;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import net.serenitybdd.screenplay.annotations.CastMember;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.rainbow.ai.clients.*;
import com.rainbow.ai.locator.AiLocatorHealer;


@ExtendWith(SerenityJUnit5Extension.class)
class WhenSearchingForTerms {

    @CastMember(name = "Wendy")
    Actor wendy;

    @Test
    void searchBySingleKeyword() {
        wendy.attemptsTo(
                Navigate.toTheHomePage()
        );

        // --- AI assisted input into search field ---
        WebDriver driver = BrowseTheWeb.as(wendy).getDriver();
        OllamaClient client = new OllamaClient("llama3"); // ή "qwen2.5-coder"
        AiLocatorHealer healer = new AiLocatorHealer(client);

        // primary locator (αυτός που πιθανώς να “σπάει”)
        By PRIMARY_SEARCH_INPUT = By.id("otinaianai");

        // Βρες ή “θεράπευσε” τον locator
        WebElement searchInput = healer.findOrHeal(
                driver,
                PRIMARY_SEARCH_INPUT,
                "the Wikipedia search input field on the home page"
        );
        searchInput.clear();
        searchInput.sendKeys("Everest");
        searchInput.sendKeys(Keys.ENTER);

        // assert με το υπάρχον Page Object σου
        wendy.attemptsTo(
                Ensure.that(DisplayedArticle.firstHeading()).isEqualTo("Mount Everest")
        );
    }
}
