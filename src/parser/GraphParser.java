package parser;

import graph_ext.ArboricityVertex;

import java.io.File;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import logger.ArboricityLogger;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Parses graphs from xml to UndirectedGraph
 * @author Shay
 *
 */
public class GraphParser {
	private static GraphParser _instance;
	
	/**
	 * @return instance of GraphParser
	 */
	public static GraphParser getInstance() {
		if (_instance == null) {
			return new GraphParser();
		}
		
		return _instance;
	}
	
	/**
	 * @param inputFile - xml of graph
	 * @return - Undirected graph from xml
	 */
	public UndirectedGraph<ArboricityVertex, DefaultEdge> parseXML(String inputFileName) {
		UndirectedGraph<ArboricityVertex, DefaultEdge> graph =
				new SimpleGraph<ArboricityVertex, DefaultEdge>(DefaultEdge.class);
		 try {
			 ArboricityLogger.getInstance().log(Level.INFO, "GraphParser",
					 "parseXML", "Parsing graph from " + inputFileName);
			 
	         File inputFile = new File(inputFileName);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         
	         doc.getDocumentElement().normalize();
	         
	         System.out.println("arboricity: " + 
	        		 doc.getElementsByTagName("arboricity").item(0).getTextContent());
	         int arboricity = Integer.parseInt(doc.getElementsByTagName("arboricity").item(0).getTextContent());
	         
	         Node node = doc.getElementsByTagName("vertices").item(0);
	         NodeList nList = null;
	         if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	 nList = ((Element) node).getElementsByTagName("vertex");
	        	 System.out.println("----------------------------");
		         for (int i = 0; i < nList.getLength(); i++) {
		            Node nNode = nList.item(i);
		            System.out.println("\nCurrent Element :" 
		               + nNode.getNodeName());
		            System.out.println("vertex: " + nNode.getTextContent());
		            
		            ArboricityVertex v = new ArboricityVertex(nNode.getTextContent(), arboricity, nList.getLength());
		            graph.addVertex(v);
		         }
		         
		         NodeList edgesList = ((Element)doc.getElementsByTagName("edges").item(0)).getElementsByTagName("edge");
		         System.out.println("----------------------------");
		         for (int i = 0; i < edgesList.getLength(); i++) {
		        	 Node edgeNode = edgesList.item(i);
		        	 System.out.println("\nCurrent Element :" 
				               + edgeNode.getNodeName());
		        	 NodeList vertexNodeList = ((Element) edgeNode).getElementsByTagName("vertex");
		        	 
		        	 graph.addEdge(getVertexByName(graph, vertexNodeList.item(0).getTextContent()),
		        			 getVertexByName(graph, vertexNodeList.item(1).getTextContent()));
		         }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return graph;
	}
	
	/**
	 * @param graph - The input graph
	 * @param name - name of vertex to return
	 * @return - a vertex with the same name
	 */
	private ArboricityVertex getVertexByName(UndirectedGraph<ArboricityVertex, DefaultEdge> graph, String name) {		
		for (ArboricityVertex vertex : graph.vertexSet()) {
			if (vertex.getName().equals(name)) {
				return vertex;
			}
		}
		
		return null;
	}
}
