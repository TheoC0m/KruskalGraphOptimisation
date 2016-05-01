import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class GraphMatrix extends Graph {

	private List<String> nodes; // liste des noms des noeuds, un noeud
	// inexistant vaut null
	private double[][] edges; // matrice representant le graphe
	private int taille; // taille de la matrice

	public GraphMatrix(int taille) {
		// si la taille est superieure a 0
		if (taille > 0) {
			// on initialise les attributs, notament la matrice et la liste de
			// noeuds avec la taille specifiee
			this.taille = taille;
			this.nodes = new ArrayList<String>(taille);
			this.edges = new double[taille][taille];
			// on affecte la valeur 0 à chaque cases de la matrice = pas d'arcs
			for (int i = 0; i < this.taille; i++) {
				for (int j = 0; j < this.taille; j++) {
					this.edges[i][j] = 0;
				}
			}
		} else {
			// si taille <= 0 : aucun sens
			throw new IllegalArgumentException("taille <= 0");

		}
	}

	/*
	 * Renvoi vrai si le noeud existe, faux sinon 2 verifications : le noeud est
	 * dans la liste nodes et son indice est compris dans [0 ; taille[
	 */
	private boolean nodeExists(String n) {
		return this.nodes.contains(n) && (this.nodes.indexOf(n) >= 0) && (this.nodes.indexOf(n) < this.taille);
	}

	@Override
	public void addEdge(double p, String n1, String n2) {
		// si le poids est positif ou nul
		if (p >= 0) {
			// si les noeuds n1 et n2 existent
			if (this.nodeExists(n1) && this.nodeExists(n2)) {
				// on donne la valeur p aux cases qui correspondent a n1xn2 et
				// n2xn1 dans la matrice edges
				this.edges[this.nodes.indexOf(n1)][this.nodes.indexOf(n2)] = p;
				this.edges[this.nodes.indexOf(n2)][this.nodes.indexOf(n1)] = p;
			}
			// sinon on ne fait rien
		} else {
			// on ne gere que les poids positifs ou nuls donc erreur.
			throw new InvalidParameterException("poids < 0");
		}

	}

	/*
	 * ajoute un nouveau noeud si la taille max n'est pas atteinte et si il
	 * n'existe pas déjà un noeud du meme nom et si le nom du noeud n'est ni
	 * vide ni null
	 */
	@Override
	public void addNode(String n) {
		// si le nombre de noeuds max n'est pas atteint
		if ((this.nodes.size() < this.taille)) {
			// si le nom du noeud est valide et que le noeud n'existe pas deja
			if (n != null && !n.equals("") && !this.nodes.contains(n)) {
				this.nodes.add(n);
			}
		} else {
			throw new ArrayIndexOutOfBoundsException("Le nombre maximal de noeuds est atteint");
		}

	}

	@Override
	public boolean areConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delEdge(String n1, String n2) {
		// si les noeuds existent
		if (this.nodeExists(n1) && this.nodeExists(n2)) {
			// on met le poids de l'arc entre ces noeuds a 0 ( = pas d'arc)
			this.edges[this.nodes.indexOf(n1)][this.nodes.indexOf(n2)] = 0;
			this.edges[this.nodes.indexOf(n2)][this.nodes.indexOf(n1)] = 0;
		}
		// sinon on ne fait rien
	}

	@Override
	public void delNode(String n1) {
		// si le noeud a supprimer existe
		if (this.nodeExists(n1)) {
			// on recupere l'indice du noeud dans la liste nodes
			int indice = this.nodes.indexOf(n1);
			// on suprime tous les arcs lies a ce noeud dans la matrice edges
			for (int i = 0; i < taille; i++) {
				this.edges[i][indice] = 0;
				this.edges[indice][i] = 0;
			}
			// on supprime le noeud de la liste nodes
			this.nodes.remove(indice);
		}
		// on ne fait rien

	}

	@Override
	public int getDegree(String n1) {
		// on initialise le compteur d'arcs a 0
		int nb = 0;
		// si le noeud existe
		if (this.nodeExists(n1)) {
			// on recupere son indice
			int indice = nodes.indexOf(n1);
			// on compte le nombre d'arcs qui y sont connectés (valeur != 0 dans
			// la matrice)
			for (int i = 0; i < taille; i++) {
				if (this.edges[i][indice] != 0) {
					nb++;
				}
			}
		}
		return nb;
	}

	/*
	 * Si le graph ne contient pas d'arc renvoi null, sinon Retourne le dernier
	 * arc de poids le plus faible rencontre pendant le parcours de la matrice
	 */
	@Override
	public int[] getLightest() {

		// on initialise le tableau de taille 2 qui contiendra les indices des 2
		// noeuds d'arc au poids le plus faible a null
		int[] noeuds = null;
		// si le graphe contient des arcs
		if (this.numEdge() != 0) {
			// noeuds devient tableau de taille 2 qui contiendra les indices des
			// 2
			// noeuds d'arc au poids le plus faible
			noeuds = new int[2];
			// on initialise le poids le plus faible a 0
			double lightest = 0;
			// pour chaque case de la matrice
			for (int i = 0; i < this.taille; i++) {
				for (int j = 0; j < this.taille; j++) {
					// si le poids de l'arc >= lightest
					if (this.edges[i][j] >= lightest) {
						// on met a jour le plus faible poids trouve
						lightest = this.edges[i][j];
						// on enregistre les indices des 2 noeuds correspondant
						// a cet arc
						noeuds[0] = i;
						noeuds[1] = i;
					}
				}
			}
		}
		return noeuds;
	}

	@Override
	public double getWeight(String n1, String n2) {
		// on initialise weight a 0
		double weight = 0;
		// si les noeuds existent
		if (this.nodeExists(n1) && this.nodeExists(n2)) {
			// on verifie par precaution que n1-n2 et n2-n1 ont bien le meme
			// poids
			if (this.edges[this.nodes.indexOf(n1)][this.nodes
					.indexOf(n2)] == this.edges[this.nodes.indexOf(n2)][this.nodes.indexOf(n1)]) {
				// on affecte le poids de l'arc a weight
				weight = this.edges[this.nodes.indexOf(n1)][this.nodes.indexOf(n2)];
			} else {
				// si il y avait une incoherence dans les poids de n1-n2 et
				// n2-n1
				throw new IllegalStateException("une incoherence dans les arcs est survenue");

			}
		}
		return weight;
	}

	@Override
	public int numEdge() {
		// on initialise le nombre d'arcs a 0
		int nbarcs = 0;
		// pour chaque case de la matrice
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				// si une case n'a pas la valeur 0
				if (this.edges[i][j] != 0) {
					// on incremente le nombre d'arcs
					nbarcs++;
				}
			}
		}
		// on retourne le nombre d'arcs du graphe
		return nbarcs;
	}

}
