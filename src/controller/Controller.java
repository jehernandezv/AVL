package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Node;
import models.Tree;
import views.WindowTree;

public class Controller implements ActionListener{
	
	private Tree tree;
	private WindowTree window;

	public Controller() {		
		tree = new Tree();
		window = new WindowTree(this);
		window.paintTree(tree.getRoot());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			Node node = new Node(Integer.valueOf(JOptionPane.showInputDialog("id")));	
			this.validateRotation(node);
		}else if(e.getActionCommand().equals("DELETE")){
			tree.delete(Integer.parseInt(JOptionPane.showInputDialog("Información del nodo a borrar")));
		}
		window.paintTree(tree.getRoot());
	}
	
	public void validateRotation(Node node){
		if(tree.getRoot() == null){
			tree.add(node);
		}else if(tree.getRoot().calculateBalance() < -1 || tree.getRoot().calculateBalance() > 1) {
			tree.rotation(tree.getRoot());
			tree.add(node);
		}else if(tree.getRoot().calculateBalance() > -2 && tree.getRoot().calculateBalance() < 2){
			tree.add(node);
		}
	}
}