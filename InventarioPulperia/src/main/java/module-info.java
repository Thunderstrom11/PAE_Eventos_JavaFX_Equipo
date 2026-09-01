module ni.edu.uam.inventariopulperia {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.inventariopulperia to javafx.fxml;
    exports ni.edu.uam.inventariopulperia;
}