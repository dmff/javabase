package improve.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
@Inherited
public @interface DbInfo {

	String url() default "jdbc:mysql://localhost:3306/test";
	String username() default "root";
	String password() default "root";
}
