package fr.formiko.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import fr.formiko.monopoly.MyButton;
import fr.formiko.monopoly.RotatedText;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.Locale;
import java.util.ResourceBundle;

public final class WidgetsFactory {
    public static Skin mySkin  = null;
    private static Batch  batch = null;
    private static ShapeDrawer  schdr = null;

    private static BitmapFont WIDGET_FONT;
    private static BitmapFont H1_FONT;
    private static BitmapFont H2_FONT;

    private static BitmapFont BOLD_TEXT_FONT;
    private static BitmapFont TEXT_FONT;

    public static Locale LANGUAGE = Locale.of("fr","FR");
    public static ResourceBundle LABELS = ResourceBundle.getBundle("languages/translation",LANGUAGE);
    private static final String DEFAULT_STYLE = "default";
    private static final String BOLD_STYLE = "bold";
    private static final String H1_STYLE = "h1";
    private static final String H2_STYLE = "h2";
    private static final String WIDGET_STYLE = "widget";

    private WidgetsFactory(){}

static {
        prepareSkin();
}

    private static void prepareSkin(){
        TEXT_FONT = Fonts.getRegularFont(20.f, Color.WHITE);
        BOLD_TEXT_FONT = Fonts.getBoldFont(20.f, Color.WHITE);
        WIDGET_FONT = Fonts.getRegularFont(40.f, Color.WHITE);
        H1_FONT = Fonts.getBoldFont(40.f, Color.WHITE);
        H2_FONT = Fonts.getBoldFont(30.f, Color.WHITE);
        mySkin =  new Skin(Gdx.files.internal("ui/uiskin.json"));

        mySkin.add(DEFAULT_STYLE, TEXT_FONT);
        mySkin.add(WIDGET_STYLE, WIDGET_FONT);
        mySkin.add(H1_STYLE,H1_FONT);
        mySkin.add(H2_STYLE,H2_FONT);
        mySkin.add(BOLD_STYLE,BOLD_TEXT_FONT);

        Label.LabelStyle defLabelStyle = new Label.LabelStyle(mySkin.get(Label.LabelStyle.class));
        defLabelStyle.font = mySkin.getFont(H1_STYLE);
        mySkin.add(H1_STYLE,defLabelStyle);

        defLabelStyle = new Label.LabelStyle(mySkin.get(Label.LabelStyle.class));
        defLabelStyle.font = mySkin.getFont(H2_STYLE);
        mySkin.add(H2_STYLE,defLabelStyle);

        defLabelStyle = new Label.LabelStyle(mySkin.get(Label.LabelStyle.class));
        defLabelStyle.font = mySkin.getFont(BOLD_STYLE);
        mySkin.add(BOLD_STYLE,defLabelStyle);

        defLabelStyle = new Label.LabelStyle(mySkin.get(Label.LabelStyle.class));
        defLabelStyle.font = mySkin.getFont(DEFAULT_STYLE);
        mySkin.add(DEFAULT_STYLE,defLabelStyle);



        TextButton.TextButtonStyle textButtonStyle = mySkin.get(TextButton.TextButtonStyle.class);
        textButtonStyle.font = mySkin.getFont(WIDGET_STYLE);
        mySkin.add(WIDGET_STYLE, textButtonStyle);
    }
    public static MyButton getButton(String label, String key, Runnable action) {
        if (mySkin == null)
            prepareSkin();
        MyButton res = new MyButton(LABELS.getString(label), mySkin, key);
        res.pad(20);
        res.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run();
            }
        });
        return res;
    }

    /**
     *
     * @param title same convention as HTML most important is h1
     * @return a label with the given text and the appropriate size.
     */
    public static Label getTile(String text, TextSize title) {
        if (mySkin == null)
            prepareSkin();
        String style = switch (title) {
            case H1 -> H1_STYLE;
            case H2 -> H2_STYLE;
            case P -> DEFAULT_STYLE;
            case EM -> BOLD_STYLE;
        };
       return new Label(text, mySkin, style);
    }

    public static void prepareLabelAndAddToTable(String labelText, TextSize textSize, Table parent, float percentageOfScreenUsed) {
        Label res = WidgetsFactory.getTile(WidgetsFactory.LABELS.getString(labelText), textSize);
        res.setWrap(textSize == TextSize.P || textSize == TextSize.EM);
        res.setAlignment(Align.left);
        parent.add(res).width(Gdx.graphics.getWidth() * percentageOfScreenUsed);
        parent.row();

    }

    public static Table backgroundColoredGroup(Color bgColor, Vector2 size) {
        Table g = new Table();
        g.setColor(bgColor);
        g.setSize(size.x,size.y);
        return g;
    }

    public static ShapeDrawer createShapeDrawer(Batch batch) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); // remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
        return new ShapeDrawer(batch, region);
    }

    public static Batch getBatch() {
        if (batch == null)
            batch = new SpriteBatch();
        return batch;
    }

    public static ShapeDrawer getShapDrawer() {
        if (schdr == null)
            schdr = createShapeDrawer(getBatch());
        return schdr;
    }

    // public static void drawRotatedText(BitmapFont font, String text, float x, float y, Batch batch, float parentAlpha) {
    //     System.out.println("BEGIN OF DRAW ROTATED TEXT");
    //     RotatedText Rtext = new RotatedText();
    //     Rtext.draw(batch,parentAlpha);
    // }

    public static void disposeResources() {
        batch.dispose();
    }
}
