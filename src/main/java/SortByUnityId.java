import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

public class SortByUnityId {
    public static List sortbyunitid(File file) throws IOException {
        List list = new ArrayList();
        InputStreamReader read = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(read);
        String strLine;
        while ((strLine = bufferedReader.readLine())!=null){
            JSONObject obj = JSON.parseObject(strLine);
            list.add(obj);
        }
        Collections.sort(list, new Comparator<JSONObject>() {
            public int compare(JSONObject o1, JSONObject o2) {
                int a = (Integer) o1.get("unityId");
                int b = (Integer) o2.get("unityId");
                if (a == b){
                    return 0;
                }else if (a > b){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        bufferedReader.close();
        read.close();
        return list;
    }
}
