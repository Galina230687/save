import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;


public class Main {

    public static void main(String[] args) {

        final String path = "D://Games/savegames";

        String s = ("D:\\Games\\savegames\\save.dat");
        GameProgress game = new GameProgress(12, 5, 10, 1000);
        saveGame(game, s);
        String s1 = ("D:\\Games\\savegames\\save1.dat");
        GameProgress game1 = new GameProgress(14, 3, 13, 2000);
        saveGame(game1, s1);
        String s2 = ("D:\\Games\\savegames\\save2.dat");
        GameProgress game2 = new GameProgress(10, 1, 15, 2500);
        saveGame(game2, s2);

        //List<String> list = Arrays.asList(s, s1, s2);
        String z = ("D:\\Games\\savegames\\zip.zip");

        zipFiles(z);
        removeNonZip(path);
    }

    private static void saveGame(GameProgress game, String s) {

        try (FileOutputStream fos = new FileOutputStream(s);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(game);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }


//        GameProgress gameRead = null;
//
//        try (FileInputStream fis = new FileInputStream(s);
//             ObjectInputStream ois = new ObjectInputStream(fis)) {
//
//            gameRead = (GameProgress) ois.readObject();
//
//        } catch (Exception ex) {
//
//            System.out.println(ex.getMessage());
//        }
//        System.out.println(gameRead);
    }

    private static void zipFiles(String z) {

        String s = ("D:\\Games\\savegames\\save.dat");
        String s1 = ("D:\\Games\\savegames\\save1.dat");
        String s2 = ("D:\\Games\\savegames\\save2.dat");
        List<String> list = Arrays.asList(s, s1, s2);

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(z));
             //FileInputStream f = new FileInputStream((File) list);
        ) {
            for (String slist : list) {
                File fileToZip = new File(slist);
                FileInputStream fis = new FileInputStream(fileToZip);


                    ZipEntry entry = new ZipEntry(slist);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                    fis.close();
                }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        }
    }

    private static void removeNonZip(String path) {
        Arrays.stream(new File(path).listFiles())
                .filter(item -> !item.getName().endsWith("zip"))
                .forEach(File::delete);
    }
}










