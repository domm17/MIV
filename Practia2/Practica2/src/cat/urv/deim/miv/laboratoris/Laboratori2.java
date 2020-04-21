package cat.urv.deim.miv.laboratoris;

import cat.urv.deim.miv.Application;
import com.sun.tools.corba.se.idl.InterfaceGen;

import java.awt.image.BufferedImage;
import java.util.*;

public class Laboratori2 extends Application {

	public static final long serialVersionUID = 1L;
	List<Integer> puntsX=new LinkedList<>();
	List<Integer> puntsY=new LinkedList<>();
	int contador;
	int numArestes,arestaActual;

	public Laboratori2() {
		super("Laboratori 2");
		numArestes=5;
		arestaActual=0;
		contador=0;
	}

	public void paint() {
		int width = getPanelWidth();
		int height = getPanelHeight();

		setColor(1.0f, 0.0f, 0.0f);
		fillPolygon(
				(int) (0.2f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.1f * height),
				(int) (0.8f * width), (int) (0.4f * height),
				(int) (0.7f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.9f * height));

		setColor(0.0f, 1.0f, 0.0f);
		drawPolygon(
				(int) (0.2f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.1f * height),
				(int) (0.8f * width), (int) (0.4f * height),
				(int) (0.7f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.9f * height));
	}


	// Practica 2, implementa defineDrawPolygon i defineFillPolygon
	// Hint: Pots utilitzar l'algorisme Scan line fill polygon
	
	// Inici codi de l'alumne

	public void defineDrawPolygon(Integer... p) {
		// TODO: has de ficar aqui el codi!
		int width = getPanelWidth();
		int height = getPanelHeight();

		setColor(0.0f, 1.0f, 0.0f);
		int x1,x2,y1,y2;

		x1= (int) (0.2*width);
		x2= (int) (0.6*height);
		y1= (int) (0.4*width);
		y2= (int) (0.9*height);
		defineDrawLine(x1,x2,y1,y2,1);


		x1= (int) (0.7*width);
		x2= (int) (0.7*height);
		y1= (int) (0.4*width);
		y2= (int) (0.9*height);
		defineDrawLine(x1,x2,y1,y2,2);


		x1= (int) (0.8*width);
		x2= (int) (0.4*height);
		y1= (int) (0.7*width);
		y2= (int) (0.7*height);
		defineDrawLine(x1,x2,y1,y2,3);

		x1= (int) (0.3*width);
		x2= (int) (0.1*height);
		y1= (int) (0.8*width);
		y2= (int) (0.4*height);
		defineDrawLine(x1,x2,y1,y2,4);


		 x1= (int) (0.2*width);
		 x2= (int) (0.6*height);
		 y1= (int) (0.3*width);
		 y2= (int) (0.1*height);
		defineDrawLine(x1,x2,y1,y2,5);



	}

	public void defineFillPolygon(Integer... p) {
		// TODO: has de ficar aqui el codi!
		setColor(1.0f, 0.0f, 0.0f);

			int iniciX=0,iniciY=0,finalX=0,finalY=0;

			for(int a=0;a<contador-1;a++){
				iniciX=puntsX.get(a);
				iniciY=puntsY.get(a);

				for(int b=0;b<contador-1;b++){
					finalY=puntsY.get(b);
					if(finalY==iniciY){
						drawLine(iniciX,iniciY,puntsX.get(b),puntsY.get(b));
					}
				}

				/*for(int b=0;b<contador-1;b++){
					finalX=puntsX.get(b);
					if(finalX==iniciX){
						drawLine(iniciX,iniciY,puntsX.get(b),puntsY.get(b));
					}
				}*/

			}


	}


	public void defineDrawLine(int x1, int y1, int x2, int y2,int numAresta){

		arestaActual++;
		boolean reinicia=false;

		if(arestaActual>numAresta){
			puntsX.clear();
			puntsY.clear();
			contador=1;
			arestaActual=0;

		}
		int x, y, dx, dy;
		int P, incE, incNE, stepx, stepy;
		dx =x2 - x1;	//DeltaX
		dy =y2 - y1;	//DeltaY

		if (dx >= 0) { //Calculem sentit X
			stepx = 1;
		}else{
			dx =x1-x2;
			stepx = -1;
		}
		if (dy >= 0) {  //Calculem sentit Y
			stepy = 1;
		}else{
			dy =y1-y2;
			stepy = -1;
		}

		int a=0;
		int b=0;

		x = x1; //Punts inicials
		y = y1;


		drawPoint(x,y);		//Pintem primer punt de la recta


			puntsX.add(x);
			puntsY.add(y);
			contador++;



		if(dx>dy){
			P=2*dy - dx;
			incE = 2*dy;
			incNE = 2*(dy-dx);
			while (x != x2){
				x = x + stepx; //Desplacem columna
				if (P < 0){   //Segons constanP pintem pixel (x+1,y) o (x+1,y+1)
					P = P + incE;
				}
				else {
					y = y + stepy; //Desplacem fila
					P=P+incNE;
				}

					puntsX.add(x);
					puntsY.add(y);
					contador++;


				drawPoint(x,y);


			}
		}
		else{
			P=2*dx-dy;
			incE=2*dx;
			incNE=2*(dx-dy);
			while (y!=y2){
				y=y+stepy;
				if (P<0){
					P=P+incE;
				}
				else {
					x=x+stepx;
					P=P+incNE;
				}

					puntsX.add(x);
					puntsY.add(y);
					contador++;



				drawPoint(x,y);

			}
		}
	}

	// Fi codi de l'alumne
	
	public static void main(String[] args) {
		Laboratori2 example = new Laboratori2();
		example.run();
	}

}
