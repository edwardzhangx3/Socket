import java.util.*;
/*
Memoization is a common strategy for dynamic programming problems, which are problems where the solution is composed of solutions to the same problem with smaller inputs (as with the Fibonacci problem, above).

**/
class momo_example {
	//Fibonacci number -> a very common recursion case
	
	//recursion method
	public static int fib(int n){
		if (n == 0 || n == 1){
			return n;
		}
		System.out.printf("computing fib(%d)\n", n);
		return fib(n - 1) + fib(n - 2);
	}
	
	//revurion with memorization
	//Store the duplicates
	private static Map<Integer, Integer> map = new HashMap<>();
	public static int fib_memo(int n){
		
		if (n == 0 || n == 1) return n;
		
		if (map.containsKey(n)){
			System.out.printf("grabbing memo[%d]\n", n);
			return map.get(n);
		}
		System.out.printf("computing fib_memo(%d)\n", n);	
		int res = fib_memo(n - 1) + fib_memo(n - 2);
		
		map.put(n, res);
		
		return res;
		
	}
	
	public static void main(String[] args) {
		System.out.println(fib(6));
		System.out.println(fib_memo(6));
	}
}