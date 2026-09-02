module ni.edu.uam.tiendaartesanias {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.tiendaartesanias to javafx.fxml;
    opens ni.edu.uam.tiendaartesanias.model to javafx.base;
    exports ni.edu.uam.tiendaartesanias;
}