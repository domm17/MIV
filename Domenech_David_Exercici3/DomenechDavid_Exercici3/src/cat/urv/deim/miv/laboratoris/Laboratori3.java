package cat.urv.deim.gc.laboratoris;

import cat.urv.deim.miv.Application;
import cat.urv.deim.miv.laboratoris.Matriu;
import cat.urv.deim.miv.laboratoris.Vertex;


import java.util.*;

public class Laboratori3 extends Application {

	public static final long serialVersionUID = 1L;
	private float X=0.0F;
    private float Y=0.0F;

    private int angle;
	
	public Laboratori3() {
		super("Laboratori 3");
	}

	private void drawRectangle() {
		glBeginPolygon();
        glVertex3f(-0.5f, -0.5f, 0.0f);
        glVertex3f(-0.5f,  0.5f, 0.0f);
        glVertex3f( 0.5f,  0.5f, 0.0f);
        glVertex3f( 0.5f, -0.5f, 0.0f);
        glEndPolygon();
	}

    public void paint() {
        int width = getPanelWidth();
        int height = getPanelHeight();
        float aspect = width / (float) height;




        glViewport(0, 0, width, height);

        setColor(1.0f, 0.0f, 0.0f);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(60.0f, aspect, 0.1f, 1000.0f);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glTranslatef(0.0f, 0.0f, -5.0f);

        glPushMatrix();
        int midAngle = angle % 200;
        if (midAngle > 100)
            midAngle = 200 - midAngle;
        glTranslatef(0.0f, 0.0f, midAngle * -0.1f);
        setColor(0.0f, 1.0f, 0.0f);
        drawRectangle();
        glPopMatrix();

        glRotatef(angle, 0.0f, 1.0f, 0.0f);

        glPushMatrix();
        glRotatef(angle, 0.0f, 0.0f, 1.0f);
        glTranslatef(-2.0f, 0.0f, 0.0f);
        setColor(1.0f, 0.0f, 0.0f);
        drawRectangle();
        glPopMatrix();

        glPushMatrix();
        glRotatef(2.0f * angle, 0.0f, 0.0f, 1.0f);
        glTranslatef( 1.0f, 0.0f, 0.0f);
        setColor(0.0f, 0.0f, 1.0f);
        drawRectangle();
        glPopMatrix();

        angle += 5.0f;


    }


	// Practica 3, implementa infraestructura de matrius OpenGL

	// Hint: Pots mirar l'API d'OpenGL

	// Inici codi de l'alumne

    private  int mode=0;
    ArrayList<Integer> PuntsFinal;
    private Stack<Matriu> PilaProj = new Stack();
    private Stack<Matriu> PilaMV = new Stack();
    private int triangle=0;
    private int entrades=0;

    float a=0.3f;
    float b=0.3f;
    float c=0.3f;
    float d=0.3f;
    float e=0.3f;
    float f=0.3f;

    boolean restar=true;

    private int x;
    private int y;
    private int width;
    private int height;

    public Matriu iden(){

        Matriu m= new Matriu();
        float[] punts=m.valors();

        for(int i=0;i<punts.length;i++){
            punts[i]=0;
        }
        punts[0]=1.0F;
        punts[5]=1.0F;
        punts[10]=1.0F;
        punts[15]=1.0F;
        return m;
    }


    public void defineGlMatrixMode(int model) { //specify which matrix is the current matrix  GL_MODELVIEW or GL_PROJECTION
        // TODO: has de ficar aqui el codi!
        this.mode=mode;
        this.PilaProj.push(this.iden());
        this.PilaMV.push(this.iden());
    }
	
