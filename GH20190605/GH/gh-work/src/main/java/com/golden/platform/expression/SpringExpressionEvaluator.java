package com.golden.platform.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import com.golden.platform.expression.context.SpringEvaluationContext;
import com.golden.platform.expression.exception.ExpressionException;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
@Service
public class SpringExpressionEvaluator implements ExpressionEvaluator, ApplicationContextAware {
	protected static Logger logger = LoggerFactory.getLogger(SpringExpressionEvaluator.class);
	private final ExpressionParser parser = new SpelExpressionParser();
	private EvaluationContext evaluationContext = null;
	private final ConcurrentMap<String, Expression> expressions = new ConcurrentHashMap<String, Expression>();
	//private final ConcurrentMap<String, Expression> templates = new ConcurrentHashMap<String, Expression>();

	@Override
	public Object getValue(Map<String, Object> context, String expression) {
		try {
			System.out.println("context=" + context.size());
			Expression parsedExpression = compile(expression);
			
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("paras", context);
			//ContextWrapper contextWrapper = new ContextWrapper(evaluationContext,cxt); // jm.z add at 20190401
			//contextWrapper.setVariable("paras", context);
			return parsedExpression.getValue(evaluationContext,paras);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new ExpressionException(expression, t);
		}
	}

	private Expression compile(String expression) {
		if (expression == null)
			return null;
		Expression o = expressions.get(expression);
		if (o == null) {
			synchronized (expressions) {
				o = expressions.get(expression);
				if (o == null) {
					o = parser.parseExpression(expression);
					expressions.put(expression, o);
				}
			}
		}
		return o;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.evaluationContext = new SpringEvaluationContext(applicationContext);
	}

}
