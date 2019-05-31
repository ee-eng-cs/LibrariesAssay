package kp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.MutableOrderedMap;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.OrderedMaps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ByteLists;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.FloatLists;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.ShortLists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.utility.Iterate;

/*-
https://www.eclipse.org/collections/
The framed javadoc for 'Eclipse Collections API' can be read by using Eclipse IDE.
 
https://github.com/eclipse/eclipse-collections/blob/master/docs/guide.md
('Eclipse Collections' were previously named 'Goldman Sachs Collections')

--------------------|-----------------------------------|
Eclipse Collections | Java Collections & Streams		|
--------------------|-----------------------------------|
FastList			| ArrayList							|
UnifiedSet			| HashSet							|
UnifiedMap			| HashMap							|
ArrayStack			| Stack								|
					|									|
select				| filter							|
reject				| filter (!)						|
collect				| map								|
flatCollect			| flatMap							|
detect 				| filter().findFirst().orElse(null)	|
detectIfNone 		| filter().findFirst().orElseGet()	|
detectOptional 		| filter().findFirst()				|
count 				| filter().count()					|
injectInto 			| reduce							|
any/all/noneSatisfy | any/all/noneMatch					|
--------------------|-----------------------------------|
*/
/**
 * Researching Eclipse Collections.
 * 
 */
public class EclipseCollections {

	/**
	 * Researches chunks and pairs.<br>
	 * The chunk - a collection of a specified fixed size.<br>
	 * Used here are: MutableList, MutableIntList, FastList, RichIterable,
	 * IntInterval, IntIterable.
	 */
	public static void researchChunksAndPairs() {

		final MutableIntList list = IntInterval.fromTo(1, 10).toList();
		final int CHUNK_SIZE = 4;
		final RichIterable<IntIterable> chunks = list.chunk(CHUNK_SIZE);
		System.out.printf("Chunk size[%d], chunks%s%n", CHUNK_SIZE, chunks);
		System.out.printf("The 4th element of a 2nd chunk[%d]. The 1st element of a 3rd chunk[%d].%n%n",
				chunks.toList().get(1).toList().get(3), chunks.toList().get(2).toList().get(0));

		final MutableList<String> firstList = FastList.newListWith("aaa", "bbb", "ccc", "truncated");
		final MutableList<String> secondList = FastList.newListWith("AAA", "BBB", "CCC");
		final MutableList<Pair<String, String>> pairs = firstList.zip(secondList);
		System.out.printf("Zipped into pairs%s%n", pairs);
		System.out.printf("The element 'Two' of a 2nd pair[%s]. The element 'One' of a 3rd pair[%s].%n%n",
				pairs.toList().get(1).getTwo(), pairs.toList().get(2).getOne());

		final MutableIntList intList1 = IntInterval.fromTo(1, 5).toList();
		final MutableIntList intList2 = IntInterval.fromToBy(5, 25, 5).toList();
		System.out.printf("Zipped into pairs%s%n", intList1.zipInt(intList2));
		System.out.println("- ".repeat(50));
	}

	/**
	 * Researches flipping a map.<br>
	 * Used here are: MutableMap, FastListMultimap.
	 */
	public static void researchMapFlipping() {

		final MutableMap<String, String> mutableMap = Iterate.toMap(/*-*/
				Lists.mutable.of("a", "b", "c"), k -> k.concat("k"), v -> v.toUpperCase().concat("V"));
		System.out.printf("MutableMap[%s]%n", mutableMap);
		System.out.printf("MutableMap[%s] ◄ flipped%n%n", mutableMap.flip());

		final FastListMultimap<String, String> multimap = FastListMultimap.newMultimap();
		multimap.putAll(Multimaps.mutable.list.of("aK", "AV", "aK", "BV", "aK", "CV"));
		multimap.putAll(Multimaps.mutable.list.of("bK", "AV", "bK", "BV", "bK", "CV"));
		multimap.putAll(Multimaps.mutable.list.of("cK", "AV", "cK", "BV", "cK", "CV"));
		// adding duplicate element
		multimap.put("cK", "CV");
		System.out.printf("Multimap[%s]%n", multimap);
		System.out.printf("Multimap[%s] ◄ flipped%n", multimap.flip());
		System.out.println("- ".repeat(50));
	}

	/**
	 * Researches set operations.<br>
	 * Used here are: MutableSortedSet, UnifiedSet.
	 */
	public static void researchSetOperations() {

		final MutableSortedSet<String> set1 = UnifiedSet.newSetWith("A", "B", "C").toSortedSet();
		final MutableSortedSet<String> set2 = UnifiedSet.newSetWith("B", "C", "D").toSortedSet();
		System.out.printf("Set 1%s, set 2%s, sets union%s, sets intersect%s%n", set1, set2, set1.union(set2),
				set1.intersect(set2));
		System.out.printf("Set 1 difference %s, set 2 difference %s, sets symmetric difference%s%n",
				set1.difference(set2), set2.difference(set1), set1.symmetricDifference(set2));
		System.out.println("- ".repeat(50));
	}

