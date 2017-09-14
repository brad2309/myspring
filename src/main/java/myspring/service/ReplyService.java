package myspring.service;

import java.util.ArrayList;
import java.util.List;

import myspring.anno.Autowired;
import myspring.anno.Component;
import myspring.entity.Reply;

@Component
public class ReplyService {

	@Autowired
	private BlogService blogService;
	@Autowired
	private ArticleService articleService;
	
	public List<Reply> getArticleReplyList(Integer articleId){
		List<Reply> list = new ArrayList<Reply>();
		System.out.println("replyService.getArticleReplyList.");
		blogService.testPrint();
		articleService.testPrint();
		return list;
	}
	
}
