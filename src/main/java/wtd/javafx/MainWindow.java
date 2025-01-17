package wtd.javafx;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import wtd.Wtd;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Wtd wtd;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setWtd(Wtd d) {
        wtd = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Wtd's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wtd.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
        checkExit(response);
    }

    private void checkExit(String response) {
        if (response.equals("Good riddance...\n")) {
            Task<Void> task = new Task<Void>(){
                @Override
                public Void call() throws InterruptedException {
                    exit();
                    return null;
                }
            };
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }
    }

    private void exit(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Platform.exit();        
    }

    /**
     * Create a dialog box showing the welcome message.
     */
    protected void showWelcome() {
        String welcome = wtd.getWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(welcome, botImage)
        );
    }
}