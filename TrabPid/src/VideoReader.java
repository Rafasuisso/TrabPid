/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author e.rafael.medeiros
 */
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public final class VideoReader extends Application {
 
    private Desktop desktop = Desktop.getDesktop();
    private String VIDEO_URL;
    @Override
    public void start(final Stage stage) {
        stage.setTitle("File Chooser Sample");
 
        final FileChooser fileChooser = new FileChooser();
 
        final Button openButton = new Button("Open a Video");
        final Button OCR = new Button("OCR");
       
        
 
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        //openFile(file);
                       
                        VIDEO_URL = file.getPath();
                      //  String nova = VIDEO_URL.replaceAll("[\\]","/");
                        for(int i = 0; i<=VIDEO_URL.length(); i++){
                            if(VIDEO_URL.charAt(i)== '\\'){
                            VIDEO_URL ="/";
                            }
                        }
                        System.out.print(VIDEO_URL);
                       //VIDEO_URL= "file:/C:/Users/e.rafael.medeiros/Documentos/NetBeansProjects/TrabPid/TrabPid/build/classes/media/vid2.mp4";
                        Media media = new Media(VIDEO_URL); // 1
                        System.out.println(VIDEO_URL);
                        MediaPlayer mediaPlayer = new MediaPlayer(media); // 2
                        MediaView mediaView = new MediaView(mediaPlayer); // 3

                        StackPane raiz = new StackPane();
                        raiz.getChildren().add(mediaView); // 4
                        Scene cena = new Scene(raiz, 600, 400);
                        stage.setTitle("Tocando Video em JavaFX");
                        stage.setScene(cena);
                        stage.show();

  mediaPlayer.play(); // 4
                    }
                }
            });
        
        
 
        OCR.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    List<File> list =
                        fileChooser.showOpenMultipleDialog(stage);
                    if (list != null) {
                        for (File file : list) {
                            openFile(file);
                        }
                    }
                }
            });
 
 
        final GridPane inputGridPane = new GridPane();
 
        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(OCR, 1, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, OCR);
 
        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));
 
        stage.setScene(new Scene(rootGroup));
        stage.show();
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
 
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(VideoReader.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}