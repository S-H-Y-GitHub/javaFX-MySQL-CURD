package ui;
import dao.NoteDao;
import dao.PaperDao;
import dao.UserDao;
import dao.UserPaperDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Note;
import model.Paper;
import model.User;
import model.UserPaper;

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
	//paper
	public TextField tfp_id;
	public TextField tfp_title;
	public TableView<Paper> tb_paper;
	public TableColumn<?, ?> tcp_id;
	public TableColumn<?, ?> tcp_title;
	//userpaper
	public TextField tfup_id;
	public TextField tfup_user;
	public TextField tfup_paper;
	public TableView<UserPaper> tb_up;
	public TableColumn<?, ?> tcup_id;
	public TableColumn<?, ?> tcup_uid;
	public TableColumn<?, ?> tcup_pid;
	
	
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
	//paper
	public void initPaper() throws Exception
	{
		//language=MySQL
		String sql = "SELECT * FROM paper;";
		PaperDao dao = new PaperDao();
		ObservableList<Paper> result = FXCollections.observableList(dao.query(sql));
		tcp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcp_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		tb_paper.setItems(result);
	}
	public void queryPaper(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("SELECT * FROM paper");
		LinkedList<String> cons = new LinkedList<>();
		if (tfp_title.getText().length() > 0)
			cons.add("title like '" + tfp_title.getText() + "'");
		if (tfp_id.getText().matches("\\d+"))
			cons.add("id=" + tfp_id.getText());
		if (cons.size() > 0)
			sql.append(" WHERE ");
		for (int i = 0; i < cons.size(); i++)
		{
			if (i != 0)
				sql.append(" AND ");
			sql.append(cons.get(i));
		}
		sql.append(';');
		PaperDao dao = new PaperDao();
		ObservableList<Paper> result = FXCollections.observableList(dao.query(sql.toString()));
		tcp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcp_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		tb_paper.setItems(result);
	}
	public void editPaper(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("UPDATE paper");
		if (!tfp_id.getText().matches("\\d+"))
			return;
		LinkedList<String> cons = new LinkedList<>();
		if (tfp_title.getText().length() > 0)
			cons.add("title='" + tfp_title.getText() + "'");
		if (cons.size() > 0)
		{
			sql.append(" SET ");
			for (int i = 0; i < cons.size(); i++)
			{
				if (i != 0)
					sql.append(" , ");
				sql.append(cons.get(i));
			}
			sql.append("where id=").append(tfp_id.getText()).append(';');
			PaperDao dao = new PaperDao();
			dao.update(sql.toString());
		}
		initPaper();
	}
	public void delPaper(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("DELETE FROM paper");
		LinkedList<String> cons = new LinkedList<>();
		if (tfp_title.getText().length() > 0)
			cons.add("title like '" + tfp_title.getText() + "'");
		if (tfp_id.getText().matches("\\d+"))
			cons.add("id=" + tfp_id.getText());
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
			PaperDao dao = new PaperDao();
			dao.update(sql.toString());
		}
		initPaper();
	}
	public void addPaper(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("INSERT INTO paper(");
		LinkedList<String> keys = new LinkedList<>();
		LinkedList<String> values = new LinkedList<>();
		if (tfp_title.getText().length() > 0)
		{
			keys.add("title");
			values.add("'" + tfp_title.getText() + "'");
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
		PaperDao dao = new PaperDao();
		dao.update(sql.toString());
		initPaper();
	}
	//userpaper
	public void initUserPaper() throws Exception
	{
		//language=MySQL
		String sql = "SELECT * FROM user_paper_tree;";
		UserPaperDao dao = new UserPaperDao();
		ObservableList<UserPaper> result = FXCollections.observableList(dao.query(sql));
		tcup_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcup_uid.setCellValueFactory(new PropertyValueFactory<>("userId"));
		tcup_pid.setCellValueFactory(new PropertyValueFactory<>("paperId"));
		tb_up.setItems(result);
	}
	public void queryUP(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("SELECT * FROM user_paper_tree");
		LinkedList<String> cons = new LinkedList<>();
		if (tfup_user.getText().matches("\\d+"))
			cons.add("user_id=" + tfup_user.getText());
		if (tfup_paper.getText().matches("\\d+"))
			cons.add("paper_id=" + tfup_paper.getText());
		if (tfup_id.getText().matches("\\d+"))
			cons.add("id=" + tfup_id.getText());
		if (cons.size() > 0)
			sql.append(" WHERE ");
		for (int i = 0; i < cons.size(); i++)
		{
			if (i != 0)
				sql.append(" AND ");
			sql.append(cons.get(i));
		}
		sql.append(';');
		UserPaperDao dao = new UserPaperDao();
		ObservableList<UserPaper> result = FXCollections.observableList(dao.query(sql.toString()));
		tcup_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcup_uid.setCellValueFactory(new PropertyValueFactory<>("userId"));
		tcup_pid.setCellValueFactory(new PropertyValueFactory<>("paperId"));
		tb_up.setItems(result);
	}
	public void addUP(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("INSERT INTO user_paper_tree(");
		LinkedList<String> keys = new LinkedList<>();
		LinkedList<String> values = new LinkedList<>();
		if (tfup_user.getText().matches("\\d+"))
		{
			keys.add("user_id");
			values.add(tfup_user.getText());
		}
		if (tfup_paper.getText().matches("\\d+"))
		{
			keys.add("paper_id");
			values.add(tfup_paper.getText());
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
		UserPaperDao dao = new UserPaperDao();
		dao.update(sql.toString());
		initUserPaper();
	}
	public void delUP(MouseEvent mouseEvent) throws Exception
	{
		//language=MySQL
		StringBuilder sql = new StringBuilder("DELETE FROM user_paper_tree");
		LinkedList<String> cons = new LinkedList<>();
		if (tfup_user.getText().matches("\\d+"))
			cons.add("user_id=" + tfup_user.getText());
		if (tfup_paper.getText().matches("\\d+"))
			cons.add("paper_id=" + tfup_paper.getText());
		if (tfup_id.getText().matches("\\d+"))
			cons.add("id=" + tfup_id.getText());
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
			UserPaperDao dao = new UserPaperDao();
			dao.update(sql.toString());
		}
		initUserPaper();
	}
	public void editUP(MouseEvent mouseEvent) throws Exception
	{
		StringBuilder sql = new StringBuilder("UPDATE user_paper_tree");
		if (!tfup_id.getText().matches("\\d+"))
			return;
		LinkedList<String> cons = new LinkedList<>();
		if (tfup_user.getText().matches("\\d+"))
			cons.add("user_id=" + tfup_user.getText());
		if (tfup_paper.getText().matches("\\d+"))
			cons.add("paper_id=" + tfup_paper.getText());
		if (cons.size() > 0)
		{
			sql.append(" SET ");
			for (int i = 0; i < cons.size(); i++)
			{
				if (i != 0)
					sql.append(" , ");
				sql.append(cons.get(i));
			}
			sql.append("where id=").append(tfup_id.getText()).append(';');
			UserPaperDao dao = new UserPaperDao();
			dao.update(sql.toString());
		}
		initUserPaper();
	}
	
}
