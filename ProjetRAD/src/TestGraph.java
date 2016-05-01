
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
		
		System.out.println(g.numEdge());
		g.addEdge(50, "Metz", "Lyon");
		System.out.println(g.numEdge());
		System.out.println(g.getDegree("Lyon"));
		System.out.println(g.getWeight("Lyon", "Metz"));
		g.delNode("Metz");
		System.out.println(g.getDegree("Lyon"));
		System.out.println(g.numEdge());
		
	
	}

}
