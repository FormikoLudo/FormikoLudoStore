package fr.formiko.formikoludostore;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.io.File;
import fr.formiko.utils.FLUOS;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Stage stg;
    private Yaml yaml;
    private Native nativeImpl;
    private static Main instance;

    public Main(Native nativeImpl) {
        this.nativeImpl = nativeImpl;
        instance = this;
    }

    public static Main getInstance() { return instance; }
    public static void exit(int code) { instance.nativeImpl.exit(code); }

    @Override
    public void create() {
        batch = new SpriteBatch();
        stg = new Stage();
        try (InputStream in = new FileInputStream(getAvailableGamesDataFile())) {
            yaml = new Yaml();
            Map<String, Map<String, Object>> authors = yaml.load(in);
            System.out.println("games: "+authors);
            int k=0;
            for(Map.Entry<String, Map<String, Object>> author : authors.entrySet()) {
                for (Map.Entry<String, Object> game : author.getValue().entrySet()) {
                    StoreEntry entry = new StoreEntry(game.getKey(), author.getKey(), "", "");
                    stg.addActor(entry);
                    entry.setPosition(50, .7f * Gdx.graphics.getHeight() + (k-- * 200));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gdx.input.setInputProcessor(stg);
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

    private File getDownloadedGamesDataFile() { return getLauncherJarFile("downloadedGames.yml"); }
    private File getAvailableGamesDataFile() { return getLauncherJarFile("availableGames.yml"); }
    private File getGameToLaunchDataFile() { return getLauncherJarFile("gameToLaunch.json"); }
    private File getLauncherJarFile(String fileName) {
        return new File(getLauncherJarFolder() + fileName);
    }
    private String getLauncherJarFolder() { return getAllGamesFolder() + "/.launcherjar/"; }
    private String getAllGamesFolder() { return FLUOS.isWindows() ? System.getenv("APPDATA") : System.getProperty("user.home"); }

    public void setGame(String title, String author) {
        System.out.println("Game selected: " + title);

        yaml.dump(Map.of("userName", author, "projectName", title), Gdx.files.absolute(getGameToLaunchDataFile().getAbsolutePath()).writer(false));
    }
}
