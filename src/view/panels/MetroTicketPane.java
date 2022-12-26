package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MetroTicketPane extends GridPane {

    public MetroTicketPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Button newCard = new Button();
        newCard.setText("New Metro card");
        newCard.setOnAction(event -> this.addNewCard());
        this.getChildren().add(newCard);
    }

    private void addNewCard(){
        System.out.println("voeg kaartje toe");
    }
}
