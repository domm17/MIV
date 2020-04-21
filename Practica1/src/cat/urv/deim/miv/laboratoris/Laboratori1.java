package cat.urv.deim.miv.laboratoris;

import cat.urv.deim.miv.Application;

public class Laboratori1 extends Application {

	public static final long serialVersionUID = 1L;
	
	public Laboratori1() {
		super("Laboratori 1");
	}

	public void paint() {

		int width = getPanelWidth();
		int height = getPanelHeight();
		
		int nlines = 10;
		int curWidth = 0;
		int curHeight = 0;

		for(int i = 0; i < nlines; i++) {
			setColor((float) i / nlines, 1.0f - (float) i / nlines, 0.0f);
			drawLine(0, curHeight, width - curWidth, 0);
			curWidth = width * i / nlines;
			curHeight = height * i / nlines;
		}
		/*

		//JOC DE PROVES

		setColor((float) 2 / nlines, 1.0f - (float) 2 / nlines, 0.0f);
		defineDrawLine(200,300,200,0); //vertex dalt baix
		defineDrawLine(250,0,250,300);//vertex baix dalt


		setColor((float) 5 / nlines, 1.0f - (float) 5 / nlines, 0.0f);

		defineDrawLine(0,200,400,200); //horitzontal dreta esquerra
		defineDrawLine(400,250,0,250);  //horitzontal esquerra dreta

		setColor((float) 9 / nlines, 1.0f - (float) 9 / nlines, 0.0f);

		defineDrawLine(0,0,300,300);  //pendent=1
		defineDrawLine(300,300,600,0);  //pendent=1

		setColor((float) 1 / nlines, 1.0f - (float) 1/ nlines, 0.0f);
		defineDrawLine(300,300,700,0);  //<1
		defineDrawLine(750,0,300,300);  //<1

		setColor((float) 7 / nlines, 1.0f - (float) 7/ nlines, 0.0f);
		defineDrawLine(300,300,450,0);
		defineDrawLine(500,0,300,300);

		*/




	}

	// Practica 1, implementa defineDrawLine
	// Hint: Pots utilitzar l'algorisme de Bresenham
	// Hint: Per dibuixar un punt a la pantalla utilitza el metode drawPoint(x, y);
	
	// Inici codi de l'alumne
	
	public void defineDrawLine(int x1, int y1, int x2, int y2) {
		// TODO: has de ficar aqui el codi!

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


		x = x1; //Punts inicials
		y = y1;

		drawPoint(x,y);		//Pintem primer punt de la recta

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
				drawPoint(x,y);
			}
		}

	}

	// Fi codi de l'alumne
	public static void main(String[] args) {
		Laboratori1 example = new Laboratori1();
		example.run();

	}

}
