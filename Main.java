

abstract class UnitateLupta{
    public abstract void ranire(int value);
    public abstract void loveste(UnitateLupta unitate);
    public abstract boolean esteVie();
}
abstract class UnitateSimpla extends UnitateLupta{
    private int viata, putere;

    protected UnitateSimpla(int viata, int putere) {
        this.viata = viata;
        this.putere=putere;
    }
    public void ranire(int valoare){
        if(esteVie()){
            viata=viata-valoare;
        }
    }
    public void loveste(UnitateLupta unitate){
        if(esteVie()){
            unitate.ranire(putere);
        }
    }
    public boolean esteVie(){
        if(viata>0){
            return true;
        }else{
            return false;
        }
    }
}
class Arcas extends UnitateSimpla {
    private static final int VIATA_ARCAS=100;
    private static final int PUTERE_ARCAS=10;
    public Arcas() {
        super(VIATA_ARCAS, PUTERE_ARCAS);
    }
}
class Calaret extends UnitateSimpla{
    private static final int VIATA_CALARET=200;
    private static final int PUTERE_CALARET=15;
    private static int nr_cai=0;

    public static int getNumarCaiPierduti(){
        return nr_cai;}
    public Calaret() {
        super(VIATA_CALARET,PUTERE_CALARET);


    }
    public void ranire(int valoare){
        boolean inainte_de_ranire=esteVie();
        super.ranire(valoare);
        boolean dupa_ranire=esteVie();
        if((inainte_de_ranire==true)&&(dupa_ranire==false)){
            nr_cai++;
        }
    }
}
class Pluton extends UnitateLupta{
    private UnitateLupta[] membri=new UnitateLupta[10];
    private int nr_membri=0;

    public void ranire(int valoare){
    for(int i=0;i<nr_membri;i++){
        if(membri[i].esteVie()){
            membri[i].ranire(valoare);
            break;
        }
    }
    }
    public void loveste(UnitateLupta unitate){
        for(int i = 0; i < nr_membri; i++) {
            membri[i].loveste(unitate);
        }
    }
    public boolean esteVie(){
        if (nr_membri==0){
            return true;
        }
        for(int i = 0; i < nr_membri; i++) {
            if(membri[i].esteVie()) {
                return true;
            }
        }return false;
    }
    public boolean adauga(UnitateLupta unitate) {
        if(!unitate.esteVie() || !this.esteVie()) {
            return false;
        }
        if(nr_membri == membri.length) {
            UnitateLupta[] tmp = new UnitateLupta[membri.length + 10];
            for(int i = 0; i < membri.length; i++) {
                tmp[i] = membri[i];
            }
            membri = tmp;
        }
        membri[nr_membri] = unitate;
        nr_membri++;
        return true;
    }
}
public class Main{

    public static void main(String[] args) {
        Pluton pluton1,pluton2,pluton3;

        pluton1=new Pluton();
        pluton1.adauga(new Arcas());
        pluton1.adauga(new Arcas());
        pluton1.adauga(new Arcas());
        pluton1.adauga(new Calaret());

        pluton3=new Pluton();
        pluton3.adauga(new Arcas());
        pluton3.adauga(new Arcas());

        pluton2=new Pluton();
        pluton2.adauga(new Calaret());
        pluton2.adauga(pluton3);
        boolean loveste_primul = (Math.random() > 0.5);
        while(pluton1.esteVie() && pluton2.esteVie()) {
            if(loveste_primul) {
                System.out.println("Loveste Pluton1");
                pluton1.loveste(pluton2);
            } else {
                System.out.println("Loveste Pluton2");
                pluton2.loveste(pluton1);
            }
            loveste_primul = !loveste_primul;
        }
        System.out.println("Pluton1 este viu:" + pluton1.esteVie());
        System.out.println("Pluton2 este viu:" + pluton2.esteVie());
        System.out.println("A castigat:" + (pluton1.esteVie() ? "pluton1" :
                pluton2.esteVie() ? "pluton2" : "nimeni"));
        System.out.println("Numar cai decedati:"
                + Calaret.getNumarCaiPierduti());

    }  }