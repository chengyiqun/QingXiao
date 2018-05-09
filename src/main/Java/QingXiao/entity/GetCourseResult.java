package QingXiao.entity;

import java.util.List;
import java.util.Map;

public class GetCourseResult {
    int result=0;
    List<Map> list;

    public GetCourseResult(int result, List<Map> list) {
        this.result = result;
        this.list = list;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Map> getList() {
        return list;
    }

    public void setList(List<Map> list) {
        this.list = list;
    }
}
