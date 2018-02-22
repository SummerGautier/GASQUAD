import java.awt.Color;
import java.util.ArrayList;
public class Fruit {
	
	private ArrayList<Cell> cells;
	private Color color;
	private Mutation m;
	public int len;
	private String code;
	private String colorCode;
	public double nutrition;
	byte[] geneBinary ;
	public Fruit(String code,String colorCode) {
		this.cells = new ArrayList<Cell>();
		this.code = code;
		this.colorCode = colorCode;
		this.m = new Mutation(code,colorCode);
		this.len = 0;
		this.color = this.m.getColor(this.len);
	}
	
	public byte[] getGeneBinary() {
		geneBinary = new byte[code.length()*2];
		byte geneHold = 00;
		for(int y = 0; y < code.length(); y++) {
			char let = code.charAt(y);
			switch(let) {
			case 'A': 
				geneHold += 00;
			case 'G':
				geneHold += 01;
			case 'C':
				geneHold += 11;
			case 'T':
				geneHold += 10; 
			}
			if(y%8 == 0) {
				geneBinary[y/8] = geneHold;
				geneHold = 00;
			}
			
		}
		System.out.println(geneBinary);
		return geneBinary;
	}
	public Color setColor() {
		this.color = this.m.getColor(this.len);
		return getColor();
	}
	public Color getColor() {
		return this.color;
	}
	public String getCode() {
		return this.code;
	}
	public void addFirstCell(Cell c) {
		addCell(c);
		c.replicate();
	}
	public void addCell(Cell c) {
		this.len++;
		this.cells.add(c);
		for(int i=0;i<this.m.nextInt(4);i++)
			c.replicate();
	}
	public ArrayList<Cell> getCells(){
		return this.cells;
	}
	public Mutation getMutationClass() {
		return this.m;
	}
	public boolean hasCancer() {
		if(len>=200)
			return true;
		return false;
	}
	public String getColorCode() {
		return this.colorCode;
	}
}

