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
			//throw new IllegalStateException("Le nombre maximal de noeuds est atteint");
			System.out.println("Le nombre maximal de noeuds est atteint");
		}

	}
	
	/*
	 * n1 : noeud courant, n2 noeud d'arrive, noeudsvisites : liste des noeuds deja visites 
	 * pour eviter les boucles infinies en cas de circuit dans le graphe
	 */
	private boolean recursiveConnectionCheck(String n1, String n2, ArrayList<String> noeudsvisites) {
		//on ajoute le noeud n1 a la liste des noeuds visites
		noeudsvisites.add(n1);
		// si les noeuds sont directement lies
		if (this.edges[this.nodes.indexOf(n1)][this.nodes.indexOf(n2)] != 0) {
			return true;
		}
		// sinon il faut chercher un chemin en verifiant
		// si parmi les noeuds lies a n1, l'un d'entre eux est connecte a n2
		else {
			// variable qui vaut true si l'un des noeuds lies a n1 possede un
			// chemin vers n2
			boolean noeud_lie_connecte = false;
			// pour chaque noeud potentiellement lie a n1 on verifie si ils sont
			// lies par un arc, et qu'ils ne sont pas présent dans la liste des noeuds deja visites
			// -> on fait un appel recursif
			// avec false OU le resultat de cet appel recursif
			// si n1 n'est lie a aucun noeud on return juste false
			//si on a trouve un chemin (true) on s'arrete, ce n'est plus la peine d'explorer les autres noeuds
			for (int i = 0; i < taille; i++) {
				if (!noeud_lie_connecte && (this.edges[i][this.nodes.indexOf(n1)] != 0) && !noeudsvisites.contains(this.nodes.get(i))) {
					noeud_lie_connecte = noeud_lie_connecte || this.recursiveConnectionCheck(this.nodes.get(i), n2, noeudsvisites);
				}
			}
			return noeud_lie_connecte;
		}

	}

	@Override
	public boolean areConnected(String n1, String n2) {
		// on initialise le boolean a false
		boolean connected = false;
		// Si les noeuds existent
		if (this.nodeExists(n1) && this.nodeExists(n2)) {
			//on cherche si ils sont lies par un chemin
			connected = this.recursiveConnectionCheck(n1, n2, new ArrayList<String>());
		}
		return connected;
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
	public String[] getLightest() {

		// on initialise le tableau de taille 2 qui contiendra les indices des 2
		// noeuds d'arc au poids le plus faible a null
		String[] noeuds = null;
		// si le graphe contient des arcs
		if (this.numEdge() != 0) {
			// noeuds devient tableau de taille 2 qui contiendra les noms des
			// 2 noeuds d'arc au poids le plus faible
			noeuds = new String[2];
			// on initialise le poids le plus faible a la plus grande valeur possible pour un double
			double lightest = Double.MAX_VALUE;
			// pour chaque case de la matrice
			for (int i = 0; i < this.taille; i++) {
				for (int j = 0; j < this.taille; j++) {
					// si le poids de l'arc >= lightest et que le poid n'est pas 0
					if ((this.edges[i][j] <= lightest) && this.edges[i][j] != 0) {
						// on met a jour le plus faible poids trouve
						lightest = this.edges[i][j];
						// on enregistre les noms des 2 noeuds correspondant
						// a cet arc
						noeuds[0] = this.nodes.get(i);
						noeuds[1] = this.nodes.get(j);
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
		// on retourne le nombre d'arcs du graphe /2 car il y a 2 enregistrements pour 1 meme arc
		return nbarcs/2;
	}

	public int getTaille() {
		return taille;
	}

	public List<String> getNodes() {
		return nodes;
	}

	
}
