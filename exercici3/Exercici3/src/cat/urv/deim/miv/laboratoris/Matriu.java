package cat.urv.deim.miv.laboratoris;

public class Matriu {

    private float[] matriz;

    public Matriu() {
        this.matriz = new float[16];
    }

    public float[] valors(){
        return this.matriz;
    }

    public void nousValors(float[] valors){
        this.matriz = valors;
    }

    public Matriu multMatriu(Matriu matrix) {
        Matriu m = new Matriu();

        float[] punts2Matriu=matrix.valors();

        for(int fila = 0; fila < 4; fila++) {
            for (int col = 0; col < 4; col++) {
                float punt = 0;
                for (int i = 0; i < 4; i++) {
                     punt += this.matriz[fila*4+i]*punts2Matriu[i*4+col];
                }
                float[] punts = m.valors();
                punts[fila * 4 + col] = punt;
                m.nousValors(punts);

            }
        }
        return m;
    }

    public Vertex multVertex(Vertex v) {
        Vertex vertex = new Vertex(0,0,0,0);

        for(int fila=0; fila<4; fila++) {
            float v1 = 0;
            for(int col=0; col<4; col++) {
                v1 += this.matriz[fila*4+col]*v.getVertex(col);
            }
            if(fila==0){
                vertex.setX(v1);
            }
            if(fila==1){
                vertex.setY(v1);
            }
            if(fila==2){
                vertex.setZ(v1);
            }
            if(fila==3){
                vertex.setW(v1);
            }

        }
        return vertex;
    }


}
