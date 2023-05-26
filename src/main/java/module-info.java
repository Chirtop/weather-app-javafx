module com.example.vreme {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires json.simple;


    opens com.example.vreme to javafx.fxml;
    exports com.example.vreme;
}