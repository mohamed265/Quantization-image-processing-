package Quantizer_MY;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class GUI extends JFrame implements ActionListener {
	JFileChooser fc;
	TextField name, ext, lev;
	JButton browes, sav;
	File file = null;

	GUI() {
		super("Quantizer");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = 400, height = 175;
		setBounds((1300 - width) / 2, (760 - height) / 2, width, height);
		setResizable(false);
		setLayout(null);
		JPanel pan = new JPanel();

		fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean accept(File f) {
				if (f.getName().contains(".jpg")
						|| f.getName().contains(".gif")
						|| f.getName().contains(".png"))
					return true;
				return false;
			}
		});

		JLabel lname = new JLabel("Name");
		pan.add(lname);
		add(lname);
		lname.setVisible(true);
		lname.setBounds(20, 10, 50, 25);

		name = new TextField();
		name.setVisible(true);
		name.setBounds(20 + 50 + 10, 10, 150, 25);
		pan.add(name);
		add(name);

		JLabel lextention = new JLabel("Extention");
		pan.add(lextention);
		add(lextention);
		lextention.setVisible(true);
		lextention.setBounds(20, 40, 60, 25);

		ext = new TextField();
		ext.setVisible(true);
		ext.setBounds(20 + 50 + 10, 40, 150, 25);
		pan.add(ext);
		add(ext);

		JLabel llevel = new JLabel("Level");
		pan.add(llevel);
		add(llevel);
		llevel.setVisible(true);
		llevel.setBounds(20, 70, 60, 25);

		lev = new TextField();
		lev.setVisible(true);
		lev.setBounds(20 + 50 + 10, 70, 150, 25);
		pan.add(lev);
		add(lev);

		browes = new JButton("Browes");
		pan.add(browes);
		add(browes);
		browes.setVisible(true);
		browes.setBounds(260, 40, 100, 30);
		browes.addActionListener(this);

		sav = new JButton("Save");
		pan.add(sav);
		add(sav);
		sav.setVisible(true);
		sav.setBounds(165, 105, 70, 30);
		sav.addActionListener(this);
		sav.setEnabled(false);

		add(pan);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {

		if (ev.getSource() == browes) {
			int res = fc.showSaveDialog(this);
			if (res == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				sav.setEnabled(true);
				name.setText(file.getName().substring(0,
						file.getName().indexOf(".")));
				ext.setText(file.getName().substring(
						file.getName().indexOf(".") + 1));
				lev.setText("8");
				try {
					new Prev(ImageIO.read(file), "Original");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			String newPath = file.getAbsolutePath().substring(0,
					file.getAbsolutePath().indexOf(file.getName()));
			try {
				long MSE = QuantizerMain.compress(file.getAbsolutePath(),
						Integer.parseInt(lev.getText()), ext.getText(), newPath + name.getText() + " - Output - level " + lev.getText());
				JOptionPane.showMessageDialog(null,
						"MES is " + MSE, "MES",
						JOptionPane.ERROR_MESSAGE);
				// new Prev(ImageIO.read(new File(newPath + "." +
				// ext.getText())));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			sav.setEnabled(false);
//			fc.setSelectedFile(null);
//			name.setText("");
//			ext.setText("");
//			lev.setText("");
		}

	}
}
