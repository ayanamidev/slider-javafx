module com.example.proyectoslider {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoslider to javafx.fxml;
    exports com.example.proyectoslider;
}