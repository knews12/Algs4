import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Picture picture = new Picture("C:\\dev\\Algs4\\SearmCarver\\src\\HJoceanSmall.png");

        SeamCarver seamCarver = new SeamCarver(picture);

//        for (int i = 0; i < 150; i++) {
//            int[] seam = seamCarver.findVerticalSeam();
//
//            for (int y = 0; y < seam.length; y++) {
//                picture.set(seam[y] + i, y, Color.RED);
//            }
//            seamCarver.removeVerticalSeam(seam);
//        }

        picture.show();
        //seamCarver.picture().show();
        seamCarver.getEnergyPicture().show();
    }
}
