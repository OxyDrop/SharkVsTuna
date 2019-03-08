package aquarium;

/**
 *
 * @author Serero
 */
public class Cannibale extends Requin{ //Un requin qui peut devorer ses congéneres
	public Cannibale(){
		super();
	}
	 @Override
        public String toString(){
            return "cannibale"+position.toString();
        }	
}
