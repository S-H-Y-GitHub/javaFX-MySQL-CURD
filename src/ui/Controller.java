package ui;
import dao.NoteDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Note;

import java.util.LinkedList;
public class Controller
{
	//note
	public TextField tfn_author;
	public TextField tfn_paper;
	public TextField tfn_title;
	public TableView<Note> tb_note;
	public TableColumn<?, ?> tcn_id;
	public TableColumn<?, ?> tcn_author;
	public TableColumn<?, ?> tcn_content;
	public TextField tfn_idgt;
	public TextField tfn_idlt;
	public TableColumn<?, ?> tcn_paper;
	public TableColumn<?, ?> tcn_title;
	public TextField tfn_content;
	
	public void queryNote(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("SELECT * FROM note");
		LinkedList<String> cons = new LinkedList<>();
		if (tfn_author.getText().trim().matches("\\d+"))
			cons.add("auther=" + tfn_author.getText().trim());
		if (tfn_title.getText().trim().length() > 0)
			cons.add("title like '" + tfn_title.getText().trim() + "'");
		if (tfn_content.getText().trim().length() > 0)
			cons.add("content like '" + tfn_content.getText().trim() + "'");
		if (tfn_paper.getText().trim().matches("\\d+"))
			cons.add("paper =" + tfn_paper.getText().trim());
		if (tfn_idgt.getText().trim().matches("\\d+"))
			cons.add("id > " + tfn_idgt.getText().trim());
		if (tfn_idlt.getText().trim().matches("\\d+"))
			cons.add("id < " + tfn_idlt.getText().trim());
		if (cons.size() > 0)
			sql.append(" WHERE ");
		for (int i = 0; i < cons.size(); i++)
		{
			if (i != 0)
				sql.append(" AND ");
			sql.append(cons.get(i));
		}
		sql.append(';');
		NoteDao dao = new NoteDao();
		ObservableList<Note> result = FXCollections.observableList(dao.query(sql.toString()));
		tcn_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcn_author.setCellValueFactory(new PropertyValueFactory<>("author"));
		tcn_content.setCellValueFactory(new PropertyValueFactory<>("content"));
		tcn_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcn_paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
		tb_note.setItems(result);
	}
	public void initNote() throws Exception
	{
		String sql = "SELECT * FROM note;";
		NoteDao dao = new NoteDao();
		ObservableList<Note> result = FXCollections.observableList(dao.query(sql.toString()));
		tcn_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcn_author.setCellValueFactory(new PropertyValueFactory<>("author"));
		tcn_content.setCellValueFactory(new PropertyValueFactory<>("content"));
		tcn_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcn_paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
		tb_note.setItems(result);
	}
	public void addNote(MouseEvent mouseEvent) {}
	public void delNote(MouseEvent mouseEvent) {}
	public void editNote(MouseEvent mouseEvent) {}
}
