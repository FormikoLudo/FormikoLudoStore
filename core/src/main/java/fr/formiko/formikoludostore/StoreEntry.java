package fr.formiko.formikoludostore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import fr.formiko.utils.WidgetsFactory;
import space.earlygrey.shapedrawer.ShapeDrawer;
import fr.formiko.utils.TextSize;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

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
    private boolean debug  = true;

    public StoreEntry(String title, String author, String description, String image) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
        this.setSize(.8f * Gdx.graphics.getWidth(), .1f * Gdx.graphics.getWidth());
        this.setColor(new Color (0.15f, 0.15f, 0.2f, 1f));
        this.schdr = WidgetsFactory.getShapDrawer();
        Label titleLabel = WidgetsFactory.getTile(title, TextSize.H1);
        Label authorLabel = WidgetsFactory.getTile(author, TextSize.H2);
        HorizontalGroup hg = new HorizontalGroup();
        VerticalGroup vg = new VerticalGroup();
        Table firstLine = new Table();
        firstLine.add(titleLabel).pad(10.f);
        firstLine.add(authorLabel).pad(10.f);
        hg.addActor(firstLine);
//        hg.addActor(authorLabel);
        this.addActor(hg);

        titleLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getInstance().setGame(title, author);
                Main.exit(101);
            }
        });
        if(debug) {
            System.out.println("Dimensions: "+getWidth()+"x"+getHeight());
            debug= false;
        }
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
        super.draw(batch, parentAlpha);
        WidgetsFactory.getBatch().begin();
        this.schdr.filledRectangle(getX(),getY(),getWidth(),getHeight(), Color.RED);//new Color (0.15f, 0.15f, 0.2f, 1f));
        WidgetsFactory.getBatch().end();
    }

    public static StoreEntry fromYAML(String content) {
        return new StoreEntry("Test", "Auth", "description", "image");
    }
}
