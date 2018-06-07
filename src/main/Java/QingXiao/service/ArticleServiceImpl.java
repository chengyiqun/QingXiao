package QingXiao.service;

import QingXiao.entity.Article;
import QingXiao.mappers.ArticleMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.TimeFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/4/17.
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    private int result=0;
    @Override
    public int insertArticle(HashMap articleMap) {
        System.out.println("开始插入文章：");
        String articleID = IdFactory.getUUID();
        String releaseTime= TimeFactory.getCurrentTime();
        articleMap.put("releaseTime",releaseTime);
        articleMap.put("articleID",articleID);
        articleMapper.insertArticleAllMap(articleMap);
        System.out.println("插入文章完成：");
        return 1;
    }

    @Override
    public List<Article> queryArticleListWithMap(String sinceTime, String nowTime) {
        System.out.println("开始获取文章列表WithMap：");
        List<Article> list = articleMapper.selectArticleListWithMap(sinceTime,nowTime);
        //List<Map> list = articleMapper.selectArticleList(sinceTime,nowTime);
        // selectArticleListWithMap
        return list;

    }

    @Override
    public Article queryArticleInfo(String id) {
        System.out.println("开始获取文章详细信息：");
        Article article = articleMapper.selectArticleInfo(id);
        return article;
    }

    @Override
    public List<Map> queryArticleList(String sinceTime, String nowTime) {
        System.out.println("开始获取文章列表：");
        List<Map> list = articleMapper.selectArticleList(sinceTime,nowTime);
        //List<Map> list = articleMapper.selectArticleList(sinceTime,nowTime);
       // selectArticleListWithMap
        return list;
    }
}
