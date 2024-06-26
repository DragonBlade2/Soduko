module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.sudoku.Controllers to javafx.fxml;

    opens com.example.sudoku to javafx.fxml;
    exports com.example.sudoku;
}