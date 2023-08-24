/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;

import org.jpl7.*; // para concetar con prolog
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

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ScrollPane;

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
        try (BufferedReader br = new BufferedReader(new FileReader("texto/Sintomas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sintomasList.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //leer las preguntas en el archivo preguntas.txt
        preguntasList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("texto/preguntas.txt"))) {
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

        primaryStage.setScene(new Scene(startPane, 1400, 700));
        primaryStage.show();
    }

    private void setupStartPane() {
        startPane = new VBox(10);
        startPane.setAlignment(Pos.CENTER);
        System.out.println("iniciando");

        // Crea la ImageView y carga la imagen desde el sistema de archivos
        File imageFile = new File("src/imagenes/logo.png");
        Image logoImage = new Image(imageFile.toURI().toString());
        ImageView logoImageView = new ImageView(logoImage);

        // Crea el texto con la fuente "Roboto" y el tamaño de fuente deseado
        Text labelText = new Text("SISTEMA EXPERTO EN DETECCIÓN DE CASOS DE DEPRESIÓN EN ESTUDIANTES");
        labelText.setFont(Font.loadFont("file:src/fonts/Roboto-Black.ttf", 30));
        Text labelText2 = new Text("DE LA UNIVERSIDAD SAN CRISTÓBAL DE HUAMANGA");
        labelText2.setFont(Font.loadFont("file:src/fonts/Roboto-Black.ttf", 30));

        // Crea el botón con el estilo deseado
        Button startButton = new Button("Iniciar Test");
        startButton.setStyle(
                "-fx-background-color: #4CAF50; "
                + // Color verde
                "-fx-text-fill: white; "
                + // Texto en blanco
                "-fx-font-size: 16px; "
                + // Tamaño de fuente
                "-fx-padding: 10px 20px; "
                + // Espaciado interno
                "-fx-border-radius: 5px; "
                + // Bordes redondeados
                "-fx-cursor: hand;" // Cambia el cursor al puntero
        );

        startButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(questionPane, 1400, 700));
            showNextQuestion();
        });

        // Agrega la ImageView, el texto y el botón al VBox
        startPane.getChildren().addAll(logoImageView, labelText, labelText2, startButton);
    }

    private void setupQuestionPane() {
        questionPane = new VBox(10);
        questionPane.setAlignment(Pos.CENTER);

        File imageFile = new File("src/imagenes/fondo.jpg");
        Image backgroundImage = new Image(imageFile.toURI().toString());
        BackgroundImage background = new BackgroundImage(
                backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        questionPane.setBackground(new Background(background));

        Text questionText = new Text("¿Cómo calificarías esto?");
        questionText.setStyle("-fx-font-size: 24px; -fx-fill: white;");
        questionText.setFont(Font.loadFont("file:src/fonts/Roboto-Regular.ttf", 24)); // Cambia la ruta al archivo de la fuente "Roboto"

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button noneButton = new Button("Nunca");
        Button lowButton = new Button("Raramente");
        Button mediumButton = new Button("A veces");
        Button highButton = new Button("Siempre");

        String buttonStyle = "-fx-background-color: #1167b1; -fx-text-fill: white; -fx-font-size: 18px; -fx-cursor: hand;";
        noneButton.setStyle(buttonStyle);
        lowButton.setStyle(buttonStyle);
        mediumButton.setStyle(buttonStyle);
        highButton.setStyle(buttonStyle);

        noneButton.setOnAction(e -> handleAnswer("nada"));
        lowButton.setOnAction(e -> handleAnswer("bajo"));
        mediumButton.setOnAction(e -> handleAnswer("medio"));
        highButton.setOnAction(e -> handleAnswer("alto"));

        buttonBox.getChildren().addAll(noneButton, lowButton, mediumButton, highButton);
        questionPane.getChildren().addAll(questionText, buttonBox);
    }

    private void setupResultPane() {
        resultPane = new VBox(10);
        resultPane.setAlignment(Pos.CENTER);
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < sintomasList.size()) {
            Text questionText = new Text(preguntasList.get(currentQuestionIndex));
            questionText.setFont(Font.loadFont("file:src/fonts/Roboto-Regular.ttf", 26));
            questionPane.getChildren().set(0, questionText);
            currentQuestionIndex++;
        } else {
            finishTest();
        }
    }

    private void finishTest() {
    resultPane.getChildren().clear(); // Limpia el contenido previo en caso necesario

    Text resultText = new Text("Puntaje total: " + puntajeTotal);
    resultText.setFont(Font.loadFont("file:src/fonts/Roboto-Black.ttf", 36)); // Aumentamos el tamaño de fuente
    resultText.setFill(Color.web("#333333")); // Color de texto más oscuro
    resultText.setTextAlignment(TextAlignment.CENTER); // Centra el texto

    Query queryRecomend = new Query("clasificar_depresion(" + puntajeTotal + ")");

    if (queryRecomend.hasSolution()) {
        System.out.println("mostrar");
        String recomendaciones = Recomendaciones.obtenerRecomendaciones(puntajeTotal);

        Text recomendacionText = new Text("Recomendaciones:");
        recomendacionText.setFont(Font.loadFont("file:src/fonts/Roboto-Bold.ttf", 30)); // Fuente en negrita y tamaño más grande
        recomendacionText.setFill(Color.web("#4CAF50")); // Color verde para destacar

        TextArea recomendacionArea = new TextArea(recomendaciones);
        recomendacionArea.setFont(Font.loadFont("file:src/fonts/Roboto-Regular.ttf", 20)); // Fuente regular y tamaño más grande
        recomendacionArea.setEditable(false);
        recomendacionArea.setStyle("-fx-background-color: linear-gradient(to bottom, #4CAF50, #45a049); -fx-text-fill: black;");
        recomendacionArea.setPrefHeight(300); // Altura fija para el área de recomendaciones
        
        // Agregamos el TextArea a un ScrollPane para manejar contenido largo
        ScrollPane scrollPane = new ScrollPane(recomendacionArea);
        scrollPane.setFitToWidth(true); // Se ajusta al ancho disponible

        resultPane.getChildren().addAll(resultText, recomendacionText, scrollPane);
    }

    primaryStage.setScene(new Scene(resultPane, 1400, 700));
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
