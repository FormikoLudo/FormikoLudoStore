package fr.formiko.utils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import java.nio.file.Files;

/**
 * {@summary Class that provide a way to load font.}
 *
 * @author Hydrolien
 * @version 2.4
 * @since 2.4
 */
public class Fonts extends BitmapFont {
    public static final String DEFAULT_COLOR = "[#000000]";

    enum FontStyle {
        REGULAR,
        BOLD,
        ITALIC
    }

    public static BitmapFont getBoldFont(float fontSize, Color c) {
        return getFont(fontSize,FontStyle.BOLD, c);
    }

    public static BitmapFont getRegularFont(float fontSize, Color c) {
        return getFont(fontSize,FontStyle.REGULAR, c);
    }

    /**
     * {@summary Load the default font.}
     *
     * @param fontSize size of the font.
     * @return the default font.
     */
    private static BitmapFont getFont(float fontSize, FontStyle style, Color c) {

        String fontLocation = "fonts/Noto_Sans/NotoSans-" + capitalize(style.toString()) + ".ttf";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontLocation));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.color = c;
        parameter.size = (int) fontSize;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;// FreeTypeFontGenerator.DEFAULT_CHARS + every char in the translation files.
        BitmapFont bmf = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        return bmf;
    }

    public static String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
