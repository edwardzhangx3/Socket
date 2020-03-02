class low_latency {
	public static void main(String[] args) {
		//For 10m records this is taking ~40–45ms for the array, but ~130–135ms for the HashMap.
		for (int i = 0; i < size; i++) {
			total += array[i];
		}
		// B) iterating over a HashMap
		for (Long value : hashmap.values()) {
			total2 += value;
		}
		// output totals to avoid JIT eliminating the need to run the loops
		System.out.println(“output1=” + total);
		System.out.println(“output2=” + total2);
		
		
		//If I run the above code for 1m records I get ~1050ms for TEST1 and ~35ms for TEST2.
		//create a trivial object as the input to the test
		String testString = “Hello World”;
		//initialising google’s JSON serialiser/deserialiser
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		StringBuilder buf = new StringBuilder();
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			// TEST1: serialise/deserialise string then append to the buffer
			buf.append(gson.fromJson(gson.toJson(testString), String.class));
		}
		System.out.println(“time=” +(System.currentTimeMillis()-start));
		StringBuilder buf2 = new StringBuilder();
		long start2 = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
		// TEST 2: simply append the test string to the buffer
			buf2.append(testString);
		}
		System.out.println(“time=”+(System.currentTimeMillis()-start2));
		// to force JIT not to optimise the loops above out of existence
		// the substring avoids messing overloading the console
		System.out.println(“output 1=” + buf.substring(5000, 5005));
		System.out.println(“output 2=” + buf2.substring(5000, 5005));
		
	}
}