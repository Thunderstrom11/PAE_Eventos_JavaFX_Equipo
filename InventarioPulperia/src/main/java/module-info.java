module ni.edu.uam.inventariopulperia {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens ni.edu.uam.inventariopulperia to javafx.fxml;
    opens ni.edu.uam.inventariopulperia.model   to javafx.base;
    exports ni.edu.uam.inventariopulperia;
}