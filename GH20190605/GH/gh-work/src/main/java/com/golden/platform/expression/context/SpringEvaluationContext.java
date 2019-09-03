package com.golden.platform.expression.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.golden.platform.exception.SystemException;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public class SpringEvaluationContext extends StandardEvaluationContext {
	private static Logger logger = LoggerFactory.getLogger(SpringEvaluationContext.class);
	private ApplicationContext applicationContext;

	@Override
	public Object lookupVariable(String name) {
		Object variable = super.lookupVariable(name);
		if (variable == null && this.applicationContext != null) {
			try {
				variable = this.applicationContext.getBean(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return variable;
	}

	public SpringEvaluationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		//BeanResolver beanResolver = new SpringBeanResolver(applicationContext);
		//this.setBeanResolver(beanResolver);
		//this.addPropertyAccessor(new CollectionPropertyAccessor());
		//this.addPropertyAccessor(new DynaBeanPropertyAccessor());
		//this.addPropertyAccessor(new JsonPropertyAccessor());
		this.addPropertyAccessor(new MapPropertyAccessor());
		//this.addPropertyAccessor(new EnvironmentAccessor());
		//this.setTypeComparator(new SpringTypeComparator());
		try {
			//this.registerFunction("map", DynaBeanHelper.class.getDeclaredMethod("createMap", new Class[] { (new Object[] {}).getClass() }));
			//this.registerFunction("list", DynaBeanHelper.class.getDeclaredMethod("createList", new Class[] { (new Object[] {}).getClass() }));
			//this.registerFunction("array", DynaBeanHelper.class.getDeclaredMethod("createArray", new Class[] { (new Object[] {}).getClass() }));
			//this.registerFunction("convert", JsonMapper.class.getDeclaredMethod("convert", new Class[] { Object.class, String.class }));
			//this.registerFunction("enrich", DynaBeanHelper.class.getDeclaredMethod("setProperties", new Class[] { Object.class, Map.class }));

			/*Map<String, Method> methods = this.applicationContext.getBeansOfType(Method.class);
			if (!CollectionHelper.isEmpty(methods)) {
				for (Entry<String, Method> entry : methods.entrySet()) {
					if ((entry.getValue().getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
						this.registerFunction(entry.getKey(), entry.getValue());
					}
				}
			}*/
		} catch (Throwable t) {
			logger.error("Regist function failed!", t);
			throw new SystemException(t);
		}
	}

}
