import mainview.MainView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainViewTest {

    private MainView view;
    private Component[] components;

    @BeforeEach
    void setUp() {
        view = new MainView();
    }

    @Test
    void resources_vorhanden() {
        String imagePath = "images/add_180x180.png";
        Path path = Paths.get(imagePath);
        assertTrue(path.toFile().exists(), "Das Bild ist nicht vorhanden.");
    }

    @Test
    void window_is_ready() {
        assertAll(
                () -> assertNotNull(view.getFrame(), "Objekt nicht erzeugt!"),
                () -> assertNotNull(view.getMainPanel(), "Objekt nicht erzeugt!"),
                () -> assertTrue(view.getMainPanel().getLayout() instanceof BorderLayout),
                () -> assertNotNull(view.getTabbedPane(), "Objekt nicht erzeugt!"));
    }

    @Test
    void check_menu_notNull() {
        assertNotNull(view.getMenuBar());
    }

    @Test
    void check_starttab_notNull() {
        assertNotNull(view.getStartTab());
    }

    @Test
    void check_bottompanel_notNull() {
        assertNotNull(view.getBottomPanel());
    }

    @Test
    void check_bottompanel_layout() {
        components = view.getBottomPanel().getComponents();
        assertEquals(3, components.length);
    }

    @Test
    void check_menuBar_layout() {

        components = view.getMenuBar().getComponents();

        assertAll(
                () -> assertEquals(3, components.length),
                () -> assertTrue(components[0] instanceof JMenu),
                () -> assertTrue(((JMenu) components[0]).getText().equals("File"), "File Menu fehlt"),
                () -> assertTrue(components[1] instanceof JMenu),
                () -> assertTrue(((JMenu) components[1]).getText().equals("Edit"), "Edit Menu fehlt"),
                () -> assertTrue(components[2] instanceof JMenuItem),
                () -> assertTrue(((JMenu) components[2]).getText().equals("Help"), "Help Menu fehlt"));
    }
}