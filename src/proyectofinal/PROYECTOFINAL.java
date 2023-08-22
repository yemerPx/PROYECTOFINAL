/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;

import org.jpl7.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class PROYECTOFINAL extends Application {

     private Stage primaryStage;
    private VBox startPane, questionPane, resultPane;
    private List<String> sintomasList;    
    private List<String> preguntasList;

    private int currentQuestionIndex = 0;
    private int puntajeTotal = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("TEST DE DEPRESION");

        // Inicializar JPL
        String[] jplArgs = {"-Xmx256M"};
        JPL.init(jplArgs);

        // Cargar el archivo de reglas y hechos de Prolog
        Query consultQuery = new Query("consult('prolog/Test3.pl')");
        if (consultQuery.hasSolution()) {
            System.out.println("Archivo de Prolog cargado exitosamente.");
        } else {
            System.out.println("No se pudo cargar el archivo de Prolog.");
        }

        // Leer síntomas desde el archivo Sintomas.txt
        sintomasList = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader("texto/Sintomas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sintomasList.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //leer las preguntas en el archivo preguntas.txt
        preguntasList = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader("texto/preguntas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                preguntasList.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setupStartPane();
        setupQuestionPane();
        setupResultPane();

        primaryStage.setScene(new Scene(startPane, 1000, 500));
        primaryStage.show();
    }

    private void setupStartPane() {
        startPane = new VBox(10);
        startPane.setAlignment(Pos.CENTER);
        System.out.println("iniciando");

        /// Crea la ImageView y carga la imagen desde el sistema de archivos
        File imageFile = new File("src/imagenes/logo.png");
        Image logoImage = new Image(imageFile.toURI().toString());
        ImageView logoImageView = new ImageView(logoImage);

        Button startButton = new Button("Iniciar Test");
        startButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(questionPane, 1000, 500));
            showNextQuestion();
        });

        // Agrega la ImageView y el botón al VBox
        startPane.getChildren().addAll(logoImageView, startButton);

    }

    private void setupQuestionPane() {
        questionPane = new VBox(10);
        questionPane.setAlignment(Pos.CENTER);
        Label questionLabel = new Label();
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button noneButton = new Button("Nunca");
        Button lowButton = new Button("Bajo");
        Button mediumButton = new Button("Medio");
        Button highButton = new Button("Alto");

        noneButton.setOnAction(e -> handleAnswer("nada"));
        lowButton.setOnAction(e -> handleAnswer("bajo"));
        mediumButton.setOnAction(e -> handleAnswer("medio"));
        highButton.setOnAction(e -> handleAnswer("alto"));

        buttonBox.getChildren().addAll(noneButton, lowButton, mediumButton, highButton);
        questionPane.getChildren().addAll(questionLabel, buttonBox);
    }

    private void setupResultPane() {
        resultPane = new VBox(10);
        resultPane.setAlignment(Pos.CENTER);
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < sintomasList.size()) {
            Label questionLabel = new Label(preguntasList.get(currentQuestionIndex) );
            questionPane.getChildren().set(0, questionLabel);
            currentQuestionIndex++;
        } else {
            finishTest();
        }
    }

    private void finishTest() {
        Label resultLabel = new Label("Puntaje total: " + puntajeTotal);
        resultPane.getChildren().add(resultLabel);

        Query queryRecomend = new Query("clasificar_depresion(" + puntajeTotal + ")");

        if (queryRecomend.hasSolution()) {
            //mostrar las recomendaciones
            System.out.println("mosrtar");
            String recomendaciones = Recomendaciones.obtenerRecomendaciones(puntajeTotal);
            TextArea recomendacionArea = new TextArea(recomendaciones);
            recomendacionArea.setEditable(false);
            resultPane.getChildren().add(recomendacionArea);
        }

        primaryStage.setScene(new Scene(resultPane, 1000, 500));
        primaryStage.show();
    }

    private void handleAnswer(String nivel) {
        int puntajeSintoma = 0;
        switch (nivel) {
            case "nada" ->
                puntajeSintoma = 0;
            case "bajo" ->
                puntajeSintoma = 1;
            case "medio" ->
                puntajeSintoma = 2;
            case "alto" ->
                puntajeSintoma = 3;
        }
        puntajeTotal += puntajeSintoma;
        String persona = "yemer";

        Query query = new Query("calcular_sintoma(" + persona + ", '" + sintomasList.get(currentQuestionIndex - 1) + "', " + nivel + ")");
        System.out.println(query);
        if (query.hasSolution()) {
            System.out.println("Síntoma " + sintomasList.get(currentQuestionIndex - 1) + " con nivel " + nivel);
        } else {
            System.out.println("No se encontró ningún resultado para el síntoma " + sintomasList.get(currentQuestionIndex - 1));
        }

        showNextQuestion();
    }
    
}
