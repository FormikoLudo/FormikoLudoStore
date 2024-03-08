package fr.formiko.formikoludostore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.awt.*;

/**
 * StoreEntry is a Table that contains necessary information about a given game.
 * It is used to display the game in the store.
 */
public class StoreEntry extends Table {
    private String title;
    private String description;
    private String image;

    private StoreEntry(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.setSize(.8f * Gdx.graphics.getWidth(), .2f * Gdx.graphics.getWidth());
        this.setColor(Color.GREEN);
        System.out.println("StoreEntry size: " + this.getWidth() + "x" + this.getHeight());
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
    protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
        super.drawBackground(batch, parentAlpha, x, y);
        batch.draw
    }

    public static StoreEntry fromYAML(String content) {
        return new StoreEntry("Test", "derscription", "image");

    }
}
