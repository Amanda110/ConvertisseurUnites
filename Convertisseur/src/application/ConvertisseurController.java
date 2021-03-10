/*
* @Author : Amanda Tishler
* @Date : Mar. 3, 2021
* @Nom du programme : Convertisseur d'unités
* @Description : Programme permettant de convertir entre les différentes unités métriques et impériales.
*/

package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConvertisseurController implements Initializable {

    @FXML
    private TextField txtVol2;

    @FXML
    private ComboBox<String> cboL1;

    @FXML
    private ComboBox<String> cboMa1;

    @FXML
    private ComboBox<String> cboMa2;

    @FXML
    private ComboBox<String> cboL2;

    @FXML
    private ComboBox<String> cboVol2;

    @FXML
    private ComboBox<String> cboVol1;

    @FXML
    private TextField txtL2;

    @FXML
    private TextField txtMa2;

    @FXML
    private TextField txtVol1;

    @FXML
    private TextField txtMa1;
    
    @FXML
    private TextField txtL1;
    
    //Liste des unités pour les longeurs
    private ObservableList<String> listL = (ObservableList<String>)FXCollections.observableArrayList("Kilomètres", "Mètres", "Centimètres", "Miles", "Pieds", "Pouces");
    double [] tabLong = {0.001, 1.00, 100.0, 0.000621371, 3.28084, 39.3701};  //tableau des équivalents
    
    //Liste des unités pour les masses
    private ObservableList<String> listMa = (ObservableList<String>)FXCollections.observableArrayList("Tonnes", "Kilogrammes", "Grammes", "Livres", "Onces");
    double [] tabMa = {0.001, 1.0, 1000.0, 2.20462, 35.274};
    
    //Liste des unités pour les volumes
    private ObservableList<String> listVol = (ObservableList<String>)FXCollections.observableArrayList("Litres", "Millilitres", "Gallons", "Pintes", "Tasses");
    double [] tabVol = {1.0, 1000.0, 0.219969, 1.75975, 3.51951};
     
    
    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//Longeurs
    	cboL1.setItems(listL); //Associe le comboBox à la liste correspondant
		cboL2.setItems(listL);
		cboL1.getSelectionModel().select(1); //sélectionne l'unité par défaut
		cboL2.getSelectionModel().select(4);
		
		//Masses
		cboMa1.setItems(listMa);
		cboMa2.setItems(listMa);
		cboMa1.getSelectionModel().select(1);
		cboMa2.getSelectionModel().select(3);
		
		//Volumes
		cboVol1.setItems(listVol);
		cboVol2.setItems(listVol);
		cboVol1.getSelectionModel().selectFirst();
		cboVol2.getSelectionModel().select(2);	
		
	}
    
    //Pour les longueurs
    @FXML
    void calculerL1()  
    {
    	convertir(txtL1, txtL2, cboL1, cboL2, tabLong);	
    }
    
    @FXML
    void calculerL2()  
    {
    	convertir(txtL2, txtL1, cboL2, cboL1, tabLong);  	
    }
    
    //Pour les masses
    @FXML
    void calculerMa1()  
    {
    	convertir(txtMa1, txtMa2, cboMa1, cboMa2, tabMa);	
    }
    
    @FXML
    void calculerMa2()  
    {
    	convertir(txtMa2, txtMa1, cboMa2, cboMa1, tabMa);  	
    }
    
    //Pour les volumes
    @FXML
    void calculerVol1()  
    {
    	convertir(txtVol1, txtVol2, cboVol1, cboVol2, tabVol);	
    }
    
    @FXML
    void calculerVol2()  
    {
    	convertir(txtVol2, txtVol1, cboVol2, cboVol1, tabVol);  	
    }
    
    //Methode permettant de convertir les unités
    public void convertir(TextField txtA, TextField txtB, ComboBox<String> boxA, ComboBox<String> boxB, double [] tab)
    {
    	verifNum(txtA);
    	int item1 = boxA.getSelectionModel().getSelectedIndex(); //index du premier élément
    	int item2 = boxB.getSelectionModel().getSelectedIndex(); //index du deuxième élément
    	double taux = tab[item2]/tab[item1]; //divise les valeurs correspondantes
    	double res = taux * (Double.parseDouble(txtA.getText()));  //multiplie le taux par le contenue de txtA
    	txtB.setText(Double.toString(res)); //affiche le résultat dans txtB
    }
    
    //Gestion numérique - accepter des caratères numériques seulement
    public void verifNum(TextField a)
    {
    	if(a.getText().equals("")) a.setText("0");
    	a.textProperty().addListener((observable,oldValue,newValue)->
    	{
    		if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
    		{
    			a.setText(newValue.replaceAll("[^\\d*\\.]", ""));
    		}
    	});
    }
    
 /* Methode permettant de fermer l'application quand on appui sur le bouton "Quitter". 
    Affiche une alerte pour verifier cette décision */
    @FXML
    void quitter()
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Attention");
    	alert.setTitle("Confirmation");
    	alert.setContentText("Voulez-vous quitter l'application ?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get()==ButtonType.OK)
    	{
    		System.exit(0);
    	}
    			
    }
}

	































