package ui;
import dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Data;

import java.util.LinkedList;
public class Controller
{
	@FXML
	private TableColumn<Object, Object> tc_population;
	@FXML
	private TableColumn<Object, Object> tc_id;
	@FXML
	private TableColumn<Object, Object> tc_city;
	@FXML
	private TableColumn<Object, Object> tc_country;
	@FXML
	private TableColumn<Object, Object> tc_district;
	@FXML
	private TextField tf_city;
	@FXML
	private TableView<Data> tb_result;
	@FXML
	private TextArea ta_sql;
	@FXML
	private TextField tf_plt;
	@FXML
	private TextField tf_pgt;
	@FXML
	private TextField tf_district;
	@FXML
	private TextField tf_country;
	@FXML
	private TextField tf_idlt;
	@FXML
	private TextField tf_idgt;
	@FXML
	public void generateSQL(MouseEvent mouseEvent)
	{
		StringBuilder sql = new StringBuilder("SELECT * FROM cities");
		LinkedList<String> cons = new LinkedList<>();
		if(tf_city.getText().trim().length()>0)
			cons.add("Name like '" + tf_city.getText().trim() + "'");
		if(tf_country.getText().trim().length()>0)
			cons.add("Country like '" + tf_country.getText().trim() + "'");
		if(tf_district.getText().trim().length()>0)
			cons.add("District like '" + tf_district.getText().trim() + "'");
		if(tf_idgt.getText().trim().matches("\\d+"))
			cons.add("ID > "+tf_idgt.getText().trim());
		if(tf_idlt.getText().trim().matches("\\d+"))
			cons.add("ID < "+tf_idlt.getText().trim());
		if(tf_pgt.getText().trim().matches("\\d+"))
			cons.add("Population > "+tf_pgt.getText().trim());
		if(tf_plt.getText().trim().matches("\\d+"))
			cons.add("Population < " + tf_plt.getText().trim());
		if (cons.size()>0)
			sql.append(" WHERE ");
		for(int i = 0; i < cons.size(); i++)
		{
			if(i != 0)
				sql.append(" AND ");
			sql.append(cons.get(i));
		}
		sql.append(';');
		ta_sql.setText(String.valueOf(sql));
	}
	@FXML
	public void runSQL(MouseEvent mouseEvent) throws Exception
	{
		String sql = ta_sql.getText();
		Dao dao = new Dao();
		ObservableList<Data> result = FXCollections.observableList(dao.runSQL(sql));
		tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tc_city.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc_country.setCellValueFactory(new PropertyValueFactory<>("country"));
		tc_district.setCellValueFactory(new PropertyValueFactory<>("district"));
		tc_population.setCellValueFactory(new PropertyValueFactory<>("population"));
		tb_result.setItems(result);
	}
}
