package fr.formiko.formikoludostore;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Stage stg;

    @Override
    public void create() {
        batch = new SpriteBatch();
        stg = new Stage();
        StoreEntry entry = StoreEntry.fromYAML("Test");
        entry.setPosition(50, .7f * Gdx.graphics.getHeight());
        stg.addActor(entry);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stg.act();
        stg.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
