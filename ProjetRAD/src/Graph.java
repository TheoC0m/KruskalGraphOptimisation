
public abstract class Graph {

	public abstract void addEdge();

	public abstract void addNode();

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
