package ui;
import dao.NoteDao;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Note;
import model.User;

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
	public TextField tfn_id;
	public TableColumn<?, ?> tcn_paper;
	public TableColumn<?, ?> tcn_title;
	public TextField tfn_content;
	//user
	public TextField tfu_id;
	public TextField tfu_username;
	public TextField tfu_password;
	public TableView<User> tb_user;
	public TableColumn<?, ?> tcu_id;
	public TableColumn<?, ?> tcu_username;
	public TableColumn<?, ?> tcu_password;
	
	
	//note
	public void queryNote(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("SELECT * FROM note");
		LinkedList<String> cons = new LinkedList<>();
		if (tfn_author.getText().matches("\\d+"))
			cons.add("author=" + tfn_author.getText());
		if (tfn_title.getText().length() > 0)
			cons.add("title like '" + tfn_title.getText() + "'");
		if (tfn_content.getText().length() > 0)
			cons.add("content like '" + tfn_content.getText() + "'");
		if (tfn_paper.getText().matches("\\d+"))
			cons.add("paper=" + tfn_paper.getText());
		if (tfn_id.getText().matches("\\d+"))
			cons.add("id=" + tfn_id.getText());
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
		//language=MySQL
		String sql = "SELECT * FROM note;";
		NoteDao dao = new NoteDao();
		ObservableList<Note> result = FXCollections.observableList(dao.query(sql));
		tcn_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcn_author.setCellValueFactory(new PropertyValueFactory<>("author"));
		tcn_content.setCellValueFactory(new PropertyValueFactory<>("content"));
		tcn_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcn_paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
		tb_note.setItems(result);
	}
	public void addNote(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("INSERT INTO note(");
		LinkedList<String> keys = new LinkedList<>();
		LinkedList<String> values = new LinkedList<>();
		if (tfn_author.getText().matches("\\d+"))
		{
			keys.add("author");
			values.add(tfn_author.getText());
		}
		if (tfn_title.getText().length() > 0)
		{
			keys.add("title");
			values.add("'" + tfn_title.getText() + "'");
		}
		if (tfn_content.getText().length() > 0)
		{
			keys.add("content");
			values.add("'" + tfn_content.getText() + "'");
		}
		if (tfn_paper.getText().matches("\\d+"))
		{
			keys.add("paper");
			values.add(tfn_paper.getText());
		}
		for (int i = 0; i < keys.size(); i++)
		{
			if (i != 0)
				sql.append(" , ");
			sql.append(keys.get(i));
		}
		sql.append(") VALUES (");
		for (int i = 0; i < values.size(); i++)
		{
			if (i != 0)
				sql.append(" , ");
			sql.append(values.get(i));
		}
		sql.append(");");
		NoteDao dao = new NoteDao();
		dao.update(sql.toString());
		initNote();
	}
	public void delNote(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("DELETE FROM note");
		LinkedList<String> cons = new LinkedList<>();
		if (tfn_author.getText().matches("\\d+"))
			cons.add("author=" + tfn_author.getText());
		if (tfn_title.getText().length() > 0)
			cons.add("title like '" + tfn_title.getText() + "'");
		if (tfn_content.getText().length() > 0)
			cons.add("content like '" + tfn_content.getText() + "'");
		if (tfn_paper.getText().matches("\\d+"))
			cons.add("paper =" + tfn_paper.getText());
		if (tfn_id.getText().matches("\\d+"))
			cons.add("id=" + tfn_id.getText());
		if (cons.size() > 0)
		{
			sql.append(" WHERE ");
			for (int i = 0; i < cons.size(); i++)
			{
				if (i != 0)
					sql.append(" AND ");
				sql.append(cons.get(i));
			}
			sql.append(';');
			NoteDao dao = new NoteDao();
			dao.update(sql.toString());
		}
		initNote();
	}
	public void editNote(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("UPDATE note");
		if (!tfn_id.getText().matches("\\d+"))
			return;
		LinkedList<String> cons = new LinkedList<>();
		if (tfn_author.getText().matches("\\d+"))
			cons.add("author=" + tfn_author.getText());
		if (tfn_title.getText().length() > 0)
			cons.add("title='" + tfn_title.getText() + "'");
		if (tfn_content.getText().length() > 0)
			cons.add("content='" + tfn_content.getText() + "'");
		if (tfn_paper.getText().matches("\\d+"))
			cons.add("paper=" + tfn_paper.getText());
		if (cons.size() > 0)
		{
			sql.append(" SET ");
			for (int i = 0; i < cons.size(); i++)
			{
				if (i != 0)
					sql.append(" , ");
				sql.append(cons.get(i));
			}
			sql.append("where id=").append(tfn_id.getText()).append(';');
			NoteDao dao = new NoteDao();
			dao.update(sql.toString());
		}
		initNote();
	}
	//user
	public void initUser() throws Exception
	{
		//language=MySQL
		String sql = "SELECT * FROM user;";
		UserDao dao = new UserDao();
		ObservableList<User> result = FXCollections.observableList(dao.query(sql));
		tcu_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcu_password.setCellValueFactory(new PropertyValueFactory<>("password"));
		tcu_username.setCellValueFactory(new PropertyValueFactory<>("username"));
		tb_user.setItems(result);
	}
	public void addUser(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("INSERT INTO user(");
		LinkedList<String> keys = new LinkedList<>();
		LinkedList<String> values = new LinkedList<>();
		if (tfu_username.getText().length()>0)
		{
			keys.add("username");
			values.add("'"+tfu_username.getText()+"'");
		}
		if (tfu_password.getText().length() > 0)
		{
			keys.add("password");
			values.add("'" + tfu_password.getText() + "'");
		}
		for (int i = 0; i < keys.size(); i++)
		{
			if (i != 0)
				sql.append(" , ");
			sql.append(keys.get(i));
		}
		sql.append(") VALUES (");
		for (int i = 0; i < values.size(); i++)
		{
			if (i != 0)
				sql.append(" , ");
			sql.append(values.get(i));
		}
		sql.append(");");
		UserDao dao = new UserDao();
		dao.update(sql.toString());
		initUser();
	}
	public void delUser(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("DELETE FROM user");
		LinkedList<String> cons = new LinkedList<>();
		if (tfu_password.getText().length() > 0)
			cons.add("password like '" + tfu_password.getText() + "'");
		if (tfu_username.getText().length() > 0)
			cons.add("username like '" + tfu_username.getText() + "'");
		if (tfu_id.getText().matches("\\d+"))
			cons.add("id=" + tfu_id.getText());
		if (cons.size() > 0)
		{
			sql.append(" WHERE ");
			for (int i = 0; i < cons.size(); i++)
			{
				if (i != 0)
					sql.append(" AND ");
				sql.append(cons.get(i));
			}
			sql.append(';');
			UserDao dao = new UserDao();
			dao.update(sql.toString());
		}
		initUser();
	}
	public void editUser(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("UPDATE user");
		if (!tfu_id.getText().matches("\\d+"))
			return;
		LinkedList<String> cons = new LinkedList<>();
		if (tfu_password.getText().length() > 0)
			cons.add("password='" + tfu_password.getText() + "'");
		if (tfu_username.getText().length() > 0)
			cons.add("username='" + tfu_username.getText() + "'");
		if (cons.size() > 0)
		{
			sql.append(" SET ");
			for (int i = 0; i < cons.size(); i++)
			{
				if (i != 0)
					sql.append(" , ");
				sql.append(cons.get(i));
			}
			sql.append("where id=").append(tfu_id.getText()).append(';');
			UserDao dao = new UserDao();
			dao.update(sql.toString());
		}
		initUser();
	}
	public void queryUser(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("SELECT * FROM user");
		LinkedList<String> cons = new LinkedList<>();
		if (tfu_password.getText().length() > 0)
			cons.add("password like '" + tfu_password.getText() + "'");
		if (tfu_username.getText().length() > 0)
			cons.add("username like '" + tfu_username.getText() + "'");
		if (tfu_id.getText().matches("\\d+"))
			cons.add("id=" + tfu_id.getText());
		if (cons.size() > 0)
			sql.append(" WHERE ");
		for (int i = 0; i < cons.size(); i++)
		{
			if (i != 0)
				sql.append(" AND ");
			sql.append(cons.get(i));
		}
		sql.append(';');
		UserDao dao = new UserDao();
		ObservableList<User> result = FXCollections.observableList(dao.query(sql.toString()));
		tcu_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcu_password.setCellValueFactory(new PropertyValueFactory<>("password"));
		tcu_username.setCellValueFactory(new PropertyValueFactory<>("username"));
		tb_user.setItems(result);
	}
	
}
