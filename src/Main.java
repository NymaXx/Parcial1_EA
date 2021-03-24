import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			PApplet.main(Main.class.getName());
		}
		
		

	comunicacionTCP comm;
	private String[] arregloRec;
	private ArrayList<Recordatorio> list;

	public void settings() {
		size(1000,700);
	}

	public void setup() {
		list = new ArrayList<Recordatorio>();
		comm = new comunicacionTCP(this);
		comm.esperarConexion();

	}

	public void draw() {
		background(255);

		for (int i = 0; i < list.size(); i++) {
			fill(255);
			rectMode(CENTER);
			rect(list.get(i).getX(), list.get(i).getY(), 300, 100);

			fill(0);
			textAlign(CENTER, CENTER);
			textSize(20);
			text(list.get(i).getRecordatorio(), list.get(i).getX(), list.get(i).getY());

			if (list.get(i).getImportancia().equals("1")) {
				fill(0, 255, 0);

			} else if (list.get(i).getImportancia().equals("2")) {
				fill(255, 255, 0);

			} else if (list.get(i).getImportancia().equals("3")) {
				fill(255, 0, 0);
				
			} else {
				fill(155);

			}

			ellipse(list.get(i).getX(), list.get(i).getY() - 50, 50, 50);
		}

		fill(0);
		textAlign(CENTER, CENTER);
		textSize(20);
		text("Recordatorios creados: " + list.size(), 150, 30);

	}

	public String[] getArregloRec() {
		return arregloRec;
	}

	public void setArregloRec(String[] arregloRec) {
		this.arregloRec = arregloRec;
	}

	public ArrayList<Recordatorio> getList() {
		return list;
	}

	public void setList(ArrayList<Recordatorio> list) {
		this.list = list;
	}

}
		

