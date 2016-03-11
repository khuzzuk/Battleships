package messagingHandler.Messages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PriorityMessage {
    public enum Priority{
        LOW, NORMAL, HIGH, VERY_HIGH
    }
    Priority prt() default Priority.NORMAL;
}
