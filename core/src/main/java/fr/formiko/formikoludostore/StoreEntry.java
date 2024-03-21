package fr.formiko.formikoludostore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import fr.formiko.utils.WidgetsFactory;
import space.earlygrey.shapedrawer.ShapeDrawer;
import fr.formiko.utils.TextSize;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * StoreEntry is a Table that contains necessary information about a given game.
 * It is used to display the game in the store.
 */
public class StoreEntry extends Table {
    private final String title;
    private final String author;
    private final String description;
    private final String image;
    private final ShapeDrawer schdr;

    public StoreEntry(String title, String author, String description, String image) {
        System.out.println("Begin of constructor");
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
        this.setSize(.8f * Gdx.graphics.getWidth(), .1f * Gdx.graphics.getWidth());
//        this.setPosition(10,Gdx.graphics.getHeight() - getHeight());
        this.setColor(new Color (0.15f, 0.15f, 0.2f, 1f));
        System.out.println("StoreEntry size: " + this.getWidth() + "x" + this.getHeight());
        this.schdr = WidgetsFactory.getShapDrawer();
        System.out.println("End of constructor");
        Label label = WidgetsFactory.getTile(title, TextSize.H1);
        this.addActor(label);

        label.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { 
                Main.getInstance().setGame(title, author);
                System.exit(101);
            }
        });
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        WidgetsFactory.getBatch().begin();
        this.schdr.filledRectangle(getX(),getY(),getWidth(),getHeight(),new Color (0.15f, 0.15f, 0.2f, 1f));
        WidgetsFactory.getBatch().end();
        super.draw(batch, parentAlpha);
    }

    public static StoreEntry fromYAML(String content) {
        return new StoreEntry("Test", "Auth", "description", "image");
    }
}
