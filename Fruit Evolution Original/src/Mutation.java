import java.awt.Color;
import java.util.Random;

class Mutation {
	private String seed;
	private String seedColor;
	private long b,c;
	private int n,n1;
	public Mutation(String seedSize, String seedColor){
		this.n= 0;
		this.seed = seedSize;
		this.seedColor = seedColor;
		String s = "";
		for(int i=0;i<seedSize.length();i++) {
			switch(seedSize.charAt(i)) {
				case 'A':
					s+="00";
					break;
				case 'T':
					s+="01";
					break;
				case 'C':
					s+="10";
					break;
				case 'G':
					s+="11";
					break;
			}
		}
		b = Long.parseLong(s,2);
		s = "";
		for(int i=0;i<seedColor.length();i++) {
			switch(seedColor.charAt(i)) {
				case 'A':
					s+="00";
					break;
				case 'T':
					s+="01";
					break;
				case 'C':
					s+="10";
					break;
				case 'G':
					s+="11";
					break;
			}
		}
		c = Long.parseLong(s,2);
	}
	public int nextInt(int max){
		return Math.abs((int) (b>>(n++))%max);
	}
	public String getColorCode() {
		return this.seedColor;
	}
	public Color getColor(int s){
		return new Color( Math.abs((int) (c>>(n1++))%255), Math.abs((int) (c>>(n1++))%255), Math.abs((int) (c>>(n1++))%255));
	}
	public static double getNutrition(Fruit f, int averageNumberOfCells) {
		return (averageNumberOfCells-f.len)/f.len;
	}
	public String getMutatedCode(String s1){
		String s = s1;
		Random r = new Random();
		int i = r.nextInt(4);
		int n = r.nextInt(s.length());
		String[] chars = {"A","C","G","T"};
		s = s.substring(0,n)+chars[i]+s.substring(n+1);
		return s;
	}
}
