
public abstract class Graph {

	public abstract void addEdge(double p, String n1, String n2);

	public abstract void addNode(String n);

	public abstract boolean areConnected();

	public abstract void delEdge();

	public abstract void delNode();

	public abstract int getDegree();

	// changer type retour !
	public abstract void getLightest();

	public abstract int getWeight();

	public abstract void graph();

	public abstract int numEdge();
}
