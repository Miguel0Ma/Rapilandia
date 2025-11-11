module co.edu.uniquindio.enviospepepicapapas {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.enviospepepicapapas to javafx.fxml;
    opens co.edu.uniquindio.enviospepepicapapas.Controllers to javafx.fxml;
    opens co.edu.uniquindio.enviospepepicapapas.model to javafx.graphics;
    exports co.edu.uniquindio.enviospepepicapapas.Controllers;
    exports co.edu.uniquindio.enviospepepicapapas.model;
}