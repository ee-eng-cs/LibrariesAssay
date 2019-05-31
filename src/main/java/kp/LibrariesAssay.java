package kp;

/**
 * The assessment of libraries:
 * <ol>
 * <li>Apache Commons Collections,
 * <li>Concurrent Trees,
 * <li>Eclipse Collections
 * </ol>
 * 
 */
public class LibrariesAssay {
	//
	private static final boolean ALL = true;
	private static final boolean APACHE_COMMONS_COLLECTIONS = false;
	private static final boolean CONCURRENT_TREES = false;
	private static final boolean ECLIPSE_COLLECTIONS = false;

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		if (ALL || APACHE_COMMONS_COLLECTIONS) {
			ApacheCommonsCollections.patriciaTrieRetrieval();
		}
		if (ALL || CONCURRENT_TREES) {
			ConcurrentTrees.launch();
		}
		if (ALL || ECLIPSE_COLLECTIONS) {
			EclipseCollections.researchChunksAndPairs();
		}
		if (ALL || ECLIPSE_COLLECTIONS) {
			EclipseCollections.researchMapFlipping();
		}
		if (ALL || ECLIPSE_COLLECTIONS) {
			EclipseCollections.researchSetOperations();
		}
		if (ALL || ECLIPSE_COLLECTIONS) {
			EclipseCollections.researchUsingDates_1();
		}
		if (ALL || ECLIPSE_COLLECTIONS) {
			EclipseCollections.researchUsingDates_2();
		}
		if (ALL || ECLIPSE_COLLECTIONS) {
			EclipseCollections.researchUsingNumbers();
		}
	}
}
