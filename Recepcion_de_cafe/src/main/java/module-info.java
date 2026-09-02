module ni.edu.uam.recepcion_de_cafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens ni.edu.uam.recepcion_de_cafe to javafx.fxml;
    opens ni.edu.uam.recepcion_de_cafe.model to javafx.base;
    exports ni.edu.uam.recepcion_de_cafe;
}