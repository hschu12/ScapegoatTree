import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		int i;
		int size = 0;
		int pointerusedRange = 20;
		int[] pointersUsedForSearch = new int[pointerusedRange];


		/***********************************************************
		* Test of Average Search Complexity                        *
		* In-Order (Ascending) input.                              *
		***********************************************************/
/*
		System.out.println("Average - In-Order");

		ArrayList<Integer> inOrderAverageList = new ArrayList<Integer>();
		for( i = 1; i <= 1000; i++) {
			inOrderAverageList.add(i);
		}

		ScapegoatTree inOrderAverageScape;

		for (int j = 1; j <= 5; j++ ) {
			System.out.println();
			System.out.println("Run: " + j);

			while( size < 950 ) {
				size = size + 50;
				inOrderAverageScape = new ScapegoatTree(0.5, true);
				double acumulatedPointers = 0;
				System.out.print("Size of n = " + size);

				for (i = 0; i < size ; i++) {
					inOrderAverageScape.insert(inOrderAverageList.get(i));
				}

				for (i = 0; i < 10000; i++) {
					int x = (int) (Math.random() * size);
					inOrderAverageScape.search(x);
					acumulatedPointers = acumulatedPointers + inOrderAverageScape.pointersused;
				}
				//System.out.println(acumulatedPointers);
				
				double averagePointersUsed = acumulatedPointers / 10000;
			
				System.out.println("\t Average: " + averagePointersUsed);
			}
			size = 0;
		}
*/		
		/***********************************************************
		* Reverse-Order (Descending) input.                        *
		***********************************************************/
/*
		System.out.println("Average - Descend-Order");

		ArrayList<Integer> descendAverageOrderList = new ArrayList<Integer>();
		for( i = 1000; i >= 1; i--) {
			descendAverageOrderList.add(i);
		}
		
		ScapegoatTree descendAverageOrderScape;

		for (int j = 1; j <= 5; j++ ) {
			System.out.println();
			System.out.println("Run: " + j);

			while( size < 950 ) {
				size = size + 50;
				descendAverageOrderScape = new ScapegoatTree(0.5, true);
				double acumulatedPointers = 0;
				System.out.print("Size of n = " + size);

				for (i = 0; i < 1000; i++) {
					descendAverageOrderScape.insert(descendAverageOrderList.get(i));
				}

				for (i = 0; i < 10000; i++) {
					int x = (int) (Math.random() * size);
					descendAverageOrderScape.search(x);
					acumulatedPointers = acumulatedPointers + descendAverageOrderScape.pointersused;
				}
				//System.out.println(acumulatedPointers);
				
				double averagePointersUsed = acumulatedPointers / 10000;
			
				System.out.println("\t Average: " + averagePointersUsed);
			}
			size = 0;
		}
*/
		/***********************************************************
		* Permuted input. Check number of pointers used per search *
		***********************************************************/
/*
		System.out.println("Average - Permute-Order");

		ArrayList<Integer> permutedAverage = new ArrayList<Integer>();
		for (i = 0; i < 1000; i++) {
			permutedAverage.add(i);
		}
		Collections.shuffle(permutedAverage);

		ScapegoatTree permutedAverageScape;

		for (int j = 1; j <= 5; j++ ) {
			System.out.println();
			System.out.println("Run: " + j);

			while( size < 950 ) {
				size = size + 50;
				permutedAverageScape = new ScapegoatTree(0.5, true);
				double acumulatedPointers = 0;
				System.out.print("Size of n = " + size);

				for (i = 0; i < 1000; i++) {
					permutedAverageScape.insert(permutedAverage.get(i));
				}

				for (i = 0; i < 10000; i++) {
					int x = (int) (Math.random() * size);
					permutedAverageScape.search(x);
					acumulatedPointers = acumulatedPointers + permutedAverageScape.pointersused;
				}
				//System.out.println(acumulatedPointers);
				
				double averagePointersUsed = acumulatedPointers / 10000;
			
				System.out.println("\t Average: " + averagePointersUsed);
			}
			size = 0;
		}
*/
		/***********************************************************
		* Test of Variation in search complexity                   *
		* In-Order (Ascending) input.                              *
		***********************************************************/
