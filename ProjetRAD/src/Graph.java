
public abstract class Graph {

	public abstract void addEdge(double p, String n1, String n2);

	public abstract void addNode(String n);

	public abstract boolean areConnected();

	public abstract void delEdge(String n1, String n2);

	public abstract void delNode(String n1);

	public abstract int getDegree(String n1);

	public abstract int[] getLightest();

	public abstract double getWeight(String n1, String n2);

	public abstract int numEdge();
}
