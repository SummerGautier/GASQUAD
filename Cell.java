public class Cell{

	private int x;
	private int y;
	private Mutation m;
	private Fruit parentFruit;
	public static int BASE_UNIT = 15;
	
	public Cell(Fruit parentFruit, int x,int y) {

		this.m = parentFruit.getMutationClass();
		this.x = x;
		this.y = y;
		this.parentFruit = parentFruit;
	}
	public void replicate() {
		int newX = x;
		int newY = y;
		newX+=this.m.nextInt(2*BASE_UNIT)-BASE_UNIT;
		newY+=this.m.nextInt(2*BASE_UNIT)-BASE_UNIT;
		if(this.parentFruit.hasCancer())
			return;
		for(int i=0;i<this.m.nextInt(20)-1;i++) {
			Cell c2 = new Cell(this.parentFruit,newX,newY);
			this.parentFruit.addCell(c2);
		}
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}
