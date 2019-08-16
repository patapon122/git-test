import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataDiff {
    private static String inputfile1 = "D:\\a.txt";
    private static String inputfile2 = "D:\\b.txt";

    public static void main(String[] args) throws IOException {
        List<JSONObject> list1 = SortByUnityId.sortbyunitid(new File(inputfile1));
        List<JSONObject> list2 = SortByUnityId.sortbyunitid(new File(inputfile2));

        Integer changeCount = 0;
        Integer addCount = 0;
        Integer delCount = 0;

        int a = 0;
        int b = 0;
        while (a<list1.size() && b<list2.size()){
            JSONObject obj1 = list1.get(a);
            JSONObject obj2 = list2.get(b);

            String line1 = obj1.toJSONString();
            String line2 = obj2.toJSONString();
            if ((Integer) obj1.get("unityId") == (Integer) obj2.get("unityId")){
                if (line1.equals(line2)){
                    a++;
                    b++;
                }else {
                    System.out.println(line1+"修改"+line2);
                    a++;
                    b++;
                    changeCount++;
                }
            }else if ((Integer) obj1.get("unityId") < (Integer) obj2.get("unityId")){
                System.out.println("less" + line1);
                delCount++;
                a++;
            }else if ((Integer) obj1.get("unityId") > (Integer) obj2.get("unityId")){
                System.out.println("add" + line2);
                addCount++;
                b++;
            }
        }
        if (b < list2.size()) {
            while (b<list2.size()) {
                System.out.println("add" + list2.get(b));
                addCount++;
                b++;
            }
        } else if (a<list1.size()) {
            while (a<list1.size()) {
                System.out.println("less" + list1.get(a));
                delCount++;
                a++;
            }
        }

        System.out.println(changeCount + ",,,,," + delCount + ",,,,," + addCount);

    }
}
