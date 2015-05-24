package coloring;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import graph_ext.ArboricityVertex;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Manages the A Coloring algorithm
 * @author Shay Yacobinski
 */
public class AColoring {
	
	private UndirectedGraph<ArboricityVertex, DefaultEdge> _inputGraph;
	private Vector<Vector<ArboricityVertex> > _hPartitionVec;
	
	public AColoring(UndirectedGraph<ArboricityVertex, DefaultEdge> graph) {
		this._inputGraph = graph;
		this._hPartitionVec = new Vector<Vector<ArboricityVertex> >();
	}
	
	/**
	 * Executes the O(a) coloring algorithm
	 */
	public void startAlg() {
		procedurePartition(2);
	}
	
	private void procedurePartition(float e) {
		Set<ArboricityVertex> vertexSet = this._inputGraph.vertexSet();
		double l = Math.ceil((2/e)*Math.log(vertexSet.size()));
		
		// For each round
		for (int i = 0; i < l; i++) {
			// Hi partition
			Vector<ArboricityVertex> roundH = new Vector<ArboricityVertex>();
			
			// For each vertex in V
			for (ArboricityVertex vertex : vertexSet) {
				
				if (vertex.isActive()) {
					List<ArboricityVertex> neighbors = Graphs.neighborListOf(this._inputGraph, vertex);
					
					int activeNeigbors = 0;
					for (ArboricityVertex neighbor : neighbors) {
						if (neighbor.isActive()) ++activeNeigbors;
					}
					
					if (activeNeigbors < (2+e)*vertex.getArboricity()) {
						vertex.setActive(false);
						roundH.add(vertex);
					}
				}
			}
			
			// Add this round's partition
			this._hPartitionVec.add(roundH);
		}
	}
}
