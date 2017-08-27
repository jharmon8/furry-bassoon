
/*public class BassoonDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, Jacob!");
		System.out.println("FORMATTING HARD DRIVE...");
		
	}

}*/
/*
Sam McKinney
CS5405
HW9
*/
//package code;

import javafx.application.Application;
import java.util.Scanner;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BassoonDriver extends Application{
	int x1, x2, y1, y2, w1, w2, h1, h2, numClicks;
	double t1_p1x, t1_p1y, t1_p2x, t1_p2y, t1_p3x, t1_p3y;
	double t2_p1x, t2_p1y, t2_p2x, t2_p2y, t2_p3x, t2_p3y;
	double xcoord, ycoord;
	Text t1, t2, t3, t4, t5, t6;
	TextField x1_text, x2_text, y1_text, y2_text, w1_text, w2_text, h1_text, h2_text;
	Polygon tri1, tri2, tri3, tri4;
	Button t1_button, t2_button, clear_button;
	Pane p, authPane, descPane;
	FlowPane fp_up, fp_down;
	Scanner scan;
	String coords = "";
	Label mouseCoords = new Label(coords);
	Label intersect;
	
	@Override
	public void start(Stage stage){
		TabPane tp = new TabPane();
		tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		Tab author = new Tab();			author.setText("Author");
		Tab description = new Tab();	description.setText("Project Description");
		Tab program = new Tab();		program.setText("Program");
		
		authPane = createAuthorPane();
		descPane = createDescriptionPane();
		
		
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-border-color: black");

		p = new Pane();
		p.setStyle("-fx-border-color: black");
		p.getChildren().add(mouseCoords);

		fp_up = new FlowPane();
		fp_down = new FlowPane();
		fp_up.setStyle("-fx-border-color: black");
		fp_down.setStyle("-fx-border-color: black");
		fp_down.getChildren().add(calcIntersect());

		Scene scene = new Scene(tp, 550, 628, Color.WHITE);
		stage.setScene(scene);
		stage.setTitle("Java HW9 - Triangle Demo");

		//Labels
		String instr = "Enter the coordinates, width, and height for each triangle and click the create button to display them.\n" +
						"Otherwise, click on the screen to add points to a triangle. The clear button will clear the screen.";
		String inter = "";
		Label instructions = new Label(instr);
		intersect = new Label(inter);
		fp_up.getChildren().add(instructions);
		Label space = new Label("");
		Label t1_label = new Label("Triangle 1");
		Label t2_label = new Label("Triangle 2");
		Label x1_label = new Label("Enter x-coordinate:"); Label x2_label = new Label("Enter x-coordinate:");
		Label y1_label = new Label("Enter y-coordinate:"); Label y2_label = new Label("Enter y-coordinate:");
		Label w1_label = new Label("Enter width:");		   Label w2_label = new Label("Enter width:");
		Label h1_label = new Label("Enter height:");	   Label h2_label = new Label("Enter height:");

		t1_label.setStyle("-fx-padding: 10 5 0 0;");	   t2_label.setStyle("-fx-padding: 30 5 0 0;");
		y1_label.setStyle("-fx-padding: 5 0 0 0;");	   	   y2_label.setStyle("-fx-padding: 5 0 0 0;");
		w1_label.setStyle("-fx-padding: 5 0 0 0;");	       w2_label.setStyle("-fx-padding: 5 0 0 0;");
		h1_label.setStyle("-fx-padding: 5 0 0 0;");	       h2_label.setStyle("-fx-padding: 5 0 0 0;");

		//Text fields
		x1_text = new TextField(); x2_text = new TextField();
		y1_text = new TextField(); y2_text = new TextField();
		w1_text = new TextField(); w2_text = new TextField();
		h1_text = new TextField(); h2_text = new TextField();

		x1_text.setPromptText("x-coord");	x2_text.setPromptText("x-coord");
		y1_text.setPromptText("y-coord");	y2_text.setPromptText("y-coord");
		w1_text.setPromptText("width");		w2_text.setPromptText("width");
		h1_text.setPromptText("height");	h2_text.setPromptText("height");

		x1_text.setPrefWidth(125);			x2_text.setPrefWidth(125);
		y1_text.setPrefWidth(125);			y2_text.setPrefWidth(125);
		w1_text.setPrefWidth(125);  		w2_text.setPrefWidth(125);
		h1_text.setPrefWidth(125);			h2_text.setPrefWidth(125);

		//Buttons
		t1_button = new Button();
		t2_button = new Button();
		clear_button = new Button();
		t1_button.setText("Create triangle");
		t2_button.setText("Create triangle");
		clear_button.setText("Clear");


		//VBox elements
		VBox vb = new VBox();
		vb.setStyle("-fx-border-color: black");
		vb.getChildren().add(t1_label);
		vb.getChildren().add(x1_label);
		vb.getChildren().add(x1_text);
		vb.getChildren().add(y1_label);
		vb.getChildren().add(y1_text);
		vb.getChildren().add(w1_label);
		vb.getChildren().add(w1_text);
		vb.getChildren().add(h1_label);
		vb.getChildren().add(h1_text);
		vb.getChildren().add(t1_button);

		vb.getChildren().add(t2_label);
		vb.getChildren().add(x2_label);
		vb.getChildren().add(x2_text);
		vb.getChildren().add(y2_label);
		vb.getChildren().add(y2_text);
		vb.getChildren().add(w2_label);
		vb.getChildren().add(w2_text);
		vb.getChildren().add(h2_label);
		vb.getChildren().add(h2_text);
		vb.getChildren().add(t2_button);
		vb.getChildren().add(space);
		vb.getChildren().add(clear_button);
		
		//Tab elements
		author.setContent(authPane);
		description.setContent(descPane);
		program.setContent(bp);

		//Add to panes
		scene.setRoot(tp);
		
		tp.getTabs().add(author);
		tp.getTabs().add(description);
		tp.getTabs().add(program);
		
		
		bp.setTop(fp_up);
		bp.setBottom(fp_down);
		bp.setCenter(p);
		bp.setRight(vb);


		//Action Handlers
		t1_button.setOnAction(new ButtonHandler());
		t2_button.setOnAction(new ButtonHandler());
		clear_button.setOnAction(new ButtonHandler());
		p.setOnMouseMoved(new MouseMoveHandler());
		p.setOnMouseEntered(new MouseEnterHandler());
		p.setOnMouseExited(new MouseExitHandler());
		p.setOnMouseClicked(new MouseClickHandler());
		
		numClicks = 0;
		tri3 = new Polygon();
		tri4 = new Polygon();
		//p.getChildren().add(tri3);
		//p.getChildren().add(tri4);
			
		stage.show();
	}
	
	/**
	Handler for button objects
	*/
	public class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			if(e.getSource() == t1_button){
				get_var1();
				p.getChildren().remove(tri1);
				tri1 = createTriangle(t1_p1x, t1_p1y, t1_p2x, t1_p2y, t1_p3x, t1_p3y);
				p.getChildren().add(tri1);
				intersect = calcIntersect();
				fp_down.getChildren().clear();
				fp_down.getChildren().add(intersect);
			}
			else if(e.getSource() == t2_button){
				get_var2();
				p.getChildren().remove(tri2);
				tri2 = createTriangle(t2_p1x, t2_p1y, t2_p2x, t2_p2y, t2_p3x, t2_p3y);
				p.getChildren().add(tri2);
				intersect = calcIntersect();
				fp_down.getChildren().clear();
				fp_down.getChildren().add(intersect);
			}
			else if(e.getSource() == clear_button){
				p.getChildren().remove(tri1);
				p.getChildren().remove(tri2);
				p.getChildren().remove(tri3);
				p.getChildren().remove(tri4);
				tri1 = new Polygon();
				tri2 = new Polygon();
				tri3 = new Polygon();
				tri4 = new Polygon();
				intersect.setText("");
				fp_down.getChildren().clear();
				fp_down.getChildren().add(intersect);
				//p.getChildren().add(tri3);
				//p.getChildren().add(tri4);
				numClicks = 0;
			}
		}
	}
	
	public class MouseMoveHandler implements EventHandler<MouseEvent>{
		public void handle(MouseEvent e){
			xcoord = e.getX();
			ycoord = e.getY();
			if(ycoord < 0 || ycoord > 540)
				coords = "";
			else
				coords = "x: " + xcoord + "\ny: " + ycoord;
			mouseCoords.setText(coords);
			mouseCoords.relocate(xcoord + 14, ycoord - 7);
		}
	}
	
	public class MouseEnterHandler implements EventHandler<MouseEvent>{
		public void handle(MouseEvent e){
			coords = "x: " + xcoord + "\ny: " + ycoord;
			mouseCoords.setText(coords);
		}
	}
	
	public class MouseExitHandler implements EventHandler<MouseEvent>{
		public void handle(MouseEvent e){
			coords = "";
			mouseCoords.setText(coords);
		}
	}
	
	public class MouseClickHandler implements EventHandler<MouseEvent>{
		public void handle(MouseEvent e){
			if(numClicks < 3){
				addTriangle3Point();
				numClicks++;
			}
			else if(numClicks < 6){
				addTriangle4Point();
				numClicks++;
			}
			
			switch(numClicks){
				case 1:
					t1_p1x = xcoord;
					t1_p1y = ycoord;
					break;
				case 2:
					t1_p2x = xcoord;
					t1_p2y = ycoord;
					break;
				case 3:
					t1_p3x = xcoord;
					t1_p3y = ycoord;
					p.getChildren().remove(tri1);
					tri1 = createTriangle(t1_p1x, t1_p1y, t1_p2x, t1_p2y, t1_p3x, t1_p3y);
					p.getChildren().add(tri1);
					intersect = calcIntersect();
					fp_down.getChildren().clear();
					fp_down.getChildren().add(intersect);
					break;
				case 4:
					t2_p1x = xcoord;
					t2_p1y = ycoord;
					break;
				case 5:
					t2_p2x = xcoord;
					t2_p2y = ycoord;
					break;
				case 6:
					t2_p3x = xcoord;
					t2_p3y = ycoord;
					p.getChildren().remove(tri2);
					tri2 = createTriangle(t2_p1x, t2_p1y, t2_p2x, t2_p2y, t2_p3x, t2_p3y);
					p.getChildren().add(tri2);
					intersect = calcIntersect();
					fp_down.getChildren().clear();
					fp_down.getChildren().add(intersect);
					numClicks = 7;
					break;
			}
		}
	}
	
	/**
	Scans text boxes and sets coordinate variables for triangle 1
	*/
	public void get_var1(){
		x1 = 0; y1 = 0; w1 = 0; h1 = 0;
		scan = new Scanner(x1_text.getText());
		if(!x1_text.getText().trim().isEmpty()){
			x1 = scan.nextInt();
		}
		scan = new Scanner(y1_text.getText());
		if(!y1_text.getText().trim().isEmpty()){
			y1 = scan.nextInt();
		}
		scan = new Scanner(w1_text.getText());
		if(!w1_text.getText().trim().isEmpty()){
			w1 = scan.nextInt();
		}
		scan = new Scanner(h1_text.getText());
		if(!h1_text.getText().trim().isEmpty()){
			h1 = scan.nextInt();
		}
		
		t1_p1x = x1; t1_p1y = y1;
		t1_p2x = x1 + w1; t1_p2y = y1;
		t1_p3x = x1 + w1/2; t1_p3y = y1 + h1;
	}
	
	/**
	Scans text boxes and sets coordinate variables for triangle 2
	*/
	public void get_var2(){
		x2 = 0; y2 = 0; w2 = 0; h2 = 0;
		scan = new Scanner(x2_text.getText());
		if(!x2_text.getText().trim().isEmpty()){
			x2 = scan.nextInt();
		}
		scan = new Scanner(y2_text.getText());
		if(!y2_text.getText().trim().isEmpty()){
			y2 = scan.nextInt();
		}
		scan = new Scanner(w2_text.getText());
		if(!w2_text.getText().trim().isEmpty()){
			w2 = scan.nextInt();
		}
		scan = new Scanner(h2_text.getText());
		if(!h2_text.getText().trim().isEmpty()){
			h2 = scan.nextInt();
		}
		
		t2_p1x = x2; t2_p1y = y2;
		t2_p2x = x2 + w2; t2_p2y = y2;
		t2_p3x = x2 + w2/2; t2_p3y = y2 + h2;
	}
  
	/**
	Creates a Polygon with 3 points and returns Polygon
	*/
	public Polygon createTriangle(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y){
		Polygon tri = new Polygon();
		tri.getPoints().addAll(new Double[]{
			p1x, p1y,
			p2x, p2y,
			p3x, p3y });
			
		tri.setFill(Color.WHITE);
		tri.setStroke(Color.BLACK);
		tri.setStrokeType(StrokeType.INSIDE);
		tri.setOpacity(.7);
			
		return tri;
	}
	
	/**
	Adds a point to a triangle 1
	*/
	public void addTriangle3Point(){
		tri3.getPoints().addAll(xcoord, ycoord);
		tri3.setFill(Color.WHITE);
		tri3.setStroke(Color.BLACK);
		tri3.setStrokeType(StrokeType.INSIDE);
	}
	
	/**
	Adds a point to a triangle 2
	*/
	public void addTriangle4Point(){
		tri4.getPoints().addAll(xcoord, ycoord);
		tri4.setFill(Color.WHITE);
		tri4.setStroke(Color.BLACK);
		tri4.setStrokeType(StrokeType.INSIDE);
	}
	
	/**
	Calculates if the two triangles intersect and returns Label
	*/
	public Label calcIntersect(){
		String str = "";
		
		if(tri1==null || tri2==null){
			str = "";
		}
		else if(t1_p1x==t2_p1x && t1_p2x==t1_p2x && t1_p3x==t2_p3x && t1_p2y==t2_p2y && t1_p3y == t2_p3y){
			str = "The triangles are equal.";
		}
		else if(!Shape.intersect(tri1, tri2).getBoundsInLocal().isEmpty()){ //Triangles overlap in any way
			if(check_in_tri1(t2_p1x, t2_p1y)==true && check_in_tri1(t2_p1x, t2_p2y)==true && check_in_tri1(t2_p3x, t2_p3y)==true){
				str = "Triangle 2 is inside triangle 1.";
				
				if(check_touch_1(t2_p1x, t2_p1y)==true || check_touch_1(t2_p1x, t2_p2y)==true || check_touch_1(t2_p3x, t2_p3y)==true)
					str = "Triangle 2 is inside triangle 1 and is touching the boundary.";
			}
			else if(check_in_tri2(t1_p1x, t1_p1y)==true && check_in_tri2(t1_p2x, t1_p2y)==true && check_in_tri2(t1_p3x, t1_p3y)==true){
				str = "Triangle 1 is inside triangle 2.";
				
				if(check_touch_2(t1_p1x, t1_p1y)==true || check_touch_2(t1_p2x, t1_p2y)==true || check_touch_2(t1_p3x, t1_p3y)==true)
					str = "Triangle 1 is inside triangle 2 and is touching the boundary.";
			}
			else
				str = "The triangles are overlapping.";
				
				
		}
		else{
			if(check_touch_1(t2_p1x, t2_p1y)==true || check_touch_1(t2_p1x, t2_p2y)==true || check_touch_1(t2_p3x, t2_p3y)==true
			|| check_touch_2(t1_p1x, t1_p1y)==true || check_touch_2(t1_p2x, t1_p2y)==true || check_touch_2(t1_p3x, t1_p3y)==true)
				str = "The triangles are disjoint and touching externally.";
			else
				str = "The triangles are disjoint.";
		}
		
		
		Label inter = new Label(str);
		return inter;
	}
	
	/**
	Checks if triangle 2 is in triangle 1 and returns boolean
	*/
	public boolean check_in_tri1(double px, double py){
		double p1x, p2x, p3x, p1y, p2y, p3y;
		p1x = t1_p1x;
		p2x = t1_p2x;
		p3x = t1_p3x;
		
		p1y = t1_p1y;
		p2y = t1_p2y;
		p3y = t1_p3y;
		
		double a = ((p2y - p3y)*(px - p3x) + (p3x - p2x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double b = ((p3y - p1y)*(px - p3x) + (p1x - p3x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double c = -a-b+1;
		
		if(a>=0 && b>=0 && c>=0)
			return true;
		else
			return false;
	}
	
	/**
	Checks if triangle 1 is in triangle 2 and returns boolean
	*/
	public boolean check_in_tri2(double px, double py){
		double p1x, p2x, p3x, p1y, p2y, p3y;
		p1x = t2_p1x;
		p2x = t2_p2x;
		p3x = t2_p3x;
		
		p1y = t2_p1y;
		p2y = t2_p2y;
		p3y = t2_p3y;
		
		double a = ((p2y - p3y)*(px - p3x) + (p3x - p2x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double b = ((p3y - p1y)*(px - p3x) + (p1x - p3x)*(py - p3y))/((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y));
		double c = -a-b+1;
		
		if(a>=0 && b>=0 && c>=0)
			return true;
		else
			return false;
	}
	
	/**
	Checks if a point of triangle 2 is touching triangle 1
	*/
	public boolean check_touch_1(double px, double py){
		double m1, m2, m3;
		m1 = (t1_p1y - t1_p2y)/(t1_p1x - t1_p2x);
		m2 = (t1_p2y - t1_p3y)/(t1_p2x - t1_p3x);
		m3 = (t1_p3y - t1_p1y)/(t1_p3x - t1_p1x);
		
		if((py-t1_p1y) == m1*(px-t1_p1x) && (t1_p1x<=px && px<=t1_p2x) && (t1_p1y<=py && py<=t1_p2y))
			return true;
		else if((py-t1_p1y) == m2*(px-t1_p2x) && (t1_p2x<=px && px<=t1_p3x) && (t1_p1y<=py && py<=t1_p3y))
			return true;
		else if((py-t1_p3y) == m3*(px - t1_p3x) && (t1_p3x<=px && px<=t1_p1x) && (t1_p3y<=py && py<=t1_p1y))
			return true;
		else
			return false;
	}
	
	/**
	Checks if a point of triangle 1 is touching triangle 2
	*/
	public boolean check_touch_2(double px, double py){
		double m1, m2, m3;
		m1 = (t2_p1y - t2_p2y)/(t2_p1x - t2_p2x);
		m2 = (t2_p2y - t2_p3y)/(t2_p2x - t2_p3x);
		m3 = (t2_p3y - t2_p1y)/(t2_p3x - t2_p1x);
		
		if((py-t2_p1y) == m1*(px-t2_p1x) && (t2_p1x<=px && px<=t2_p2x) && (t2_p1y<=py && py<=t2_p2y))
			return true;
		else if((py-t2_p1y) == m2*(px-t2_p2x) && (t2_p2x<=px && px<=t2_p3x) && (t2_p1y<=py && py<=t2_p3y))
			return true;
		else if((py-t2_p3y) == m3*(px-t2_p3x) && (t2_p3x<=px && px<=t2_p1x) && (t2_p3y<=py && py<=t2_p1y))
			return true;
		else
			return false;
	}
	
	/**
	Creates pane that displays welcome message and author information
	*/
	public Pane createAuthorPane(){
		Pane pane = new Pane();
		t1 = new Text(25, 50, "Demonstration of Assignment 9\nJava, GUI and Visualization: CS5405");
        t1.setFont(new Font(20));
        t1.setFill(Color.RED);
        t1.setWrappingWidth(500);
        t1.setTextAlignment(TextAlignment.CENTER);
		
		t2 = new Text(25, 110, "Presented by:");
		t2.setFont(new Font(15));
		t2.setWrappingWidth(500);
        t2.setTextAlignment(TextAlignment.CENTER);
		
		t3 = new Text(25, 135, "Samuel McKinney");
		t3.setFont(new Font(20));
		t3.setWrappingWidth(500);
		t3.setTextAlignment(TextAlignment.CENTER);
		
		t4 = new Text(25, 150, "samt34@mst.edu");
		t4.setFont(new Font(10));
		t4.setWrappingWidth(500);
		t4.setTextAlignment(TextAlignment.CENTER);
		
		t5 = new Text(25, 300, "This is my orignal code\nNo IDE was used in the submission.\n\nI did not give my code to anyone nor did I use anyone else's code in this work.");
		t5.setFont(new Font(20));
		t5.setWrappingWidth(500);
		t5.setTextAlignment(TextAlignment.CENTER);
		
		
		pane.getChildren().addAll(t1, t2, t3, t4, t5);
		
		return pane;
	}
	
	/**
	Creates pane that displays problem information
	*/
	public Pane createDescriptionPane(){
		Pane pane = new Pane();
		t6 = new Text(25, 50, "This is extension of HW08. Use mouse to input triangles interactively on the screen. "
							+ "As the mouse moves it tells you the x-y coordinates of the point under the cursor. "
							+ "You can see the coordinates before clicking it. When you click mouse, you can record x, y coordinates of the point. "
							+ "You can control the program with button for new set of triangles.");
		t6.setFont(new Font(15));
		t6.setWrappingWidth(500);
		t6.setTextAlignment(TextAlignment.CENTER);
							
		pane.getChildren().addAll(t6);
		
		return pane;
	}

	public static void main(String[] args){
		launch(args);
	}
}


