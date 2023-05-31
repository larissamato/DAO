package view;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.dao.AlunoDAO;
import model.dao.FactoryDAO;
import model.entities.Aluno;

public class TelaAluno {
	
	static AlunoDAO alunoDao = FactoryDAO.createAlunoDAO();
	
	@SuppressWarnings("resource")
	public static Scanner menuAluno(Scanner console) throws InterruptedException, ParseException {

		int opcao = 0;
		do {
			System.out.println("\n\n");
			System.out.println("    ###   Tela: Curso     ###");
			System.out.println("    =========================");
			System.out.println("    |     1 - Cadastrar     |");
			System.out.println("    |     2 - Listar        |");
			System.out.println("    |     3 - Alterar       |");
			System.out.println("    |     4 - Excluir       |");
			System.out.println("    |     0 - Retornar      |");
			System.out.println("    =========================");
			System.out.print("    Op��o -> ");
			opcao = console.nextInt();
			console.nextLine();
			
			switch (opcao) {
			case 1: console = cadastrar(console);
					break;
			case 2:	console = listar(console);
					break;
			case 3: console = alterar(console);
					break;
			case 4: console = excluir(console);
					break;
			case 0:	console = TelaPrincipal.menuPrincipal(console);
					break;
			default:
				System.out.println("Opcao invalida!");
				TimeUnit.SECONDS.sleep(1);
			}
		} while (opcao != 0);
		return console;
	}
	
	private static Scanner cadastrar(Scanner console) throws ParseException {
		
		Aluno a = new Aluno(); 
		
		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Cadastrar ###");
		System.out.println("    =========================");
		
		System.out.print("    |     Nome: "); 
	    a.setNome(console.nextLine());
	    
	    System.out.println("    =========================");
	    
	    alunoDao.insert(a);
	    
	    console.nextLine();
	    return console;
	} 
	
	private static Scanner listar(Scanner console) {
				
		List<Aluno> alunos = alunoDao.findAll();
		
		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Listar    ###");
		System.out.println("    =========================");
		System.out.println("    |     Id\tNome");
		for(Aluno a : alunos) { 
			System.out.println("    |     " + a.getIdaluno()
							 + "\t" 		+ a.getNome() ); 
		}
		System.out.println("    =========================");
		console.nextLine();
		return console;
	}
	
	private static Scanner alterar(Scanner console) throws ParseException {
		
		Aluno a = new Aluno(); 
		
		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Alterar   ###");
		System.out.println("    =========================");  		
		System.out.print("    |     Id: "); 
		a.setIdaluno(console.nextInt()); 
		console.nextLine();
		  
		System.out.print("    |     Nome: "); 
		a.setNome(console.nextLine());
		  
		System.out.println("    =========================");
		alunoDao.update(a);
		
		console.nextLine();
		return console;
	} 
	
	private static Scanner excluir(Scanner console) throws ParseException {

		System.out.println("\n\n");
		System.out.println("    ###   Aluno-Excluir   ###");
		System.out.println("    =========================");
		System.out.print("    |     Digite o Id: ");
		int id = console.nextInt();
		console.nextLine();
		System.out.println("    =========================");
		
		alunoDao.deleteById(id);
		
		console.nextLine();
		return console;
	}
}