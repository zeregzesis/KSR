package ksr.grupa3.ling;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import ksr.grupa3.fuzzy.FoodItem;
import ksr.grupa3.fuzzy.MembershipFuction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Variable implements Serializable {
    
    private String name;
    private transient Field foodProperty;
    private List<String> values;
    private List<MembershipFuction> MemberFuncList;

    public double getFuncValue(String value, double x) {
        try{
            return MemberFuncList.get(values.indexOf(value)).getValue(x);
        } catch (Exception e) {
            System.out.println(name);
            System.out.println("getFuncValue : " + value + " : " + values.indexOf(value));
            System.out.println(values);
            try {
                Thread.sleep(2000000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return 0;

    }

    public MembershipFuction getMemberFunc(String value){

        return MemberFuncList.get(values.indexOf(value));

    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(name);
        stream.writeObject(values);
        stream.writeObject(MemberFuncList);
        stream.writeObject(foodProperty.getName());
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        name = (String) stream.readObject();
        values = (List<String>) stream.readObject();
        MemberFuncList = (List<MembershipFuction>) stream.readObject();
        try {
            foodProperty = FoodItem.class.getDeclaredField((String) stream.readObject());
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }
    
}