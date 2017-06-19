package InterfaceClient;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.ClientDAO;
import DAO.DAO;
import DAO.MachineDAO;
import Gestionnaire.Botteleuse;
import Gestionnaire.Client;
import Gestionnaire.Machine;
import Gestionnaire.Moissoneuse;
import Gestionnaire.Tracteur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class MachineController implements Initializable{

	/*
	 * Tracteur
	 */
		@FXML private TextField marqueTM;
		@FXML private TextField modeleTM;
		@FXML private TextField etatTM;
		@FXML private TextField capaciteTM;
		@FXML private TextField marqueTA;
		@FXML private TextField modeleTA;
		@FXML private TextField etatTA;
		@FXML private TextField capaciteTA;
		
		/*
		 * Botteleuse
		 */
		@FXML private TextField marqueBM;
		@FXML private TextField modeleBM;
		@FXML private TextField etatBM;
		@FXML private TextField typeBM;
		@FXML private TextField marqueBA;
		@FXML private TextField modeleBA;
		@FXML private TextField etatBA;
		@FXML private TextField typeBA;
		
		/*
		 * Moisseuneuse
		 */
		@FXML private TextField marqueMM;
		@FXML private TextField modeleMM;
		@FXML private TextField etatMM;
		@FXML private TextField lgCoupeMM;
		@FXML private TextField consoRMM;
		@FXML private TextField consoFMM;
		@FXML private TextField lgRouteMM;
		@FXML private TextField hauteurMM;
		@FXML private TextField poidsMM;
		@FXML private TextField capaciteRMM;
		@FXML private TextField tremieMM;
		@FXML private TextField marqueMA;
		@FXML private TextField modeleMA;
		@FXML private TextField etatMA;
		@FXML private TextField lgCoupeMA;
		@FXML private TextField consoRMA;
		@FXML private TextField consoFMA;
		@FXML private TextField lgRouteMA;
		@FXML private TextField hauteurMA;
		@FXML private TextField poidsMA;
		@FXML private TextField capaciteRMA;
		@FXML private TextField tremieMA;
		
		
		@FXML private TableView<Machine> tableMoissoneuse;
		@FXML private TableView<Machine> tableBotteleuse;
		@FXML private TableView<Machine> tableTracteur;
		
		/*
		 * Button add - update
		 */
		@FXML private Button tracteurUpdate;
		@FXML private Button moissoneuseUpdate;
		@FXML private Button botteleuseUpdate;
		@FXML private Button tracteurAdd;
		@FXML private Button moissoneuseAdd;
		@FXML private Button botteleuseAdd;
    
	    final TableColumn<Machine, Integer> Idm = new TableColumn<>("Identifiant");
	    final TableColumn<Machine, String> Modelem = new TableColumn<>("Modèle");
	    final TableColumn<Machine, String> Marquem = new TableColumn<>("Marque");
	    final TableColumn<Machine, String> Dispom = new TableColumn<>("Disponibilité");
	    
	    final TableColumn<Machine, Integer> Idb = new TableColumn<>("Identifiant");
	    final TableColumn<Machine, String> Modeleb = new TableColumn<>("Modèle");
	    final TableColumn<Machine, String> Marqueb = new TableColumn<>("Marque");
	    final TableColumn<Machine, String> Dispob = new TableColumn<>("Disponibilité");
	    
	    final TableColumn<Machine, Integer> Idt = new TableColumn<>("Identifiant");
	    final TableColumn<Machine, String> Modelet = new TableColumn<>("Modèle");
	    final TableColumn<Machine, String> Marquet = new TableColumn<>("Marque");
	    final TableColumn<Machine, String> Dispot = new TableColumn<>("Disponibilité");
	    
		private ObservableList<Machine> tracteurs = FXCollections.observableArrayList();
		private ObservableList<Machine> botteleuses = FXCollections.observableArrayList();
		private ObservableList<Machine> moissoneuses = FXCollections.observableArrayList();
		private int selectedId;
		
		List<Moissoneuse> listMoissoneuse = new ArrayList();
		List<Botteleuse> listBotteleuse = new ArrayList();
		List<Tracteur> listTracteur = new ArrayList();
		
		public void initialize(URL arg0, ResourceBundle arg1) {
			/*
			 * Récupérer la liste des machine
			 */
			
			loadData();
    	

	        
	        
	        tableTracteur.setOnMouseClicked(e -> {
	        	Tracteur tracteur = (Tracteur) tableTracteur.getSelectionModel().getSelectedItem();
	        	selectedId = tracteur.getId();
	        	marqueTM.setText(tracteur.getMarque());
	        	modeleTM.setText(tracteur.getModele());
	        	etatTM.setText(String.valueOf(tracteur.getEtat()));
	        	capaciteTM.setText(tracteur.getCapacite());
	        });
	        tableBotteleuse.setOnMouseClicked(e -> {
	        	Botteleuse botteleuse = (Botteleuse) tableBotteleuse.getSelectionModel().getSelectedItem();
	        	selectedId = botteleuse.getId();
	        	marqueBM.setText(botteleuse.getMarque());
	        	modeleBM.setText(botteleuse.getModele());
	        	etatBM.setText(String.valueOf(botteleuse.getEtat()));
	        	typeBM.setText(botteleuse.getType());
	        });
	        
	        tableMoissoneuse.setOnMouseClicked(e -> {
	        	Moissoneuse moissoneuse = (Moissoneuse) tableMoissoneuse.getSelectionModel().getSelectedItem();
	        	selectedId = moissoneuse.getId();
	        	marqueMM.setText(moissoneuse.getMarque());
	        	modeleMM.setText(moissoneuse.getModele());
	        	etatMM.setText(String.valueOf(moissoneuse.getEtat()));
	        	lgCoupeMM.setText(String.valueOf(moissoneuse.getLgCoupe()));
	    		consoRMM.setText(String.valueOf(moissoneuse.getConsoR()));
	    		consoFMM.setText(String.valueOf(moissoneuse.getConsoF()));
	    		tremieMM.setText(String.valueOf(moissoneuse.getTremie()));
	    		lgRouteMM.setText(String.valueOf(moissoneuse.getLgRoute()));
	    		hauteurMM.setText(String.valueOf(moissoneuse.getHauteur()));
	    		poidsMM.setText(String.valueOf(moissoneuse.getPoids()));
	    		capaciteRMM.setText(String.valueOf(moissoneuse.getCapaciteReserve()));
	        });
	        
	        
	        
	        /*
	         * Update 
	         */
	        botteleuseUpdate.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Botteleuse b = new Botteleuse(selectedId, marqueBM.getText(), modeleBM.getText(), Integer.valueOf(etatBM.getText()), typeBM.getText());
					DAO<Machine> m = new MachineDAO();
					m.modifier(b);
					loadData();
	                alert("Les informations d'une botteleuse ont été mise à jour.", "Modification", (Stage) ((Node) event.getSource()).getScene().getWindow());
	            }
			});
	        
	        tracteurUpdate.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Tracteur t = new Tracteur(selectedId, marqueTM.getText(), modeleTM.getText(), Integer.valueOf(etatTM.getText()), capaciteTM.getText());
					DAO<Machine> m = new MachineDAO();
					m.modifier(t);
					loadData();
	                alert("Les informations d'un tracteur ont été mise à jour.", "Modification", (Stage) ((Node) event.getSource()).getScene().getWindow());
	            }
			});
	        
	        moissoneuseUpdate.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Moissoneuse mo = new Moissoneuse(selectedId, marqueMM.getText(), modeleMM.getText(), Integer.valueOf(etatMM.getText()), Integer.valueOf(consoRMM.getText()), Integer.valueOf(consoFMM.getText()), Integer.valueOf(tremieMM.getText()), Integer.valueOf(lgCoupeMM.getText()), Integer.valueOf(lgRouteMM.getText()), Integer.valueOf(hauteurMM.getText()), Integer.valueOf(poidsMM.getText()), Integer.valueOf(capaciteRMM.getText()));
					DAO<Machine> m = new MachineDAO();
					m.modifier(mo);
					loadData();
	                alert("Les informations d'une moissonneuse ont été mise à jour.", "Modification", (Stage) ((Node) event.getSource()).getScene().getWindow());
	            }
			});
	        
	        /*
	         * Add
	         */
	        botteleuseAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Botteleuse b = new Botteleuse(selectedId, marqueBA.getText(), modeleBA.getText(), Integer.valueOf(etatBA.getText()), typeBA.getText());
					DAO<Machine> m = new MachineDAO();
					m.ajouter(b);
					loadData();
	                alert("Une nouvelle machine bottleuse a été ajouté avec succès", "Ajout", (Stage) ((Node) event.getSource()).getScene().getWindow());
	            }
			});
	        tracteurAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Tracteur t = new Tracteur(selectedId, marqueTA.getText(), modeleTA.getText(), Integer.valueOf(etatTA.getText()), capaciteTA.getText());
					DAO<Machine> m = new MachineDAO();
					m.ajouter(t);
					loadData();
	                alert("Une nouvelle machine tracteur a été ajouté avec succès", "Ajout", (Stage) ((Node) event.getSource()).getScene().getWindow());
	            }
			});
	        moissoneuseAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Moissoneuse mo = new Moissoneuse(selectedId, marqueMA.getText(), modeleMA.getText(), Integer.valueOf(etatMA.getText()), Integer.valueOf(consoRMA.getText()), Integer.valueOf(consoFMA.getText()), Integer.valueOf(tremieMA.getText()), Integer.valueOf(lgCoupeMA.getText()), Integer.valueOf(lgRouteMA.getText()), Integer.valueOf(hauteurMA.getText()), Integer.valueOf(poidsMA.getText()), Integer.valueOf(capaciteRMA.getText()));
					DAO<Machine> m = new MachineDAO();
					m.ajouter(mo);
					loadData();
	                alert("Une nouvelle machine moissoneuse a été ajouté avec succès", "Ajout", (Stage) ((Node) event.getSource()).getScene().getWindow());
	            }
			});
		
	     }
		public void loadData() {

			clearData();
			
			DAO<Machine> machine = new MachineDAO();
			List<Machine> listMachines = machine.recupererTout();
			
			for(Machine list : listMachines) {
				if(list instanceof Tracteur) {
					listTracteur.add((Tracteur) list);
				}
				if(list instanceof Botteleuse) {
					listBotteleuse.add((Botteleuse) list);
				}
				if(list instanceof Moissoneuse) {
					listMoissoneuse.add((Moissoneuse) list);
				}
			}
			
			tracteurs.addAll(listTracteur);
    		moissoneuses.addAll(listMoissoneuse);
    		botteleuses.addAll(listBotteleuse);
			
			Idt.setPrefWidth(100);
			Idt.setCellValueFactory(new PropertyValueFactory<Machine,Integer>("id"));
			Modelet.setPrefWidth(100);
			Modelet.setCellValueFactory(new PropertyValueFactory<Machine,String>("modele"));
			Marquet.setPrefWidth(100);
	        Marquet.setCellValueFactory(new PropertyValueFactory<Machine,String>("marque"));
	        Dispot.setPrefWidth(100);
	        Dispot.setCellValueFactory(new PropertyValueFactory<Machine,String>("dispo"));
	        
	        
	   
	        Idb.setPrefWidth(100);
			Idb.setCellValueFactory(new PropertyValueFactory<Machine,Integer>("id"));
			Modeleb.setPrefWidth(100);
			Modeleb.setCellValueFactory(new PropertyValueFactory<Machine,String>("modele"));
			Marqueb.setPrefWidth(100);
	        Marqueb.setCellValueFactory(new PropertyValueFactory<Machine,String>("marque"));
	        Dispob.setPrefWidth(100);
	        Dispob.setCellValueFactory(new PropertyValueFactory<Machine,String>("dispo"));
	        
	        Idm.setPrefWidth(100);
			Idm.setCellValueFactory(new PropertyValueFactory<Machine,Integer>("id"));
			Modelem.setPrefWidth(100);
			Modelem.setCellValueFactory(new PropertyValueFactory<Machine,String>("modele"));
			Marquem.setPrefWidth(100);
	        Marquem.setCellValueFactory(new PropertyValueFactory<Machine,String>("marque"));
	        Dispom.setPrefWidth(100);
	        Dispom.setCellValueFactory(new PropertyValueFactory<Machine,String>("dispo"));
	        
	        tableTracteur.setItems(tracteurs);
	        tableTracteur.getColumns().clear();
	        tableTracteur.getColumns().addAll(Idt,Modelet,Marquet,Dispot);
	        
	        tableBotteleuse.setItems(botteleuses);
	        tableBotteleuse.getColumns().clear();
	        tableBotteleuse.getColumns().addAll(Idb,Modeleb,Marqueb,Dispob);
	        
	        tableMoissoneuse.setItems(moissoneuses);
	        tableMoissoneuse.getColumns().clear();
	        tableMoissoneuse.getColumns().addAll(Idm,Modelem,Marquem,Dispom);
			
			
		}
		
		public void clearData() {
			
			listTracteur.clear();
			listBotteleuse.clear();
			listMoissoneuse.clear();
			
			tableTracteur.getItems().clear();
			tableBotteleuse.getItems().clear();
			tableMoissoneuse.getItems().clear();
		}
		
		public void alert(String text, String title, Stage stage) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(title);
            alert.initOwner(stage);
            alert.setHeaderText(null);
            alert.setContentText(text);
            alert.showAndWait();
		}
	     
}


		
