import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Controller {

    @FXML
    public Button buttonStart;

    @FXML
    private ChoiceBox<String> keyList;

    HashMap<String, Integer> keyMap = new HashMap<>();
    BotThread bt;

    @FXML
    public void initialize() {
        Class reflected = null;
        try {
            reflected = Class.forName("java.awt.event.KeyEvent");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(reflected.getFields()!=null) {
            for (Field f : reflected.getFields()) {
                if (f.getName().startsWith("VK_")) {
                    try {
                        if (f.get(null) != null) {
                            String name = f.getName().replaceFirst("VK_", "");
                            keyList.getItems().add(name);
                            keyMap.put(name, (Integer) f.get(null));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        keyList.getSelectionModel().select(0);
        buttonStart.setOnAction(event -> {
            String name = keyList.getItems().get(keyList.getSelectionModel().getSelectedIndex());
            bt = new BotThread(keyMap.get(name));
            bt.start();
        });
        System.out.println(KeyEvent.VK_Z - KeyEvent.VK_A);
    }

}
