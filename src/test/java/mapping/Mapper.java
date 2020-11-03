package mapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.NoSuchSelector;
import exceptions.UndefinedEnum;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;


public class Mapper {


    private static JsonObject readJSON(String elementFound) throws IOException {
        Gson gson = new Gson();
        JsonElement jsonObject;
        FileReader reader = null;
        JsonObject jsonElement;
        JsonObject foundElement;
        foundElement = null;

        try {
            reader = new FileReader("mapJSON/element.JSON");
            jsonObject = gson.fromJson(reader, JsonElement.class);
            jsonElement = jsonObject.getAsJsonObject();
            foundElement = jsonElement.get(clearTurkishCharsAndUpperCase(elementFound)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            fail(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return foundElement;
    }

    static By generateByElement(String s_ByType, String byValue) throws UndefinedEnum, NoSuchSelector {
        By byElement;
        MapValue byType;
        try {
            byType = MapValue.valueOf(s_ByType);
        } catch (IllegalArgumentException e) {
            throw new UndefinedEnum("UndefinedEnum \"" + s_ByType + "\"");
        }
        switch (byType) {
            case ID:
                byElement = By.id(byValue);
                break;
            case XPATH:
                byElement = By.xpath(byValue);
                break;
            case CLASSNAME:
                byElement = By.className(byValue);
                break;
            case LINK_TEXT:
                byElement = By.linkText(byValue);
                break;
            case CSS_SELECTOR:
                byElement = By.cssSelector(byValue);
                break;
            case ID_CONTAINS:
                byElement = By.xpath("//*[contains(@id, '" + byValue + "')]");
                break;
            default:
                throw new NoSuchSelector(byType.getText());
        }
        return byElement;
    }

    private static String clearTurkishCharsAndUpperCase(String str) {
        String returnStr = str;
        char[] turkishChars = new char[]{0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F,
                0x11E};
        char[] englishChars = new char[]{'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i = 0; i < turkishChars.length; i++) {
            returnStr = returnStr.replaceAll(new String(new char[]{turkishChars[i]}),
                    new String(new char[]{englishChars[i]}));
        }
        return returnStr.toLowerCase(Locale.ENGLISH);
    }

    public static By foundActivity(String elementFound) throws UndefinedEnum, NoSuchSelector {
        Set<Map.Entry<String, JsonElement>> entries = null;
        try {
            entries = readJSON(elementFound).entrySet();
        } catch (NullPointerException | IOException e) {

            fail(elementFound + "is not found in JSON file");
        }
        By by = null;
        for (Map.Entry<String, JsonElement> entry : entries) {

            by = generateByElement(entry.getKey(), entry.getValue().getAsString());
        }
        return by;
    }
}
