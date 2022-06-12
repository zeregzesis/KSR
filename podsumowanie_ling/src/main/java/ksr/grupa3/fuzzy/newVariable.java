package ksr.grupa3.fuzzy;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class newVariable implements Serializable {
    
    private String name;
    private transient Field foodProperty;
    private String value;

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(name);
        stream.writeObject(value);
        //stream.writeObject(MemberFuncList);
        stream.writeObject(foodProperty.getName());
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        name = (String) stream.readObject();
        value = (String) stream.readObject();
        //MemberFuncList = (List<MemberFunc>) stream.readObject();
        try {
            foodProperty = FoodItem.class.getDeclaredField((String) stream.readObject());
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }
    
}