package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Model.Pessoa;
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  nome = request.getParameter("nome");
		
		char sexo =  request.getParameter("sexo").charAt(0);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate dataNasc =  LocalDate.parse(request.getParameter("dataNasc"),formatter);
	
		String rg = request.getParameter("rg");
		
		String cpf = request.getParameter("cpf");
		
		double salario = Double.parseDouble(request.getParameter("salario"));
		
		int tempoContribuicao = Integer.parseInt(request.getParameter("tempoContribuicao"));
		
		String areaTrabalho = request.getParameter("areaTrabalho");
		
		Pessoa p = new Pessoa(nome,sexo,dataNasc,rg,cpf,salario,tempoContribuicao,areaTrabalho);
		
		String opcao = request.getParameter("metodo");
		
		switch(opcao) {
		
		case "1":
			request.setAttribute("metodo", p.calcSalarioAnual());
			break;
		case "2":
			request.setAttribute("metodo", p.calcSalarioAnualImposto());
			break;
		case "3":
			request.setAttribute("metodo", p.calcAposentadoria());
			break;
		case "4":
			request.setAttribute("metodo", p.calcSalarioAposentadoria());
			break;
			
		}

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CarteiraDigital.jsp");
		dispatcher.forward(request, response);
	}

}
