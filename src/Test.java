

import java.util.Random;

public class Test {

	static long sequence = 1;
	
	
	public static void main(String[] args) {
		StringBuilder b = new StringBuilder();
		
		Random random = new Random();
		
		for (int i = 0; i < 25; i++) {
			int num = random.nextInt(4);
			if (num == 0) {
				addGene(Gene.A);
				b.append("A");
			}
			if (num == 1) {
				addGene(Gene.T);
				b.append("T");

			}
			if (num == 2) {
				addGene(Gene.C);
				b.append("C");
			}
			if (num == 3) {
				addGene(Gene.G);
				b.append("G");
			}
		}
		
		System.out.println("\n\n" + b);
		System.out.println(Long.toString(sequence, 2).substring(1));
	
	}
	
	
	
	
	
	public static void addGene(Gene g) {
		if (g == Gene.A) {
			System.out.println("A");
		}
		if (g == Gene.T) {
			System.out.println("T");
		}
		if (g == Gene.C) {
			System.out.println("C");
		}
		if (g == Gene.G) {
			System.out.println("G");
		}

		
		StringBuilder b = new StringBuilder();		

		if (!(sequence == 0)) {
			b.append(Long.toString(sequence, 2));
		}
		
		System.out.println(g + " adding to " + b);
		
		b.append(g.toString());
		
		System.out.println("--> " + b + "\n");
		
		sequence = Long.parseLong(b.toString(), 2);
		
		
	}
    
	
	
	
	private enum Gene { 
		A {
			public String toString() {
				return "00";
			}
		},
		T {
			public String toString() {
				return "11";
			}
		},
		C {
			public String toString() {
				return "01";
			}

		},
		G {
			public String toString() {
				return "10";
			}
		}
	};
	
}
