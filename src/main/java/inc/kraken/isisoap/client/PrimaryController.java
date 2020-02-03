package inc.kraken.isisoap.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import inc.kraken.isisoap.ConfigClient;
import inc.kraken.isisoap.PersonClient;
import inc.kraken.wsdl.AddPersonResponse;
import inc.kraken.wsdl.DeletePersonResponse;
import inc.kraken.wsdl.PersonInfo;
import inc.kraken.wsdl.GetAllPersonsResponse;
import inc.kraken.wsdl.ServiceStatus;
import inc.kraken.wsdl.UpdatePersonResponse;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    private ConfigClient configClient = new ConfigClient();
    private PersonClient personClient = configClient.personClient(configClient.marshaller());

    @FXML
    private Label mainTitle;
    @FXML
    private Label lb_id;
    @FXML
    private JFXTextField txtFirstname;
    @FXML
    private JFXTextField txtLastname;
    @FXML
    private JFXButton btnValidate;
    @FXML
    private TableView<PersonInfo> tbl_persons;
    @FXML
    private TableColumn<PersonInfo, String> col_firstname;
    @FXML
    private TableColumn<PersonInfo, String> col_lastname;
    @FXML
    private TableColumn<PersonInfo, Long> col_id;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        loadItems();

        tbl_persons.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                txtFirstname.setText(tbl_persons.getSelectionModel().getSelectedItem().getFirstname());
                txtLastname.setText(tbl_persons.getSelectionModel().getSelectedItem().getLastname());
                lb_id.setText(Long.toString(tbl_persons.getSelectionModel().getSelectedItem().getPersonId()));
                btnValidate.setText("Modifier");
                btnValidate.setStyle("-fx-background-color: #f0ad4e");

            }

        });

        // Create context menu
        ContextMenu contextMenu = new ContextMenu();

        MenuItem itemDel = new MenuItem("Supprimer");
        itemDel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Hum, confirmez l'opération");
                alert.setContentText("Etes-vous sûr ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
            long personId = Long.parseLong(lb_id.getText());
            DeletePersonResponse deletePersonResponse = personClient.deletePerson(personId);
            ServiceStatus serviceStatus = deletePersonResponse.getServiceStatus();
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Cool, opération réussie");
                alert.setContentText("Contenu suprimé avec succès! \n" + "StatusCode: " + serviceStatus.getStatusCode()
                        + ", Message: " + serviceStatus.getMessage());

                alert.showAndWait();
                loadItems();
                clear();
            
            
                } else {
                    clear();
                }
            }
        });

        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(itemDel);

        tbl_persons.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent t) {
                contextMenu.show(tbl_persons, t.getScreenX(), t.getScreenY());

            }
        ;

    }

    );
   }

    public void loadItems() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("personId"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tbl_persons.setItems(getPersons());
    }

    public void clear() {
        lb_id.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        btnValidate.setText("Enregistrer");
        btnValidate.setStyle("-fx-background-color: #318ce7");
    }

    public ObservableList<PersonInfo> getPersons() {
        ObservableList<PersonInfo> persons = FXCollections.observableArrayList();

        GetAllPersonsResponse allPersonsResponse = personClient.getAllPersons();
        allPersonsResponse.getPersonInfo().stream()
                .forEach(e -> {
                    persons.add(new PersonInfo(e.getPersonId(), e.getFirstname(), e.getLastname()));
                });

        return persons;
    }

    @FXML
    private void onValidateClicked(MouseEvent event) {
        if (txtFirstname.getText().equalsIgnoreCase("") || txtLastname.getText().equalsIgnoreCase("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Oups, une erreur est survenue");
            alert.setContentText("Oups, vous avez oublié de remplir un ou plusieurs champs!");

            alert.showAndWait();
        } else {
            String firstname = txtFirstname.getText();
            String lastname = txtLastname.getText();
            String id = lb_id.getText();
            // There is no ID, so we have to insert
            if (id.equalsIgnoreCase("0")) {
                AddPersonResponse addPersonResponse = personClient.addPerson(firstname, lastname);
                PersonInfo personInfo = addPersonResponse.getPersonInfo();
                ServiceStatus serviceStatus = addPersonResponse.getServiceStatus();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Cool, opération réussie");
                alert.setContentText("Contenu enregistré avec succès! \n" + "StatusCode: " + serviceStatus.getStatusCode()
                        + ", Message: " + serviceStatus.getMessage());

                alert.showAndWait();
                loadItems();
                // There is a ID, so we update
            } else {

                PersonInfo personInfo = new PersonInfo();
                personInfo.setPersonId(Integer.parseInt(id));
                personInfo.setFirstname(firstname);
                personInfo.setLastname(lastname);
                UpdatePersonResponse updatePersonResponse = personClient.updatePerson(personInfo);
                ServiceStatus serviceStatus = updatePersonResponse.getServiceStatus();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Cool, opération réussie");
                alert.setContentText("Contenu modifié avec succès! \n" + "StatusCode: " + serviceStatus.getStatusCode()
                        + ", Message: " + serviceStatus.getMessage());

                alert.showAndWait();
                loadItems();
                clear();
            }

        }
    }

}
