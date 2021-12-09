package FilEksempler;
        import java.io.*;
        import java.util.ArrayList;
        import java.util.Scanner;

public class Main {

    private static Object object;

    public static void main(String[] args) throws FileNotFoundException {


        ArrayList<Vare> array=new ArrayList<Vare>();

        //Læs fra txtfil til arrayet array og kald herefter udskrivSkærm
        laesTxtFil(array);
        udskrivSkaerm(array);

        //Skriv arrayet array indhold til .dat fil
        skrivDatFil(array);
        //Læs fra .dat fil til arrayet array og kald herefter udskrivSkærm
        array.clear();
        laesDatFil(array);
        udskrivSkaerm(array);

        //Skriv arrayet array indhold til .ser fil
        skrivObjFil(array);

        //Læs fra .ser fil til arrayet array og kald herefter udskrivSkærm
        array.clear();
        laesObjFil(array);
        udskrivSkaerm(array);


        //Kald af samletPris
        samletPris(array);
    }

    //Skrivning til .txt fil
    public static void laesTxtFil(ArrayList<Vare>  array) throws FileNotFoundException {
        File bestilling = new File("Bestilling.txt");
        Scanner input = new Scanner(bestilling);
        int antal;
        String navn;
        double pris;
        while (input.hasNext()) {
            antal = input.nextInt();
            navn = input.next();
            pris = input.nextDouble();
            Vare v = new Vare();
            v.setAntal(antal);
            v.setNavn(navn);
            v.setPris(pris);
            array.add(v);
        }
    }

    //Skrivning til .dat fil
    public static void skrivDatFil(ArrayList<Vare> array) throws FileNotFoundException {
        try {
            Vare v=new Vare();
            DataOutputStream out = new DataOutputStream(new FileOutputStream("Varer.dat"));
            for (int i = 0; i < array.size(); i++) {
                v=(Vare)array.get(i);
                out.writeInt(v.getAntal());
                out.writeUTF(v.getNavn());
                out.writeDouble(v.getPris());
            }
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Læsning fra .dat fil
    public static void laesDatFil(ArrayList<Vare> array) throws FileNotFoundException {
        try {
            FileInputStream f = new FileInputStream("Varer.dat");
            DataInputStream input = new DataInputStream(f);
            while (f.available() > 0) {
                int antal=input.readInt();
                String navn=input.readUTF();
                double pris=input.readDouble();
                array.add(new Vare(antal,navn,pris));
            }
            f.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //Skrivning til .ser fil
    public static void skrivObjFil(ArrayList<Vare> array) throws FileNotFoundException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Varer.ser"));
            for (int i = 0; i < array.size(); i++)
                out.writeObject(array.get(i));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Læsning fra .ser fil
    public static void laesObjFil(ArrayList<Vare> array) throws FileNotFoundException {
        int i = 0;
        try {
            FileInputStream f = new FileInputStream("Varer.ser");
            ObjectInputStream input = new ObjectInputStream(f);
            while (f.available() > 0) {
                array.add((Vare) input.readObject());
                i++;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void udskrivSkaerm (ArrayList<Vare> array) {
        for(Vare v : array)
            System.out.format("%d %s %.2f \n",v.getAntal(),v.getNavn(),v.getPris());
    }


    public static void samletPris (ArrayList<Vare> array) {
        double prisIaltUdenRabat=0.0;
        double samletRabat=0.0;
        double rabat=0.0;

        System.out.format("\n%-7s %-20s  %20s  %20s\n","Stk", "Varenavn", "Samlet pris","Rabat");
        for (int i = 0; i < array.size(); i++) {
            rabat=0.0;
            prisIaltUdenRabat+=array.get(i).getAntal()*array.get(i).getPris();
            if(array.get(i).getAntal()>10) {
                rabat= array.get(i).getAntal() * array.get(i).getPris() * 0.15;
                samletRabat+=rabat;
            }
            System.out.format("%-7d %-20s  %20.2f  %20.2f\n",array.get(i).getAntal(), array.get(i).getNavn(), array.get(i).getAntal() * array.get(i).getPris(),rabat);
        }
        System.out.format("\nSamlet pris uden rabat: %f",prisIaltUdenRabat);
        System.out.format("\nSamlet rabat: %f",samletRabat);
        System.out.format("\nSamlet pris med rabat: %f",prisIaltUdenRabat-samletRabat);
    }

}



