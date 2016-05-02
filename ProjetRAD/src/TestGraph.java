
public class TestGraph {

	public TestGraph() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		GraphMatrix g = new GraphMatrix(7);
		
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
		
		
		System.out.println("Nombre d'arcs : " + g.numEdge());
		System.out.println("Arc le plus leger : " + g.getLightest()[0] + "-" + g.getLightest()[1]);
		System.out.println("Distance Paris-Metz : " + g.getWeight("Paris", "Metz")); 
		System.out.println("Nombre de connexions de Metz : " + g.getDegree("Metz"));
		System.out.println("Nombre de connexions de test " + g.getDegree("test"));
		System.out.println("Metz et test sont-elles reliees ? : " + g.areConnected("Metz", "test"));
		System.out.println("Metz et test2 sont-elles reliees ? : " + g.areConnected("Metz", "test2"));
		System.out.println("\nSuppression de Nancy...");  
		g.delNode("Nancy");
		System.out.println("Nouveau nombre d'arcs : " + g.numEdge());
		System.out.println("Nouvel arc le plus leger : " + g.getLightest()[0] + "-" + g.getLightest()[1]);
		System.out.println("Distance Paris-Metz : " + g.getWeight("Paris", "Metz"));
		System.out.println("Nouveau nombre de connexions de Metz : " + g.getDegree("Metz"));
		System.out.println("Distance Paris-Lille : " + g.getWeight("Paris", "Lille"));
		System.out.println("Distance Lyon-test: " + g.getWeight("Lyon", "test"));
		System.out.println("\nSuppression de test...");  
		g.delNode("test");
		System.out.println("Nouveau nombre d'arcs : " + g.numEdge());
		System.out.println("Nouvel arc le plus leger : " + g.getLightest()[0] + "-" + g.getLightest()[1]);
		System.out.println("Distance Paris-Metz : " + g.getWeight("Paris", "Metz"));
	}

}
