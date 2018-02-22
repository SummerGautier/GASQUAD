import java.util.ArrayList;

public class Generation {

	private ArrayList<Fruit> fruits;
	private String[] genes = {
			"CCCCCCCCCCCCCCCCCCCCCC",
			"CCGCCCCCCCCCCCCCCCCCCC",
			"ACTAGTCTACGCTACAGGTTGC",
			"TGTAGCTGATCGTAGCTAGCTG",
			"TACGATGCTGATGCTGTGTGGT",
			"ATATCGATCTAGCTCGTTAGCT",
			"ATCGATCGTAGTCGATCGTAGC",
			"TGATCGTAGCTGAGTCTAGCTG",
			"TAGCTGACTACGTGATGCGATG",
			"CACTGATATGCTATTAGCTACT",
			"TAGCTGATCGTAGCTGTAGCTG",
			"ACTGATCGTGACTATGCTGACT",
			"GTAGCTGATGCTACTGATCTAC",
			"GTAGTCGTAGCTATCGTAGCTA",
			"TCGGATCGTAGCTATCTAGTCA",
	};
	private String[] colorGenes = {
		"AGTCGATCGAGCTCGAAAGAGAGTCCC",
		"AAAAAAAAAAAAAAAAAAAAAAAAACGC",
		"GGGCTCGCTTTACGCGGGCTAGTAGAGC",
		"GCGCGCGCTAGATCAAAAAAAGAGGGGG",
		"GGGCGGGAGGGTGGGCGGGAGGGTGGGC",
		"ATGTCGATGCATGCATCGATCGATCGAC",
		"GCGCGCGCTAGCGAAAATCGCCGCGGGA",
		"GGGGGGGGGGGGTTTTTTTTTTTTTTTT",
		"AGTCAGCTCTCGAAGCTCGGGCGAAAAA",
		"AGAGATTTTATTCGCGCGCCCCCCCCCC",
		"CCCCCCCCCCCCCCCCCCCCCCCCCCCC",
		"AAAAAAAAAAACCCCCCTTTTTTATTTT",
		"AGCTTTTCGAGCTATAGCTAGGCTAGCT",
		"ATTTCGATCGTGCTAGCGGGGGGGAAAA",
		"AGATAGCCCCCCCCCCCCCCCCCCCAAA"
	};
	public Generation() {
		fruits = new ArrayList<Fruit>();
		for(int i=0;i<genes.length;i++) {
			fruits.add(new Fruit(genes[i],colorGenes[i]));
		}
	}
	public ArrayList<Fruit> getFruits() {
		return this.fruits;
	}

	public void generate() {
		ArrayList<Fruit> newFruits = new ArrayList<Fruit>();
		for(Fruit fruit: this.fruits) {
			newFruits.add(new Fruit(fruit.getMutationClass().getMutatedCode(fruit.getCode()),fruit.getMutationClass().getMutatedCode(fruit.getMutationClass().getColorCode())));
		}
		this.fruits = newFruits;
	}
	public void killPainfullyAndSlowly(int index) {
		try {
			this.fruits.remove(index-1);
		}catch(IndexOutOfBoundsException e) {
			//NO EXCEPTION FOR YOU.
		}
	}
}

