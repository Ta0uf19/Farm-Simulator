package InterfaceClient;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.ClientDAO;
import DAO.DAO;
import Gestionnaire.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientController implements Initializable {
	@FXML private TableView<Client> tableClient;
	@FXML private TextField search;
	@FXML private CheckBox checkAgr;
	@FXML private CheckBox checkCoop;
	@FXML private TextField nomV;
	@FXML
	    private TextField prenomV;

	    @FXML
	    private TextField adresseV;

	    @FXML
	    private TextField telephoneV;

	    @FXML
	    private ComboBox<String> tyV;
	    
	    
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
		
		getClient();
		tyV.getItems().addAll("Agriculteur","Coopérative");
		
		id.setPrefWidth(100);
		id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
		
        nom.setPrefWidth(150);
        nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        type.setPrefWidth(150);
        type.setCellValueFactory(new PropertyValueFactory<Client,String>("typeCl"));
        
        tableClient.setItems(clients);
        tableClient.getColumns().addAll(id,type, nom);
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
        	nomV.setText(client.getNom());
        	prenomV.setText(client.getPrenom());
        	adresseV.setText(client.getAdresse());
        	telephoneV.setText(client.getTelephone());
        	//tyV.setTooltip(client.getTypeCl()));
        });
        
	}
	
	private void getClient() {
		DAO<Client> client = new ClientDAO();
		List<Client> listClient = client.recupererTout();
		
		clients.addAll(listClient);
		clientsFiltred.addAll(clients);
		
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

	    private void reapplyTableSortOrder() {
	        ArrayList<TableColumn<Client, ?>> sortOrder = new ArrayList<>(tableClient.getSortOrder());
	        tableClient.getSortOrder().clear();
	        tableClient.getSortOrder().addAll(sortOrder);
	        tableClient.setItems(clientsFiltred);
	    }
}

