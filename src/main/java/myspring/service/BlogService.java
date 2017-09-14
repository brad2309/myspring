package myspring.service;

import myspring.anno.Autowired;
import myspring.anno.Component;
import myspring.entity.Article;

@Component
public class BlogService {

	@Autowired
	private ArticleService articleService;
	
	
	public Article getArticleDetail(Integer articleId){
		System.out.println("blogService.getArticleDetail.");
		return articleService.getDetail(articleId);
	}
	
	public void testPrint(){
		System.out.println("blogService.testPrint");
	}
	
	
}
