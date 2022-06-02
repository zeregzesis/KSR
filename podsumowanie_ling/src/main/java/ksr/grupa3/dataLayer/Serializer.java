package ksr.grupa3.dataLayer;

import java.io.IOException;
import java.util.List;
import java.io. *;


public class Serializer{
    

    public static <T> void serialize(List<T> listOfObjects, String className) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(className+".ser");
        System.out.println(className+".ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(listOfObjects);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    // public static <T> List<T> deserializeAll() throws IOException, ClassNotFoundException {
    //     FileInputStream fileInputStream = new FileInputStream("Quantifiers.ser");
    //     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    //     List<T> listOfQuantifiers = (List<T>) objectInputStream.readObject();
    //     objectInputStream.close();
    //     fileInputStream.close();

    //     fileInputStream = new FileInputStream("LingVariables.ser");
    //     objectInputStream = new ObjectInputStream(fileInputStream);
    //     List<T> listOfVariables = (List<T>) objectInputStream.readObject();
    //     objectInputStream.close();
    //     fileInputStream.close();




    //     return null;
    // }

    public static <T> List<T> deserialize(String className) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(className+".ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<T> listOfObjects = (List<T>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        return listOfObjects;

    }




}