	/**
	 * Researches using dates.<br>
	 * Used here are: MutableList, FastList.
	 * 
	 */
	public static void researchUsingDates_1() {

		final LocalDate SOURCE_DATE = LocalDate.of(2000, 1, 1);
		final Stream<LocalDate> stream = IntStream.rangeClosed(-3, 3).boxed().map(SOURCE_DATE::plusDays);
		final MutableList<LocalDate> dateList = FastList.newList(stream.collect(Collectors.toList()));
		System.out.printf("Date list%s%n", dateList);
		org.eclipse.collections.api.block.predicate.Predicate<LocalDate> predicate1 = Predicates
				.attributeEqual(LocalDate::getDayOfWeek, DayOfWeek.MONDAY);
		System.out.printf("Monday detected in date list: date[%s]%n", dateList.detect(predicate1));

		final boolean successFlag = dateList.anySatisfyWith(LocalDate::isAfter, SOURCE_DATE);
		System.out.printf("Days after[%s]: selected any [%b], selected days count[%d]%n", SOURCE_DATE, successFlag,
				dateList.countWith(LocalDate::isAfter, SOURCE_DATE));
		final MutableList<LocalDate> selectedList = dateList.selectWith(LocalDate::isAfter, SOURCE_DATE);
		final MutableList<LocalDate> rejectList = dateList.rejectWith(LocalDate::isAfter, SOURCE_DATE);
		System.out.printf("Days after[%s]: rejected%s, selected%s%n%n", SOURCE_DATE, rejectList, selectedList);

		final MutableList<DayOfWeek> dayOfWeekList = dateList.collect(LocalDate::getDayOfWeek);
		System.out.printf("Days of week%s%n", dayOfWeekList);
		final MutableList<DayOfWeek> notMondayDOWList1 = dayOfWeekList.reject(DayOfWeek.MONDAY::equals);
		System.out.printf("Days of week%s ◄ with Monday rejected%n", notMondayDOWList1);

		org.eclipse.collections.api.block.predicate.Predicate<LocalDate> predicate2 = Predicates
				.attributeNotEqual(LocalDate::getDayOfWeek, DayOfWeek.MONDAY);
		final MutableList<DayOfWeek> notMondayDOWList2 = dateList.select(predicate2).collect(LocalDate::getDayOfWeek);
		System.out.printf("Days of week%s ◄ with any day not equal Monday%n", notMondayDOWList2);

		org.eclipse.collections.api.block.predicate.Predicate<LocalDate> predicate3 = Predicates
				.attributeBetweenInclusive(LocalDate::getDayOfWeek, DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
		final MutableList<DayOfWeek> weekBeginDOWList = dateList.select(predicate3).collect(LocalDate::getDayOfWeek);
		System.out.printf("Days of week%s ◄ only Monday, Tuesday, Wednesday%n", weekBeginDOWList);
		System.out.println("- ".repeat(50));
	}

	/**
	 * Researches using dates.<br>
	 * Used here are: MutableList, MutableListMultimap, MutableBag, FastList.
	 * 
	 */
	public static void researchUsingDates_2() {

		final LocalDate SOURCE_DATE = LocalDate.of(2000, 1, 1);
		final LocalDate monthEnd = SOURCE_DATE.plusMonths(1).minusDays(1);
		final Stream<LocalDate> dateStream = IntStream.rangeClosed(SOURCE_DATE.getDayOfYear(), monthEnd.getDayOfYear())
				.boxed().map(arg -> SOURCE_DATE.withDayOfYear(arg));
		final MutableList<LocalDate> datesOfMonthList = FastList.newList(dateStream.collect(Collectors.toList()));

		System.out.printf("All days in list are from January[%b], the 31st day of month is in that list[%b]%n",
				datesOfMonthList.allSatisfy(day -> day.getMonth() == Month.JANUARY),
				datesOfMonthList.anySatisfy(day -> day.getDayOfMonth() == 31));

		final MutableBag<DayOfWeek> dayOfWeekBag = datesOfMonthList.countBy(LocalDate::getDayOfWeek);
		System.out.printf("Between [%s] and [%s] there were [%d] Mondays.%n", SOURCE_DATE, monthEnd,
				dayOfWeekBag.occurrencesOf(DayOfWeek.MONDAY));
		final MutableListMultimap<DayOfWeek, LocalDate> daysToWeekdays = datesOfMonthList
				.groupBy(LocalDate::getDayOfWeek);
		final MutableList<LocalDate> mondaysList = daysToWeekdays.get(DayOfWeek.MONDAY);
		System.out.printf("The Mondays%s%n", mondaysList);
		System.out.printf("Bag with weekdays %s%n", dayOfWeekBag.toStringOfItemToCount());
		System.out.println("- ".repeat(50));
	}

	/**
	 * Researches using numbers.<br>
	 * Used here are: UnifiedMap, MutableOrderedMap, OrderedMaps, FastList,
	 * MutableList, RichIterable, PartitionMutableList, IntInterval.
	 * 
	 */
	public static void researchUsingNumbers() {

		final MutableOrderedMap<Integer, String> orderedMap = OrderedMaps.adapt(new LinkedHashMap<>());
		orderedMap.putAll(UnifiedMap.newWithKeysValues(4, "d", 3, "c", 1, "a", 2, "b"));
		System.out.printf("OrderedMap %s%n%n", orderedMap);

		final FastList<Integer> srcList = FastList.newListWith(6, 6, 5, 4, 1, 2, 3, 3);
		final MutableList<Integer> distinctList = srcList.toSortedList().distinct();
		System.out.printf("Source list%s, distinct list%s%n", srcList, distinctList);
		System.out.printf("Between 2 and 5: selected list%s, rejected list%s%n",
				distinctList.select(Predicates.betweenInclusive(2, 5)),
				distinctList.reject(Predicates.betweenInclusive(2, 5)));

		final List<Integer> modulo2List = distinctList.select(arg -> arg % 2 == 0);
		final RichIterable<Integer> iterableSelected = distinctList.selectWith((arg1, arg2) -> arg1 % arg2 == 0,
				Integer.valueOf(3));
		final RichIterable<Integer> iterableRejected = distinctList.rejectWith((arg1, arg2) -> arg1 % arg2 == 0,
				Integer.valueOf(3));

		System.out.printf("Modulo 2: list%s. Modulo 3: selected list%s, rejected list%s%n", modulo2List,
				iterableSelected, iterableRejected);

		final PartitionMutableList<Integer> partitionedList = distinctList
				.partition(number -> number == 2 || number == 3 || number == 5 || number == 7);
		System.out.printf("Partition with prime numbers: selected list%s, rejected list%s%n%n",
				partitionedList.getSelected(), partitionedList.getRejected());

		System.out.printf("Intervals(from 1 to 10): by 3 %s, odds%s, evens%s%n", IntInterval.fromToBy(1, 10, 3),
				IntInterval.oddsFromTo(1, 10), IntInterval.evensFromTo(1, 10));
		System.out.println("- ".repeat(50));
	}

	/**
	 * This method has intentionally all declarations unused.<br>
	 * These examples were taken from <a
	 * href=https://www.eclipse.org/collections/>https://www.eclipse.org/collections/</a>
	 * 
	 */
	@SuppressWarnings("unused")
	private void declareLocalVariables() {

		MutableList<String> mutableListEmpty = Lists.mutable.empty();
		MutableList<String> mutableListOf = Lists.mutable.of("One", "One", "Two", "Three");
		MutableList<String> mutableListWith = Lists.mutable.with("One", "One", "Two", "Three");
		MutableSet<String> mutableSet = Sets.mutable.with("One", "One", "Two", "Three");
		MutableBag<String> mutableBag = Bags.mutable.with("One", "One", "Two", "Three");
		MutableStack<String> mutableStack = Stacks.mutable.with("One", "One", "Two", "Three");
		MutableMap<String, String> mutableMap = Maps.mutable.with("key1", "value1", "key2", "value2", "key3", "value3");
		MutableMultimap<String, String> multimapWithList = Multimaps.mutable.list.with("key1", "value1-1", "key1",
				"value1-2", "key2", "value2-1");
		MutableBiMap<String, String> mutableBiMap = BiMaps.mutable.with("key1", "value1", "key2", "value2", "key3",
				"value3");

		ImmutableList<String> immutableListEmpty = Lists.immutable.empty();
		ImmutableList<String> immutableListOf = Lists.immutable.of("One", "One", "Two", "Three");
		ImmutableList<String> immutableListWith = Lists.immutable.with("One", "One", "Two", "Three");
		ImmutableSet<String> immutableSet = Sets.immutable.with("One", "One", "Two", "Three");
		ImmutableBag<String> immutableBag = Bags.immutable.with("One", "One", "Two", "Three");
		ImmutableStack<String> immutableStack = Stacks.immutable.with("One", "One", "Two", "Three");
		ImmutableMap<String, String> immutableMap = Maps.immutable.with("key1", "value1", "key2", "value2", "key3",
				"value3");
		ImmutableMultimap<String, String> immutableMultimapWithList = Multimaps.immutable.list.with("key1", "value1-1",
				"key1", "value1-2", "key2", "value2-1");
		ImmutableBiMap<String, String> immutableBiMap = BiMaps.immutable.with("key1", "value1", "key2", "value2",
				"key3", "value3");

		MutableIntList intList = IntLists.mutable.of(1, 2, 3);
		MutableLongList longList = LongLists.mutable.of(1L, 2L, 3L);
		MutableCharList charList = CharLists.mutable.of('a', 'b', 'c');
		MutableShortList shortList = ShortLists.mutable.of((short) 1, (short) 2, (short) 3);
		MutableByteList byteList = ByteLists.mutable.of((byte) 1, (byte) 2, (byte) 3);
		MutableBooleanList booleanList = BooleanLists.mutable.of(true, false);
		MutableFloatList floatList = FloatLists.mutable.of(1.0f, 2.0f, 3.0f);
		MutableDoubleList doubleList = DoubleLists.mutable.of(1.0, 2.0, 3.0);
	}
}