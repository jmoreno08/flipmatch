package application.controller;

import application.model.Card;
import application.model.GameStats;
import application.service.DeckService;
import application.service.GameService;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;

public class GameController {
    private final BorderPane root = new BorderPane();

    // Top bar
    private final Label timeLbl = makeLabel("Time\n00:00");
    private final Label scoreLbl = makeLabel("Score\n0");
    private final Label triesLbl = makeLabel("Tries\n0");
    private final ComboBox<String> deckCombo = new ComboBox<>();

    // Center grid
    private final GridPane grid = new GridPane();

    // Bottom
    private final Button restartBtn = new Button("Restart Game");

    private final GameService game = new GameService();
    private final DeckService decks = new DeckService();

    private Timeline timer;

    public GameController() {
        buildUI();
        setupTimer();
    }

    public Pane getRoot() { return root; }

    private void buildUI() {
        // TOP
        HBox statBox = new HBox(16, boxed(timeLbl), boxed(scoreLbl), boxed(triesLbl));
        statBox.getStyleClass().add("stats-row");

        deckCombo.getItems().addAll("Animals", "Food", "Objects");
        deckCombo.setValue("Animals");
        deckCombo.setOnAction(e -> startNewGame(deckCombo.getValue()));
        HBox deckBox = new HBox(8, new Label("Deck:"), deckCombo);
        deckBox.setAlignment(Pos.CENTER_LEFT);
        deckBox.setPadding(new Insets(0,0,0,8));

        VBox top = new VBox(8, statBox, deckBox);
        top.getStyleClass().add("top-pane");
        root.setTop(top);

        // GRID
        grid.setPadding(new Insets(12));
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setAlignment(Pos.TOP_CENTER);
        root.setCenter(grid);

        // BOTTOM
        restartBtn.setMaxWidth(Double.MAX_VALUE);
        restartBtn.getStyleClass().add("restart-btn");
        restartBtn.setOnAction(e -> startNewGame(deckCombo.getValue()));
        HBox bottom = new HBox(restartBtn);
        bottom.setPadding(new Insets(12));
        root.setBottom(bottom);
    }

    private void setupTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            game.getStats().tick();
            refreshTopBar();
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    private Label makeLabel(String text) {
        Label l = new Label(text);
        l.setFont(Font.font(14));
        return l;
    }

    private Region boxed(Label inner) {
        VBox box = new VBox(inner);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(8));
        box.getStyleClass().add("stat-box");
        return box;
    }

    public void startNewGame(String deckName) {
        game.newBoard(decks.buildDeck(deckName).shuffledCopy16());
        buildGrid();
        game.getStats().reset();
        refreshTopBar();
        timer.stop();
        timer.playFromStart();
    }

    private void buildGrid() {
        grid.getChildren().clear();
        List<Card> cards = game.getBoard();
        int idx = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                Card card = cards.get(idx++);
                Button b = cardButton(card, r, c);
                grid.add(b, c, r);
            }
        }
    }

    private Button cardButton(Card card, int row, int col) {
        Button b = new Button("?");
        b.setPrefSize(80, 80);
        b.getStyleClass().add("card");
        b.setOnAction(e -> onFlip(card, b));
        updateCardClasses(b, card);
        return b;
    }

    private void onFlip(Card card, Button b) {
        if (game.isLocked()) return;
        if (!game.flip(card)) return;

        updateCardClasses(b, card);
        refreshTopBar();

        if (game.isLocked()) {
            // fallo → mostrar 700ms y tapar
            PauseTransition pt = new PauseTransition(Duration.millis(700));
            pt.setOnFinished(ev -> {
                game.hideUnmatched();
                rebuildFaces();
            });
            pt.play();
        } else if (game.allMatched()) {
            timer.stop();
            // Bonus por tiempo < 5:00
            if (game.getStats().getSeconds() < 300) {
                game.getStats().addScore(200);
                refreshTopBar();
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION,
                "¡Completado!\nTiempo: " + format(game.getStats().getSeconds()) +
                "\nIntentos: " + game.getStats().getTries() +
                "\nPuntaje: " + game.getStats().getScore());
            a.setHeaderText("Flip & Match");
            a.showAndWait();
        }
    }

    private void rebuildFaces() {
        grid.getChildren().forEach(node -> {
            if (node instanceof Button) {
                int col = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int idx = row * 4 + col;
                Card card = game.getBoard().get(idx);
                updateCardClasses((Button) node, card);
            }
        });
        refreshTopBar();
    }

    private void updateCardClasses(Button b, Card card) {
        b.getStyleClass().removeAll("card-faceup", "card-matched");
        if (card.isMatched()) {
            b.setText(card.getFace());
            b.setDisable(true);
            b.getStyleClass().add("card-matched");
        } else if (card.isFaceUp()) {
            b.setText(card.getFace());
            b.getStyleClass().add("card-faceup");
        } else {
            b.setText("?");
        }
    }

    private void refreshTopBar() {
        GameStats s = game.getStats();
        timeLbl.setText("Time\n" + format(s.getSeconds()));
        scoreLbl.setText("Score\n" + s.getScore());
        triesLbl.setText("Tries\n" + s.getTries());
    }

    private String format(int seconds) {
        int m = seconds / 60, s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }
}
