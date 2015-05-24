package main;

import graph_ext.ArboricityVertex;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import parser.GraphParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UndirectedGraph<ArboricityVertex, DefaultEdge> graph = 
				GraphParser.getInstance().parseXML("input.xml");
	}

}