/*
		System.out.println("Ascending order list");

		ArrayList<Integer> inOrderList = new ArrayList<Integer>();
		for( i = 1; i <= 1000; i++) {
			inOrderList.add(i);
		}

		ScapegoatTree inOrderScape = new ScapegoatTree(0.5, true);

		for (i = 0; i < 1000; i++) {
			inOrderScape.insert(inOrderList.get(i));
		}

		for ( int j = 1; j <= 5; j++) {

			System.out.println("Run: " + j);
			System.out.println();

			for ( i = 0; i < pointerusedRange; i++ ) {
				pointersUsedForSearch[i] = 0;
			}

			for (i = 0; i < 10000; i++) {
				int x = (int) (Math.random() * 1000);
				inOrderScape.search(x);
				pointersUsedForSearch[inOrderScape.pointersused]++;
			}
			for ( i = 0; i < pointerusedRange; i++ ) {
				//System.out.println("There are " + pointersUsedForSearch[i] + " that uses " + i + " pointer(s)");
				System.out.println(pointersUsedForSearch[i]);
			}
		}
*/
		/***********************************************************
		* Reverse-Order (Descending) input.                        *
		***********************************************************/
/*		
		System.out.println();
		System.out.println("Descending order list");

		ArrayList<Integer> descendOrderList = new ArrayList<Integer>();
		for( i = 1000; i >= 1; i--) {
			descendOrderList.add(i);
		}
		
		ScapegoatTree descendOrderScape = new ScapegoatTree(0.5, true);

		for (i = 0; i < 1000; i++) {
			descendOrderScape.insert(descendOrderList.get(i));
		}

		for (int j = 1; j<=5 ; j++) {

			System.out.println("Run: " + j);
			System.out.println();

			for ( i = 0; i < pointerusedRange; i++ ) {
				pointersUsedForSearch[i] = 0;
			}

			for (i = 0; i < 10000; i++) {
				int x = (int) (Math.random() * 1000);
				descendOrderScape.search(x);
				pointersUsedForSearch[descendOrderScape.pointersused]++;
			}
			for ( i = 0; i < pointerusedRange; i++ ) {
				//System.out.println("There are " + pointersUsedForSearch[i] + " that uses " + i + " pointer(s)");
				System.out.println(pointersUsedForSearch[i]);
			}	
		}
*/
		/***********************************************************
		* Permuted input. Check number of pointers used per search *
		***********************************************************/
/*
		System.out.println();
		System.out.println("Permuted list");

		ArrayList<Integer> permuted = new ArrayList<Integer>();
		for (i = 0; i < 1000; i++) {
			permuted.add(i);
		}
		Collections.shuffle(permuted);

		ScapegoatTree permutedScape = new ScapegoatTree(0.5, true);

		for (i = 0; i < 1000; i++) {
			permutedScape.insert(permuted.get(i));
		}

		for (int j = 1; j <= 5 ; j++) {

			System.out.println("Run: " + j);	
			System.out.println();

			for ( i = 0; i < pointerusedRange; i++ ) {
				pointersUsedForSearch[i] = 0;
			}

			for (i = 0; i < 10000; i++) {
				int x = (int) (Math.random() * 1000);
				permutedScape.search(x);
				pointersUsedForSearch[permutedScape.pointersused]++;
			}
			for ( i = 0; i < pointerusedRange; i++ ) {
				//System.out.println("There are " + pointersUsedForSearch[i] + " that uses " + i + " pointer(s)");
				System.out.println(pointersUsedForSearch[i]);
			}
		}	
*/		

		/***********************************************************
		* How many rebuild are performed and what are the sizes    *
		* of the trees involved.                                   *
		* In-Order input.                                          *
		***********************************************************/
	}
}