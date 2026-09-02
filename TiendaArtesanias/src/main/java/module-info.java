module ni.edu.uam.tiendaartesanias {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.tiendaartesanias to javafx.fxml;
    exports ni.edu.uam.tiendaartesanias;
}