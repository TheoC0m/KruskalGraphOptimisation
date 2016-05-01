import java.util.List;

public class Entreprise {

	private GraphMatrix reseau_nationnal;
	private GraphMatrix reseau_entreprise;
	private List<String> villes; // liste des villes du reseau

	public Entreprise(GraphMatrix g1) {
		this.reseau_nationnal = g1;
		this.reseau_entreprise = new GraphMatrix(g1.getTaille()); // nouveau
																	// graphe
																	// vide de
																	// la meme
																	// taille
																	// que g1
	}

	public void add_city(String cityname) {
		this.reseau_entreprise.addNode(cityname);
		this.villes = reseau_entreprise.getNodes();
	}

	public void add_rail(double distance, String city1, String city2) {
		this.reseau_entreprise.addEdge(distance, city1, city2);
		this.villes = reseau_entreprise.getNodes();
	}

	public void del_rail(String city1, String city2) {
		this.reseau_entreprise.delEdge(city1, city2);
		this.villes = reseau_entreprise.getNodes();
	}

	public void optimal(){

		//tant que reseau_entreprise a moins de taille-1 arcs et reseau_nationnal a au moins un arc
		while(this.reseau_entreprise.numEdge() <= this.reseau_nationnal.getTaille()-1 &&
		this.reseau_nationnal.numEdge() >= 1){
			
			//tableau de 2 Strings
			String[] lightest_edge = new String[2];
			//on y stock l'arc le plus leger de reseau_nationnal
			lightest_edge = this.reseau_nationnal.getLightest();
			//on stock le poids de l'arc
			double poidsarc = this.reseau_nationnal.getWeight(lightest_edge[0], lightest_edge[1]);
			//on le supprime de reseau_nationnal
			this.reseau_nationnal.delEdge(lightest_edge[0], lightest_edge[1]);
			
			//si le reseau entreprise ne contient pas la ville lightest_edge[0] issue de l'arc le plus leger
			if(!this.reseau_entreprise.getNodes().contains(lightest_edge[0])){
				//on l'y ajoute
				this.add_city(lightest_edge[0]);
			}
			//si le reseau entreprise ne contient pas la ville lightest_edge[1] issue de l'arc le plus leger
			if(!this.reseau_entreprise.getNodes().contains(lightest_edge[1])){
				//on l'y ajoute
				this.add_city(lightest_edge[1]);
			}
			//si il n'existe pas de chemin entre les deux villes, 
			if(!this.reseau_entreprise.areConnected(lightest_edge[0], lightest_edge[1])){
				//on le cree
				this.add_rail(poidsarc, lightest_edge[0], lightest_edge[1]);
			}
			
		}
		
	}
}
