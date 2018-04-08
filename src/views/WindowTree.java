package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import models.Node;

public class WindowTree extends JFrame{

	private static final long serialVersionUID = 1L;
	private Node root;

	public WindowTree(ActionListener listener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setBackground(Color.WHITE);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(listener);
		btnAdd.setActionCommand("ADD");
		add(btnAdd, BorderLayout.PAGE_START);
		
		JButton btnDelete = new JButton("-");
		btnDelete.addActionListener(listener);
		btnDelete.setActionCommand("DELETE");
		add(btnDelete, BorderLayout.PAGE_END);
		
		JButton btnRotate = new JButton("---->");
		btnRotate.addActionListener(listener);
		btnRotate.setActionCommand("Rotate");
		add(btnRotate, BorderLayout.EAST);
		setVisible(true);
	}

	public void paintTree(Node root) {
		this.root = root;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (root != null) {
			addNode(root, 0, getContentPane().getWidth() / 2, 100, g);
		}
	}

	public void addNode(Node node, int x, int width, int height, Graphics g) {
		if (node != null) {
			g.fillOval(x + width, height, 40, 40);
			g.setColor(Color.WHITE);
			g.drawString(node.toString(), x + width + 17, height + 23);
			g.setColor(Color.BLACK);
			g.drawString("FB: "+ String.valueOf(node.calculateBalance()), x + width, height);
			g.setColor(Color.BLACK);
			addNode(node.getLeft(), x + width, -1 * (Math.abs(width) / 2), height + 50, g);
			//g.drawLine(x + width, height,x + width , (Math.abs(width)/2) + height + 50);
			addNode(node.getRight(), x + width, (Math.abs(width) / 2), height + 50, g);
		}
	}
}