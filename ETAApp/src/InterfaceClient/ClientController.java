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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientController implements Initializable {
	@FXML private TableView<Client> tableClient;
	@FXML private TextField search;
	@FXML private CheckBox checkAgr;
	@FXML private CheckBox checkCoop;
	@FXML private TextField nomV;
	@FXML private TextField prenomV;
	@FXML private TextField adresseV;
	@FXML private TextField telephoneV;
	@FXML private ComboBox<String> tyV; // checkbox
	  
	
	@FXML private TextField nomV1;
	@FXML private TextField prenomV1;
	@FXML private TextField adresseV1;
	@FXML private TextField telephoneV1;
	@FXML private ComboBox<String> tyV1; // checkbox
	/*
	 * Update-add FXML
	 *
	 */
	@FXML private Button addClient;
	@FXML private Button updateClient;
	private int selectedId;
	/*
	 * Checkbox à compléter
	 */
	private TableColumn<Client, Integer> id = new TableColumn<>("Identifiant");
	private TableColumn<Client, String> type = new TableColumn<>("Type Client");
	private TableColumn<Client, String> nom = new TableColumn<>("Nom");
	private ObservableList<Client> clients = FXCollections.observableArrayList();
	private ObservableList<Client> clientsFiltred = FXCollections.observableArrayList();
	

	public ClientController() {
		/*
		 * Mise à jour de la liste pour contenir les nouvelles infos après filtre - Recherche
		 */
		 clients.addListener(new ListChangeListener<Client>() {
	            @Override
	            public void onChanged(ListChangeListener.Change<? extends Client> change) {
	                updateFilteredData();
	            }
	        });
		 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		 * Récupérer la liste des clients
		 */
		loadData();
		tyV.getItems().addAll("Agriculteur","Coopérative");
		tyV1.getItems().addAll("Agriculteur","Coopérative");
		
        /*
         * Option - recherche ecouter l'input
         */
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                updateFilteredData();
            }
        });
        /*
         * Checkbox
         */
        checkAgr.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				clientsFiltred.clear();
				for(Client c : clients) {
					if(!c.getTypeCl().toLowerCase().equals("agriculteur"))
						clientsFiltred.add(c);
				}
				reapplyTableSortOrder();
			}
        });
        
        checkCoop.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				clientsFiltred.clear();
				for(Client c : clients) {
					if(!c.getTypeCl().toLowerCase().equals("coopérative"))
						clientsFiltred.add(c);
				}
				reapplyTableSortOrder();
			}
        });
        
        tableClient.setOnMouseClicked(e -> {
        	Client client = tableClient.getSelectionModel().getSelectedItem();
        	selectedId = client.getId();
        	nomV.setText(client.getNom());
        	prenomV.setText(client.getPrenom());
        	adresseV.setText(client.getAdresse());
        	telephoneV.setText(client.getTelephone());
        	//tyV.setTooltip(client.getTypeCl()));
        });
        
        /*
         * Update - add clients
         */
        
        updateClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Client c = new Client(selectedId, nomV.getText(), prenomV.getText(), adresseV.getText(), telephoneV.getText());
				DAO<Client> m = new ClientDAO();
				m.modifier(c);
				loadData();
                alert("Les informations client ont été mise à jour.", "Modification", (Stage) ((Node) event.getSource()).getScene().getWindow());

				
			}});
        
        addClient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Client c = new Client(null, nomV1.getText(), prenomV1.getText(), adresseV1.getText(), telephoneV1.getText());
				DAO<Client> m = new ClientDAO();
				m.ajouter(c);
				loadData();
                alert("Le client "+c.getNom()+" a été ajouté avec succès.", "Ajout", (Stage) ((Node) event.getSource()).getScene().getWindow());

				
			}
        });
        
	}
	
	/*
	 * Récupérer la liste après un filtre
	 */
	private void updateFilteredData() {
        clientsFiltred.clear();

        for (Client c : clients) {
            if (matchesFilter(c)) {
            	clientsFiltred.add(c);
            }
        }

        reapplyTableSortOrder();
    }
	
	/*
	 * Effectuer un filtre par Nom du client et Type du Client 
	 * Retourne boolean
	 */
	 private boolean matchesFilter(Client client) {
	        String filterString = search.getText();
	        if (filterString == null || filterString.isEmpty()) {
	            return true;
	        }

	        String lowerCaseFilterString = filterString.toLowerCase();

	        if (client.getNom().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
	            return true;
	        } else if (client.getTypeCl().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
	            return true;
	        }
	        if(search.getText().equals("helo")) System.out.println("YEAH !!");
	        return false;
	 }
	 
	 /*
	  * Réordonnner la table
	  */

	 private void reapplyTableSortOrder() {
	        ArrayList<TableColumn<Client, ?>> sortOrder = new ArrayList<>(tableClient.getSortOrder());
	        tableClient.getSortOrder().clear();
	        tableClient.getSortOrder().addAll(sortOrder);
	        tableClient.setItems(clientsFiltred);
	 }
	 
	 public void loadData() {
		 
		 	clearData();
		 
		 	DAO<Client> client = new ClientDAO();
			List<Client> listClient = client.recupererTout();
			
			clients.addAll(listClient);
			clientsFiltred.addAll(clients);
		
			
			id.setPrefWidth(100);
			id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
	        nom.setPrefWidth(150);
	        nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
	        type.setPrefWidth(150);
	        type.setCellValueFactory(new PropertyValueFactory<Client,String>("typeCl"));
	        
	        tableClient.setItems(clients);
	        tableClient.getColumns().addAll(id,type, nom);
		 
	 }
	 public void clearData() {
		 clients.clear();
		 tableClient.getItems().clear();
		 tableClient.getColumns().clear();
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

