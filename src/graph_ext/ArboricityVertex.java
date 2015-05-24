package graph_ext;

/**
 * Representation of a vertex
 * @author Shay Yacobinski
 */
public class ArboricityVertex {
	
	private int _color = 0;
	private String _name;
	private boolean _active = true;
	private int _arboricity;
	private int _verticesNum;
	
	public ArboricityVertex(String name, int arboricity, int verticesNum) {
		this._name = name;
		this._arboricity = arboricity;
		this._verticesNum = verticesNum;
	}
	
	/**
	 * @return vertex color
	 */
	public int getColor() {
		return _color;
	}
	
	/**
	 * @param _color - vertex color
	 */
	public void setColor(int _color) {
		this._color = _color;
	}
	
	/**
	 * @return graph arboricity
	 */
	public int getArboricity() {
		return _arboricity;
	}
	
	/**
	 * @param _arboricity - graph arboricity
	 */
	public void setArboricity(int _arboricity) {
		this._arboricity = _arboricity;
	}
	
	/**
	 * @return vertex name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * @param _name - vertex name
	 */
	public void setName(String _name) {
		this._name = _name;
	}
	
	/**
	 * @return number of vertices in the graph
	 */
	public int getVerticesNum() {
		return _verticesNum;
	}
	
	/**
	 * @param _verticesNum - number of vertices in the graph
	 */
	public void setVerticesNum(int _verticesNum) {
		this._verticesNum = _verticesNum;
	}
	
	/**
	 * @param _active - set if the vertex is active or not
	 */
	public void setActive(boolean _active) {
		this._active = _active;
	}
	
	/**
	 * @return vertex active or not
	 */
	public boolean isActive() {
		return _active;
	}
}
