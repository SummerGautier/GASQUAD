import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;


public class Main
{
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static void run(Generation gg)
	{
		Generation ggg = gg;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Fruits");
		JPanel pnl2 = new JPanel();
		JPanel pnl = new JPanel();
		pnl2 = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Draw draw = new Draw();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JTextField f = new JTextField(8);
		pnl2.add(new JLabel("Enter numbers to kill seperated by spaces: "));
		pnl2.add(f);
		JButton btn = new JButton("Kill");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String[] s = f.getText().split(" ");
				for(int i=0;i<s.length;i++) {
					ggg.killPainfullyAndSlowly(Integer.parseInt(s[i]));
				}
			}
		});
		JButton btn2 = new JButton("Generate");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ggg.generate();
				run(ggg);
			}
		});
		
		JButton btn3 = new JButton("Fill in");
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if(ggg.getFruits().size()<15) {
					for(int i=0;i<15-ggg.getFruits().size();i++) {
						ggg.getFruits().add(new Fruit(ggg.getFruits().get(i).getMutationClass().getMutatedCode(ggg.getFruits().get(i).getCode()),ggg.getFruits().get(i).getMutationClass().getMutatedCode(ggg.getFruits().get(i).getMutationClass().getColorCode())));
					}
				}
			}
			
		});
		





/*		JButton btn4 = new JButton("Open");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String path = JOptionPane.showInputDialog("Enter file path");
				try {
					BufferedReader reader = new BufferedReader(new FileReader(path));
					String line = "";
					ArrayList<String> g1= new ArrayList<String>();
					ArrayList<String> g2 = new ArrayList<String>();
					while((line=reader.readLine())!=null) {
						g1.add(line.split(" ")[0]);
						g2.add(line.split(" ")[1]);
					}
					ggg.setGenes((String [])g1.toArray(), (String []) g2.toArray());
				} catch (IOException e) {
					actionPerformed(event);
					e.printStackTrace();
				}catch(NullPointerException e1) {
					actionPerformed(event);
				}
			}
			
		});
		JButton btn5 = new JButton("Save");
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String path = JOptionPane.showInputDialog("Enter file path");
				try {
					
				}catch(IOException e2) {
					
				}
			}
			
		});
		TOO MANY BUTTONS
		*/
		pnl2.add(btn);
		pnl2.add(btn2);
		pnl2.add(btn3);
		/*pnl2.add(btn4);
		pnl2.add(btn5);*/
		pnl2.setBackground(Color.gray);
		pnl.setBackground(Color.WHITE);
		pnl.add(pnl2);
		
		double sum = 1.0;
		for(Fruit fruit:ggg.getFruits()) {
			sum+=fruit.getCells().size();
		}
		for(Fruit fruit : ggg.getFruits())
		{
			fruit.addFirstCell(new Cell(fruit, 0, 0));
			fruit.setColor();
			fruit.nutrition = 100*(fruit.getCells().size())/(20*sum);
			draw.addFruit(fruit);
		}
		//moved so no fruits text is printed without fruits
		pnl.add(draw);
		frame.repaint();
		
		frame.add(pnl);
		frame.repaint();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		run(new Generation());
	}
}