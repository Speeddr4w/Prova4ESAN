package controller;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.ConnectionFactory;
import model.Funcionario;
import model.FuncionarioDAO;

public class CrudFuncionarioController implements Initializable {
	
	
	//private ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
	
	
	@FXML
    private TextField IncluirsalarioFuncionario;

    @FXML
    private DatePicker AlterardataFuncionario;

    @FXML
    private TextField AlteraridFuncionario;

    @FXML
    private Button ExcluirButton;

    @FXML
    private TextField IncluirnomeFuncionario;

    @FXML
    private Button IncluirButton;

    @FXML
    private Label QuantidadeItens;

    @FXML
    private Button AlterarButton;

    @FXML
    private TextField AlterarnomeFuncionario;

    @FXML
    private Button PesquisarButton;

    @FXML
    private DatePicker IncluirdataFuncionario;

    @FXML
    private TextField AlterarsalarioFuncionario;

    @FXML
    private TextField ExcluiridFuncionario;

    private FuncionarioDAO dao;

    private Main main;

    public void setMain(Main main) {

        this.main = main;
        
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			dao = new FuncionarioDAO();
			AtualizarQuantidade();
			
			
	}
	
	void AtualizarQuantidade() {
		try {
		QuantidadeItens.setText(String.valueOf(dao.NumeroDeRegistros()));
		}catch(Exception e) {
        	exibirDialogoInformacao("Falha na consulta de registros");
        	e.printStackTrace();
    		
    	}
		
	}

    
    @FXML
    void IncluirFuncionario(ActionEvent event) {
    	
    	String nome = IncluirnomeFuncionario.getText();
    	LocalDate dataNiver = IncluirdataFuncionario.getValue();
    	Float salario = Float.parseFloat(IncluirsalarioFuncionario.getText());
    	
    	Funcionario funcionario = new Funcionario(nome,dataNiver,salario);
    	try {
        	dao.cadastrar(funcionario);
        	exibirDialogoInformacao("Funcionario cadastrado");
        	
    	}  catch(Exception e) {
        	exibirDialogoInformacao("Falha no cadastro");
        	e.printStackTrace();
    		
    	}
    	
	AtualizarQuantidade();

    }

    @FXML
    void PesquisarFuncionario(ActionEvent event) {
    	int idFuncionario = Integer.valueOf(AlteraridFuncionario.getText()) ;
    	Funcionario fun = dao.pesquisar(idFuncionario);

    	try {
        	AlterarnomeFuncionario.setText(fun.getNome());
        	AlterardataFuncionario.setValue(fun.getDataNascimento());
        	AlterarsalarioFuncionario.setText(String.valueOf(fun.getSalario()));
        	
    	}  catch(Exception e) {
        	exibirDialogoInformacao("Falha na busca");
        	e.printStackTrace();
    		
    	}
    }

    @FXML
    void AlterarFuncionario(ActionEvent event) {
    	
    	int idFuncionario = Integer.valueOf(AlteraridFuncionario.getText());
    	String nome = AlterarnomeFuncionario.getText();
    	LocalDate dataNiver = AlterardataFuncionario.getValue();
    	Float salario = Float.parseFloat(AlterarsalarioFuncionario.getText());
    	
    	Funcionario fun = new Funcionario(nome,dataNiver,salario);
    	fun.setId(idFuncionario);

    	try {
        	dao.atualizar(fun);
        	exibirDialogoInformacao("Funcionario atualizado");
        	
    	}  catch(Exception e) {
        	exibirDialogoInformacao("Falha na atualizacao");
        	e.printStackTrace();
    		
    	}
    	
    	
    	
    }

   
    @FXML
    void ExcluirFuncionario(ActionEvent event) {
    	int idFuncionario = Integer.valueOf(ExcluiridFuncionario.getText());
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Deletar ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {

        	try {
            	dao.excluir(idFuncionario);
            	exibirDialogoInformacao("Funcionario deletado");
            	AtualizarQuantidade();
            	
        	}  catch(Exception e) {
            	exibirDialogoInformacao("Falha");
            	e.printStackTrace();
        		
        	}
        }
    	
    }
    private void exibirDialogoInformacao(String mensagem) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(null);
    	alert.setHeaderText(null);
    	alert.setContentText(mensagem);
    	
    	alert.showAndWait();
    }

}


