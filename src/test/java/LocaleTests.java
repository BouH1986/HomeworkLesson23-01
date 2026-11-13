import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocaleTests {

    public static Stream<Arguments> localeTestParameters() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome")
        );
    }

    @ParameterizedTest
    @MethodSource("localeTestParameters")

    public void localeTest(Country country, String expected) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        //when
        String actual = localizationService.locale(country);

        //then
        Assertions.assertEquals(expected, actual);
    }
}
