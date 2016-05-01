
public class TestGraph {

	public TestGraph() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		GraphMatrix g = new GraphMatrix(5);
		
		g.addNode("Metz");
		g.addNode("Nancy");
		g.addNode("Paris");
		g.addNode("Lille");
		g.addNode("Lyon");
		g.addNode("test");
		
		g.addEdge(50, "Metz", "Nancy");
		System.out.println(g.getWeight("Nancy", "Metz") + " " + g.getWeight("Metz", "Nancy"));
		g.addEdge(55, "Nancy", "Metz");
		System.out.println(g.getWeight("Nancy", "Metz") + " " + g.getWeight("Metz", "Nancy"));
		
		System.out.println(g.getLightest()[0]+"-"+g.getLightest()[1]);
		
		
		g.addEdge(230, "Nancy", "Paris");		
		System.out.println(g.getWeight("Nancy", "Paris") + " " + g.getWeight("Paris", "Nancy"));
		
		g.addEdge(200, "Paris", "Metz");
		System.out.println(g.getWeight("Metz", "Paris") + " " + g.getWeight("Paris", "Metz"));
		
		g.addEdge(300, "Metz", "Lille");
		g.addEdge(350, "Metz", "Lyon");
		g.addEdge(310, "Nancy", "Lille");
		g.addEdge(330, "Lyon", "Nancy");
		g.addEdge(250, "Paris", "Lille");
		
		System.out.println(g.numEdge());
		
		System.out.println(g.getLightest()[0] + "-" + g.getLightest()[1]);
		
		//System.out.println(g.areConnected("Metz", "Nancy") + ","+ g.areConnected( "Nancy", "Metz"));
		
		System.out.println(g.areConnected("Lille", "Lyon") + ","+ g.areConnected( "Lyon", "Lille"));
		
	}

}
