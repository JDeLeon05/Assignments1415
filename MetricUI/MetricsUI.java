import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class MetricsUI extends Application{
    //grid pane & scene
    GridPane gPane = new GridPane();
    Scene mainScene = new Scene(gPane, 500, 400);
    
    //nodes user input
    Text inputTitle = new Text("Enter number you'd like to convert below: ");
    TextField input = new TextField();

    //radio button what switches conversion
    RadioButton calcDirection = new RadioButton("switch direction");

    //text that displays the converted input
    Text conversion = new Text("");

    //buttons which trigger the conversions
    Button MilesKilometers = new Button("Miles & Kilometers");
    Button FahrenheitCelsius = new Button("Fahrenheit & Celsius");
    Button PoundsKilograms = new Button("Pounds & Kilograms");
    Button FeetMeters = new Button("Feet & Meters");

    //variables
    boolean switchDirection = false;
    double metricValue;
    double convertValue;
    public static void main(String[] args) throws Exception {
        launch(args);
    }//end main

    @Override
    public void start(Stage primaryStage){
        //gridPane & stage are set up
        primaryStage.setTitle("Metric Converter");
        gPane.setHgap(5);
        gPane.setVgap(5);
        gPane.setAlignment(Pos.CENTER);

        //direction switch & result text are added
        gPane.add(conversion, 2, 7);
        gPane.add(calcDirection, 2, 6); 
        calcDirection.setOnAction(event -> ChangeDirection());
        
        //components for user input are added
        gPane.add(inputTitle, 1, 4 );
        gPane.add(input, 1, 5);

        //button which converts between Miles & Kilometers is added
        gPane.add(MilesKilometers, 1 , 6);
        MilesKilometers.setOnAction(event -> conversion.setText(MilesKilometers()));

        //button which converts between Fahrenheit & celsius is added
        gPane.add(FahrenheitCelsius, 1 ,7);
        FahrenheitCelsius.setOnAction(event -> conversion.setText(FahrenheitCelsius()));

        //button which converts between Pounds & Kilograms is added
        gPane.add(PoundsKilograms, 1, 8);
        PoundsKilograms.setOnAction(event -> conversion.setText(PoundsKilograms()));

        //button which converts between Feet & Meters is added
        gPane.add(FeetMeters, 1 , 9);
        FeetMeters.setOnAction(event -> conversion.setText(FeetMeters()));

        //scene is set & stage is shown
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }//end start

    public void ChangeDirection(){
        //handles the variable responsible for the conversion direction
        if(switchDirection == true){
            switchDirection = false;
            //end if
        } else {
            switchDirection = true;
        }//end else

    }//end method

    public String MilesKilometers(){
        //gets input from text field
        metricValue = Double.valueOf(input.getText());

        //converts from kilometers to miles if radio button is clicked
        if(switchDirection){
            convertValue = metricValue / 1.6094;
            return metricValue + " Kilometers is the same as " + convertValue + " Miles";
            //end if
        } else{
            //converts from miles to kilometers otherwise
            convertValue = metricValue * 1.6094;
            return metricValue + " Miles is equal to " + convertValue + " Kilometers" ;
        }//end else

    }// end method

    public String FahrenheitCelsius(){
        //obtains input from text field
        metricValue = Double.valueOf(input.getText());

        //converts from Fahrenheit to Celsius if radio button is not on
        if(switchDirection == false){
            convertValue = (metricValue - 32)  / 1.8;
            return metricValue + " degrees Fahrenheit is the equivalent  of " + convertValue + " degrees Celsius";
            //end if
        } else{
            //converts from Celsius to Fahrenheit otherwise
            convertValue = (metricValue * 1.8) + 32;
            return metricValue + " degrees Celsius is equal to " + convertValue + " degrees Fahrenheit" ;
        }//end else

    }//end method

    public String PoundsKilograms(){
        //obtains input from text field
        metricValue = Double.valueOf(input.getText());

        //converts kilograms to pounds if radio button is on
        if(switchDirection){
            convertValue = metricValue * 2.2046;
            return metricValue + " Kilograms is equal to " + convertValue + " pounds";
            //end if
        } else {
            //converts pounds to kilograms otherwise
            convertValue = metricValue / 2.2046;
            return metricValue + " pounds is the same as " + convertValue + " kilograms";
        }//end else

    }//end method

    public String FeetMeters(){
        //obtains input from text field
        metricValue = Double.valueOf(input.getText());

        //converts meters to feet if radio button is on
        if(switchDirection){
            convertValue = metricValue * 3.2808;
            return metricValue + " Meters is equivalent of " + convertValue + " Feet";
            //end if
        } else {
            //convets feet to meters otherwise
            convertValue = metricValue / 3.2808;
            return metricValue + " Feet is the same as " + convertValue + " Meters";
        }//end else

    }//end method
    
}//end class