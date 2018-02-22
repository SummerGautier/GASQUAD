import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Draw extends JComponent{
	ArrayList<Integer> fruitsizes = new ArrayList<Integer>();
	ArrayList<Ellipse2D.Double> shapes = new ArrayList<Ellipse2D.Double>();
	ArrayList<Color> colors = new ArrayList<Color>();
	ArrayList<Integer> xcoor = new ArrayList<Integer>();
	ArrayList<Integer> ycoor = new ArrayList<Integer>();
	ArrayList<Double> nutrients = new ArrayList<Double>();
	ArrayList<String> genomes = new ArrayList<String>();
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		int current = 0;
		if (fruitsizes.size() == 0)
		{
			g2.setPaint(Color.BLACK);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g2.drawString("Extinction", (int)(Main.SCREEN_SIZE.getWidth() / 2) - 220, (int)(Main.SCREEN_SIZE.getHeight() / 2));
		}
		double[] nutriCount = new double[nutrients.size()];
		for (int j = 0; j < fruitsizes.size(); j++)	{
			for (int i = current; i < current + fruitsizes.get(j); i++)	{
				g2.setPaint(colors.get(i));
				g2.fill(shapes.get(i));
				g2.setPaint(Color.BLACK);
			}
			int i = current;
			g2.drawString("Fruit " + (j + 1) + ": "+nutrients.get(i)+" (" + xcoor.get(i) + ", " + ycoor.get(i) + ")", xcoor.get(i), ycoor.get(i));
			g2.drawString(genomes.get(i).split(" ")[0], xcoor.get(i), ycoor.get(i) - 10);
			g2.drawString(genomes.get(i).split(" ")[1], xcoor.get(i), ycoor.get(i) - 20);
			current = current + fruitsizes.get(j);
			nutriCount[i] = nutrients.get(i);
		}
		
		double s = 0;
		for(int u = 0; u < nutrients.size(); u++) {
			s += nutrients.get(u);
		}
		s /= nutrients.size();
		g2.drawString("Average Nutrition: " + s, 200, 50);
		
	}
	public Dimension getPreferredSize() {
		  return new Dimension((int) Main.SCREEN_SIZE.getWidth(), (int) Main.SCREEN_SIZE.getHeight()-75);
	}
	public void addShape(Ellipse2D.Double newshape, Color col)	{
		shapes.add(newshape);
		colors.add(col);
	}
	public void addCell(Fruit fruit, int cell, int xoffset, int yoffset){
		xcoor.add(fruit.getCells().get(cell).getX() + xoffset + (int)(Main.SCREEN_SIZE.getWidth() / 2.0));
		ycoor.add(fruit.getCells().get(cell).getY() + yoffset + (int)(Main.SCREEN_SIZE.getHeight() / 2.0));
		nutrients.add(fruit.nutrition);
		genomes.add(fruit.getCode()+" "+fruit.getColorCode());
		addShape(new Ellipse2D.Double(fruit.getCells().get(cell).getX() + xoffset + (Main.SCREEN_SIZE.getWidth() / 2.0), fruit.getCells().get(cell).getY() + yoffset + (Main.SCREEN_SIZE.getHeight() / 2.0), (double) Cell.BASE_UNIT, (double) Cell.BASE_UNIT), fruit.getColor());
	}
	public void addFruit(Fruit fruit){
		for (int j = 0; j < fruit.len; j++)	{
			int xoff = 0;
			int yoff = 0;
			xoff = ((fruitsizes.size() % 5) * 200) - 400;
			if (fruitsizes.size() <= 14)	{
				yoff = 200;
			}
			if (fruitsizes.size() <= 9)
			{
				yoff = 0;
			}
			if (fruitsizes.size() <= 4)
			{
				yoff = -200;
			}
			addCell(fruit, j, xoff, yoff);
		}
		fruitsizes.add(fruit.len);
	}
}