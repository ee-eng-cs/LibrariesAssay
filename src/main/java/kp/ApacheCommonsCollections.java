package kp;

import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

/*-
https://commons.apache.org/proper/commons-collections/
https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/trie/PatriciaTrie.html
 
PATRICIA: Practical Algorithm to Retrieve Information Coded in Alphanumeric
 
A trie is also called digital tree, radix tree or prefix tree. 
*/
/**
 * Researching Apache Commons Collections.
 *
 */
public class ApacheCommonsCollections {
	/**
	 * Data accessing from Patricia trie.
	 * 
	 */
	public static void patriciaTrieRetrieval() {

		final Trie<String, String> patriciaTrie = new PatriciaTrie<>();
		int number = 1;
		for (String key1 : List.of("a", "b", "c")) {
			for (String key2 : List.of("a", "b", "c")) {
				for (String key3 : List.of("a", "b", "c")) {
					patriciaTrie.put(key1.concat(key2).concat(key3), String.valueOf(number++));
				}
			}
		}
		final SortedMap<String, String> prefixMap = patriciaTrie.prefixMap("ab");
		System.out.printf("All keys prefixed by 'ab' %s%n", prefixMap.keySet().stream().collect(Collectors.toList()));
		final String firstKey = patriciaTrie.firstKey();
		System.out.printf("First key[%s], value[%2s], previous key[%4s], next key[%4s]%n", firstKey,
				patriciaTrie.get(firstKey), patriciaTrie.previousKey(firstKey), patriciaTrie.nextKey(firstKey));
		final String lastKey = patriciaTrie.lastKey();
		System.out.printf("Last  key[%s], value[%2s], previous key[%4s], next key[%4s]%n", lastKey,
				patriciaTrie.get(lastKey), patriciaTrie.previousKey(lastKey), patriciaTrie.nextKey(lastKey));
		System.out.println("- ".repeat(50));
	}
}
