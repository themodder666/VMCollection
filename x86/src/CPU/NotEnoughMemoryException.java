package CPU;

public class NotEnoughMemoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5668036864562185116L;
public String getMessage() {
	// TODO Auto-generated method stub
	return "Less Than 1024 bytes reserved to memory";
}
}