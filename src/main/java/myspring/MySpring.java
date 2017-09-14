package myspring;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myspring.anno.Autowired;
import myspring.anno.Component;
import myspring.service.BlogService;
import myspring.util.ClassUtil;

public class MySpring {

	public static Map<String,Class<?>> beanClassMap = new HashMap<String, Class<?>>();
	public static Map<Class<?>,Object> beanInstanceMap = new HashMap<Class<?>, Object>();
	static{
		try{
			init();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		BlogService bs = getBean(BlogService.class);
		bs.getArticleDetail(2);
	}
	
	public static Object getBean(String beanName){
		Class<?> beanClass = beanClassMap.get(beanName);
		return beanInstanceMap.get(beanClass);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T  getBean(Class<T> cls){
		return (T)beanInstanceMap.get(cls);
	}
	
	public static void init() throws Exception{
		//获取被注解的所有类
		List<Class<?>> list = ClassUtil.getClasses("myspring",Component.class);
		//创建对象
		for(Class<?> cls:list){
			String className = cls.getName();
			String beanName = className.substring(className.lastIndexOf(".")+1);
			beanName = String.valueOf(beanName.charAt(0)).toLowerCase()+beanName.substring(1);
			beanClassMap.put(beanName, cls);
			Object instance = cls.newInstance();
			beanInstanceMap.put(cls, instance);
		}
		//注入属性
		for(String beanName:beanClassMap.keySet()){
			Class<?> beanClass = beanClassMap.get(beanName);
			for(Field field:beanClass.getDeclaredFields()){
				if(field.isAnnotationPresent(Autowired.class)){
					field.setAccessible(true);
					field.set(beanInstanceMap.get(beanClass), beanInstanceMap.get(field.getType()));
				}
			}
		}
	}
	
}
