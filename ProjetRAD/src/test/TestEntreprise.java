package test;

import java.util.Arrays;

import classes.Entreprise;
import classes.GraphMatrix;

public class TestEntreprise {

	public TestEntreprise() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		//Creation d'un graphe representant le reseau national
		int taille = 7;
		GraphMatrix g = new GraphMatrix(taille);
		//on ajoute des neouds et on cree des arcs
		g.addNode("Metz");
		g.addNode("Nancy");
		g.addNode("Paris");
		g.addNode("Lille");
		g.addNode("Lyon");
		g.addNode("test");
		g.addNode("test2");
		g.addEdge(55, "Metz", "Nancy");
		g.addEdge(200, "Metz", "Paris");
		g.addEdge(300, "Metz", "Lille");
		g.addEdge(350, "Metz", "Lyon");
		g.addEdge(230, "Nancy", "Paris");
		g.addEdge(310, "Nancy", "Lille");
		g.addEdge(330, "Nancy", "Lyon");
		g.addEdge(250, "Paris", "Lille");
		g.addEdge(100, "Lyon", "test");
		//on cree une entrprise en lui passant le reseau national en param
		Entreprise e = new Entreprise(g);
		//on optimise le reseau de transport de l'entreprise
		e.optimal();
		
		//affichage de la matrice edges du reseau entreprise de l'entreprise
		for (double[] row : e.getReseau_entreprise().getEdges())
		{
		    System.out.println(Arrays.toString(row));
		}
	
	}

}
