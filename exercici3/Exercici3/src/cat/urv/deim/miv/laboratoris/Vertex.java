package cat.urv.deim.miv.laboratoris;

public class Vertex {

    private float x,y,z,w;
    private float[] punts;
    private int x1,y1;

    public Vertex(float x, float y, float z, float w){
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=w;
        this.punts = new float[]{x, y, z, w};
    }

    public void setX(float x){
        this.x=x;
    }

    public void setY(float y){
        this.y=y;

    }

    public void setZ(float z){
        this.z=z;
    }

    public void setW(float w){
        this.w=w;
    }

    public float getVertex(int index) {
        return this.punts[index];
    }

    public void transform(int height, int width, int a, int b){

        this.x1= (int)((x/w+1.0F)*(width/2.0F)+a);
        this.y1 = height-(int)((y/w+1.0F)*(height/2.0F)+b);
    }

    public int getX(){
        return  x1;
    }

    public int getY(){
        return  y1;
    }

}
