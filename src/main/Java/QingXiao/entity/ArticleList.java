package QingXiao.entity;

import java.util.List;

/**
 * Created by xpb on 2018/4/19.
 */
public class ArticleList {
    int result;
    int count;
    private List<Article> articleList;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public String toString() {
        return "ArticleList{" +
                "result=" + result +
                ", count=" + count +
                ", articleList=" + articleList +
                '}';
    }
}
