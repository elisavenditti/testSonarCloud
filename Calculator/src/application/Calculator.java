package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Calculator extends Application{

	private TextField calcoli;
	private TextField risultato;
	private static ArrayList<String> numbers = new ArrayList<>();
	private static ArrayList<String> parentesi = new ArrayList<>();
	private static ArrayList<String> operazioni = new ArrayList<>();
	
	public static void main(String[] args){
		int i=0;
		for(i=0; i<10; i++) {
			numbers.add(Integer.toString(i));
		}
		parentesi.add("(");
		parentesi.add(")");
		operazioni.add("+");
		operazioni.add("-");
		operazioni.add("*");
		operazioni.add("/");

		launch(args);
	}
	
	
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Calcolatrice");

		VBox root = new VBox();	
		ArrayList<Node> rows = new ArrayList<>();
		rows.add(new HBox());
		rows.add(new HBox());
		rows.add(new Region());
		rows.add(new HBox(15));
		rows.add(new HBox(15));
		rows.add(new HBox(15));
		
		for(Node h: rows) {
			if(!h.equals(rows.get(2))) {
				((HBox) h).setPrefSize(220, 50);
				((HBox) h).setAlignment(Pos.CENTER);
			} else{
				((Region) h).setPrefSize(220, 40);
			}
		}
		HBox lastRow = new HBox(15);
		lastRow.setPrefSize(220, 100);
		lastRow.setAlignment(Pos.CENTER);
		rows.add(lastRow);
		
		
		
		
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>(){	
			@Override
			public void handle(ActionEvent e) {
				click(e);
			}};

		ArrayList<Button> buttons = new ArrayList<>();
		String labels[] = {".","0", "C","=","1","2","3","4","5","6","+","7","8","9","-","(",")","*","/"};
		for(String b: labels) {
			Button newB = new Button(b);
			if(b.compareToIgnoreCase("=")==0) {
				newB.setPrefSize(37, 95);
				newB.setStyle("-fx-background-radius: 15; -fx-background-color: linear-gradient(#ffa64d, #ff5050);-fx-text-fill: #FFFFFF");
			}
			
			else if(b.compareTo("(")==0||b.compareTo(")")==0||b.compareTo("+")==0||
					b.compareTo("-")==0||b.compareTo("*")==0||b.compareTo("/")==0) {
				newB.setPrefSize(40, 40);
				newB.setStyle("-fx-background-radius: 15; -fx-background-color: linear-gradient(#ffe066, #ffa64d);-fx-text-fill: #FFFFFF");
				
			} else {
				newB.setPrefSize(40, 40);
				newB.setStyle("-fx-background-radius: 15; -fx-background-color: #FFFFFF30; -fx-text-fill: #FFFFFF");
			}
			buttons.add(newB);
			newB.setOnAction(handler);
			
		}
		calcoli = new TextField();
		risultato = new TextField();
		calcoli.setPrefSize(220, 50);
		risultato.setPrefSize(220, 50);
		calcoli.setStyle("-fx-background-color: #00000000; -fx-text-fill: #FFFFFF");
		risultato.setStyle("-fx-background-color: #00000000; -fx-text-fill: #FFFFFF");
		calcoli.setFont(Font.font("Arial Nova Light"));
		risultato.setFont(Font.font("Arial Nova Light", FontWeight.NORMAL, 25));
		
		HBox zero = (HBox) rows.get(0);
		zero.getChildren().add(calcoli);
		HBox one = (HBox) rows.get(1);
		one.getChildren().add(risultato);
		
		//popolo le righe
		HBox three = (HBox) rows.get(3);
		three.getChildren().add(buttons.get(15));
		three.getChildren().add(buttons.get(16));
		three.getChildren().add(buttons.get(17));
		three.getChildren().add(buttons.get(18));
		
		HBox four = (HBox) rows.get(4);
		four.getChildren().add(buttons.get(11));
		four.getChildren().add(buttons.get(12));
		four.getChildren().add(buttons.get(13));
		four.getChildren().add(buttons.get(14));
		
		HBox five = (HBox) rows.get(5);
		five.getChildren().add(buttons.get(7));
		five.getChildren().add(buttons.get(8));
		five.getChildren().add(buttons.get(9));
		five.getChildren().add(buttons.get(10));
		
		HBox six = (HBox) rows.get(6);
		VBox v = new VBox();
		v.setPrefSize(154, 100);
		v.setAlignment(Pos.CENTER);
		HBox h1 = new HBox(15);
		h1.setPrefSize(220, 50);
		h1.setAlignment(Pos.CENTER);
		h1.getChildren().add(buttons.get(4));
		h1.getChildren().add(buttons.get(5));
		h1.getChildren().add(buttons.get(6));
		HBox h2 = new HBox(15);
		h2.setPrefSize(220, 50);
		h2.setAlignment(Pos.CENTER);
		h2.getChildren().add(buttons.get(0));
		h2.getChildren().add(buttons.get(1));
		h2.getChildren().add(buttons.get(2));
		v.getChildren().add(h1);
		v.getChildren().add(h2);
		six.getChildren().add(v);
		six.getChildren().add(buttons.get(3));
		root.setStyle("-fx-background-color: linear-gradient( #001133B5, #001133);");
		root.getChildren().add(rows.get(0));
		root.getChildren().add(rows.get(1));
		root.getChildren().add(rows.get(2));
		root.getChildren().add(rows.get(3));
		root.getChildren().add(rows.get(4));
		root.getChildren().add(rows.get(5));
		root.getChildren().add(rows.get(6));
	
		Scene scene = new Scene(root,220,400);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	private void click(ActionEvent e) {

		Button bottone = (Button) e.getSource();
		String label = bottone.getText();
		String equazione = calcoli.getText();
		
		switch(label) {
		
		case "0":	case "1":	case "2": case "3":	case "4":	case "5":	case "6":	case "7":	case "8":	case "9":
			if(equazione==null||equazione.compareTo("")==0) equazione=label;
			else{	
				//devo vedere se posso inserire il numero
				String lastChar = equazione.substring(equazione.length()-1);
				if(numbers.contains(lastChar) || operazioni.contains(lastChar) || lastChar.compareTo(".")==0) equazione = equazione + label;
			}
			calcoli.setText(equazione);
			break;
		
		
		case "+": case "-": case "*": case "/":
			if(equazione==null||equazione.compareTo("")==0) break;
			else{
				String lastChar = equazione.substring(equazione.length()-1);
			
				if(lastChar.compareTo("(")==0 || operazioni.contains(lastChar)) break;
				else equazione = equazione + label;
				calcoli.setText(equazione);
			}
			break;
		case "C":
			calcoli.setText("");
			risultato.setText("");
			break;
			
			
		case ".":
			if(equazione==null||equazione.compareTo("")==0) break;
			String lastChar = equazione.substring(equazione.length()-1);
			if(operazioni.contains(lastChar) || lastChar.compareTo(".")==0 || !commaAllowed(equazione)) break;
			equazione = equazione + label;
			calcoli.setText(equazione);
			break;
			
		case "=":
			calcoli.setText("");
			Expression exp = new Expression(equazione);
			//imposta il risultato
			risultato.setText(exp.calculateResult());
			break;
			
		default: break;
		
		}

		
		
	}
	private boolean commaAllowed(String exp) {
		
		int k;
		for(k=exp.length()-2; k>=0; k--) {
			if((exp.substring(k, k+1)).compareTo(".")==0) return false;
			else if(operazioni.contains(exp.substring(k, k+1))) return true;
		}
		
		return true;
	}
	
}
