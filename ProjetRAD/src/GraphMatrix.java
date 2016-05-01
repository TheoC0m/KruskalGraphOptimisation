import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class GraphMatrix extends Graph {
	
	private List<String> nodes; //liste des noms des noeuds, un noeud inexistant vaut null
	private double[][] edges; //matrice representant le graphe
	private int taille; //taille de la matrice

	public GraphMatrix(int taille) {
		// on initialise les attributs, notament la matrice et la liste de noeuds avec la taille specifiee
		this.taille = taille;
		this.nodes = new ArrayList<String>(taille);
		this.edges = new double[taille][taille];
		//on affecte la valeur 0 à chaque cases de la matrice
		for(int i = 0; i < this.taille; i++){
			for(int j = 0; j < this.taille; j++){
				this.edges[i][j] = 0;
			}
		}
	}
	
	
	/*
	 * Renvoi vrai si le noeud existe, faux sinon
	 * 2 verifications : le noeud est dans la liste nodes et son indice 
	 * est compris dans [0 ; taille[ 
	 */
	private boolean nodeExists(String n){
		return this.nodes.contains(n) && (this.nodes.indexOf(n) >=0) && 
				(this.nodes.indexOf(n) < this.taille);
	}
	
	/*
	 * Renvoi vrai si w >= 0, faux sinon
	 * permet de verifier que le poids d'un arc est positif ou nul
	 */
	private boolean isValidWeight(double w){
		return (w >= 0);
	}

	@Override
	public void addEdge(double p, String n1, String n2) {
		//si les parametres poids, noeud 1 et noeud 2 sont correctes
		if(this.nodeExists(n1) && this.nodeExists(n2) && this.isValidWeight(p)){
			this.edges[this.nodes.indexOf(n1)][this.nodes.indexOf(n2)] = p;
			this.edges[this.nodes.indexOf(n2)][this.nodes.indexOf(n1)] = p;
		}
		else{
			throw new InvalidParameterException("poids < 0 ou noeud(s) inexistant(s)");
		}

	}

	/*
	 * ajoute un nouveau noeud si la taille max n'est pas atteinte
	 * si il n'existe pas déjà un noeud du même nom et si le nom du noeud n'est ni vide ni null
	 */
	@Override
	public void addNode(String n) {
		if((this.nodes.size() < this.taille)){
			if(n!= null && !n.equals("") && !this.nodes.contains(n)){
				this.nodes.add(n);
			}
		}
		else{
			throw new ArrayIndexOutOfBoundsException("La taille maximale de noeuds est atteinte");
		}

	}

	@Override
	public boolean areConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delEdge() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delNode() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getLightest() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void graph() {
		// TODO Auto-generated method stub

	}

	@Override
	public int numEdge() {
		// TODO Auto-generated method stub
		return 0;
	}

}
