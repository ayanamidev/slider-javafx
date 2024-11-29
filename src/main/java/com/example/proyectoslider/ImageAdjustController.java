package com.example.proyectoslider;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageAdjustController {

    @FXML
    private ImageView imageView;

    //Brillo
    @FXML
    private Slider brightnessSlider;

    //Contraste
    @FXML
    private Slider contrastSlider;

    //Saturación
    @FXML
    private Slider saturationSlider;

    //Tonalidad
    @FXML
    private Slider hueSlider;

    private final ColorAdjust colorAdjust = new ColorAdjust();

    public void initialize() {
        // Cargar una imagen
        Image image = new Image(String.valueOf(getClass().getResource("imagen.jpg"))); // Ruta de la imagen
        imageView.setImage(image);

        // Asignar el efecto de ajuste de color a la imagen
        imageView.setEffect(colorAdjust);

        // Vincular los sliders con sus efectos de forma directa
        brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setBrightness(newValue.doubleValue()); // Ajuste de brillo
            updateSliderTrackColor(brightnessSlider);
        });

        contrastSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setContrast(newValue.doubleValue()); // Ajuste de contraste
            updateSliderTrackColor(contrastSlider);
        });

        saturationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setSaturation(newValue.doubleValue()); // Ajuste de saturación
            updateSliderTrackColor(saturationSlider);
        });

        hueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setHue(newValue.doubleValue()); // Ajuste de tonalidad
            updateSliderTrackColor(hueSlider);
        });

        // Usar Platform.runLater para asegurarse de que el Slider se haya renderizado
        Platform.runLater(() -> {
            // Establecer los colores de la barra al inicio
            updateSliderTrackColor(brightnessSlider);
            updateSliderTrackColor(contrastSlider);
            updateSliderTrackColor(saturationSlider);
            updateSliderTrackColor(hueSlider);
        });
    }

    /**
     * Método para actualizar el color de la barra del slider
     */
    private void updateSliderTrackColor(Slider slider) {
        // Usamos lookup() para obtener el track del slider
        var track = slider.lookup(".track");
        if (track != null) {
            // Calcula el porcentaje lleno del slider
            double percentage = (slider.getValue() - slider.getMin()) / (slider.getMax() - slider.getMin());
            // Aplica el color dinámico a la barra de progreso
            track.setStyle("-fx-background-color: linear-gradient(to right, #2196F3 "
                    + (percentage * 100) + "%, lightgray " + (percentage * 100) + "%);");
        }
    }
}
