package QingXiao.mappers;

import QingXiao.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/4/17.
 */
public interface ArticleMapper {
    void  insertArticleAllMap(HashMap infoMap);
    void deleteUser(@Param("articleID") String id);
    List<Map> selectArticleList(@Param("sinceTime") String sinceTime, @Param("nowTime") String nowTime);
    List<Article> selectArticleListWithMap(@Param("sinceTime") String sinceTime, @Param("nowTime") String nowTime);
    Article selectArticleInfo(@Param("articleID") String id);
}
