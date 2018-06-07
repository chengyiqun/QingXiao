package QingXiao.service;

import QingXiao.entity.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/4/17.
 */
public interface ArticleService {
    int insertArticle(HashMap articleMap);
    List<Article> queryArticleListWithMap(String sinceTime, String nowTime);
    Article queryArticleInfo(String id);
    //List<Map> queryArticleList(HashMap articleMap);

    List<Map> queryArticleList(String sinceTime, String nowTime);
}
