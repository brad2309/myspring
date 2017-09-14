package myspring.service;

import java.util.ArrayList;
import java.util.List;

import myspring.anno.Autowired;
import myspring.anno.Component;
import myspring.entity.Article;
import myspring.entity.Reply;

@Component
public class ArticleService {

	@Autowired
	private ReplyService replyService;
	
	public Article getDetail(Integer articleId){
		System.out.println("articleService.getDetail.");
		Article art = new Article();
		List<Reply> replyList = replyService.getArticleReplyList(articleId);
		art.setReplyList(replyList);
		return art;
	}
	
	public List<Article> getUserArticleList(Integer userId){
		List<Article> list = new ArrayList<Article>();
		
		return list;
	}
	
	public void testPrint(){
		System.out.println("articleService.testPrint");
	}
	
}
