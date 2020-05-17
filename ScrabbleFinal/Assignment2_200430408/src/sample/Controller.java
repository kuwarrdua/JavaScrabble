package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private static Model model=new Model();

    @FXML
    private ListView<String> words =new ListView<>();
    @FXML
    private ListView<String> rules =new ListView<>();
    @FXML
    private ListView<String> points =new ListView<>();
    @FXML
    private TextField word;
    @FXML
    private Label status;
    @FXML
    private Label error;
    @FXML
    private ListView<String> point2 =new ListView<>();
    @FXML
    private Label totalPoints;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        words.setItems(model.getWordList());
        rules.setItems(model.getRules());
        points.setItems(model.getCountList());
        point2.setItems(model.getPointTable());


    }


    public void MainPage(ActionEvent event) throws IOException {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Main.getMainPage());
        window.show();
    }

    public void RulePage(ActionEvent event) throws IOException {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Main.getRulePage());
        window.show();
    }
    public void Exit(ActionEvent event) {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

    }

    public void SubmitWord(ActionEvent event) throws IOException {

        if(!checkIt(word.getText())) {
            status.setText("Invalid Word: "+word.getText());
            word.clear();
            status.setTextFill(Paint.valueOf("Red"));
            return;
        }
        int p=model.getWordPoints(word.getText().toUpperCase());
        status.setText("Done! "+p+" Points Added");
        error.setText("");
        status.setTextFill(Paint.valueOf("Green"));
        model.AddWord(word.getText());
        model.TakePoints(word.getText().toUpperCase());
        totalPoints.setText(model.getNetPoints()+"");
        words.setItems(model.getWordList());
        points.setItems(model.getCountList());
        word.clear();
    }


    private boolean checkIt(String text) {
        if(text==null||text.length()<2||text.length()>8) {
            error.setText("Error: word length should be in 2 to 8");
            return false;
        }
        if(model.CheckWord(text)) {
            error.setText("Error: No duplication of words allowed!");
            return false;
        }
        text=text.toUpperCase();
        for(int i=0;i<text.length();i++) {
            if(text.charAt(i)<'A'||text.charAt(i)>'Z') {
                error.setText("Error: only alphabets are allowed!");
                return false;
            }
        }
        int[] arr=new int[26];
        for(int i=0;i<text.length();i++) {
            arr[text.charAt(i)-'A']++;
        }
        if(arr['A'-'A']+arr['E'-'A']+arr['I'-'A']+arr['O'-'A']+arr['U'-'A']+arr['Y'-'A']==0) {
            error.setText("Error:  enter one vowel atleast (A, E, I, O, U)!");
            return false;
        }
        if(!model.CheckPoints(arr)) {
            error.setText("Error:  not enough alphabets!");
            return false;
        }

        return true;
    }

}