	public void defineGlViewport(int x, int y, int width, int height) { //set the viewport
		// TODO: has de ficar aqui el codi!
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
	
	public void defineGluPerspective(float fovy, float aspect, float zNear, float zFar) { //sets up a perspective projection matrix
		// TODO: has de ficar aqui el codi!

        Matriu m = new Matriu();

        double ymax, xmax;
        double temp, temp2, temp3, temp4;

        ymax = zNear * Math.tan( (double) fovy * 3.141592653589793D / 360.0);
        xmax = ymax * aspect;
        Perspective(m, -xmax, xmax, -ymax, ymax, zNear, zFar);

        pilaActual().push((this.pilaActual().pop()).multMatriu(m));

    }

    void Perspective(Matriu matrix, double left, double right, double bottom, double top, double znear, double zfar) {

        double temp, temp2, temp3, temp4;
        temp = 2.0 * znear;
        temp2 = right - left;
        temp3 = top - bottom;
        temp4 = zfar - znear;

        float[] punts=matrix.valors();
        punts[0]=(float) ((temp/temp2));
        punts[5]=(float) ((temp/temp3));
        punts[8]=(float) (((right + left) / temp2));
        punts[9]=(float) (((top + bottom) / temp3));
        punts[10]=(float) (((-zfar - znear) / temp4));
        punts[11]=-1.0F;
        punts[14]=1.0F;
        matrix.nousValors(punts);
    }


	public void defineGlLoadIdentity() { //replace the current matrix
		// TODO: has de ficar aqui el codi!

        pilaActual().push(this.iden());
        this.glScalef(a,b,c);

    }
	
	public void defineGlPushMatrix() {
		// TODO: has de ficar aqui el codi!

        Matriu m = this.pilaActual().lastElement();
        this.pilaActual().push(m);
    }
	
	public void defineGlPopMatrix() {
		// TODO: has de ficar aqui el codi!
        this.pilaActual().pop();
    }

    private Stack<Matriu> pilaActual() {
        if(this.mode==0){
            return this.PilaProj;
        }
        else{
            return  this.PilaMV;
        }
    }


    public void defineGlTranslatef(float x, float y, float z) {
		// TODO: has de ficar aqui el codi!

        Matriu m = this.iden();
        float[] punts=m.valors();
        punts[3]=x;
        punts[7]=y;
        punts[11]=z;
        m.nousValors(punts);
        m = (this.pilaActual().pop()).multMatriu(m);
        this.pilaActual().push(m);

    }
	
	public void defineGlScalef(float x, float y, float z) {
		// TODO: has de ficar aqui el codi!

        Matriu m = this.iden();
        float[] punts=m.valors();
        punts[0]=x;
        punts[5]=y;
        punts[10]=z;
        punts[15]=1.0F;

        m.nousValors(punts);
        m = (this.pilaActual().pop()).multMatriu(m);
        this.pilaActual().push(m);

    }
	
	public void defineGlRotatef(float angle, float x, float y, float z) {
		// TODO: has de ficar aqui el codi!

            double radians =angle*3.14/180;
            double cos =Math.cos(radians);
            double sin =Math.sin(radians);

            Matriu m = new Matriu();
            float[] punts=m.valors();

        punts[0]=(float)((x*x)*(1-cos)+cos);
        punts[1]=(float)((x*y)*(1-cos) - z*sin);
        punts[2]=(float)((x*z)*(1-cos) + y*sin);
        punts[3]=0.0F;
        punts[4]=(float)((y*x)*(1-cos) + z*sin);
        punts[5]=(float)((y*y)*(1-cos) +cos);
        punts[6]=(float)((y*z)*(1-cos) -x*sin);
        punts[7]=0.0F;
        punts[8]=(float)((x*z)*(1-cos) -y*sin);
        punts[9]=(float)((y*z)*(1-cos) +x*sin);
        punts[10]=(float)((z*z)*(1-cos) +cos);
        punts[11]=0.0F;
        punts[12]=0.0F;
        punts[13]=0.0F;
        punts[14]=0.0F;
        punts[15]=1.0F;

        m.nousValors(punts);

        m = pilaActual().pop().multMatriu(m);
        this.pilaActual().push(m);

    }
	
	public void defineGlBeginPolygon() {
		// TODO: has de ficar aqui el codi!
        triangle++;
        if(triangle==1){
            if(restar==true){
                d=d-0.025f;
                e=e-0.025f;
                f=f-0.025f;
                if(d<0.2){
                    restar=false;
                }
            }
            if(restar==false){
                d=d+0.025f;
                e=e+0.025f;
                f=f+0.025f;
                if(d>0.7){
                    restar=true;
                }
            }
            this.glScalef(d,e,f);
        }
        if(triangle==3){
            triangle=0;
        }
        this.PuntsFinal= new ArrayList<>();
    }
	
	public void defineGlVertex3f(float x, float y, float z) {
		// TODO: has de ficar aqui el codi!

        Matriu modelview = this.PilaMV.lastElement();
        Matriu projection = this.PilaProj.lastElement();
        Matriu projxmodelview = projection.multMatriu(modelview);

        Vertex v =new Vertex(x, y, z, 1.0F);
        v=projxmodelview.multVertex(v);

        v.transform(this.height,this.width,this.x,this.y);

        PuntsFinal.add(v.getX());
        PuntsFinal.add(v.getY());

    }
	
	public void defineGlEndPolygon() {
		// TODO: has de ficar aqui el codi!
        defineFillPolygon(PuntsFinal.toArray(new Integer[0]));
    }
	
	// Fi codi de l'alumne
	
	public static void main(String[] args) {
		Laboratori3 example = new Laboratori3();
		example.run();
	}

}
