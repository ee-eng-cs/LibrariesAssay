package kp;

import java.util.List;

import com.googlecode.concurrenttrees.common.CharSequences;
import com.googlecode.concurrenttrees.common.Iterables;
import com.googlecode.concurrenttrees.common.PrettyPrinter;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.radix.node.util.PrettyPrintable;
import com.googlecode.concurrenttrees.radixinverted.ConcurrentInvertedRadixTree;
import com.googlecode.concurrenttrees.radixinverted.InvertedRadixTree;
import com.googlecode.concurrenttrees.radixreversed.ConcurrentReversedRadixTree;
import com.googlecode.concurrenttrees.radixreversed.ReversedRadixTree;
import com.googlecode.concurrenttrees.solver.LCSubstringSolver;
import com.googlecode.concurrenttrees.suffix.ConcurrentSuffixTree;
import com.googlecode.concurrenttrees.suffix.SuffixTree;

/*-
https://github.com/npgall/concurrent-trees
The framed javadoc for 'Concurrent-Trees API' can be read by using Eclipse IDE. 

A Radix Tree is known as patricia trie or compact prefix tree.
A Suffix Tree is known as PAT tree or position tree.
*/
/**
 * Researching Concurrent Radix and Suffix Trees.
 *
 */
public class ConcurrentTrees {
	/**
	 * Researches:
	 * <ul>
	 * <li>radix tree,
	 * <li>reversed radix tree,
	 * <li>inverted radix tree,
	 * <li>suffix tree,
	 * <li>pretty printer,
	 * <li>longest common substring solver
	 * </ul>
	 */
	public static void launch() {

		final RadixTree<Integer> radixTree = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());
		final ReversedRadixTree<Integer> reversedRadixTree = new ConcurrentReversedRadixTree<>(
				new DefaultCharArrayNodeFactory());
		final InvertedRadixTree<Integer> invertedRadixTree = new ConcurrentInvertedRadixTree<>(
				new DefaultCharArrayNodeFactory());
		final SuffixTree<Integer> suffixTree = new ConcurrentSuffixTree<>(new DefaultCharArrayNodeFactory());
		int number = 1;
		for (String key1 : List.of("a", "b", "c")) {
			for (String key2 : List.of("a", "b", "c")) {
				for (String key3 : List.of("a", "b", "c")) {
					radixTree.put(key1.concat(key2).concat(key3), number);
					reversedRadixTree.put(key1.concat(key2).concat(key3), number);
					invertedRadixTree.put(key1.concat(key2).concat(key3), number);
					suffixTree.put(key1.concat(key2).concat(key3), number++);
				}
			}
		}
		PrettyPrinter.prettyPrint((PrettyPrintable) radixTree, System.out);

		System.out.printf("RadixTree: keys starting with 'a' %s%n",
				Iterables.toString(radixTree.getKeysStartingWith("a")));
		System.out.printf("RadixTree: keys starting with 'ab' %s%n",
				Iterables.toString(radixTree.getKeysStartingWith("ab")));
		System.out.printf("RadixTree: values for keys starting with 'ab' %s%n",
				Iterables.toString(radixTree.getValuesForKeysStartingWith("ab")));
		System.out.printf("RadixTree: key-Value pairs for keys starting with 'ab' %s%n",
				Iterables.toString(radixTree.getKeyValuePairsForKeysStartingWith("ab")));
		System.out.printf("RadixTree: keys closest to 'abxxx' %s%n%n",
				Iterables.toString(radixTree.getClosestKeys("abxxx")));

		System.out.printf("SuffixTree: keys ending with 'c' %s%n",
				Iterables.toString(suffixTree.getKeysEndingWith("c")));
		System.out.printf("SuffixTree: keys ending with 'bc' %s%n",
				Iterables.toString(suffixTree.getKeysEndingWith("bc")));
		System.out.printf("SuffixTree: keys containing 'ab' %s%n%n",
				Iterables.toString(suffixTree.getKeysContaining("ab")));

		System.out.printf("InvertedRadixTree: keys starting with 'ab' %s%n",
				Iterables.toString(invertedRadixTree.getKeysStartingWith("ab")));
		System.out.printf("InvertedRadixTree: keys contained in text 'abcba' %s%n",
				Iterables.toString(invertedRadixTree.getKeysContainedIn("abcba")));
		System.out.printf("InvertedRadixTree: keys contained in text 'aabbcc' %s%n",
				Iterables.toString(invertedRadixTree.getKeysContainedIn("aabbcc")));
		System.out.printf("InvertedRadixTree: keys contained in text 'aaaa bbb cc' %s%n%n",
				Iterables.toString(invertedRadixTree.getKeysContainedIn("aaaa bbb cc")));

		System.out.printf("ReversedRadixTree: keys ending with 'bc' %s%n%n",
				Iterables.toString(reversedRadixTree.getKeysEndingWith("bc")));

		System.out.printf("Suffixes for 'xyz' %s%n%n", Iterables.toString(CharSequences.generateSuffixes("xyz")));

		final LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
		/*-     */solver.add("ijkl mn opqr stuv wxyz");
		/*-*/solver.add("efgh ijkl mn opqr stuv");
		System.out.printf("Longest common substring [%s]%n",
				CharSequences.toString(solver.getLongestCommonSubstring()));
		solver.add("abcd efgh ijkl mn opqr");
		System.out.printf("Longest common substring [%s]%n",
				CharSequences.toString(solver.getLongestCommonSubstring()));
		System.out.println("- ".repeat(50));
	}

}
